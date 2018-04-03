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
import javafx.application.Application; 
import java.awt.event.FocusEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import p0_model.Model;
import p0_model.db_objects.AnlageKlasse;
import p0_model.db_objects.Portfolio;
import p0_model.db_objects.PortfolioTableEntry;
import p1_controller.Controller;


public class VC_CompletePortfolioCompare {

	public Model m1;
	public Controller c1;

	@FXML
	private TextField WelcomeTextField;
	
		@FXML
		private TableView<PortfolioTableEntry> pTable1;

@FXML
	private TableView<PortfolioTableEntry> pTable2;
	
		@FXML
		private TableColumn<PortfolioTableEntry, Number> idColumn1;
		@FXML
		private TableColumn<PortfolioTableEntry, String> nameColumn1;
	
	@FXML
	private TableColumn<PortfolioTableEntry, Number> idColumn2;
	@FXML
	private TableColumn<PortfolioTableEntry, String> nameColumn2;
	
		@FXML
		private Label nameLabel1;
	
	@FXML
	private Label nameLabel2;

	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	
	public VC_CompletePortfolioCompare() {
		
	}

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	
	private void initialize() {
		
	}
	
	public void updateData() {
		
		//Fill Portfolio-tables & add listeners
				pTable1.setItems(m1.allPortfolioTE);
				idColumn1.setCellValueFactory(cellData -> cellData.getValue().getPortfolio_id());
				nameColumn1.setCellValueFactory(cellData -> cellData.getValue().getName());

				
				pTable1.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> handleSaveSelectedAndUseSelectedToFill1(newValue));
				
				// Clear person details.
				handleSaveSelectedAndUseSelectedToFill1(null);
		
			pTable2.setItems(m1.allPortfolioTE);
				idColumn2.setCellValueFactory(cellData -> cellData.getValue().getPortfolio_id());
				nameColumn2.setCellValueFactory(cellData -> cellData.getValue().getName());

				
				pTable2.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> handleSaveSelectedAndUseSelectedToFill2(newValue));
				
				// Clear person details.
				handleSaveSelectedAndUseSelectedToFill2(null);
		 
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Handle-Methoden
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private void handleSaveSelectedAndUseSelectedToFill1(PortfolioTableEntry portf1) {
		if (portf1 != null) {
			m1.selectedPortfolio = portf1;
			nameLabel1.setText(m1.selectedPortfolio.get2Name());
		} else {
			//
			m1.selectedPortfolio = null;
			nameLabel1.setText("");
		}
	}
	
	private void handleSaveSelectedAndUseSelectedToFill2(PortfolioTableEntry portf2) {
		if (portf2 != null) {
			m1.selectedPortfolio = portf2;
			nameLabel2.setText(m1.selectedPortfolio.get2Name());
		} else {
			//
			m1.selectedPortfolio = null;
			nameLabel2.setText("");
	}	
	}
	
	
	@FXML
	private void handleZurueck() throws IOException {
		System.out.println("Weiterbutton pressed!");
		this.c1.setSceneToV_CompletePortfolio();
	}

  
		
	
}