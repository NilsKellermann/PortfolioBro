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

import db_objects.Assetclass;
import db_objects.Portfolio;
import db_objects.PortfolioTableEntry;
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
	
	
	
	
	
	@FXML 
	private Button go;
	


	

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
		
		
	
	}
	
//	public void updatePieChart() {
//		if (Double.parseDouble(this.newShareDistTextField.getText())
//				+ Double.parseDouble(this.newCommDistTextField.getText())
//				+ Double.parseDouble(this.newCurrDistTextField.getText())
//				+ Double.parseDouble(this.newEstateDistTextField.getText())
//				+ Double.parseDouble(this.newBondDistTextField.getText()) != 100) {
//			System.out.println("Die Summe der Prozentwerte muss 100% sein.");
//		} else {
//
//			DefaultPieDataset result = new DefaultPieDataset();
//			result.setValue("Aktien", Double.parseDouble(this.newShareDistTextField.getText()));
//			result.setValue("Rohstoffe", Double.parseDouble(this.newCommDistTextField.getText()));
//			result.setValue("Liquide Mittel", Double.parseDouble(this.newCurrDistTextField.getText()));
//			result.setValue("Immobilien", Double.parseDouble(this.newEstateDistTextField.getText()));
//			result.setValue("Anleihen", Double.parseDouble(this.newBondDistTextField.getText()));
//
//			JFreeChart chart = ChartFactory.createPieChart3D("Assetklassen", // chart title
//					result, // data
//					true, // include legend
//					true, false);
//
//			PiePlot3D plot = (PiePlot3D) chart.getPlot();
//			plot.setStartAngle(290);
//			plot.setDirection(Rotation.CLOCKWISE);
//			plot.setForegroundAlpha(0.5f);
//			plot.setNoDataMessage("No data to display");
//			plot.setLabelGenerator(new PieChart3DDemo3.CustomLabelGenerator());
//
//			JFreeChart chart1 = chart;
//			JPanel chartPanel = new ChartPanel(chart1);
//			chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
//
//			ChartPanel panel = new ChartPanel(chart);
//			panel.setMouseWheelEnabled(true);
//			sn1.setContent(panel);
//			System.out.println("Hallo");
//		}
//		}
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Handle-Methoden
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@FXML
	private void handleGo() throws IOException {
		System.out.println("Weiterbutton pressed!");
		this.c1.setSceneToV_CompletePortfolioCompare();
	}
	

  
		
	
}