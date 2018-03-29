package p2_view;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import db_objects.Aktie;
import db_objects.AktieTableEntry;
import db_objects.Assetclass;
import db_objects.PortfolioTableEntry;
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
import p1_controller.Controller;

public class VC_Shares {
	public Model m1;
	public Controller c1;

	@FXML
	private BorderPane bp1;
	// Nicht FXML!
	private SwingNode sn1;
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
	private TableColumn<AktieTableEntry, Number> industryColumn1;
	@FXML
	private TableColumn<AktieTableEntry, String> sigmaColumn1;
	@FXML
	private TableColumn<AktieTableEntry, String> riskColumn1;
	@FXML
	private TableColumn<AktieTableEntry, Number> IdColumn2;
	@FXML
	private TableColumn<AktieTableEntry, String> nameColumn2;
	@FXML
	private TableColumn<AktieTableEntry, Number> industryColumn2;
	@FXML
	private TableColumn<AktieTableEntry, String> sigmaColumn2;
	@FXML
	private TableColumn<AktieTableEntry, String> riskColumn2;
	@FXML
	private TableColumn<AktieTableEntry, Number> IdColumn3;
	@FXML
	private TableColumn<AktieTableEntry, String> nameColumn3;
	@FXML
	private TableColumn<AktieTableEntry, Number> industryColumn3;
	@FXML
	private TableColumn<AktieTableEntry, String> sigmaColumn3;
	@FXML
	private TableColumn<AktieTableEntry, String> riskColumn3;
	@FXML
	private TableColumn<AktieTableEntry, Number> IdColumn4;
	@FXML
	private TableColumn<AktieTableEntry, String> nameColumn4;
	@FXML
	private TableColumn<AktieTableEntry, Number> industryColumn4;
	@FXML
	private TableColumn<AktieTableEntry, String> sigmaColumn4;
	@FXML
	private TableColumn<AktieTableEntry, String> riskColumn4;
	@FXML
	private TableColumn<AktieTableEntry, Number> IdColumn5;
	@FXML
	private TableColumn<AktieTableEntry, String> nameColumn5;
	@FXML
	private TableColumn<AktieTableEntry, Number> industryColumn5;
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

		// tabPane1.getStyleClass().add("floating");

		ObservableList<AktieTableEntry> allAktienOhneKurseTE_DAX = m1.allAktienOhneKurseTE.stream()
				.filter(x -> (x.getIndex().equals("DAX")))
				.collect(Collectors.toCollection(FXCollections::observableArrayList));
		System.out.println("Tabelle22222" + allAktienOhneKurseTE_DAX);
		tableView1.setItems(allAktienOhneKurseTE_DAX);
		IdColumn1.setCellValueFactory(cellData -> cellData.getValue().get2Share_id());
		nameColumn1.setCellValueFactory(cellData -> cellData.getValue().get2Name());

		ObservableList<AktieTableEntry> allAktienOhneKurseTE_TecDAX = m1.allAktienOhneKurseTE.stream()
				.filter(x -> (x.getIndex().equals("TecDAX")))
				.collect(Collectors.toCollection(FXCollections::observableArrayList));
		System.out.println("Tabelle22222" + allAktienOhneKurseTE_TecDAX);
		tableView2.setItems(allAktienOhneKurseTE_TecDAX);
		IdColumn2.setCellValueFactory(cellData -> cellData.getValue().get2Share_id());
		nameColumn2.setCellValueFactory(cellData -> cellData.getValue().get2Name());

		ObservableList<AktieTableEntry> allAktienOhneKurseTE_MDAX = m1.allAktienOhneKurseTE.stream()
				.filter(x -> (x.getIndex().equals("MDAX")))
				.collect(Collectors.toCollection(FXCollections::observableArrayList));
		System.out.println("Tabelle33333" + allAktienOhneKurseTE_MDAX);
		tableView3.setItems(allAktienOhneKurseTE_MDAX);
		IdColumn3.setCellValueFactory(cellData -> cellData.getValue().get2Share_id());
		nameColumn3.setCellValueFactory(cellData -> cellData.getValue().get2Name());

		ObservableList<AktieTableEntry> allAktienOhneKurseTE_Nikkei = m1.allAktienOhneKurseTE.stream()
				.filter(x -> (x.getIndex().equals("Nikkei 225")))
				.collect(Collectors.toCollection(FXCollections::observableArrayList));
		System.out.println("Tabelle44444" + allAktienOhneKurseTE_Nikkei);
		tableView4.setItems(allAktienOhneKurseTE_Nikkei);
		IdColumn4.setCellValueFactory(cellData -> cellData.getValue().get2Share_id());
		nameColumn4.setCellValueFactory(cellData -> cellData.getValue().get2Name());

