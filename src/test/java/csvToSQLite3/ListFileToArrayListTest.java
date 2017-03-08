/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csvToSQLite3;

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
public class ListFileToArrayListTest {
    //String DirNameOfCsv = "D:\\Users\\okuda\\NetBeans";
    String DirNameOfCsv = "C:\\Users\\am.E0927.000\\Documents";
        
    //String inputFileList = "src\\main\\resources\\csv\\files.txt";
    String inputFileList = "src\\main\\resources\\csv\\files_topia.txt";
    
    public ListFileToArrayListTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
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
     * Test of toArrayList method, of class ListFileToArrayList.
     */
    @Test
    public void testToArrayList() {
        System.out.println("toArrayList");
        
        ListFileToArrayList instance = new ListFileToArrayList( inputFileList );
        String expResult = DirNameOfCsv + "\\NetBeansProjects\\HibernateToSQLite3\\src\\main\\resources\\csv\\20000104.CSV";
        String result = instance.toArrayList().get(0);
        assertEquals(expResult, result);
        
        expResult = DirNameOfCsv + "\\NetBeansProjects\\HibernateToSQLite3\\src\\main\\resources\\csv\\20000131.CSV";
        result = instance.toArrayList().get(18);
        assertEquals(expResult, result);
        
        result = null;
        try {
            result = instance.toArrayList().get(19);
        } catch ( Exception e ) {
        } finally {
            assertNull(result);
        }
        
    }
    
}
