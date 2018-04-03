package p2_view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.StackPane;
import p0_model.Model;
import p0_model.db_objects.AnlageKlasse;
import p0_model.db_objects.Portfolio;
import p0_model.db_objects.PortfolioTableEntry;
import p1_controller.Controller;
import p2_view.chart_objects.PieChart3D;

public class VC_AssetClasses {

	public Model m1;
	public Controller c1;

	// Nicht FXML!
	private SwingNode sn1;

	@FXML
	private ScatterChart<Double, Double> sc;
	@FXML
	private TableView<PortfolioTableEntry> pTable;
	@FXML
	private TableColumn<PortfolioTableEntry, Number> IdColumn;
	@FXML
	private TableColumn<PortfolioTableEntry, String> nameColumn;

	@FXML
	private StackPane paneWithSwing;

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
	public VC_AssetClasses() {
	}

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
	}

	public void updateData() {
		// Tabellen-Elemente (Nur während Testing visible==true)
		pTable.setItems(m1.allPortfolioTE);
		IdColumn.setCellValueFactory(cellData -> cellData.getValue().getPortfolio_id());
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
		pTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> handleSaveSelectedAndUseSelectedToFill(newValue));

		handleSaveSelectedAndUseSelectedToFill(null);

		List<TextField> tList1 = new ArrayList<TextField>();
		tList1.add(this.newShareDistTextField);
		tList1.add(this.newCommDistTextField);
		tList1.add(this.newCurrDistTextField);
		tList1.add(this.newEstateDistTextField);
		tList1.add(this.newBondDistTextField);
		for (TextField f1 : tList1) {
			f1.focusedProperty().addListener(new ChangeListener<Boolean>() {
				@Override
				public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue,
						Boolean newPropertyValue) {
					if (newPropertyValue) {

					} else {

						updatePieChartScatteredChartAndFillSIGMAR_FULL();
					}
				}
			});

			//Textfelder
			newNameTextField.setText("" + m1.usedPortfolio.getName());
			newInvestmentTextField.setText("" + m1.usedPortfolio.getCapital());
			;
			newShareDistTextField.setText("" + m1.usedPortfolio.getShare_dist());
			newCommDistTextField.setText("" + m1.usedPortfolio.getComm_dist());
			newCurrDistTextField.setText("" + m1.usedPortfolio.getCurr_dist());
			newEstateDistTextField.setText("" + m1.usedPortfolio.getEstate_dist());
			newBondDistTextField.setText("" + m1.usedPortfolio.getBond_dist());

			f1.setOnAction((ActionEvent event) -> {
				this.weiter.requestFocus();
			});
		}
		
		//Piechart und ScatteredChart
		sn1 = new SwingNode();
		paneWithSwing.getChildren().add(sn1);
		updatePieChartScatteredChartAndFillSIGMAR_FULL();
	}

	public void updatePieChartScatteredChartAndFillSIGMAR_FULL() {
		if (Double.parseDouble(this.newShareDistTextField.getText())
				+ Double.parseDouble(this.newCommDistTextField.getText())
				+ Double.parseDouble(this.newCurrDistTextField.getText())
				+ Double.parseDouble(this.newEstateDistTextField.getText())
				+ Double.parseDouble(this.newBondDistTextField.getText()) != 100) {
		} else {

			DefaultPieDataset result = new DefaultPieDataset();
			result.setValue("Aktien", Double.parseDouble(this.newShareDistTextField.getText()));
			result.setValue("Rohstoffe", Double.parseDouble(this.newCommDistTextField.getText()));
			result.setValue("Liquide Mittel", Double.parseDouble(this.newCurrDistTextField.getText()));
			result.setValue("Immobilien", Double.parseDouble(this.newEstateDistTextField.getText()));
			result.setValue("Anleihen", Double.parseDouble(this.newBondDistTextField.getText()));

			JFreeChart chart = ChartFactory.createPieChart3D("Assetklassen",
					result, 
					true,
					true, false);

			PiePlot3D plot = (PiePlot3D) chart.getPlot();
			plot.setStartAngle(290);
			plot.setDirection(Rotation.CLOCKWISE);
			plot.setForegroundAlpha(0.5f);
			plot.setNoDataMessage("No data to display");
			plot.setLabelGenerator(new PieChart3D.CustomLabelGenerator());

			ChartPanel panel = new ChartPanel(chart);
			panel.setMouseWheelEnabled(true);
			sn1.setContent(panel);

			/////////////////////////
			// Update SigmaRFull
			/////////////////////////
			HashMap<Integer, Double> dist_list = new HashMap<Integer, Double>();
			dist_list.put(new Integer(1), Double.parseDouble(this.newShareDistTextField.getText()));
			dist_list.put(new Integer(2), Double.parseDouble(this.newCommDistTextField.getText()));
			dist_list.put(new Integer(3), Double.parseDouble(this.newCurrDistTextField.getText()));
			dist_list.put(new Integer(4), Double.parseDouble(this.newBondDistTextField.getText()));
			dist_list.put(new Integer(5), Double.parseDouble(this.newEstateDistTextField.getText()));
			int i = 1;
			double sigma_full = 0.0;
			double risk_full = 0.0;
			for (AnlageKlasse ac1 : m1.assetclasses.values()) {
				sigma_full = sigma_full + (1.0 / 100.0) * ac1.getSigma() * dist_list.get(i);
				risk_full = risk_full + (1.0 / 100.0) * ac1.getRisk() * dist_list.get(i);

				i = i + 1;
				// ;
			}
			m1.usedPortfolio.setRisk_full(risk_full);
			m1.usedPortfolio.setSigma_full(sigma_full);
			m1.updateDB_PORTFOLIO_updatePortfolioTE(m1.usedPortfolio);
			m1.loadSelectedPortfolioData();

			////////////////////////////////////////////
			// FILL SCATTERCHART
			////////////////////////////////////////////
			sc.setTitle("Risiko-Rendite-Diagramm der Assetklassen");
			// 5x Assetclass-Daten einfüllen
			ObservableList<XYChart.Series<Double, Double>> scatterGraphSeries = FXCollections.observableArrayList();
			for (AnlageKlasse ac1 : m1.assetclasses.values()) {
				XYChart.Series<Double, Double> series1 = new XYChart.Series<Double, Double>();
				series1.getData().add(new XYChart.Data<Double, Double>(ac1.getSigma()/100, ac1.getRisk()/100));
				series1.setName(ac1.getName());
				scatterGraphSeries.add(series1);
			}
			// 1x GesamtPortfolio-Daten einfüllen
			XYChart.Series<Double, Double> series1 = new XYChart.Series<Double, Double>();
			series1.getData().add(new XYChart.Data<Double, Double>(m1.usedPortfolio.getSigma_full()/100,
					m1.usedPortfolio.getRisk_full()/100));
			series1.setName(m1.usedPortfolio.getName());
			scatterGraphSeries.add(series1);

			sc.setData(scatterGraphSeries);
		}
	}
	/////////////////////////////////////////////////////
	// Handle-Methoden
	/////////////////////////////////////////////////////

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
	private void handleUnfertigesPortfolioErstellen() throws IOException {
		try {
			boolean alreadyExists = m1.yourPortfolioTE.stream()
					.anyMatch(t -> t.get2Name().equals(this.newNameTextField.getText()));
			if (alreadyExists == true) {
//				Alert alert = new Alert(AlertType.INFORMATION);
//				alert.setTitle("Information Dialog");
//				alert.setHeaderText("");
//				alert.setContentText("Der Name des Portfolios wird bereits verwendet.");
//				alert.showAndWait();
				m1.usedPortfolio.setPortfolio_id(m1.usedPortfolio.getPortfolio_id());
				m1.usedPortfolio.setName(this.newNameTextField.getText());
				m1.usedPortfolio.setUser_id(m1.loggedInUser_id);
				m1.usedPortfolio.setInvestment(Double.parseDouble(this.newInvestmentTextField.getText()));
				m1.usedPortfolio.setShare_dist(Double.parseDouble(this.newShareDistTextField.getText()));
				m1.usedPortfolio.setComm_dist(Double.parseDouble(this.newCommDistTextField.getText()));
				m1.usedPortfolio.setCurr_dist(Double.parseDouble(this.newCurrDistTextField.getText()));
				m1.usedPortfolio.setEstate_dist(Double.parseDouble(this.newEstateDistTextField.getText()));
				m1.usedPortfolio.setBond_dist(Double.parseDouble(this.newBondDistTextField.getText()));
			} else {

				PortfolioTableEntry p1 = new PortfolioTableEntry(m1.usedPortfolio.getPortfolio_id(),
						this.newNameTextField.getText(), m1.loggedInUser_id,
						Double.parseDouble(this.newInvestmentTextField.getText()),
						Double.parseDouble(this.newShareDistTextField.getText()),
						Double.parseDouble(this.newCommDistTextField.getText()),
						Double.parseDouble(this.newCurrDistTextField.getText()),
						Double.parseDouble(this.newEstateDistTextField.getText()),
						Double.parseDouble(this.newBondDistTextField.getText()));

				Portfolio portf1 = new Portfolio(m1.usedPortfolio.getPortfolio_id(), this.newNameTextField.getText(),
						m1.loggedInUser_id, Double.parseDouble(this.newInvestmentTextField.getText()),
						Double.parseDouble(this.newShareDistTextField.getText()),
						Double.parseDouble(this.newCommDistTextField.getText()),
						Double.parseDouble(this.newCurrDistTextField.getText()),
						Double.parseDouble(this.newEstateDistTextField.getText()),
						Double.parseDouble(this.newBondDistTextField.getText()), 0, 0, 0, 0, 0, 0);

				// 0 Liquide, 1 Anleihen, 2 Aktien, 3 Rohstoffe, 4 Immobilien
				AnlageKlasse a0 = m1.assetclasses.get(new Integer(0));
				Double sigma0 = a0.getSigma();
				Double risk0 = a0.getRisk();
				AnlageKlasse a1 = m1.assetclasses.get(new Integer(1));
				Double sigma1 = a1.getSigma();
				Double risk1 = a1.getRisk();
				AnlageKlasse a2 = m1.assetclasses.get(new Integer(2));
				Double sigma2 = a2.getSigma();
				Double risk2 = a2.getRisk();
				AnlageKlasse a3 = m1.assetclasses.get(new Integer(3));
				Double sigma3 = a3.getSigma();
				Double risk3 = a3.getRisk();
				AnlageKlasse a4 = m1.assetclasses.get(new Integer(4));
				Double sigma4 = a4.getSigma();
				Double risk4 = a4.getRisk();
				m1.usedPortfolio.setSigma_asset_preview((portf1.getCurr_dist() * sigma0 + portf1.getBond_dist() * sigma1
						+ portf1.getShare_dist() * sigma2 + portf1.getComm_dist() * sigma3
						+ portf1.getEstate_dist() * sigma4) / 100.0);
				m1.usedPortfolio.setRisk_asset_preview(
						(portf1.getCurr_dist() * risk0 + portf1.getBond_dist() * risk1 + portf1.getShare_dist() * risk2
								+ portf1.getComm_dist() * risk3 + portf1.getEstate_dist() * risk4) / 100.0);

				// m1.usedPortfolio.setRisk_asset_preview(risk_asset_preview);
				if (p1.get2Name() == "") {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText("");
					alert.setContentText("Das Namensfeld kann nicht leer bleiben.");
					alert.showAndWait();
					return;
				}
				if (p1.get2Share_dist() + p1.get2Comm_dist() + p1.get2Curr_dist() + p1.get2Estate_dist()
						+ p1.get2Bond_dist() != 100) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText("");
					alert.setContentText("Fehler. Die Summe der Prozentwerte muss 100% sein..");
					alert.showAndWait();
					return;
				}

				m1.yourPortfolioTE.add(p1);
				m1.allPortfolioTE.add(p1);
				m1.allPortfolios.put(portf1.getPortfolio_id(), portf1);
				m1.usedPortfolio = portf1;
				m1.updateDB_PORTFOLIO_updatePortfolioTE(portf1);
				updateData();
				
				
			}
			this.c1.setSceneToV_Shares();
		} catch (java.lang.NumberFormatException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("");
			alert.setContentText("Fehler. Der Inhalt der Textviews muss überprüft werden.");
			alert.showAndWait();
		}
	}

	@FXML
	private void handleWeiter() throws IOException {
		handleUnfertigesPortfolioErstellen();
		//Scene-Wechsel in der "unfertige...-Methode

	}

	@FXML
	private void handleZurueck() throws IOException {
		this.c1.setSceneToV_Portfolios();
	}

}