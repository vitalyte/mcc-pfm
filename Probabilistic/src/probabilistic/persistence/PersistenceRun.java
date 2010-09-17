/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package probabilistic.persistence;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import probabilistic.Point;
import probabilistic.SemiellipticalCrack;

/**
 *
 * @author Vitaly Brevus
 */
public class PersistenceRun {

    private static final String PERSISTENCE_UNIT_NAME = "ProbabilisticPU";
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    EntityManager em = factory.createEntityManager();

    public void begin() {
        // Begin a new local transaction so that we can persist a new entity
        em.getTransaction().begin();
    }

    public void setUpPersistence(ArrayList<SemiellipticalCrack> ellipticalCrackList) {
//        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
if (!em.getTransaction().isActive()) {
            begin();
        }



        // Read the existing entries
//        Query q = em.createQuery("select m from Person m");
        // Persons should be empty

        // Do we have entries?
//        boolean createNewEntries = (q.getResultList().isEmpty());

        for (int i = 0; i < ellipticalCrackList.size(); i++) {
            SemiellipticalCrack semiellipticalCrack = ellipticalCrackList.get(i);
            em.persist(semiellipticalCrack);
            for (int j = 0; j < semiellipticalCrack.getCrackTip().size(); j++) {
                Point tip = semiellipticalCrack.getCrackTip().get(j);
                em.persist(tip);
            }

        }

        // Commit the transaction, which will cause the entity to
        // be stored in the database
        em.getTransaction().commit();


    }
    public void close() {
        // It is always good practice to close the EntityManager so that
        // resources are conserved.
        em.close();
    }
}
