/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQLite3ToChart;

import java.util.List;
import javax.swing.JFrame;
import csvToSQLite3.PojoUntil2005;

/**
 *
 * @author okuda
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int code = 1002; // "ＴＯＰＩＸ";
        int Xaxis = 500; // Xaxis(=width) of graph

        String NameOfCfgFile = "D:/Users/okuda/NetBeans/NetBeansProjects/HibernateToSQLite3/src/main/java/hibernate_read.cfg.xml";
//        String NameOfEntityManager = "com.company_csvSQLite3Chart_jar_1.0-SNAPSHOTPU";
        SQLite3toPojo sqLite3toPojo = new SQLite3toPojo( code, NameOfCfgFile );
        List<PojoUntil2005> listOfPojo = sqLite3toPojo.find();
        
        ToChartData toChartData = new ToChartData( listOfPojo );
        Chart frame = new Chart( toChartData, Xaxis );
        
        Xaxis = 2300;
        frame = new Chart( toChartData, Xaxis );

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(10, 10, Xaxis, 500 );
        frame.setTitle("グラフサンプル");
        frame.setVisible(true);
        
    }
    
}
