package p2_view;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import p0_db_objects.AnlageKlasse;
import p0_db_objects.PortfolioTableEntry;
import javafx.application.Application;
//import javafx.scene.Scene; 
//import javafx.scene.image.Image; 
//import javafx.scene.image.ImageView; 
//import javafx.scene.layout.BorderPane; 
//import javafx.stage.Stage; 

import p0_model.Model;
import p1_controller.Controller;

public class VC_MainMenu {

	public Model m1;
	public Controller c1;

	@FXML
	private TextField WelcomeTextField;

	@FXML
	private Button go;

	// @FXML
	// private ImageView picture;

	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */

	public VC_MainMenu() {

	}

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */

	private void initialize() {

	}

	public void updateData() {

	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Handle-Methoden
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@FXML
	private void handleGo() throws IOException {
		System.out.println("Weiterbutton pressed!");
		this.c1.setSceneToV_Portfolios();

	}

}