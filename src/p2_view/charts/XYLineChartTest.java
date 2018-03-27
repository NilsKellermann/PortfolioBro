package p2_view.charts;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class XYLineChartTest extends JFrame {
	  private static final long serialVersionUID = 6294689542092367723L;

	  public XYLineChartTest(String title) {
	    super(title);

	    // Create dataset
	    XYDataset dataset = createDataset();

	    // Create chart
	    JFreeChart chart = ChartFactory.createXYLineChart(
	        "XY Line Chart Example",
	        "X-Axis",
	        "Y-Axis",
	        dataset,
	        PlotOrientation.VERTICAL,
	        true, true, false);
	    

	    // Create Panel
	    ChartPanel panel = new ChartPanel(chart);
	    setContentPane(panel);
	  }

	  private XYDataset createDataset() {
	    XYSeriesCollection dataset = new XYSeriesCollection();

	    XYSeries series = new XYSeries("Y = X*X");
	    series.add(1, 1);
	    series.add(2, 4);
	    series.add(3, 9);
	    series.add(4, 16);

	    //Add series to dataset
	    dataset.addSeries(series);
	    
	    return dataset;
	  }
	  
	  

	  public static void main(String[] args) {
	    SwingUtilities.invokeLater(() -> {
	    	XYLineChartTest example = new XYLineChartTest("XY Chart Example | BORAJI.COM");
	      example.setSize(800, 400);
	      example.setLocationRelativeTo(null);
	      example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	      example.setVisible(true);
	    });
	  }
	}