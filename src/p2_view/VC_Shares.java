package p2_view;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import db_objects.PortfolioTableEntry;
import db_objects.User;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.fxml.FXML;
import javafx.scene.chart.Axis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import p0_model.Model;
import p1_controller.Controller;

public class VC_Shares {
	public Model m1;
	public Controller c1;
	
	
	@FXML
	private ListView<String> list1;
	@FXML
	private TabPane tabPane1;

	@FXML
	private ScatterChart<Number, Number> scatterChart1;

	@FXML
	private TableView<PortfolioTableEntry> pTable;
	@FXML
	private TableColumn<PortfolioTableEntry, Number> IdColumn;
	@FXML
	private TableColumn<PortfolioTableEntry, String> nameColumn;

	@FXML
	private Label idLabel;
	@FXML
	private Label nameLabel;

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
	// Reference to the main application.
	// private MainApp mainApp;

	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	public VC_Shares() {
	}

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

		ObservableList<String> items = FXCollections.observableArrayList("A", "B", "C", "D");
		list1.setItems(items);

		// tabPane1.setStyle("-fx-background-color:transparent;");
		tabPane1.getStyleClass().add("floating");

		//// pTable.setItems(Model.yourPortfolioList);
		// // Initialize the person table with the two columns.
		// IdColumn.setCellValueFactory(cellData ->
		//// cellData.getValue().getPortfolio_id());
		// nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
		//
		// // Clear person details.
		// saveSelectedAndUseSelectedToFill(null);
		// // Listen for selection changes and show the person details when changed.
		// pTable.getSelectionModel().selectedItemProperty()
		// .addListener((observable, oldValue, newValue) ->
		//// saveSelectedAndUseSelectedToFill(newValue));
		//
		//

		/////////////////////// SCATTERCHART///////////////////////////
		// final NumberAxis xAxis = new NumberAxis(0, 10, 1);
		// final NumberAxis yAxis = new NumberAxis(-100, 500, 100);
		// scatterChart1 = new ScatterChart<Number,Number>(xAxis,yAxis);

		// final ScatterChart<Number,Number> sc = new
		// ScatterChart<Number,Number>(xAxis,yAxis);
		// xAxis.setLabel("Age (years)");
		// yAxis.setLabel("Returns to date");

		// scatterChart1.setTitle("Investment Overview44");
		//
		// // CategoryAxis xAxis = (CategoryAxis) scatterChart1.getXAxis();
		// Axis<Number> xAxis = scatterChart1.getXAxis();
		// xAxis
		// xAxis.getCategories().setAll(
		// "UFO sightings",
		// "Paranormal Events",
		// "Inexplicable Tweets"
		// );

		// XYChart.Series series1 = new XYChart.Series();
		// series1.setName("Equities");
		// series1.getData().add(new XYChart.Data(4.2, 193.2));
		//
		// XYChart.Series series2 = new XYChart.Series();
		// series2.setName("Mutual funds");
		// series2.getData().add(new XYChart.Data(5.2, 229.2));
		// scatterChart1.getData().addAll(series1, series2);
		///////////////////// SCATTERCHATT END////////////////////////

	}

	private void saveSelectedAndUseSelectedToFill(PortfolioTableEntry portf1) {
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
				PortfolioTableEntry p1 = new PortfolioTableEntry(m1.nextPortfolio_id,
						this.newNameTextField.getText(), m1.loggedInUser_id,
						Double.parseDouble(this.newInvestmentTextField.getText()),
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
		Controller.setSceneToUser();
	}

}