package p2_view;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javafx.application.Application;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import p0_model.Model;
import p0_model.db_objects.Aktie;
import p0_model.db_objects.AktieTableEntry;
import p0_model.db_objects.PortfolioTableEntry;
import p1_controller.Controller;

public class VC_Shares {
	public Model m1;
	public Controller c1;

	// Nicht FXML!
	private SwingNode sn1;

	@FXML
	private BorderPane bp1;
	@FXML
	private ListView<String> listView1;
	@FXML
	private TabPane tabPane1;

	@FXML
	private ScatterChart<Double, Double> sc;
	@FXML
	private StackPane paneWithSwing;
	@FXML
	private TableView<AktieTableEntry> tableView1;
	@FXML
	private TableView<AktieTableEntry> tableView2;
	@FXML
	private TableView<AktieTableEntry> tableView3;
	@FXML
	private TableView<AktieTableEntry> tableView4;
	@FXML
	private TableView<AktieTableEntry> tableView5;

	@FXML
	private TableColumn<AktieTableEntry, Number> IdColumn1;
	@FXML
	private TableColumn<AktieTableEntry, String> nameColumn1;
	@FXML
	private TableColumn<AktieTableEntry, String> industryColumn1;
	@FXML
	private TableColumn<AktieTableEntry, String> sigmaColumn1;
	@FXML
	private TableColumn<AktieTableEntry, String> riskColumn1;
	@FXML
	private TableColumn<AktieTableEntry, Number> IdColumn2;
	@FXML
	private TableColumn<AktieTableEntry, String> nameColumn2;
	@FXML
	private TableColumn<AktieTableEntry, String> industryColumn2;
	@FXML
	private TableColumn<AktieTableEntry, String> sigmaColumn2;
	@FXML
	private TableColumn<AktieTableEntry, String> riskColumn2;
	@FXML
	private TableColumn<AktieTableEntry, Number> IdColumn3;
	@FXML
	private TableColumn<AktieTableEntry, String> nameColumn3;
	@FXML
	private TableColumn<AktieTableEntry, String> industryColumn3;
	@FXML
	private TableColumn<AktieTableEntry, String> sigmaColumn3;
	@FXML
	private TableColumn<AktieTableEntry, String> riskColumn3;
	@FXML
	private TableColumn<AktieTableEntry, Number> IdColumn4;
	@FXML
	private TableColumn<AktieTableEntry, String> nameColumn4;
	@FXML
	private TableColumn<AktieTableEntry, String> industryColumn4;
	@FXML
	private TableColumn<AktieTableEntry, String> sigmaColumn4;
	@FXML
	private TableColumn<AktieTableEntry, String> riskColumn4;
	@FXML
	private TableColumn<AktieTableEntry, Number> IdColumn5;
	@FXML
	private TableColumn<AktieTableEntry, String> nameColumn5;
	@FXML
	private TableColumn<AktieTableEntry, String> industryColumn5;
	@FXML
	private TableColumn<AktieTableEntry, String> sigmaColumn5;
	@FXML
	private TableColumn<AktieTableEntry, String> riskColumn5;

	@FXML
	private Button addbutton1;
	@FXML
	private Button addbutton2;
	@FXML
	private Button addbutton3;
	@FXML
	private Button addbutton4;
	@FXML
	private Button addbutton5;

	@FXML
	private Button deletebutton1;
	@FXML
	private Button vorleaufigUpdatenButton1;

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

