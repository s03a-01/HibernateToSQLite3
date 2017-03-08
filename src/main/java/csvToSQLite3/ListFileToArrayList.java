/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csvToSQLite3;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * input: String of file name of input csv file list
 * output: ArrayList<String> of input csv file list
 * @author okuda
 */
class ListFileToArrayList {
    String   DirName;
    String[] FileList;
    
    // constructor
    ListFileToArrayList ( String inputFileNameArgument ) {
        // "D:\Users\okuda\kabuData\2000\Files.txt";
//        String inputFileNames = "D:\\Users\\okuda\\kabuData\\2000\\Files.txt";
        Path path = Paths.get( inputFileNameArgument );
        DirName = path.getParent().toString();
        
        try {
            FileList = Files.lines(path, Charset.forName("UTF-8"))
                    .collect(Collectors.joining(System.getProperty("line.separator"))).split("\n");
        } catch (IOException ex) {
            Logger.getLogger(ListFileToArrayList.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } // end of constructor
    
    /**
     *
     * @return arrayList of csv file name
     */
    ArrayList<String> toArrayList() {
        ArrayList<String> returnList = new ArrayList<>();

        for ( String s: FileList ) {
            s = s.replaceAll("\r$", "");
            s = s.replaceAll("\r\n$", "");
//            s = DirName + "\\" + s ;
            
            System.out.println("DEBUG:" + s);
            returnList.add( s );
        }
        return returnList;
    }

}
