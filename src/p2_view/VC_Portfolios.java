package p2_view;

import java.awt.event.FocusEvent;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import p0_model.Model;
import p0_model.db_objects.Portfolio;
import p0_model.db_objects.PortfolioTableEntry;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
	private TableColumn<PortfolioTableEntry, Number> InvestmentColumn;
	@FXML
	private TableColumn<PortfolioTableEntry, Number> A_Column;
	@FXML
	private TableColumn<PortfolioTableEntry, Number> B_Column;
	@FXML
	private TableColumn<PortfolioTableEntry, Number> C_Column;
	@FXML
	private TableColumn<PortfolioTableEntry, Number> D_Column;
	@FXML
	private TableColumn<PortfolioTableEntry, Number> E_Column;
	
	@FXML
	private Label testidLabel;
//	@FXML
//	private Label idLabel;
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
		// Portfolio-Tabelle füllen
		pTable.setItems(m1.allPortfolioTE);
		IdColumn.setCellValueFactory(cellData -> cellData.getValue().getPortfolio_id());
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
		InvestmentColumn.setCellValueFactory(cellData -> cellData.getValue().getCapital());
		A_Column.setCellValueFactory(cellData -> cellData.getValue().getShare_dist());
		B_Column.setCellValueFactory(cellData -> cellData.getValue().getComm_dist());
		C_Column.setCellValueFactory(cellData -> cellData.getValue().getCurr_dist());
		D_Column.setCellValueFactory(cellData -> cellData.getValue().getEstate_dist());
		E_Column.setCellValueFactory(cellData -> cellData.getValue().getBond_dist());

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
//			idLabel.setText("" + m1.selectedPortfolio.get2Portfolio_id());
			nameLabel.setText(m1.selectedPortfolio.get2Name());
		} else {
			m1.selectedPortfolio = null;
//			idLabel.setText("");
			nameLabel.setText("");
		}
	}

	@FXML
	private void handleDelete() {
		m1.allPortfolios.remove(m1.selectedPortfolio.get2Portfolio_id());
		m1.deleteDB_Portfolio_updatePortfolioTE(m1.selectedPortfolio);
		this.updateData();
	}

	@FXML
	private void handleWeiter() throws IOException {
		m1.usedPortfolio = new Portfolio(m1.calculateNextPortfolioID(), "empty", m1.loggedInUser_id, 0, 50, 50, 0, 0, 0,
				0, 0, 0, 0, 0, 0);
		this.c1.setSceneToV_AssetType();
	}

	@FXML
	private void handleZurueck() throws IOException {
		this.c1.setSceneToV_MainMenu();
	}

	@FXML
	private void handleVergleichen() throws IOException {
		Controller.setSceneToV_CompletePortfolioCompare();
	}
	
	@FXML
	private void handleAnpassen() throws IOException {
		if (m1.selectedPortfolio != null) {
			m1.usedPortfolio = m1.allPortfolios.get(m1.selectedPortfolio.get2Portfolio_id());
			this.c1.setSceneToV_AssetType();
		} else {
			// Show a predefined Warning notification
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Sie haben kein Portfolio ausgewählt.");
			alert.setContentText("");
			alert.showAndWait();
		}
	}

}