		ObservableList<AktieTableEntry> allAktienOhneKurseTE_DowJones = m1.allAktienOhneKurseTE.stream()
				.filter(x -> (x.getIndex().equals("Dow Jones")))
				.collect(Collectors.toCollection(FXCollections::observableArrayList));
		System.out.println("Tabelle55555" + allAktienOhneKurseTE_DowJones);
		tableView5.setItems(allAktienOhneKurseTE_DowJones);
		IdColumn5.setCellValueFactory(cellData -> cellData.getValue().get2Share_id());
		nameColumn5.setCellValueFactory(cellData -> cellData.getValue().get2Name());

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

		System.out.println("6666" + m1.currentPortfoliosAktienMitKursen);
		simpleStringList = new ArrayList<>();

		m1.currentPortfoliosAktienMitKursen.forEach(
				(k, v) -> simpleStringList.add(v.getShare_id() + " " + v.getName() + " (" + v.getIndex() + ")"));

		simpleStringList.add("CNH");
		simpleStringList.add("JPY");
		simpleStringList.add("HKD");
		simpleStringList.add("KRW");
		simpleStringList.add("SGD");

		listView1.itemsProperty().bind(listProperty);
		listProperty.set(FXCollections.observableArrayList(simpleStringList));

		listView1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				System.out.println(
						"ListView selection changed from oldValue = " + oldValue + " to newValue = " + newValue);
				m1.selectedCurrentSharesString = newValue;

