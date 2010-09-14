/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package probabilistic;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Alximik
 */
@Entity
public class Point extends java.awt.geom.Point2D.Double
        implements Externalizable {
    @Id
    private Long id;

    public Point() {
        super();
    }

    public Point(Point p) {
        this.x = p.x;
        this.y = p.y;
    }

    public Point(double x, double y) {
        super(x, y);
    }

    public static double Distance(Point p1, Point p2) {
//        double distance = 0.0;
//        distance = Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2)
//                + Math.pow(p1.getY() - p2.getY(), 2));
        return distance(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    public Point mostLeftP(Point obj2) {
        Point left;
        double lefttX = Math.min(this.getX(), obj2.getX());
        if (this.getX() <= lefttX) {
            left = this;
        } else {
            left = obj2;
        }
        return left;
    }

    public void writeExternal(ObjectOutput oo) throws IOException {
//        throw new UnsupportedOperationException("Not supported yet.");
        oo.writeDouble(x);
        oo.writeDouble(y);
    }

    public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException {
//        throw new UnsupportedOperationException("Not supported yet.");
        x = oi.readDouble();
        y = oi.readDouble();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
