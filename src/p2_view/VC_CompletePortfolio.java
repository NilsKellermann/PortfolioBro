package p2_view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.FocusEvent;
import javax.swing.JPanel;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import p0_model.Model;
import p0_model.db_objects.AnlageKlasse;
import p0_model.db_objects.Portfolio;
import p0_model.db_objects.PortfolioTableEntry;
import p0_model.db_objects.RohstoffTableEntry;
import p1_controller.Controller;
import p2_view.chart_objects.PieChart3D;




public class VC_CompletePortfolio {

	public Model m1;
	public Controller c1;
	private SwingNode sn1;
	
	@FXML 
	private Button go;
	
	@FXML 
	private TextField textfieldname;
	
	@FXML 
	private TextField textfieldid;
	
	@FXML 
	private TextField textfieldinvestment;
	
	@FXML 
	private TextField textfieldshares;
	
	@FXML 
	private TextField textfieldcommodities;
	
	@FXML 
	private TextField textfieldrealestates;
	
	@FXML 
	private TextField textfieldbonds;
	
	@FXML 
	private TextField textfieldcash;
	
	@FXML
	private StackPane paneWithSwing;
	
	@FXML
	private TextField gesamtrendite;
	
	@FXML
	private TextField gesamtrisiko;
	
	@FXML
	private TextField immorendite;
	
	@FXML
	private TextField immorisiko;
	
	@FXML
	private TextField lmittelrendite;
	
	@FXML
	private TextField lmittelrisiko;
	
	@FXML
	private TextField rohstofferendite;
	
	@FXML
	private TextField rohstofferisiko;
	
	@FXML
	private TextField aktienrendite;
	
	@FXML
	private TextField aktienrisiko;
	
	@FXML
	private TextField anleihenrendite;
	
	@FXML
	private TextField anleihenrisiko;


	@FXML
	private ListView<String> listView1;
	@FXML
	private ListView<String> listView2;
	
