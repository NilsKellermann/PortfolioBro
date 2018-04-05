package p2_view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.application.Application;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import java.awt.event.FocusEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.StackPane;
import p0_model.Model;
import p0_model.db_objects.AnlageKlasse;
import p0_model.db_objects.Portfolio;
import p0_model.db_objects.PortfolioTableEntry;
import p1_controller.Controller;
import p2_view.chart_objects.PieChart3D;




public class VC_CompletePortfolioCompare {

	public Model m1;
	public Controller c1;
	private SwingNode sn2;
	private SwingNode sn3;
	
	@FXML
	private StackPane paneWithSwing1;
	
	@FXML
	private StackPane paneWithSwing2;

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
	
	@FXML 
	private ListView<String> listview1shares;
	@FXML 
	private ListView<String> listview2shares;
	@FXML 
	private ListView<String> listview1comm;
	@FXML 
	private ListView<String> listview2comm;
	
	protected List<String> simpleStringList1 ;
	protected ListProperty<String> listProperty1 = new SimpleListProperty<>();
    protected List<String> simpleStringList2 ;
	protected ListProperty<String> listProperty2 = new SimpleListProperty<>();
	protected List<String> simpleStringList3 ;
	protected ListProperty<String> listProperty3 = new SimpleListProperty<>();
    protected List<String> simpleStringList4 ;
	protected ListProperty<String> listProperty4 = new SimpleListProperty<>();
	
	@FXML
	private Label labelrendite1;
	@FXML
	private Label labelrendite2;
	@FXML
	private Label labelrisiko1;
	@FXML
	private Label labelrisiko2;
	
	private Double aktien1;
	private Double rohstoffe1;
	private Double liquidemittel1;
	private Double anleihen1;
	private Double immobilien1;
	private Double aktien2;
	private Double rohstoffe2;
	private Double liquidemittel2;
	private Double anleihen2;
	private Double immobilien2;

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
	
	public void getData1 (PortfolioTableEntry portf1) {
		if (portf1 != null) {
			m1.selectedPortfolio = portf1;
			aktien1 =  m1.selectedPortfolio.get2Share_dist();
			rohstoffe1 = m1.selectedPortfolio.get2Comm_dist();
			anleihen1 = m1.selectedPortfolio.get2Bond_dist();
			liquidemittel1 = m1.selectedPortfolio.get2Curr_dist();
			immobilien1 = m1.selectedPortfolio.get2Estate_dist();
					
		} else {
			//
			m1.selectedPortfolio = null;
		}
	}
	
	public void getData2 (PortfolioTableEntry portf2) {
		if (portf2 != null) {
			m1.selectedPortfolio = portf2;
			aktien2 =  m1.selectedPortfolio.get2Share_dist();
			rohstoffe2 = m1.selectedPortfolio.get2Comm_dist();
			anleihen2 = m1.selectedPortfolio.get2Bond_dist();
			liquidemittel2 = m1.selectedPortfolio.get2Curr_dist();
			immobilien2 = m1.selectedPortfolio.get2Estate_dist();
					
		} else {
			//
			m1.selectedPortfolio = null;
		}
	}
	
	
	public void updateData() {
		
		sn2 = new SwingNode();
		paneWithSwing1.getChildren().add(sn2);
		
		sn3 = new SwingNode();
		paneWithSwing2.getChildren().add(sn3);
		
		
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

	
		
	
	public void updatePieChart1() {
		
			DefaultPieDataset result = new DefaultPieDataset();
			result.setValue("Aktien", aktien1);
			result.setValue("Rohstoffe", rohstoffe1);
			result.setValue("Liquide Mittel", liquidemittel1);
			result.setValue("Immobilien", immobilien1);
			result.setValue("Anleihen", anleihen1);

			JFreeChart chart = ChartFactory.createPieChart3D("Verteilung auf die Anlageklassen", // chart title
					result, // data
					true, // include legend
					true, false);

			PiePlot3D plot = (PiePlot3D) chart.getPlot();
			plot.setStartAngle(290);
			plot.setDirection(Rotation.CLOCKWISE);
			plot.setForegroundAlpha(0.5f);
			plot.setNoDataMessage("No data to display");
			plot.setLabelGenerator(new PieChart3D.CustomLabelGenerator());

			JFreeChart chart1 = chart;
			JPanel chartPanel = new ChartPanel(chart1);
			chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));

