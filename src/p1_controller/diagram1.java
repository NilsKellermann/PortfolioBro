package p1_controller;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;

import javafx.embed.swing.SwingNode;

public class diagram1 {
	////////////////////////////////
	//LINECHART1
	////////////////////////////////
	sn1= new SwingNode();
	paneWithSwing.getChildren().add(sn1);
	
	
	
	
	// Create dataset
	// XYDataset dataset = createDataset();
	// private XYDataset createDataset() {
	    //TimeSeriesCollection dataset2 = new XYSeriesCollection();
		XYSeriesCollection dataset2 = new XYSeriesCollection();

	    //TimeSeries series = new TimeSeries("Y = X"); 
		XYSeries series = new XYSeries("Y = X"); 
	// ArrayList<XYSeries> xyList= new ArrayList<XYSeries>();


//aktuelle auswahl
//dessen Courses
//durchlaufen und jeden eintrag als Punkt adden

			Aktie a1 = m1.currentPortfoliosAktienMitKursen.get(Integer.valueOf(m1.selectedCurrentSharesString.substring(0, m1.selectedCurrentSharesString.indexOf(" "))));
			HashMap <Date, Double> hashOfHalfYearX = a1.getHashOfHalfYear();
			hashOfHalfYearX.entrySet().forEach(entry -> {
	    	series.add(new Day(entry.getKey()), entry.getValue());
	    }); 
	  //  JFreeChart chart = // create your XY chart here.
	    
//	    series.add(1, 1);
//	    series.add(2, 4);
//	    series.add(3, 9);
//	    series.add(4, 600);

	    //Add series to dataset
	    dataset2.addSeries(series);
	    XYDataset dataset = dataset2;
//	    return dataset;
//	  }
	    
	    
	    
	    
	    
	    
	    // Create chart
	    JFreeChart chart = ChartFactory.createTime(
	        "XY Line Chart Example",
	        "X-Axis",
	        "Y-Axis",
	        dataset,
	        PlotOrientation.VERTICAL,
	        true, true, false);
	    

	    // Create Panel
	    ChartPanel panel = new ChartPanel(chart);
	    sn1.setContent(panel);    
	  }
}
