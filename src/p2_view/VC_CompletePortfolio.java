package p2_view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.FocusEvent;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

import db_objects.AktieTableEntry;
import db_objects.Assetclass;
import db_objects.Portfolio;
import db_objects.PortfolioTableEntry;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import p0_model.Model;
import p1_controller.Controller;
import p2_view.charts.PieChart3DDemo3;




public class VC_CompletePortfolio {

	public Model m1;
	public Controller c1;
	private SwingNode sn1;
	
	@FXML 
	private Button go;
	
	@FXML 
	private TextField textfieldname;
	
	@FXML 
	private TextField textfieldid;
	
	@FXML 
	private TextField textfieldinvestment;
	
	@FXML 
	private TextField textfieldshares;
	
	@FXML 
	private TextField textfieldcommodities;
	
	@FXML 
	private TextField textfieldrealestates;
	
	@FXML 
	private TextField textfieldbonds;
	
	@FXML 
	private TextField textfieldcash;
	
	@FXML
	private StackPane paneWithSwing;
	
//	@FXML
//	private TableView<AktieTableEntry> listviewshares;
	
//	protected List<String> simpleStringList ;
//	protected ListProperty<String> listProperty = new SimpleListProperty<>();
	
//	@FXML
//	private TableView listviewcommodities;

	
	
	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	
	public VC_CompletePortfolio() {
		
	}

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	
	private void initialize() {
		
	}
	
	public void updateData() {
		
		sn1 = new SwingNode();
		paneWithSwing.getChildren().add(sn1);
		
/*	textfieldname.setText("" + m1.usedPortfolio.getName());
	textfieldinvestment.setText("" + m1.usedPortfolio.getCapital());
	textfieldshares.setText("" + m1.usedPortfolio.getShare_dist());
	textfieldcommodities.setText("" + m1.usedPortfolio.getComm_dist());
	textfieldrealestates.setText("" + m1.usedPortfolio.getEstate_dist());
	textfieldbonds.setText("" + m1.usedPortfolio.getBond_dist());
	textfieldcash.setText("" + m1.usedPortfolio.getCurr_dist());	
*/		
	textfieldname.setText("test");
	textfieldinvestment.setText("100");
	textfieldshares.setText("1");
	textfieldcommodities.setText("24");
	textfieldrealestates.setText("25");
	textfieldbonds.setText("25");
	textfieldcash.setText("25");	
		
/*	m1.currentPortfoliosAktienMitKursen.forEach( (k,v) -> simpleStringList.add(v.getShare_id() + " " + v.getName() + " (" +v.getIndex() + ")"));
	simpleStringList= new ArrayList<>();
	
    simpleStringList.add("CNH");
    simpleStringList.add("JPY");
    simpleStringList.add("HKD");
    simpleStringList.add("KRW");
    simpleStringList.add("SGD");

    listviewshares.itemsProperty().bind(listProperty);
	listProperty.set(FXCollections.observableArrayList(simpleStringList));    
*/	
	
	   
	
	
	
	
	
	
		updatePieChart();
	}
	
	public void updatePieChart() {
		if (Double.parseDouble(this.textfieldshares.getText())
				+ Double.parseDouble(this.textfieldcommodities.getText())
				+ Double.parseDouble(this.textfieldcash.getText())
				+ Double.parseDouble(this.textfieldrealestates.getText())
				+ Double.parseDouble(this.textfieldbonds.getText()) != 100) {
			System.out.println("Die Summe der Prozentwerte muss 100% sein.");
		} else {

			DefaultPieDataset result = new DefaultPieDataset();
			result.setValue("Aktien", Double.parseDouble(this.textfieldshares.getText()));
			result.setValue("Rohstoffe", Double.parseDouble(this.textfieldcommodities.getText()));
			result.setValue("Liquide Mittel", Double.parseDouble(this.textfieldcash.getText()));
			result.setValue("Immobilien", Double.parseDouble(this.textfieldrealestates.getText()));
			result.setValue("Anleihen", Double.parseDouble(this.textfieldbonds.getText()));

			JFreeChart chart = ChartFactory.createPieChart3D("Verteilung auf die Anlageklassen", // chart title
					result, // data
					true, // include legend
					true, false);

			PiePlot3D plot = (PiePlot3D) chart.getPlot();
			plot.setStartAngle(290);
			plot.setDirection(Rotation.CLOCKWISE);
			plot.setForegroundAlpha(0.5f);
			plot.setNoDataMessage("No data to display");
			plot.setLabelGenerator(new PieChart3DDemo3.CustomLabelGenerator());

			JFreeChart chart1 = chart;
			JPanel chartPanel = new ChartPanel(chart1);
			chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));

			ChartPanel panel = new ChartPanel(chart);
			panel.setMouseWheelEnabled(true);
			sn1.setContent(panel);
			System.out.println("Hallo");
		}
	
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Handle-Methoden
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	@FXML
	private void handleGo() throws IOException {
		System.out.println("Weiterbutton pressed!");
		this.c1.setSceneToV_CompletePortfolioCompare();
	}
	

  
		
	
}