/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQLite3ToChart;

import csvToSQLite3.PojoUntil2005;
import java.io.File;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * input: MySQL から呼び出したい銘柄のコード
 * input: Hibernate Configuration File for read
 * output: SQLite3 から呼び出した、Pojo のList
 * @author okuda
 */
public class SQLite3toPojo {
//    private EntityManager em; // EntityManager の使用は成功できていない
    private int code;
    private Session session;
    private SessionFactory sessionFactory;
    
    // constructor
    public SQLite3toPojo ( int code, String NameOfCfgFile ) {
//        EntityManagerFactory fac;
//        fac = Persistence.createEntityManagerFactory( NameOfEntityManager );
//        em = fac.createEntityManager();
        this.code = code;
        Configuration cfg = new Configuration().configure( new File( NameOfCfgFile ) );
        sessionFactory = cfg.buildSessionFactory();
        session = sessionFactory.openSession();
    }
    // end of constructor
    
    public List<PojoUntil2005> find() {
        String queryCode = "SELECT * FROM kabu_until_2005 WHERE code='" + code + "'";
        // addEntity() は、SQLQuery にEntity を追加するのではなく、
        // SQLQuery をEntity の形に成形する
        List ls = session.createSQLQuery(queryCode)
                .addEntity(PojoUntil2005.class)
                .list();
        
        session.close();
        sessionFactory.close();
        return ls;
    }
    
    
}
