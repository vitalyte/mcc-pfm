/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package probabilistic.persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import probabilistic.Point;
import probabilistic.SemiellipticalCrack;

/**
 *
 * @author Vitaly Brevus
 */
public class DatabasePersist {

    private String framework = "embedded";
    private String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private String protocol = "jdbc:derby:";
    ArrayList statements;
    PreparedStatement crackInsert = null;
    PreparedStatement pointInsert = null;
    PreparedStatement psUpdate = null;
    PreparedStatement crackPointInsert = null;
    PreparedStatement crackCrackSelect = null;
    PreparedStatement crackPointSelect = null;
    Statement stat = null;
    ResultSet rs = null;
    Connection conn;
    long crackID, tipID;
    String dbName = "crackDB";
    private static final String selectSemielepticalcrackPoint =
            "SELECT ID,CURRENTTIME,DEPTHB,INITTIME,ASPECTRATIO "
            + "FROM CRACK.SEMIELLIPTICALCRACK WHERE CURRENTTIME = ?";
    private static final String selectPoint = 
            "SELECT ID,X,Y,IDCRACK FROM CRACK.POINT WHERE POINT.IDCRACK = ?";
    private static final String deleteSemielepticalcrack =
            "DROP TABLE CRACK.SEMIELLIPTICALCRACK";
    private static final String deletePoint =
            "DROP TABLE CRACK.POINT";
    private static final String deleteSemielepticalcrackINDX =
            "DROP INDEX CURTIME";
    private static final String deletePointINDXIDCRACK =
            "DROP INDEX POINTIDCRACK";
    private static final String createSemielepticalcrack =
            "create table CRACK.SEMIELLIPTICALCRACK"
            + "(ID BIGINT not null primary key,"
            + "CURRENTTIME DOUBLE, DEPTHB DOUBLE, INITTIME DOUBLE, ASPECTRATIO DOUBLE)";
    private static final String createPoint =
            "create table CRACK.POINT"
            + "(ID BIGINT not null primary key, Y DOUBLE, X DOUBLE, IDCRACK BIGINT NOT NULL)";
    private static final String createSemielepticalcrackINDX =
            "CREATE INDEX CURTIME ON CRACK.SEMIELLIPTICALCRACK (CURRENTTIME)";
    private static final String createPointINDX =
            "CREATE INDEX POINTIDCRACK ON CRACK.POINT (IDCRACK)";

    public ArrayList<SemiellipticalCrack> retrieve(double currentTime) {
        ArrayList<SemiellipticalCrack> retrievedCracks = new ArrayList<SemiellipticalCrack>();
        try {
            System.out.println("Select from " + dbName);
            crackCrackSelect = conn.prepareStatement(selectSemielepticalcrackPoint);
//            statements.add(crackCrackSelect);
            crackPointSelect = conn.prepareStatement(selectPoint);
//            statements.add(crackPointSelect);
            crackCrackSelect.setDouble(1, currentTime);

            try {
                ArrayList<Long> idList = new ArrayList<Long>();
                ResultSet resultOfCtacks = crackCrackSelect.executeQuery();
                while (resultOfCtacks.next()) {
                    SemiellipticalCrack crack = new SemiellipticalCrack();
                    idList.add(resultOfCtacks.getLong(1));
                    long id = resultOfCtacks.getLong(1);
                    crack.setCurrentTime(resultOfCtacks.getDouble(2));
                    crack.setDepthBORM(resultOfCtacks.getDouble(3));
                    crack.setInitTime(resultOfCtacks.getDouble(4));
                    crack.setAspectRatioORM(resultOfCtacks.getDouble(5));
                    retrievedCracks.add(crack);
                    crackPointSelect.setLong(1, id);
                    ResultSet rsPoint = crackPointSelect.executeQuery();
                    while (rsPoint.next()) {
                        crack.getCrackTip().add(new Point(rsPoint.getDouble(2), rsPoint.getDouble(3)));
                    }
                    rsPoint.close();
                }
                resultOfCtacks.close();



            } catch (SQLException sQLException) {
                System.out.println("sQLException");
            }
            crackPointSelect.close();
            crackCrackSelect.close();
            statements.add(stat);
        } catch (SQLException sqle) {
            printSQLException(sqle);
        }


        return retrievedCracks;
    }