				create2();
			}
		});

		////////////////////////////////////////////
		// FILL SCATTERCHART
		////////////////////////////////////////////
		sc.setTitle("Sigma-R-Diagramm der Aktien");
		// 5x Assetclass-Daten einfüllen
		ObservableList<XYChart.Series<Double, Double>> scatterGraphSeries = FXCollections.observableArrayList();
		for (Aktie ac1 : m1.currentPortfoliosAktienMitKursen.values()) {
			XYChart.Series<Double, Double> series1 = new XYChart.Series<Double, Double>();
			series1.getData().add(new XYChart.Data<Double, Double>(ac1.getSigma(), ac1.getRisk()));
			series1.setName(ac1.getName());
			scatterGraphSeries.add(series1);
		}
		//// 1x GesamtPortfolio-Daten einfüllen
		// XYChart.Series<Double,Double> series1 = new XYChart.Series<Double,Double>();
		// series1.getData().add(new XYChart.Data<Double,
		//// Double>(m1.usedPortfolio.getSigma_asset_preview(),
		//// m1.usedPortfolio.getRisk_asset_preview()));
		// series1.setName(m1.usedPortfolio.getName());
		// scatterGraphSeries.add(series1);

		sc.setData(scatterGraphSeries);
		////////////////////////////////////////////
		// END FILL SCATTERCHART
		////////////////////////////////////////////

		// ////////////////////////////////
		// //LINECHART1
		// ////////////////////////////////
		sn1 = new SwingNode();
		paneWithSwing.getChildren().add(sn1);

		// sn1= new SwingNode();
		// paneWithSwing.getChildren().add(sn1);
		// Aktie a1 =
		// m1.currentPortfoliosAktienMitKursen.get(Integer.valueOf(m1.selectedCurrentSharesString.substring(0,
		// m1.selectedCurrentSharesString.indexOf(" "))));
		// XYSeries series = new XYSeries("Y = X");
		// XYSeriesCollection dataset = new XYSeriesCollection();
		// dataset.addSeries(series);
		// // Create chart
		// JFreeChart chart = ChartFactory.crea(
		// "XY Line Chart Example",
		// "X-Axis",
		// "Y-Axis",
		// dataset,
		// PlotOrientation.VERTICAL,
		// true, true, false);
		//
		//
		// // Create Panel
		// ChartPanel panel = new ChartPanel(chart);
		// sn1.setContent(panel);
	}

	////////////////////////////////////////////////////////
	// Handle-Methoden
	////////////////////////////////////////////////////////
	@FXML
	private void handleDeleteSelected() {
		System.out.println(" Removing from HashMap" + m1.currentPortfoliosAktienMitKursen);
		m1.currentPortfoliosAktienMitKursen.remove(Integer
				.valueOf(m1.selectedCurrentSharesString.substring(0, m1.selectedCurrentSharesString.indexOf(" "))));

		updateData();
	}

	private void handleSaveSelected(AktieTableEntry aktie1) {
		if (aktie1 != null) {
			m1.selectedAktie = aktie1;
			// idLabel.setText("" + m1.selectedAktie.get2Portfolio_id());
			// nameLabel.setText(m1.selectedAktie.get2Name());
			System.out.println("Selected Aktie:" + aktie1);
		} else {
			//
			m1.selectedAktie = null;
			// idLabel.setText("");
			// nameLabel.setText("");
		}
	}

	@FXML
	private void handleAdd() {
		m1.currentPortfoliosAktienMitKursen.put(new Integer(m1.selectedAktie.getShare_id()),
				m1.allAktienOhneKurse.get(m1.selectedAktie.getShare_id()));
		System.out.println("hinzugefügte Aktie: " + m1.currentPortfoliosAktienMitKursen);

		m1.sortCurrentPortfoliosAktien();
		m1.loadSelectedPortfolioData();
		updateData();

	}

	@FXML
	private void handleDelete() {
		System.out.println("DeleteButton Pressed!");
	}

	@FXML
	private void handleUnfertigesPortfolioErstellen() {
		try {
			boolean alreadyExists = m1.yourPortfolioTE.stream()
					.anyMatch(t -> t.get2Name().equals(this.newNameTextField.getText()));
			if (alreadyExists == true) {
				System.out.println("Portfolio name already in use.");
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
					System.out.println("Das Namensfeld kann nicht leer bleiben.");
					return;
				}
				if (p1.get2Share_dist() + p1.get2Comm_dist() + p1.get2Curr_dist() + p1.get2Estate_dist()
						+ p1.get2Bond_dist() != 100) {
					System.out.println("Die Summe der Prozentwerte muss 100% sein.");
					return;
				}
				;
				m1.yourPortfolioTE.add(p1);
				m1.allPortfolioTE.add(p1);
				System.out.println("Portfolio created.");
			}
		} catch (java.lang.NumberFormatException e) {
			System.out.println("Fehler. Der Inhalt der Textviews muss überprüft werden.");
		}
	}

	@FXML
	private void handleWeiter() throws IOException {
		System.out.println("Weiterbutton pressed!");
		this.c1.setSceneToV_Analysis_Shares();
	}

	@FXML
	private void handleZurueck() throws IOException {
		System.out.println("Weiterbutton pressed!");
		this.c1.setSceneToV_AssetType();

	}

	@FXML
	private void handleVorleaufigUpdaten() throws IOException {
		System.out.println("VorleaufigUpdaten pressed!");
		m1.updatePB_PORTF_SHAREmit12();
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
//		s1.setName("My portfolio");	
//		s1.setName("My portfolio");
		chart.getData().add(s1);
		chart.getData().add(s2);
		chart.setAnimated(false);

		final int dayRange = 60;
		LocalDate today = LocalDate.now();
		Random rng = new Random();

		// m1.foreachmap.get(map.keySet().toArray()[0])
		// Aktie a1 = m1.currentPortfoliosAktienMitKursen
		// .get(new Integer(6));
		// HashMap<Date, Double> h1= a1.getHashOfHalfYear();
		// Set<Date> d1 = h1.keySet();
		// Date dd1 = (Date) d1.toArray()[0];
		for (Map.Entry<Date, Double> entry : m1.currentPortfoliosAktienMitKursen
				.get(new Integer(Integer.valueOf(
						m1.selectedCurrentSharesString.substring(0, m1.selectedCurrentSharesString.indexOf(" ")))))
				.getHashOfHalfYear().entrySet()) {
			Date key = entry.getKey();
			Double value = entry.getValue();
			LocalDate dateA = Instant.ofEpochMilli(key.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
			String formattedDateA = formatter.format(dateA);
			addData(data, formattedDateA, value);
			s1.setName(m1.currentPortfoliosAktienMitKursen.get(
					new Integer(Integer.valueOf(
						m1.selectedCurrentSharesString.substring(0, m1.selectedCurrentSharesString.indexOf(" ")))
				)).getName());	
			// do what you have to do here
			// In your case, another loop.
		}

		// Date input = (Date) m1.currentPortfoliosAktienMitKursen
		// .get(new Integer(Integer.valueOf(m1.selectedCurrentSharesString.substring(0,
		// m1.selectedCurrentSharesString.indexOf(" ")))))
		// .getHashOfHalfYear()
		// .keySet()
		// .toArray()[0];//new Date();
		// LocalDate date =Instant.ofEpochMilli(input.getTime())
		// .atZone(ZoneId.systemDefault())
		// .toLocalDate();
		// String formattedDate = formatter.format(date);
		// double value = rng.nextDouble() ;
		// addData(data, formattedDate, value);

		for (int i = 0; i < 20; i++) {
			LocalDate date7 = today.minusDays(rng.nextInt(dayRange));
			String formattedDate = formatter.format(date7);
			double value = rng.nextDouble();

			addData(data7, formattedDate, value);
		}

		DatePicker datePicker = new DatePicker();
		Spinner<Double> valuePicker = new Spinner<>(0.0, 1.0, 0, 0.1);
		valuePicker.setEditable(true);

		Button addButton = new Button("Add");
		addButton.setOnAction(e -> addData(data, formatter.format(datePicker.getValue()), valuePicker.getValue()));

		HBox controls = new HBox(5, datePicker, valuePicker, addButton);
		controls.setAlignment(Pos.CENTER);
		controls.setPadding(new Insets(5));

		this.bp1.setCenter(chart);
	//	this.bp1.setBottom(controls);
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