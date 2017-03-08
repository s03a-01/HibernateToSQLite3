/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csvToSQLite3;

import com.github.mygreen.supercsv.io.CsvAnnotationBeanReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.supercsv.prefs.CsvPreference;

/**
 * input: input CSV file name
 * output: List of POJO from CSV file
 * @author okuda
 */
public class FileToPojoList {
    Path path;
    
    public FileToPojoList ( String inputCsvFile ) {
        path = Paths.get(inputCsvFile);        
    }
    
    public List<PojoUntil2005> fileToPojoList () {
        CsvAnnotationBeanReader<PojoUntil2005> csvReader;
        List<PojoUntil2005> returnPojoList = new ArrayList<>();

        try {
            csvReader = new CsvAnnotationBeanReader<>(
                    PojoUntil2005.class,
                    Files.newBufferedReader(path, Charset.forName("Windows-31j")),
                    CsvPreference.STANDARD_PREFERENCE);
            returnPojoList = csvReader.readAll();
            csvReader.close();

        } catch (IOException ex) {
            String msg = path.toAbsolutePath().toString() + " cant read using csvReader.redAll()";
            Logger.getLogger(FileToPojoList.class.getName()).log(Level.SEVERE, msg, ex);
        }
        
//        System.out.println("DEBUG:" + path.getFileName().toString() + " " + returnPojoList.size() );
        
        return returnPojoList;
    } // end of method

} // end of class