	protected List<String> simpleStringList;
	protected ListProperty<String> listProperty = new SimpleListProperty<>();

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
	}

	public void updateData() {

		// Initiale Anpassungen von UI-Elementen
		tabPane1.getStyleClass().add("floating");

		// Tabellen f�llen
		ObservableList<AktieTableEntry> allAktienOhneKurseTE_DAX = m1.allAktienOhneKurseTE.stream()
				.filter(x -> (x.getIndex().equals("DAX")))
				.collect(Collectors.toCollection(FXCollections::observableArrayList));
		tableView1.setItems(allAktienOhneKurseTE_DAX);
		IdColumn1.setCellValueFactory(cellData -> cellData.getValue().get2Share_id());
		nameColumn1.setCellValueFactory(cellData -> cellData.getValue().get2Name());
		industryColumn1.setCellValueFactory(cellData -> cellData.getValue().get2Industry());

		ObservableList<AktieTableEntry> allAktienOhneKurseTE_TecDAX = m1.allAktienOhneKurseTE.stream()
				.filter(x -> (x.getIndex().equals("TecDAX")))
				.collect(Collectors.toCollection(FXCollections::observableArrayList));
		tableView2.setItems(allAktienOhneKurseTE_TecDAX);
		IdColumn2.setCellValueFactory(cellData -> cellData.getValue().get2Share_id());
		nameColumn2.setCellValueFactory(cellData -> cellData.getValue().get2Name());
		industryColumn2.setCellValueFactory(cellData -> cellData.getValue().get2Industry());

		ObservableList<AktieTableEntry> allAktienOhneKurseTE_MDAX = m1.allAktienOhneKurseTE.stream()
				.filter(x -> (x.getIndex().equals("MDAX")))
				.collect(Collectors.toCollection(FXCollections::observableArrayList));
		tableView3.setItems(allAktienOhneKurseTE_MDAX);
		IdColumn3.setCellValueFactory(cellData -> cellData.getValue().get2Share_id());
		nameColumn3.setCellValueFactory(cellData -> cellData.getValue().get2Name());
		industryColumn3.setCellValueFactory(cellData -> cellData.getValue().get2Industry());

		ObservableList<AktieTableEntry> allAktienOhneKurseTE_Nikkei = m1.allAktienOhneKurseTE.stream()
				.filter(x -> (x.getIndex().equals("Nikkei 225")))
				.collect(Collectors.toCollection(FXCollections::observableArrayList));
		tableView4.setItems(allAktienOhneKurseTE_Nikkei);
		IdColumn4.setCellValueFactory(cellData -> cellData.getValue().get2Share_id());
		nameColumn4.setCellValueFactory(cellData -> cellData.getValue().get2Name());
		industryColumn4.setCellValueFactory(cellData -> cellData.getValue().get2Industry());

		ObservableList<AktieTableEntry> allAktienOhneKurseTE_DowJones = m1.allAktienOhneKurseTE.stream()
				.filter(x -> (x.getIndex().equals("Dow Jones")))
				.collect(Collectors.toCollection(FXCollections::observableArrayList));
		tableView5.setItems(allAktienOhneKurseTE_DowJones);
		IdColumn5.setCellValueFactory(cellData -> cellData.getValue().get2Share_id());
		nameColumn5.setCellValueFactory(cellData -> cellData.getValue().get2Name());
		industryColumn5.setCellValueFactory(cellData -> cellData.getValue().get2Industry());

		///////////////////////
		// 5x Table Listener
		///////////////////////
		tableView1.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> handleSaveSelected(newValue));
		tableView2.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> handleSaveSelected(newValue));
		tableView3.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> handleSaveSelected(newValue));
		tableView4.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> handleSaveSelected(newValue));
		tableView5.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> handleSaveSelected(newValue));

		// Liste f�llen
		simpleStringList = new ArrayList<>();
		m1.currentPortfoliosAktienMitKursen.forEach(
				(k, v) -> simpleStringList.add(v.getShare_id() + " " + v.getName() + " (" + v.getIndex() + ")"));

		listView1.itemsProperty().bind(listProperty);
		listProperty.set(FXCollections.observableArrayList(simpleStringList));

		listView1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue != null) {
					m1.selectedCurrentSharesString = newValue;
				}
				create2();
			}
		});

		////////////////////////////////////////////
		// Scattertchart f�llen
		////////////////////////////////////////////
		sc.setTitle("Risiko-Rendite-Diagramm der Aktien");
		// 5x Assetclass-Daten einf�llen
		ObservableList<XYChart.Series<Double, Double>> scatterGraphSeries = FXCollections.observableArrayList();
		for (Aktie ac1 : m1.currentPortfoliosAktienMitKursen.values()) {
			XYChart.Series<Double, Double> series1 = new XYChart.Series<Double, Double>();
			series1.getData().add(new XYChart.Data<Double, Double>(ac1.getRisk(), ac1.getSigma()));
			series1.setName(ac1.getName());
			scatterGraphSeries.add(series1);
		}

		sc.setData(scatterGraphSeries);
		
		sn1 = new SwingNode();
		paneWithSwing.getChildren().add(sn1);
	}

	////////////////////////////////////////////////////////
	// Handle-Methoden
	////////////////////////////////////////////////////////
	@FXML
	private void handleDeleteSelected() {
		m1.deleteFromCurrentPAktien(Integer
				.valueOf(m1.selectedCurrentSharesString.substring(0, m1.selectedCurrentSharesString.indexOf(" "))));
		m1.updatePB_PORTF_SHAREwithPercents();
		updateData();
	}

	private void handleSaveSelected(AktieTableEntry aktie1) {
		if (aktie1 != null) {
			m1.selectedAktie = aktie1;
		} else {
			//
			m1.selectedAktie = null;
		}
	}

	@FXML
	private void handleAdd() {
		m1.currentPortfoliosAktienMitKursen.put(new Integer(m1.selectedAktie.getShare_id()),
				m1.allAktienOhneKurse.get(m1.selectedAktie.getShare_id()));

		m1.sortBothCurrentPortfoliosAktien();
		m1.loadSelectedPortfolioData();
		updateData();

	}

	@FXML
	private void handleUnfertigesPortfolioErstellen() {
		try {
			boolean alreadyExists = m1.yourPortfolioTE.stream()
					.anyMatch(t -> t.get2Name().equals(this.newNameTextField.getText()));
			if (alreadyExists == true) {
			} else {
				PortfolioTableEntry p1 = new PortfolioTableEntry(m1.calculateNextPortfolioID(),
						this.newNameTextField.getText(), m1.loggedInUser_id,
						Double.parseDouble(this.newInvestmentTextField.getText()),
						Double.parseDouble(this.newShareDistTextField.getText()),
						Double.parseDouble(this.newCommDistTextField.getText()),
						Double.parseDouble(this.newCurrDistTextField.getText()),
						Double.parseDouble(this.newEstateDistTextField.getText()),
						Double.parseDouble(this.newBondDistTextField.getText()));

				if (p1.get2Name() == "") {
					return;
				}
				if (p1.get2Share_dist() + p1.get2Comm_dist() + p1.get2Curr_dist() + p1.get2Estate_dist()
						+ p1.get2Bond_dist() != 100) {
					return;
				}
				;
				m1.yourPortfolioTE.add(p1);
				m1.allPortfolioTE.add(p1);
			}
		} catch (java.lang.NumberFormatException e) {
		}
	}

	@FXML
	private void handleWeiter() throws IOException {
		handleVorleaufigUpdaten();
		m1.loadSelectedPortfolioData();
		this.c1.setSceneToV_Aktienanalyse();
	}

	@FXML
	private void handleZurueck() throws IOException {
		this.c1.setSceneToV_AssetType();
	}

	@FXML
	private void handleVorleaufigUpdaten() throws IOException {
		m1.updatePB_PORTF_SHAREwithPercents();
	}

	public void create2() {
		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis();

		ObservableList<Data<String, Number>> data = FXCollections.observableArrayList();
		ObservableList<Data<String, Number>> data7 = FXCollections.observableArrayList();
		SortedList<Data<String, Number>> sortedData = new SortedList<>(data,
				(data1, data2) -> data1.getXValue().compareTo(data2.getXValue()));
		SortedList<Data<String, Number>> sortedData2 = new SortedList<>(data7,
				(data1, data2) -> data1.getXValue().compareTo(data2.getXValue()));
		LineChart<String, Number> chart = new LineChart<>(xAxis, yAxis);
		Series<String, Number> s1 = new Series<>(sortedData);
		Series<String, Number> s2 = new Series<>(sortedData2);
		chart.getData().add(s1);
		chart.getData().add(s2);
		chart.setAnimated(false);

		try {
			for (Map.Entry<Date, Double> entry : m1.currentPortfoliosAktienMitKursen
					.get(new Integer(Integer.valueOf(
							m1.selectedCurrentSharesString.substring(0, m1.selectedCurrentSharesString.indexOf(" ")))))
					.getHashOfHalfYear().entrySet()) {
				Date key = entry.getKey();
				Double value = entry.getValue();
				LocalDate dateA = Instant.ofEpochMilli(key.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
				String formattedDateA = formatter.format(dateA);
				addData(data, formattedDateA, value);
				s1.setName(m1.currentPortfoliosAktienMitKursen.get(new Integer(Integer.valueOf(
						m1.selectedCurrentSharesString.substring(0, m1.selectedCurrentSharesString.indexOf(" ")))))
						.getName());
			}
		} catch (NullPointerException e) {
			System.out.print(" ");
		}
		// for (int i = 0; i < 20; i++) {
		// LocalDate date7 = today.minusDays(rng.nextInt(dayRange));
		// String formattedDate = formatter.format(date7);
		// double value = rng.nextDouble();
		//
		// addData(data7, formattedDate, value);
		// }

		DatePicker datePicker = new DatePicker();
		Spinner<Double> valuePicker = new Spinner<>(0.0, 1.0, 0, 0.1);
		valuePicker.setEditable(true);

		Button addButton = new Button("Add");
		addButton.setOnAction(e -> addData(data, formatter.format(datePicker.getValue()), valuePicker.getValue()));
		HBox controls = new HBox(5, datePicker, valuePicker, addButton);
		controls.setAlignment(Pos.CENTER);
		controls.setPadding(new Insets(5));

		this.bp1.setCenter(chart);
		// this.bp1.setBottom(controls);
	}

	private DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

	private void addData(ObservableList<Data<String, Number>> data, String formattedDate, double value) {
		Data<String, Number> dataAtDate = data.stream().filter(d -> d.getXValue().equals(formattedDate)).findAny()
				.orElseGet(() -> {
					Data<String, Number> newData = new Data<String, Number>(formattedDate, 0.0);
					data.add(newData);
					return newData;
				});
		dataAtDate.setYValue(dataAtDate.getYValue().doubleValue() + value);
	}

}