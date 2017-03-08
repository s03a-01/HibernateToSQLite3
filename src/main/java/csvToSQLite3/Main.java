/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csvToSQLite3;

import java.util.ArrayList;

/**
 *
 * @author okuda
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

//        String inputFileList = "C:\\Users\\am.E0927.000\\Documents\\NetBeansProjects\\sampleHibernate\\src\\main\\resources\\csv\\\\Files.txt";
//        String inputFileList = "/sampleHibernate\\src\\main\\resources\\csv\\\\Files.txt";
        String inputFileList = "src\\main\\resources\\csv\\files.txt";
//        String inputFileList = "src\\main\\resources\\csv\\files_topia.txt";

// String DirName = "D:/Users/okuda/NetBeans/";
String DirName = "C:/Users/am.E0927.000/Documents";
String NameOfCfgFile = DirName + "NetBeansProjects/HibernateToSQLite3/src/main/java/hibernate.cfg.xml";
// String NameOfEntityManager = "com.company_HibernateToMySQL_jar_1.0-SNAPSHOTPU";
//        String NameOfEntityManager = "com.topia_HibernateJavaDB_jar_1.0-SNAPSHOTPU";
//        String NameOfEntityManager = "com.topia_sampleHibernate_jar_1.0-SNAPSHOTPU";
        ArrayList<String> csvFileArrayList;        
        ListFileToArrayList listFileToArrayList = new ListFileToArrayList( inputFileList );
        csvFileArrayList = listFileToArrayList.toArrayList();

        ArrayList<PojoUntil2005> PojoList = new ArrayList<>();
        ArrayListToPojoList arrayListToPojoList = new ArrayListToPojoList( csvFileArrayList );
        PojoList = arrayListToPojoList.arrayListToPojoList();
        
        PojoListToDB pojoListToDB = new PojoListToDB( PojoList, NameOfCfgFile );
        pojoListToDB.pojoListToDB();
        
        System.out.println("DEBUG: Main: ここに処理は来ていますか?");
        
    }
    
}
