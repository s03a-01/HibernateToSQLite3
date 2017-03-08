/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQLite3ToChart;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import csvToSQLite3.PojoUntil2005;
import org.jfree.data.xy.DefaultHighLowDataset;

/**
 * input:  ArrayList of POJO from MySQL
 * output: DefaultHighLowDataset to CandlestickChart
 * @author okuda
 */
public class ToChartData {
    List<PojoUntil2005> listOfPojo;
    
    // constructor
    public ToChartData( List<PojoUntil2005> listOfPojo ) {
        this.listOfPojo = listOfPojo;
        
    }
    // end of constructor
    
    public DefaultHighLowDataset toChartData () {
        DefaultHighLowDataset returnData;
        int i        = listOfPojo.size();
        String title = listOfPojo.get(0).getName();        
        
        Date[] date     = new Date[i];
        double[] high   = new double[i];
        double[] low    = new double[i];
        double[] open   = new double[i];
        double[] close  = new double[i];
        double[] volume = new double[i];
        
        Calendar calendar = Calendar.getInstance();
        int year;
        int month;
        int day;
        
        i = 0;
        for ( PojoUntil2005 pojo : listOfPojo ) {
                        
            // date
            year  = pojo.getMarketDate().toLocalDate().getYear();
            month = pojo.getMarketDate().toLocalDate().getMonthValue();
            day   = pojo.getMarketDate().toLocalDate().getDayOfMonth();
            calendar.set(year, month, day);
            date[i] = calendar.getTime();
            
            high[i]   = pojo.getHigh();
            low[i]    = pojo.getLow();
            open[i]   = pojo.getMarketOpen();
            close[i]  = pojo.getMarketClose();
            volume[i] = (double)pojo.getVolume(); // long -> double
            i++;
        }

        returnData = new DefaultHighLowDataset( title,
                                                       date,
                                                       high,
                                                       low,
                                                       open,
                                                       close,
                                                       volume);

        return returnData;
    } // end of method
    
} // end of class
