/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csvToSQLite3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.excel.XlsDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.ext.mysql.MySqlConnection;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author okuda
 */
public class PojoListToDBTest {
    //String DirName = "D:/Users/okuda/NetBeans";
    String DirName = "C:/Users/am.E0927.000/Documents"; // topia
    String NameOfBackupXmlFile = "backUp.xml";
    //String NameOfExpected = "InsertTestExpected.xls" ;
    //String NameOfExpected = "csvToSQLite3/InsertTestExpected.xls" ;
    //String NameOfExpected = "/src/test/java/csvToSQLite3/InsertTestExpected.xls" ;
    String NameOfExpected = "src/test/java/csvToSQLite3/InsertTestExpected.xlsx" ;
    
    static PojoUntil2005 exp2000_01_04;
    static PojoUntil2005 exp2000_01_31;
    IDatabaseConnection databaseConnection = null;
    Connection connection = null;
    String NameOfDriver = "org.sqlite.JDBC";
    String URLofConnect = "jdbc:sqlite:" + DirName + "\\NetBeansProjects\\HibernateToSQLite3\\src\\main\\resources\\sqlite3\\PojoUntil2005_skeltonForTest.db";
    String UserIdOfConnect = "";
    String PasswdOfConnect = "";
        
    public PojoListToDBTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        exp2000_01_04 = new PojoUntil2005();
        exp2000_01_04.setId(Long.MIN_VALUE);
        exp2000_01_04.setMarketDate(new Date(2000-1900, 1-1, 4));
        exp2000_01_04.setCode(1002);
        exp2000_01_04.setName("ＴＯＰＩＸ");
        exp2000_01_04.setMarketOpen(1726.21f);
        exp2000_01_04.setHigh(1744.16f);
        exp2000_01_04.setLow(1710.34f);
        exp2000_01_04.setMarketClose(1717.47f);
        exp2000_01_04.setVolume(285970000L);
        exp2000_01_04.setMarket(1);
        
        exp2000_01_31 = new PojoUntil2005();
        exp2000_01_31.setId(Long.MIN_VALUE);
        exp2000_01_31.setMarketDate(new Date(2000-1900, 1-1, 31));
        exp2000_01_31.setCode(1002);
        exp2000_01_31.setName("ＴＯＰＩＸ");
        exp2000_01_31.setMarketOpen(1699.68f);
        exp2000_01_31.setHigh(1721.96f);
        exp2000_01_31.setLow(1691.18f);
        exp2000_01_31.setMarketClose(1707.96f);
        exp2000_01_31.setVolume(510622000L);
        exp2000_01_31.setMarket(1);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        try {
            // DbUnit でDB とコネクションを確立
            Class.forName( NameOfDriver );
            connection = DriverManager.getConnection(
                    URLofConnect, UserIdOfConnect, PasswdOfConnect);
            
        } catch (ClassNotFoundException ex) {
            String msg = "PojoListToDBTest:setUp(): can't use " + NameOfDriver ;
            Logger.getLogger(PojoListToDBTest.class.getName()).log(Level.SEVERE, msg, ex);    
        } catch (SQLException ex) {
            String msg = "PojoListToDBTest:setUp(): can't connect to database\n";
            msg += "URL is " + URLofConnect + "\n" ;
            msg += "UserId is '" + UserIdOfConnect + "'\n";
            msg += "Password is '" + PasswdOfConnect + "'\n";
            Logger.getLogger(PojoListToDBTest.class.getName()).log(Level.SEVERE, msg, ex);
        }
        
        IDataSet dataSet = null;
        try {
            // テスト前のDB の状態をバックアップ
            databaseConnection = new MySqlConnection(connection, "");
            dataSet = databaseConnection.createDataSet();
            
        } catch (DatabaseUnitException ex) {
            String msg = "PojoListToDBTest:setUp(): can't connect to IDatabaseConnection";
            Logger.getLogger(PojoListToDBTest.class.getName()).log(Level.SEVERE, msg, ex);    
        } catch (SQLException ex) {
            String msg = "PojoListToDBTest:setUp(): can't connect to IDataSet";
            Logger.getLogger(PojoListToDBTest.class.getName()).log(Level.SEVERE, msg, ex);
        }
        
        OutputStream os = null;
        File backUpFile = new File(NameOfBackupXmlFile);
        
        try {
            os = new FileOutputStream(backUpFile);
            XmlDataSet.write(dataSet, os, "UTF-8");
            
        } catch (FileNotFoundException ex) {
            String msg = "PojoListToDBTest:setUp(): can't open OutputStream";
            Logger.getLogger(PojoListToDBTest.class.getName()).log(Level.SEVERE, msg, ex);
        } catch (IOException ex) {
            String msg = "PojoListToDBTest:setUp(): can't write to " + NameOfBackupXmlFile ;
            Logger.getLogger(PojoListToDBTest.class.getName()).log(Level.SEVERE, msg, ex);
        } catch (DataSetException ex) {
            String msg = "PojoListToDBTest:setUp(): can't use dataset of IDataSet" ;
            Logger.getLogger(PojoListToDBTest.class.getName()).log(Level.SEVERE, msg, ex);
        }
        
