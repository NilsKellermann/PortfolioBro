package p2_view;

import java.io.IOException;
import db_objects.Assetclass;
import db_objects.PortfolioTableEntry;
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
import p0_model.Model;
import p1_controller.Controller;

public class VC_AssetType {

	public Model m1;
	public Controller c1;

	@FXML
	private ScatterChart<Number, Number> sc;
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
	private TextField newNameTextField;
	@FXML
	private TextField newInvestmentTextField;
	@FXML
	private TextField newShareDistTextField;
	@FXML
	private TextField newCommDistTextField;
	@FXML
	private TextField newCurrDistTextField;
	@FXML
	private TextField newEstateDistTextField;
	@FXML
	private TextField newBondDistTextField;


	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	public VC_AssetType() {
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
		pTable.setItems(m1.yourPortfolioList);
		IdColumn.setCellValueFactory(cellData -> cellData.getValue().getPortfolio_id());
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
		pTable.getSelectionModel().selectedItemProperty()
		.addListener((observable, oldValue, newValue) -> handleSaveSelectedAndUseSelectedToFill(newValue));
		
		// Clear person details.
		handleSaveSelectedAndUseSelectedToFill(null);
		
		//Fill Scatter Chart
		sc.setTitle("Sigma-r Diagramm der Assetklassen");

		ObservableList<XYChart.Series<Number, Number>> scatterGraphSeries = FXCollections.observableArrayList();
		for (Assetclass ac1 : m1.assetclasses.values()) {
			XYChart.Series series1 = new XYChart.Series();
			series1.getData().add(new XYChart.Data(ac1.getSigma(), ac1.getRisk()));
			series1.setName(ac1.getName());
			scatterGraphSeries.add(series1);
		}
		sc.setData(scatterGraphSeries);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Handle-Methoden
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
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

	}

	@FXML
	private void handleUnfertigesPortfolioErstellen() {
		try {
			boolean alreadyExists = m1.yourPortfolioList.stream()
					.anyMatch(t -> t.get2Name().equals(this.newNameTextField.getText()));
			if (alreadyExists == true) {
				System.out.println("Portfolio name already in use.");
			} else {
				PortfolioTableEntry p1 = new PortfolioTableEntry(m1.nextPortfolio_id, this.newNameTextField.getText(),
						m1.loggedInUser_id, Double.parseDouble(this.newInvestmentTextField.getText()),
						Double.parseDouble(this.newShareDistTextField.getText()),
						Double.parseDouble(this.newCommDistTextField.getText()),
						Double.parseDouble(this.newCurrDistTextField.getText()),
						Double.parseDouble(this.newEstateDistTextField.getText()),
						Double.parseDouble(this.newBondDistTextField.getText()));

				if (p1.get2Name() == "") {
					System.out.println("Das Namensfeld kann nicht leer bleiben.");
					return;
				}
				if (p1.get2Share_dist() + p1.get2Comm_dist() + p1.get2Curr_dist() + p1.get2Estate_dist()
						+ p1.get2Bond_dist() != 100) {
					System.out.println("Die Summe der Prozentwerte muss 100% sein.");
					return;
				}
				m1.nextPortfolio_id = m1.nextPortfolio_id + 1;
				m1.yourPortfolioList.add(p1);
				m1.allPortfolioList.add(p1);
				System.out.println("Portfolio created.");
			}
		} catch (java.lang.NumberFormatException e) {
			System.out.println("Fehler. Der Inhalt der Textviews muss überprüft werden.");
		}
	}

	@FXML
	private void handleWeiter() throws IOException {
		System.out.println("Weiterbutton pressed!");
		this.c1.setSceneToV_Shares();
	}

}