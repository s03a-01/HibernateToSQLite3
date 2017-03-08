/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csvToSQLite3;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;



/**
 * MySQL への登録を行う
 * input: PojoUntil2005 のList
 * output: 今のところvoid
 * @author okuda
 */
public class PojoListToDB {
    private ArrayList<PojoUntil2005> listOfPojo;
//    private EntityManager em;
//    private EntityTransaction tx;
    private Session session;
    private Transaction transaction;
    private SessionFactory sessionFactory;
    
    // constructor
    public PojoListToDB( ArrayList<PojoUntil2005> listOfPojo, String NameOfCfgFile ) {
        this.listOfPojo = listOfPojo;
        /*
        HIbernate の場合、以下のようにするべきという意見があるが、
        下記のprops に相当する情報が見つからない
        このやりかたはhibernate5.1. ではだめだというコメントもある
        PersistenceProvider provider = new HibernatePersistenceProvider();
        EntityManagerFactory emf = provider.createEntityManagerFactory(PERSISTENCE_UNIT, props);
        */
        
        Configuration cfg = new Configuration().configure(new File( NameOfCfgFile ) );
        sessionFactory = cfg.buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.getTransaction();
        
        /*
        EntityManagerFactory fac = Persistence.createEntityManagerFactory(NameOfEntityManager, createProps());
        em = fac.createEntityManager();
        tx = em.getTransaction();
*/
        
        System.out.println("DEBUG:PojoListToDB:" + listOfPojo.size()  );
    } // end of constructor
    
    // method
    // 下記のmethod は、hibernate をEntityManagerFactory で使用できているときに、
    // 使用できるらしい。今は、hibernate をEntityManagerFactory で使用できていない
    @SuppressWarnings("all")
    private Map createProps() {
        Map props = new HashMap();
        props.put(AvailableSettings.STATEMENT_BATCH_SIZE, "100");
        return props;
    }
    
    public void pojoListToDB () {

        transaction.begin();            

        int i = 0;
        for ( PojoUntil2005 pojo : listOfPojo ) {

            try {
//                em.persist(pojo);
                session.save(pojo);
            } catch (Exception ex) {
                String msg = "pojoListToDB(): create cant execute to SQLite";
                Logger.getLogger(PojoListToDB.class.getName()).log(Level.SEVERE, msg, ex);
            }

//            System.out.println("DEBUG:pojoListToDB():" + i++ );
            System.out.println("DEBUG:pojoListToDB(): " + pojo + " "  +  i++ );

        }
        System.out.println("DEBUG:pojoListToDB():before commit" );

        transaction.commit();
//        em.close();
        session.close();
        sessionFactory.close();
        System.out.println("DEBUG:pojoListToDB():after commit" );
    }
    
    public void update( PojoUntil2005 pojo ) {
//        em.merge(pojo);
        session.merge(pojo);
    }
    
    public void commit() {
//        session.flush();
        transaction.commit();
//        em.getTransaction().commit();
    }
    
    public void delete(PojoUntil2005 pojo) {
//        em.remove(em.merge(pojo));
        session.delete(session.merge(pojo));
    }

    /*
    public PojoUntil2005 find( Integer key ) {
        return em.find(PojoUntil2005.class, key);
    }
    public List<PojoUntil2005> getAll() {
        return em.createQuery("SELECT c FROM PojoUntil2005 c").getResultList();
    }
*/
    
    // end of method
    
} // end of class