	protected List<String> simpleStringList ;
	protected ListProperty<String> listProperty = new SimpleListProperty<>();
    protected List<String> simpleStringList2 ;
	protected ListProperty<String> listProperty2 = new SimpleListProperty<>();


	
	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	
	public VC_CompletePortfolio()  {
		
	}

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	
    private void initialize() {
		
	}
		
	
	
		
	
	
	public void updateData() {
		

		//Berechnung Gesamtrendite und Gesamtrisiko des Portfolios
		
		m1.usedPortfolio.setRisk_full( 
				(double)Math.round((m1.usedPortfolio.getShare_dist()/100 * m1.usedPortfolio.getRisk_share() + 
				m1.usedPortfolio.getComm_dist()/100 * m1.usedPortfolio.getRisk_comm() + 
				m1.usedPortfolio.getEstate_dist()/100 * m1.assetclasses.get(4).getRisk() + 
 				m1.usedPortfolio.getBond_dist()/100 * m1.assetclasses.get(1).getRisk() + 
				m1.usedPortfolio.getCurr_dist()/100 * m1.assetclasses.get(0).getRisk()) *1000)/1000);
		
		m1.usedPortfolio.setYield_full( 
				(double)Math.round(( m1.usedPortfolio.getShare_dist()/100 *  m1.usedPortfolio.getYield_share() + 
				m1.usedPortfolio.getComm_dist()/100 *  m1.usedPortfolio.getYield_comm() +
				m1.usedPortfolio.getEstate_dist() /100*  m1.assetclasses.get(4).getSigma() + 
		 		m1.usedPortfolio.getBond_dist()/100 *  m1.assetclasses.get(1).getSigma() + 
				m1.usedPortfolio.getCurr_dist()/100 *  m1.assetclasses.get(0).getSigma()) *1000)/1000 );
				
				
			
		
		
		
		
		
		
		
		sn1 = new SwingNode();
		paneWithSwing.getChildren().add(sn1);

		
	textfieldname.setText("" + m1.usedPortfolio.getName());
	textfieldinvestment.setText("" + m1.usedPortfolio.getCapital());
	textfieldshares.setText("" + m1.usedPortfolio.getShare_dist());
	textfieldcommodities.setText("" + m1.usedPortfolio.getComm_dist());
	textfieldrealestates.setText("" + m1.usedPortfolio.getEstate_dist());
	textfieldbonds.setText("" + m1.usedPortfolio.getBond_dist());
	textfieldcash.setText("" + m1.usedPortfolio.getCurr_dist());	
	


	aktienrendite.setText("" + m1.usedPortfolio.getYield_share());
	aktienrisiko.setText("" + m1.usedPortfolio.getRisk_share());
	rohstofferendite.setText("" + m1.usedPortfolio.getYield_comm());
	rohstofferisiko.setText("" + m1.usedPortfolio.getRisk_comm());
	immorendite.setText("" + m1.assetclasses.get(4).getSigma());
	immorisiko.setText("" + m1.assetclasses.get(4).getRisk());
	lmittelrendite.setText("" + m1.assetclasses.get(0).getSigma());
	lmittelrisiko.setText("" + m1.assetclasses.get(0).getRisk());
	anleihenrendite.setText("" + m1.assetclasses.get(1).getSigma());
	anleihenrisiko.setText("" + m1.assetclasses.get(1).getRisk());

	
	gesamtrendite.setText("" + m1.usedPortfolio.getYield_full());
	gesamtrisiko.setText("" + m1.usedPortfolio.getRisk_full());


	//Aktienendstand
		simpleStringList= new ArrayList<>();
		m1.currentPortfoliosAktienMitKursen.forEach( (k,v) -> 
				{
					if(m1.currentPortfoliosAktienProzente.get(k)>0)
						{
							System.out.println(v.getShare_id() + " " + v.getName() + "     (" +m1.currentPortfoliosAktienProzente.get(k) + "% )");
							simpleStringList.add(v.getShare_id() + " " + v.getName() + "     (" +m1.currentPortfoliosAktienProzente.get(k) + "% )");
						}
					//else m1.currentPortfoliosAktienMitKursen.remove(k);
				}
			);
		listView1.itemsProperty().bind(listProperty);
		listProperty.set(FXCollections.observableArrayList(simpleStringList));
				
		//Rohstoffendstand
		simpleStringList2= new ArrayList<>();
		m1.currentPortfoliosRohstoffeMitKursen.forEach( (k,v) -> 
				{
					if(m1.currentPortfoliosRohstoffeProzente.get(k)>0)
						{
							System.out.println(v.getShare_id() + " " + v.getName() + "     (" +m1.currentPortfoliosRohstoffeProzente.get(k) + "% )");
							simpleStringList2.add(v.getShare_id() + " " + v.getName() + "     (" +m1.currentPortfoliosRohstoffeProzente.get(k) + "% )");
						}
					//else m1.currentPortfoliosRohstoffeMitKursen.remove(k);				
				}
			);
		listView2.itemsProperty().bind(listProperty2);
		listProperty2.set(FXCollections.observableArrayList(simpleStringList2));
	
	
		updatePieChart();
	
	}
		
	
	
	public void updatePieChart() {
		if (Double.parseDouble(this.textfieldshares.getText())
				+ Double.parseDouble(this.textfieldcommodities.getText())
				+ Double.parseDouble(this.textfieldcash.getText())
				+ Double.parseDouble(this.textfieldrealestates.getText())
				+ Double.parseDouble(this.textfieldbonds.getText()) != 100) {
			System.out.println("Die Summe der Prozentwerte muss 100% sein.");
		} else {

			DefaultPieDataset result = new DefaultPieDataset();
			result.setValue("Aktien", Double.parseDouble(this.textfieldshares.getText()));
			result.setValue("Rohstoffe", Double.parseDouble(this.textfieldcommodities.getText()));
			result.setValue("Liquide Mittel", Double.parseDouble(this.textfieldcash.getText()));
			result.setValue("Immobilien", Double.parseDouble(this.textfieldrealestates.getText()));
			result.setValue("Anleihen", Double.parseDouble(this.textfieldbonds.getText()));
			
			sn1 = new SwingNode();
			paneWithSwing.getChildren().add(sn1);
			
			
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
			sn1.setContent(panel);
			System.out.println("Hallo");
		}
	
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Handle-Methoden
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	@FXML
	private void handleGo() throws IOException {
		System.out.println("Weiterbutton pressed!");
		this.c1.setSceneToV_CompletePortfolioCompare();
	}
	
	
  
}
	
