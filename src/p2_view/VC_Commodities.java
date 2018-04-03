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
import p0_db_objects.Rohstoff;
import p0_db_objects.RohstoffTableEntry;
import p0_db_objects.PortfolioTableEntry;
import p0_model.Model;
import p1_controller.Controller;

public class VC_Commodities {
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
	private TableView<RohstoffTableEntry> tableView1;
	@FXML
	private TableView<RohstoffTableEntry> tableView2;
	@FXML
	private TableView<RohstoffTableEntry> tableView3;
	@FXML
	private TableView<RohstoffTableEntry> tableView4;
	@FXML
	private TableView<RohstoffTableEntry> tableView5;

	@FXML
	private TableColumn<RohstoffTableEntry, Number> IdColumn1;
	@FXML
	private TableColumn<RohstoffTableEntry, String> nameColumn1;
	@FXML
	private TableColumn<RohstoffTableEntry, String> industryColumn1;
	@FXML
	private TableColumn<RohstoffTableEntry, String> sigmaColumn1;
	@FXML
	private TableColumn<RohstoffTableEntry, String> riskColumn1;
	@FXML
	private TableColumn<RohstoffTableEntry, Number> IdColumn2;
	@FXML
	private TableColumn<RohstoffTableEntry, String> nameColumn2;
	@FXML
	private TableColumn<RohstoffTableEntry, String> industryColumn2;
	@FXML
	private TableColumn<RohstoffTableEntry, String> sigmaColumn2;
	@FXML
	private TableColumn<RohstoffTableEntry, String> riskColumn2;
	@FXML
	private TableColumn<RohstoffTableEntry, Number> IdColumn3;
	@FXML
	private TableColumn<RohstoffTableEntry, String> nameColumn3;
	@FXML
	private TableColumn<RohstoffTableEntry, String> industryColumn3;
	@FXML
	private TableColumn<RohstoffTableEntry, String> sigmaColumn3;
	@FXML
	private TableColumn<RohstoffTableEntry, String> riskColumn3;
	@FXML
	private TableColumn<RohstoffTableEntry, Number> IdColumn4;
	@FXML
	private TableColumn<RohstoffTableEntry, String> nameColumn4;
	@FXML
	private TableColumn<RohstoffTableEntry, String> industryColumn4;
	@FXML
	private TableColumn<RohstoffTableEntry, String> sigmaColumn4;
	@FXML
	private TableColumn<RohstoffTableEntry, String> riskColumn4;
	@FXML
	private TableColumn<RohstoffTableEntry, Number> IdColumn5;
	@FXML
	private TableColumn<RohstoffTableEntry, String> nameColumn5;
	@FXML
	private TableColumn<RohstoffTableEntry, String> industryColumn5;
	@FXML
	private TableColumn<RohstoffTableEntry, String> sigmaColumn5;
	@FXML
	private TableColumn<RohstoffTableEntry, String> riskColumn5;

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
	public VC_Commodities() {
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

		// Tabellen füllen
		ObservableList<RohstoffTableEntry> allAktienOhneKurseTE_DAX = m1.allRohstoffeOhneKurseTE.stream()
				.filter(x -> (x.getIndustry().equals("Industriemetalle")))
				.collect(Collectors.toCollection(FXCollections::observableArrayList));
		tableView1.setItems(allAktienOhneKurseTE_DAX);
		IdColumn1.setCellValueFactory(cellData -> cellData.getValue().get2Share_id());
		nameColumn1.setCellValueFactory(cellData -> cellData.getValue().get2Name());
		industryColumn1.setCellValueFactory(cellData -> cellData.getValue().get2Industry());

		ObservableList<RohstoffTableEntry> allAktienOhneKurseTE_TecDAX = m1.allRohstoffeOhneKurseTE.stream()
				.filter(x -> (x.getIndustry().equals("Edelmetalle")))
				.collect(Collectors.toCollection(FXCollections::observableArrayList));
		tableView2.setItems(allAktienOhneKurseTE_TecDAX);
		IdColumn2.setCellValueFactory(cellData -> cellData.getValue().get2Share_id());
		nameColumn2.setCellValueFactory(cellData -> cellData.getValue().get2Name());
		industryColumn2.setCellValueFactory(cellData -> cellData.getValue().get2Industry());

		ObservableList<RohstoffTableEntry> allAktienOhneKurseTE_MDAX = m1.allRohstoffeOhneKurseTE.stream()
				.filter(x -> (x.getIndustry().equals("Energie")))
				.collect(Collectors.toCollection(FXCollections::observableArrayList));
		tableView3.setItems(allAktienOhneKurseTE_MDAX);
		IdColumn3.setCellValueFactory(cellData -> cellData.getValue().get2Share_id());
		nameColumn3.setCellValueFactory(cellData -> cellData.getValue().get2Name());
		industryColumn3.setCellValueFactory(cellData -> cellData.getValue().get2Industry());

//		ObservableList<RohstoffTableEntry> allAktienOhneKurseTE_Nikkei = m1.allRohstoffeOhneKurseTE.stream()
//				.filter(x -> (x.getIndex().equals("Nikkei 225")))
//				.collect(Collectors.toCollection(FXCollections::observableArrayList));
//		tableView4.setItems(allAktienOhneKurseTE_Nikkei);
//		IdColumn4.setCellValueFactory(cellData -> cellData.getValue().get2Share_id());
//		nameColumn4.setCellValueFactory(cellData -> cellData.getValue().get2Name());
//		industryColumn4.setCellValueFactory(cellData -> cellData.getValue().get2Industry());
//
//		ObservableList<RohstoffTableEntry> allAktienOhneKurseTE_DowJones = m1.allRohstoffeOhneKurseTE.stream()
//				.filter(x -> (x.getIndex().equals("Dow Jones")))
//				.collect(Collectors.toCollection(FXCollections::observableArrayList));
//		tableView5.setItems(allAktienOhneKurseTE_DowJones);
//		IdColumn5.setCellValueFactory(cellData -> cellData.getValue().get2Share_id());
//		nameColumn5.setCellValueFactory(cellData -> cellData.getValue().get2Name());
//		industryColumn5.setCellValueFactory(cellData -> cellData.getValue().get2Industry());

		///////////////////////
		// 5x Table Listener
		///////////////////////
		tableView1.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> handleSaveSelected(newValue));
		tableView2.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> handleSaveSelected(newValue));
		tableView3.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> handleSaveSelected(newValue));

		// Liste füllen
		simpleStringList = new ArrayList<>();
		m1.currentPortfoliosRohstoffeMitKursen.forEach(
				(k, v) -> simpleStringList.add(v.getShare_id() + " " + v.getName() + " (" + v.getIndustry() + ")"));

		listView1.itemsProperty().bind(listProperty);
		listProperty.set(FXCollections.observableArrayList(simpleStringList));

		listView1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue != null) {
					m1.selectedCurrentCommodityString = newValue;
				}
				create2();
			}
		});

		////////////////////////////////////////////
		// Scattertchart füllen
		////////////////////////////////////////////
		sc.setTitle("Risiko-Rendite-Diagramm der Aktien");
		// 5x Assetclass-Daten einfüllen
		ObservableList<XYChart.Series<Double, Double>> scatterGraphSeries = FXCollections.observableArrayList();
		for (Rohstoff ac1 : m1.currentPortfoliosRohstoffeMitKursen.values()) {
			XYChart.Series<Double, Double> series1 = new XYChart.Series<Double, Double>();
			series1.getData().add(new XYChart.Data<Double, Double>(ac1.getSigma(), ac1.getRisk()));
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
		m1.deleteFromCurrentPRohstoffe(Integer
				.valueOf(m1.selectedCurrentCommodityString.substring(0, m1.selectedCurrentCommodityString.indexOf(" "))));
m1.updatePB_PORTF_COMMwithPercents();
		updateData();
	}

	private void handleSaveSelected(RohstoffTableEntry aktie1) {
		if (aktie1 != null) {
			m1.selectedRohstoff = aktie1;
		} else {
			//
			m1.selectedRohstoff = null;
		}
	}

	@FXML
	private void handleAdd() {
		m1.currentPortfoliosRohstoffeMitKursen.put(new Integer(m1.selectedRohstoff.getShare_id()),
				m1.allRohstoffeOhneKurse.get(m1.selectedRohstoff.getShare_id()));

		m1.sortBothCurrentPortfoliosRohstoffe();
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
		this.c1.setSceneToV_Rohstoffanalyse();
	}

	@FXML
	private void handleZurueck() throws IOException {
		this.c1.setSceneToV_AssetType();
	}

	@FXML
	private void handleVorleaufigUpdaten() throws IOException {
		m1.updatePB_PORTF_COMMwithPercents();
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

		final int dayRange = 60;
		LocalDate today = LocalDate.now();
		Random rng = new Random();

		try {
			for (Map.Entry<Date, Double> entry : m1.currentPortfoliosRohstoffeMitKursen
					.get(new Integer(Integer.valueOf(
							m1.selectedCurrentCommodityString.substring(0, m1.selectedCurrentCommodityString.indexOf(" ")))))
					.getHashOfHalfYear().entrySet()) {
				Date key = entry.getKey();
				Double value = entry.getValue();
				LocalDate dateA = Instant.ofEpochMilli(key.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
				String formattedDateA = formatter.format(dateA);
				addData(data, formattedDateA, value);
				s1.setName(m1.currentPortfoliosRohstoffeMitKursen.get(new Integer(Integer.valueOf(
						m1.selectedCurrentCommodityString.substring(0, m1.selectedCurrentCommodityString.indexOf(" ")))))
						.getName());
			}
		} catch (NullPointerException e) {
			System.out.print("Caught the NullPointerException");
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