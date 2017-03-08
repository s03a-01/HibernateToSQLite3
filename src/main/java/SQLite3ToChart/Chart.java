/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQLite3ToChart;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.OHLCDataset;

/**
 *
 * @author okuda
 */
public class Chart extends JFrame {
    public Chart( ToChartData toChartData, int Xaxis ) {
        chart( toChartData, Xaxis );
    }
    
    private void chart( ToChartData toChartData, int Xaxis ) {
        
        String titleChart = "Daily chart of TOPIX";
        String timeAxisLabel = "2000 Year";
        String valueAxisLabel = "point";
        OHLCDataset oHLCDataset = toChartData.toChartData();
        JFreeChart chart = ChartFactory.createCandlestickChart(
                titleChart,
                timeAxisLabel,
                valueAxisLabel,
                oHLCDataset,
                false );
        ChartPanel cpanel = new ChartPanel(chart);
        getContentPane().add(cpanel, BorderLayout.CENTER);
        
        File file = new File("./filename_" + Xaxis + ".png");
        try {
            ChartUtilities.saveChartAsPNG(file, chart, Xaxis, 500 );
        } catch (IOException ex) {
            String msg = "saveChartAsPNG() make Exception ";
            Logger.getLogger(Chart.class.getName()).log(Level.SEVERE, msg, ex);
        }
    }
    
}
