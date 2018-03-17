package p2_view;

import java.awt.event.FocusEvent;
import java.io.IOException;
import db_objects.Portfolio;
import db_objects.PortfolioTableEntry;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import p0_model.Model;
import p1_controller.Controller;

public class VC_Portfolios {

	public Model m1;
	public Controller c1;

	@FXML
	private TableView<PortfolioTableEntry> pTable;
	@FXML
	private TableColumn<PortfolioTableEntry, Number> IdColumn;
	@FXML
	private TableColumn<PortfolioTableEntry, String> nameColumn;


	@FXML
	private Label testidLabel;
	@FXML
	private Label idLabel;
	@FXML
	private Label nameLabel;
	@FXML
	private Button veraendern;
	@FXML
	private Button loeschen;
	@FXML
	private Button weiter;
	

	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	public VC_Portfolios() {
	}

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

	}
	
	public void updateData() {
		//Fill Portfolio-table & add listener
		pTable.setItems(m1.allPortfolioTE);
		IdColumn.setCellValueFactory(cellData -> cellData.getValue().getPortfolio_id());
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());

		
		pTable.getSelectionModel().selectedItemProperty()
		.addListener((observable, oldValue, newValue) -> handleSaveSelectedAndUseSelectedToFill(newValue));
		
		// Clear person details.
		handleSaveSelectedAndUseSelectedToFill(null);
	}
	
	////////////////////////////////////////////////////////
	// Handle-Methoden
	////////////////////////////////////////////////////////
	private void handleSaveSelectedAndUseSelectedToFill(PortfolioTableEntry portf1) {
		if (portf1 != null) {
			m1.selectedPortfolio = portf1;
			idLabel.setText("" + m1.selectedPortfolio.get2Portfolio_id());
			nameLabel.setText(m1.selectedPortfolio.get2Name());
		} else {
			//
			m1.selectedPortfolio = null;
			idLabel.setText("");
			nameLabel.setText("");
		}
	}

	@FXML
	private void handleDelete() {
		System.out.println("DeleteButton Pressed!");
		m1.allPortfolios.remove(m1.selectedPortfolio.get2Portfolio_id());
		m1.deleteDB_Portfolio_updatePortfolioTE(m1.selectedPortfolio);
		this.updateData();
	}

	@FXML
	private void handleWeiter() throws IOException {
		System.out.println("Weiterbutton pressed!");
		m1.usedPortfolio = new Portfolio(m1.calculateNextPortfolioID(), "empty" , m1.loggedInUser_id, 0, 50, 50, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		this.c1.setSceneToV_AssetType();
	}

	@FXML
	private void handleAnpassen() throws IOException {
		if(m1.selectedPortfolio != null) {
	m1.usedPortfolio = m1.allPortfolios.get(m1.selectedPortfolio.get2Portfolio_id());
	this.c1.setSceneToV_AssetType();}
		else {
			// Show a predefined Warning notification
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Sie haben kein Portfolio ausgewählt.");
			alert.setContentText("");
			alert.showAndWait();
		}
	}
	
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

}