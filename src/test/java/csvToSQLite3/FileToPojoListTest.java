/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csvToSQLite3;

import java.sql.Date;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author am
 */
public class FileToPojoListTest {
    static PojoUntil2005 exp2000_01_04;
    static PojoUntil2005 exp2000_01_31;
    
    public FileToPojoListTest() {
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
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of fileToPojoList method, of class FileToPojoList.
     */
    @Test
    public void testFileToPojoList() {
        System.out.println("fileToPojoList");
        
        String DirNameOfCsv = "D:\\Users\\okuda\\NetBeans";
//        String DirNameOfCsv = "C:\\Users\\am.E0927.000\\Documents";
        
        String inputCsvFile = DirNameOfCsv + "\\NetBeansProjects\\HibernateToSQLite3\\src\\main\\resources\\csv\\20000104.CSV";
        FileToPojoList instance = new FileToPojoList( inputCsvFile );
        
        PojoUntil2005 result = instance.fileToPojoList().get(0);
        System.out.println("expected: " + exp2000_01_04.toString());
        System.out.println("result:   " + result.toString());
        assertThat(result.toString(), is(exp2000_01_04.toString()));
        
        inputCsvFile = DirNameOfCsv + "\\NetBeansProjects\\HibernateToSQLite3\\src\\main\\resources\\csv\\20000131.CSV";
        instance = new FileToPojoList( inputCsvFile );
        
        result = instance.fileToPojoList().get(0);
        assertThat(result.toString(), is(exp2000_01_31.toString()));
    }
    
}