    public void setup() {
        crackID = 1;
        tipID = 1;
        /* load the desired JDBC driver */
        loadDriver();



        /* This ArrayList usage may cause a warning when compiling this class
         * with a compiler for J2SE 5.0 or newer. We are not using generics
         * because we want the source to support J2SE 1.4.2 environments. */
        statements = new ArrayList(); // list of Statements, PreparedStatements
        crackInsert = null;
        psUpdate = null;
        stat = null;
        rs = null;

        try {

            Properties props = new Properties(); // connection properties
            // providing a user name and password is optional in the embedded
            // and derbyclient frameworks

            props.put("create", "true");
            props.put("user", "crack");
            props.put("password", "crack");
            props.put("durability", "test");

            /* By default, the schema APP will be used when no username is
             * provided.
             * Otherwise, the schema name is the same as the user name (in this
             * case "user1" or USER1.)
             *
             * Note that user authentication is off by default, meaning that any
             * user can connect to your database using any password. To enable
             * authentication, see the Derby Developer's Guide.
             */

            dbName = "crackDB"; // the name of the database

            /*
             * This connection specifies create=true in the connection URL to
             * cause the database to be created when connecting for the first
             * time. To remove the database, remove the directory derbyDB (the
             * same as the database name) and its contents.
             *
             * The directory derbyDB will be created under the directory that
             * the system property derby.system.home points to, or the current
             * directory (user.dir) if derby.system.home is not set.
             */
            conn = DriverManager.getConnection(protocol + dbName, props);

            System.out.println("Connected to and created database " + dbName);
            // We want to control transactions manually. Autocommit is on by
            // default in JDBC.
            conn.setAutoCommit(false);
//            String deleteSemielepticalcrackPoint = "DROP TABLE CRACK.SEMIELLIPTICALCRACK_POINT";


            /* Creating a statement object that we can use for running various
             * SQL statements commands against the database.*/
            stat = conn.createStatement();
            try {
                stat.execute(deleteSemielepticalcrack);
                stat.execute(deletePoint);
                stat.execute(deleteSemielepticalcrackINDX);
                stat.execute(deletePointINDXIDCRACK);
            } catch (SQLException sQLException) {
            }
            stat.execute(createSemielepticalcrack);
            stat.execute(createPoint);
            stat.execute(createPointINDX);
            stat.execute(createSemielepticalcrackINDX);
            statements.add(stat);
        } catch (SQLException sqle) {
            printSQLException(sqle);
        }

    }

    public void persist(ArrayList<SemiellipticalCrack> ellipticalCrackList) {

        try {
            /* It is recommended to use PreparedStatements when you are
             * repeating execution of an SQL statement. PreparedStatements also
             * allows you to parameterize variables. By using PreparedStatements
             * you may increase performance (because the Derby engine does not
             * have to recompile the SQL statement each time it is executed) and
             * improve security (because of Java type checking).
             */
            // parameter 1 is num (int), parameter 2 is addr (varchar)
            String crackSql = "insert into SEMIELLIPTICALCRACK (CURRENTTIME, DEPTHB, INITTIME, ASPECTRATIO, ID) "
                    + "values (?, ?, ?, ?, ?)";
            crackInsert = conn.prepareStatement(crackSql);
            statements.add(crackInsert);
            String pointSql = "insert into POINT (ID, Y, X, IDCRACK) "
                    + "values (?, ?, ?, ?)";
            pointInsert = conn.prepareStatement(pointSql);
            statements.add(pointInsert);


            for (int i = 0; i < ellipticalCrackList.size(); i++) {
                SemiellipticalCrack crack = ellipticalCrackList.get(i);
                crackInsert.setDouble(1, crack.getCurrentTime());
                crackInsert.setDouble(2, crack.getDepthB());
                crackInsert.setDouble(3, crack.getInitTime());
                crackInsert.setDouble(4, crack.getAspectRatio());
                crackInsert.setLong(5, crackID);
                crackInsert.executeUpdate();


                for (int j = 0; j < crack.getCrackTip().size(); j++) {
                    Point tip = crack.getCrackTip().get(j);
                    pointInsert.setLong(1, tipID);
                    pointInsert.setDouble(2, tip.getY());
                    pointInsert.setDouble(3, tip.getX());
                    pointInsert.setLong(4, crackID);
                    pointInsert.executeUpdate();
                    tipID++;
                }
                crackID++;
            }


        } catch (SQLException sqle) {
            printSQLException(sqle);
        } finally {
        }
    }

