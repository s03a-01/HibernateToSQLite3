/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csvToSQLite3;

import java.util.ArrayList;
import java.util.List;

/**
 * input: csvFile 名を持つArrayList
 * output: 全てのcsvFile の内容を持つ、POJO のlist
 * @author okuda
 */
class ArrayListToPojoList {
    private ArrayList<String> csvFileArrayList;
    
    // constructor
    public ArrayListToPojoList( ArrayList<String> csvFileArrayList ) {
        this.csvFileArrayList = csvFileArrayList;
    }
    
    ArrayList<PojoUntil2005> arrayListToPojoList () {
        FileToPojoList fileToPojoList;
        ArrayList<PojoUntil2005> returnPojoList = new ArrayList<>();
        List<PojoUntil2005> listOfPojo = new ArrayList<>();
        
        for (String inputCsvFile : csvFileArrayList) {
            fileToPojoList = new FileToPojoList( inputCsvFile );
            listOfPojo = fileToPojoList.fileToPojoList();
            returnPojoList.addAll(listOfPojo);
            
            System.out.println("ArrayListToPojoList: " + inputCsvFile + " to PojoUntil2005");
        } // end of method
        
        System.out.println("DEBUG:ArrayListToPojoList: " + returnPojoList.size() );
        
        return returnPojoList;
    } // end of method
    
} // end of class