			ChartPanel panel = new ChartPanel(chart);
			panel.setMouseWheelEnabled(true);
			sn2.setContent(panel);
			System.out.println("Hallo");
		}
	
	
	
	public void updatePieChart2() {
		
		DefaultPieDataset result = new DefaultPieDataset();
		result.setValue("Aktien", aktien2);
		result.setValue("Rohstoffe", rohstoffe2);
		result.setValue("Liquide Mittel", liquidemittel2);
		result.setValue("Immobilien", immobilien2);
		result.setValue("Anleihen", anleihen2);
		
		JFreeChart chart = ChartFactory.createPieChart3D("Verteilung auf die Anlageklassen", // chart title
				result, // data
				true, // include legend
				true, false);

		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setStartAngle(290);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.5f);
		plot.setNoDataMessage("No data to display");
		plot.setLabelGenerator(new PieChart3D.CustomLabelGenerator());

		JFreeChart chart1 = chart;
		JPanel chartPanel = new ChartPanel(chart1);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));

		ChartPanel panel = new ChartPanel(chart);
		panel.setMouseWheelEnabled(true);
		sn3.setContent(panel);
		System.out.println("Hallo");
	}


	public void displaysharesandcommodities1 () {
		
	//////verwendete Rohstoffe eintragen////
/*	simpleStringList2= new ArrayList<>();
	m1.currentPortfoliosRohstoffeMitKursen.forEach( (k,v) -> 
			{if(m1.currentPortfoliosRohstoffeProzente.get(k)!=0)
				{
					System.out.println(v.getShare_id() + " " + v.getName() + "     (" +m1.currentPortfoliosRohstoffeProzente.get(k) + "% )");
					simpleStringList2.add(v.getShare_id() + " " + v.getName() + "     (" +m1.currentPortfoliosRohstoffeProzente.get(k) + "% )");
				}
			}
		);
	listview1comm.itemsProperty().bind(listProperty2);
	listProperty2.set(FXCollections.observableArrayList(simpleStringList2));

	///////verwendete Aktien eintragen /////
	simpleStringList1= new ArrayList<>();
	m1.currentPortfoliosAktienMitKursen.forEach( (k,v) -> 
			{if(m1.currentPortfoliosAktienProzente.get(k)!=0)
				{
					System.out.println(v.getShare_id() + " " + v.getName() + "     (" +m1.currentPortfoliosAktienProzente.get(k) + "% )");
					simpleStringList1.add(v.getShare_id() + " " + v.getName() + "     (" +m1.currentPortfoliosAktienProzente.get(k) + "% )");
				}
			}
		);
	listview1shares.itemsProperty().bind(listProperty1);
	listProperty1.set(FXCollections.observableArrayList(simpleStringList1));
*/	}

	
	public void displaysharesandcommodities2() {
		
	//////verwendete Rohstoffe eintragen////
/*		simpleStringList3= new ArrayList<>();
		m1.currentPortfoliosRohstoffeMitKursen.forEach( (k,v) -> 
				{if(m1.currentPortfoliosRohstoffeProzente.get(k)!=0)
					{
						System.out.println(v.getShare_id() + " " + v.getName() + "     (" +m1.currentPortfoliosRohstoffeProzente.get(k) + "% )");
						simpleStringList3.add(v.getShare_id() + " " + v.getName() + "     (" +m1.currentPortfoliosRohstoffeProzente.get(k) + "% )");
					}
				}
			);
		listview2comm.itemsProperty().bind(listProperty2);
		listProperty3.set(FXCollections.observableArrayList(simpleStringList3));

		///////verwendete Aktien eintragen /////
		simpleStringList4= new ArrayList<>();
		m1.currentPortfoliosAktienMitKursen.forEach( (k,v) -> 
				{if(m1.currentPortfoliosAktienProzente.get(k)!=0)
					{
						System.out.println(v.getShare_id() + " " + v.getName() + "     (" +m1.currentPortfoliosAktienProzente.get(k) + "% )");
						simpleStringList4.add(v.getShare_id() + " " + v.getName() + "     (" +m1.currentPortfoliosAktienProzente.get(k) + "% )");
					}
				}
			);
		listview2shares.itemsProperty().bind(listProperty4);
		listProperty4.set(FXCollections.observableArrayList(simpleStringList4));
	
*/	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Handle-Methoden
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private void handleSaveSelectedAndUseSelectedToFill1(PortfolioTableEntry portf1) {
		if (portf1 != null) {
			m1.selectedPortfolio = portf1;
			nameLabel1.setText("" + m1.selectedPortfolio.get2Capital());
			labelrendite1.setText("" + m1.selectedPortfolio.get2Sigma_full());
			labelrisiko1.setText(""+ m1.selectedPortfolio.get2Risk_comm());
			
		} else {
			//
			m1.selectedPortfolio = null;
			nameLabel1.setText("Portfolio auswählen");
		}
		getData1(portf1);
		updatePieChart1();
		displaysharesandcommodities1();
		
	}
	
	private void handleSaveSelectedAndUseSelectedToFill2(PortfolioTableEntry portf2) {
		if (portf2 != null) {
			m1.selectedPortfolio = portf2;
			nameLabel2.setText("" + m1.selectedPortfolio.get2Capital());
			labelrendite2.setText("" + m1.selectedPortfolio.get2Sigma_full());
			labelrisiko2.setText("" + m1.selectedPortfolio.get2Risk_full());
			
		} else {
			//
			m1.selectedPortfolio = null;
			nameLabel2.setText("Portfolio auswählen");
	}	
		getData2(portf2);
		updatePieChart2();
		displaysharesandcommodities2();
	}
	
	
	@FXML
	private void handleZurueck() throws IOException {
		System.out.println("Weiterbutton pressed!");
		this.c1.setSceneToV_Portfolios();
	}


		
	
}