    public void commit() {
        try {
            conn.commit();
//            System.out.println("Committed the transaction");
            if (rs != null) {
                rs.close();
                rs = null;
            }
        } catch (SQLException sqle) {
            printSQLException(sqle);
        }
    }

    public void close(boolean param) {
        if (param == true) {
            // release all open resources to avoid unnecessary memory usage

            // ResultSet
            this.commit();

            // Statements and PreparedStatements
            int i = 0;
            while (!statements.isEmpty()) {
                // PreparedStatement extend Statement
                Statement st = (Statement) statements.remove(i);
                try {
                    if (st != null) {
                        st.close();
                        st = null;
                    }
                } catch (SQLException sqle) {
                    printSQLException(sqle);
                }
            }

            //Connection
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException sqle) {
                printSQLException(sqle);
            }

        }
    }

    private void loadDriver() {
        /*
         *  The JDBC driver is loaded by loading its class.
         *  If you are using JDBC 4.0 (Java SE 6) or newer, JDBC drivers may
         *  be automatically loaded, making this code optional.
         *
         *  In an embedded environment, this will also start up the Derby
         *  engine (though not any databases), since it is not already
         *  running. In a client environment, the Derby engine is being run
         *  by the network server framework.
         *
         *  In an embedded environment, any static Derby system properties
         *  must be set before loading the driver to take effect.
         */
        try {
            Class.forName(driver).newInstance();
            System.out.println("Loaded the appropriate driver");
        } catch (ClassNotFoundException cnfe) {
            System.err.println("\nUnable to load the JDBC driver " + driver);
            System.err.println("Please check your CLASSPATH.");
            cnfe.printStackTrace(System.err);
        } catch (InstantiationException ie) {
            System.err.println(
                    "\nUnable to instantiate the JDBC driver " + driver);
            ie.printStackTrace(System.err);
        } catch (IllegalAccessException iae) {
            System.err.println(
                    "\nNot allowed to access the JDBC driver " + driver);
            iae.printStackTrace(System.err);
        }
    }

    /**
     * Prints details of an SQLException chain to <code>System.err</code>.
     * Details included are SQL State, Error code, Exception message.
     *
     * @param e the SQLException from which to print details.
     */
    public static void printSQLException(SQLException e) {
        // Unwraps the entire exception chain to unveil the real cause of the
        // Exception.
        while (e != null) {
            System.err.println("\n----- SQLException -----");
            System.err.println("  SQL State:  " + e.getSQLState());
            System.err.println("  Error Code: " + e.getErrorCode());
            System.err.println("  Message:    " + e.getMessage());
            // for stack traces, refer to derby.log or uncomment this:
            //e.printStackTrace(System.err);
            e = e.getNextException();
        }
    }

    private void disableLogArchiveMode(Connection conn) throws SQLException {
        String sqlstmt = "CALL SYSCS_UTIL.SYSCS_DISABLE_LOG_ARCHIVE_MODE(?)";
        CallableStatement cs = conn.prepareCall(sqlstmt);
        cs.setInt(1, 1);
        cs.execute();
//    cs.close();
    }
}