      // テストの前提条件となるデータを設定
      // PojoListToDBTest では、該当作業なし
        
    }
    
    @After
    public void tearDown() {
        // DbUnit でDB とコネクションを確立
        try {
            Class.forName( NameOfDriver );
            connection = DriverManager.getConnection(
                    URLofConnect, UserIdOfConnect, PasswdOfConnect);
            
        } catch (ClassNotFoundException ex) {
            String msg = "PojoListToDBTest:tearDown(): can't use " + NameOfDriver ;
            Logger.getLogger(PojoListToDBTest.class.getName()).log(Level.SEVERE, msg, ex);    
        } catch (SQLException ex) {
            String msg = "PojoListToDBTest:tearDown(): can't connect to database\n";
            msg += "URL is " + URLofConnect + "\n" ;
            msg += "UserId is '" + UserIdOfConnect + "'\n";
            msg += "Password is '" + PasswdOfConnect + "'\n";
            Logger.getLogger(PojoListToDBTest.class.getName()).log(Level.SEVERE, msg, ex);
        }
        
        try {
            databaseConnection = new MySqlConnection(connection, "");
        } catch (DatabaseUnitException ex) {
            String msg = "PojoListToDBTest:tearDown(): can't connect to IDatabaseConnection";
            Logger.getLogger(PojoListToDBTest.class.getName()).log(Level.SEVERE, msg, ex);    
        }
        
        // バックアップしたデータをDB に戻す
        InputStream is;
        IDataSet importDataSet;
        
        try {
            is = new FileInputStream(NameOfBackupXmlFile);
            importDataSet = new XmlDataSet(is);
            DatabaseOperation.CLEAN_INSERT.execute(databaseConnection, importDataSet);
        
        } catch (FileNotFoundException ex) {
            String msg = "PojoListToDBTest:tearDown(): can't find file " + NameOfBackupXmlFile ;
            Logger.getLogger(PojoListToDBTest.class.getName()).log(Level.SEVERE, msg, ex);
        } catch (DataSetException ex) {
            String msg = "PojoListToDBTest:tearDown(): can't use XmlDataSet";
            Logger.getLogger(PojoListToDBTest.class.getName()).log(Level.SEVERE, msg, ex);
        } catch (DatabaseUnitException ex) {
            String msg = "PojoListToDBTest:tearDown(): can't connect to databaseConnection";
            Logger.getLogger(PojoListToDBTest.class.getName()).log(Level.SEVERE, msg, ex);
        } catch (SQLException ex) {
            String msg = "PojoListToDBTest:tearDown(): can't insert";
            Logger.getLogger(PojoListToDBTest.class.getName()).log(Level.SEVERE, msg, ex);
        }

/*
// commit() するdata がないから        
        try {
            connection.commit(); 
        } catch (SQLException ex) {
            String msg = "PojoListToDBTest:tearDown(): can't commit";
            Logger.getLogger(PojoListToDBTest.class.getName()).log(Level.SEVERE, msg, ex);
        }
*/        
        try {
            databaseConnection.close();
        } catch (SQLException ex) {
            String msg = "PojoListToDBTest:tearDown(): can't close";
            Logger.getLogger(PojoListToDBTest.class.getName()).log(Level.SEVERE, msg, ex);
        }
        
    }

    /**
     * Test of pojoListToDB method, of class PojoListToDB.
     */
    @Test
    public void testPojoListToDB() {
        System.out.println("pojoListToDB");
        
        String NameOfCfgFile = DirName
                + "/NetBeansProjects/HibernateToSQLite3/src/main/java/hibernate_write_test.cfg.xml";
        String ErrMsg = "DEBUG:PojoListToDBTest:testPojoListToDB(): ";
        
        ArrayList<PojoUntil2005> listOfPojo = new ArrayList<>();
        listOfPojo.add(exp2000_01_04);
        listOfPojo.add(exp2000_01_31);
        System.out.println(ErrMsg + NameOfCfgFile );
        PojoListToDB instance = new PojoListToDB(listOfPojo, NameOfCfgFile);
        instance.pojoListToDB();
        
        File expectedFile = new File( NameOfExpected );
        InputStream expectedIs = null;
        IDataSet expectedDataSet = null;
        IDataSet databaseDataSet = null;
        ITable expectedTable = null;
        ITable actualTable = null;
        
        try {
            expectedIs = new FileInputStream ( expectedFile );
            expectedDataSet = new XlsDataSet(expectedIs);
            expectedTable = expectedDataSet.getTable("kabu_until_2005");
            databaseDataSet = databaseConnection.createDataSet();
        } catch (FileNotFoundException ex) {
            String msg = ErrMsg + "cant open expectedFile" ;
            Logger.getLogger(PojoListToDBTest.class.getName()).log(Level.SEVERE, msg, ex);
            fail(msg);
        } catch (IOException ex) {
            String msg = ErrMsg + "cant open expectedFile";            
            Logger.getLogger(PojoListToDBTest.class.getName()).log(Level.SEVERE, msg, ex);
            fail(msg);
        } catch (DataSetException ex) {
            String msg = ErrMsg + "cant make expectedDataSet";            
            Logger.getLogger(PojoListToDBTest.class.getName()).log(Level.SEVERE, msg, ex);
            fail(msg);
        } catch (SQLException ex) {
            String msg = ErrMsg + "cant make expectedTable";            
            Logger.getLogger(PojoListToDBTest.class.getName()).log(Level.SEVERE, msg, ex);
            fail(msg);
        } 
        
        try {
            actualTable = databaseDataSet.getTable("kabu_until_2005");
        } catch (DataSetException ex) {
            String msg = ErrMsg + "cant make actualTable";            
            Logger.getLogger(PojoListToDBTest.class.getName()).log(Level.SEVERE, msg, ex);
            fail(msg);
        }

        try {
            // 比較する
            Assertion.assertEquals(expectedTable, actualTable);
            if(databaseConnection!=null) databaseConnection.close();
        } catch (DatabaseUnitException ex) {
            String msg = ErrMsg + "cant execute assert";            
            Logger.getLogger(PojoListToDBTest.class.getName()).log(Level.SEVERE, msg, ex);
            fail(msg);
        } catch (SQLException ex) {
            String msg = ErrMsg + "cant close DConnection";            
            Logger.getLogger(PojoListToDBTest.class.getName()).log(Level.SEVERE, msg, ex);
            fail(msg);
        }
        
              /*
            String NameOfTable = "kabu_until_2005";
            String SqlQuery = "SELECT * FROM " + NameOfTable + " ORDER BY marketDate";
            String[] ignoreCols = new String[0];
            try {
            expectedIs = new FileInputStream ( expectedFile );
            expectedDataSet = new XlsDataSet(expectedIs);
            Assertion.assertEqualsByQuery(expectedDataSet,
            databaseConnection,
            SqlQuery,
            NameOfTable,
            ignoreCols);
            
            } catch (FileNotFoundException ex) {
            String msg = "PojoListToDBTest:testPojoListToDB: cant find " + NameOfExpected ;
            Logger.getLogger(PojoListToDBTest.class.getName()).log(Level.SEVERE, msg, ex);
            } catch (IOException ex) {
            String msg = "PojoListToDBTest:testPojoListToDB: cant get expectedIs";
            Logger.getLogger(PojoListToDBTest.class.getName()).log(Level.SEVERE, msg, ex);
            } catch (DataSetException ex) {
            String msg = "PojoListToDBTest:testPojoListToDB: cant set IDataSet";
            Logger.getLogger(PojoListToDBTest.class.getName()).log(Level.SEVERE, msg, ex);
            } catch (DatabaseUnitException ex) {
            String msg = "PojoListToDBTest:testPojoListToDB: cant execute Query";
            Logger.getLogger(PojoListToDBTest.class.getName()).log(Level.SEVERE, msg, ex);
            } catch (SQLException ex) {
            String msg = "PojoListToDBTest:testPojoListToDB: wrong about SQL " + SqlQuery;
            Logger.getLogger(PojoListToDBTest.class.getName()).log(Level.SEVERE, msg, ex);
            fail("SQLException occured. PojoListToDBTest:testPojoListToDB: cant execute Query");
            } catch ( NullPointerException ex ) {
            String msg = "PojoListToDBTest:testPojoListToDB: cant execute Query";
            Logger.getLogger(PojoListToDBTest.class.getName()).log(Level.SEVERE, msg, ex);
            fail("NullPointerException occured. PojoListToDBTest:testPojoListToDB: cant execute Query");
            }
            */
        
    }

    /**
     * Test of update method, of class PojoListToDB.
     */
    /*
    @Test
    public void testUpdate() {
        System.out.println("update");
        PojoUntil2005 pojo = null;
        PojoListToDB instance = null;
        instance.update(pojo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */

    /**
     * Test of commit method, of class PojoListToDB.
     */
    /*
    @Test
    public void testCommit() {
        System.out.println("commit");
        PojoListToDB instance = null;
        instance.commit();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */

    /**
     * Test of delete method, of class PojoListToDB.
     */
    /*
    @Test
    public void testDelete() {
        System.out.println("delete");
        PojoUntil2005 pojo = null;
        PojoListToDB instance = null;
        instance.delete(pojo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
}
