package p2_view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.StackPane;
import p0_model.Model;
import p0_model.db_objects.AnlageKlasse;
import p0_model.db_objects.PortfolioTableEntry;
import p1_controller.Controller;

public class VC_Aktienanalyse {

	public Model m1;
	public Controller c1;

	// Nicht FXML!
	private SwingNode sn1;
	private SwingNode sn2;
	
	@FXML
	private StackPane paneWithSwing1;
	@FXML
	private StackPane paneWithSwing2;
	@FXML
	private TabPane tabPane1;
	
	@FXML
	private TextField aversionscoefficient;
	@FXML
	private ChoiceBox<String> functiontyp;
	ObservableList<String> functiontypList = FXCollections.observableArrayList("Linear","Quadratisch");
	
	@FXML
	private ListView<String> listView1;
	@FXML
	private ListView<String> listView2;
	@FXML
	private TextField textFieldPercent;

    protected List<String> simpleStringList ;
	protected ListProperty<String> listProperty = new SimpleListProperty<>();
    protected List<String> simpleStringList2 ;
	protected ListProperty<String> listProperty2 = new SimpleListProperty<>();
	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	public VC_Aktienanalyse() {
	}

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		functiontyp.setItems(functiontypList);
		functiontyp.setValue("Quadratisch");
	}
	
	public void updateData() {

		simpleStringList= new ArrayList<>();
		if(m1.analyseErgebnis.isEmpty()) {m1.currentPortfoliosAktienMitKursen.forEach( (k,v) -> m1.analyseErgebnis.put(k, new Boolean(true)));
		}
		
		m1.currentPortfoliosAktienMitKursen.forEach( (k,v) -> 
		{if(m1.analyseErgebnis.isEmpty() || (m1.analyseErgebnis.containsKey(k) ? m1.analyseErgebnis.get(k).booleanValue() : false) == true) //0.0 != m1.currentPortfoliosAktienProzente.get(k))
		{simpleStringList.add(v.getShare_id() + " " + v.getName() + "     (" +m1.currentPortfoliosAktienProzente.get(k) + "% )");}});

	   // simpleStringList.add("CNH");

		listView1.itemsProperty().bind(listProperty);
		listProperty.set(FXCollections.observableArrayList(simpleStringList));        

		listView1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		        System.out.println("ListView selection changed from oldValue = " 
		                + oldValue + " to newValue = " + newValue);
		        m1.selectedCurrentSharesStringAnalyse1 = newValue;
		    }
		});
		//Liste f�llen		
		simpleStringList2= new ArrayList<>();
		m1.currentPortfoliosAktienMitKursen.forEach( (k,v) -> 
		{if(! m1.analyseErgebnis.isEmpty() && (m1.analyseErgebnis.get(k) == null ||m1.analyseErgebnis.get(k).booleanValue() == false))
		{simpleStringList2.add(v.getShare_id() + " " + v.getName() + "     (" +m1.currentPortfoliosAktienProzente.get(k) + "% )");}});

	    //simpleStringList2.add("CNH");

		listView2.itemsProperty().bind(listProperty2);
		listProperty2.set(FXCollections.observableArrayList(simpleStringList2));        

		listView2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		        System.out.println("ListView2 selection changed from oldValue = " 
		                + oldValue + " to newValue = " + newValue);
		        m1.selectedCurrentSharesStringAnalyse1 = newValue;
		    }
		});
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Handle-Methoden
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private void handleSaveSelectedAndUseSelectedToFill(PortfolioTableEntry portf1) {
		if (portf1 != null) {
			m1.selectedPortfolio = portf1;
			textFieldPercent.setText("" + m1.selectedPortfolio.get2Portfolio_id());
		
		} else {
			//
			m1.selectedPortfolio = null;

			textFieldPercent.setText("");

		}
	}

	@FXML
	private void handleDelete() {
		System.out.println("DeleteButton Pressed!");

	}

	@FXML
	private void handleWeiter() throws IOException {
		System.out.println("Weiterbutton pressed!");
		
		//zusammenrechnen
		List<Double> doubleList= new ArrayList<>();
	if(!m1.currentPortfoliosAktienProzente.isEmpty()) {

		m1.currentPortfoliosAktienProzente.forEach( (k,v) -> {if (m1.analyseErgebnis.get(k) == true) {doubleList.add(new Double(v.doubleValue()));}});
	}
		double prozentSumme = doubleList.stream().collect(Collectors.summingDouble(Double::doubleValue));;
		if(prozentSumme == 100.0) {
			//in db speichern und aus datenbank rausl�schen die aussortierten
		//TODOOOO	//////////777777777777777777777777777777
		////////////////////////////////////////
		///////////////////////////////////////
			
			//aussortierte Aktien-Prozentwerte auf 0.0 setzen
			m1.analyseErgebnis.forEach((i,boolean1) -> {if(boolean1==false)m1.currentPortfoliosAktienProzente.put(i, 0.0);});
			
			//Alle Aktien durchlaufen und average Sigma und Rendite berechnen.
			List<Double> risk_shareL = new ArrayList<Double>();
			List<Double> sigma_shareL = new ArrayList<Double>();
			List<Double> risk_commL = new ArrayList<Double>();
			List<Double> sigma_commL = new ArrayList<Double>();
			m1.currentPortfoliosAktienMitKursen.forEach ((i,aktie1) -> {
				risk_shareL.add(aktie1.getRisk() * m1.currentPortfoliosAktienProzente.get(i) ); //ohne Teilen durch 100. Im Exceldokument lagen bereits Prozentwerte vor.
				sigma_shareL.add(aktie1.getSigma() * m1.currentPortfoliosAktienProzente.get(i) );//ohne Teilen durch 100. Im Exceldokument lagen bereits Prozentwerte vor.

			} );
			Double sigma_shareValue = sigma_shareL.stream().reduce(0.0, Double::sum);
			Double risk_shareValue = risk_shareL.stream().reduce(0.0, Double::sum);
			m1.usedPortfolio.setYield_share((double)Math.round(sigma_shareValue*1000)/1000);
			m1.usedPortfolio.setRisk_share((double)Math.round(risk_shareValue*1000)/1000);
			m1.updatePB_PORTF_SHAREwithPercents();
			this.c1.setSceneToV_Commodities();}

		else {
			
			if(m1.currentPortfoliosAktienMitKursen.isEmpty()) {
			this.c1.setSceneToV_Commodities();} 
			else {
			// Show a predefined Warning notification
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Die Summe der Prozentzahlen der nicht aussortierten Aktien betr�gt nicht 100%. (" + prozentSumme + ")" );
			alert.setContentText("");
			alert.showAndWait();
			}
		}

	}
	
	@FXML
	private void handleZurueck() throws IOException {
		System.out.println("Weiterbutton pressed!");
		this.c1.setSceneToV_Shares();
	}
	
	@FXML
	private void handleSpeichern() throws IOException {
		System.out.println("Speichernbutton pressed!");
		m1.currentPortfoliosAktienProzente.put(Integer.valueOf(m1.selectedCurrentSharesStringAnalyse1.substring(0, m1.selectedCurrentSharesStringAnalyse1.indexOf(" "))),Double.parseDouble(this.textFieldPercent.getText()));
		this.updateData();
	}

	
	@FXML
	private void handleSuedOstStreng() throws IOException {
		
		System.out.println("suedOst pressed!");
		
		// erstelle Array mit (Risiko, Rendite, AktienID, Effizienzbewertung)
		int counter = m1.currentPortfoliosAktienMitKursen.size();
		double [][] temp = new double [counter][4];
		final int[] i = {0};

		// Vorbereitung der Daten
		m1.currentPortfoliosAktienMitKursen.forEach( (k,v) -> 
			{
				temp [i[0]][0] = v.getRisk();
				temp [i[0]][1] = v.getSigma();
				temp [i[0]][2] = v.getShare_id();
				temp [i[0]][3] = 1;
				System.out.println(temp[i[0]][0] + " " + temp[i[0]][1] + " " + temp[i[0]][2] + " " + temp[i[0]][3]);	
				i[0] += 1;
			}
		);
		
		//Sortierung (geringes Risiko, hohe Rendite)
		Arrays.sort(temp, 
        		new Comparator<double[]>() {
		            @Override
		            public int compare( double[] a,  double[] b) {
		                 int distance = Double.compare(a[0],b[0]);
		                 if(distance == 0) {
		                	 distance = Double.compare(b[1],a[1]);
		                 }
		            return distance;
		            }
        		}
        );
		
		for(int j = 0; j<counter; j++) {
    		System.out.println(temp[j][0] + " " + temp[j][1] + " " + temp[j][2] + " " + temp[j][3]);
		}
		
		//Klassifizierung (Effizient/Ineffizient)
		for(int j = 0; j < temp.length-1; j++) {
			
			if(temp[j][0] <= temp[j+1][0] && temp[j+1][1] <= temp[j][1] && (temp[j][3] == 1 && temp[j+1][3] == 1)) {
				
				double [][] temp2 = {{temp[j+1][0],temp[j+1][1],temp[j+1][2],temp[j+1][3]}};
				
				for (int m = j+1; m < temp.length-1; m++) {
					temp[m][0] = temp[m+1][0];
					temp[m][1] = temp[m+1][1];
					temp[m][2] = temp[m+1][2];
					temp[m][3] = temp[m+1][3];
				};
				
				temp[temp.length-1][0] = temp2[0][0];
				temp[temp.length-1][1] = temp2[0][1];
				temp[temp.length-1][2] = temp2[0][2];
				temp[temp.length-1][3] = 0;
				
				j--;
			}
		};
		
		for(int j = 0; j<counter; j++) {
    		System.out.println(temp[j][0] + " " + temp[j][1] + " " + temp[j][2] + " " + temp[j][3]);
		}
		
		// Erstellung der Datasets
		XYSeriesCollection dataset = new XYSeriesCollection();
		
		//Grenzlinie des Sued-ost-Bereichs
		XYSeries series2 = new XYSeries("Kurve1");
		for(int j = 0; j < temp.length; j++) {
			if(j==0) {series2.add(temp[j][0],-1);}
			if(temp[j][3]==1) {
				series2.add(temp[j][0],temp[j][1]);
				if(j+1 < temp.length) {
					if(temp[j+1][3]==1) {
						series2.add(temp[j+1][0],temp[j][1]);
					}
					else series2.add(1,temp[j][1]);
				}
				else series2.add(1,temp[j][1]);
			}
		};
		dataset.addSeries(series2);
		
		
		// Aktien mit Beschriftung
		String [][] Label = new String [counter][2];
		i[0] = 0;
		m1.currentPortfoliosAktienMitKursen.forEach( (k,v) -> 
			{
				Label [i[0]][0] = String.valueOf(v.getShare_id());
				Label [i[0]][1] = v.getName();
				System.out.println(Label[i[0]][0] + " " + Label[i[0]][1]);	
				i[0] += 1;
			}
		);
		
		for(int j = 0; j < temp.length; j++) {
			String Info = null;
			for(int m = 0; m < Label.length; m++) {
				if (temp[j][2] == Integer.valueOf(Label[m][0])) {Info = Label[m][1];}
			}
		
			XYSeries series1 = new XYSeries(Info);
			series1.add(temp[j][0], temp[j][1]);
			dataset.addSeries(series1);
		};
		
		for(int j = 0; j < temp.length; j++) {
        	if(temp[j][3] == 1) {m1.analyseErgebnis.put((int)(temp[j][2]), new Boolean(true));}
        	else {m1.analyseErgebnis.put((int)(temp[j][2]), new Boolean(false));}
        }		
		
		sn1 = new SwingNode();
		paneWithSwing1.getChildren().add(sn1);
		
		// Erstelle den chart
        JFreeChart chart = ChartFactory.createXYLineChart(
            "Sued-Ost-Bereich",      // chart title
            "Risiko",               // x axis label
            "Rendite",              // y axis label
            dataset,                // data
            PlotOrientation.VERTICAL,
            false,                  // include legend
            true,                   // tooltips
            false                   // urls
        );
        chart.setBackgroundPaint(Color.gray);
        chart.getTitle().setPaint(Color.black);
        
        // Customisations
        XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.black);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        
        NumberAxis domain = (NumberAxis) plot.getDomainAxis();
        domain.setRange(0, 0.71);
        domain.setTickUnit(new NumberTickUnit(0.1));
        NumberAxis range = (NumberAxis) plot.getRangeAxis();
        range.setRange(-0.31, 0.51);
        range.setTickUnit(new NumberTickUnit(0.1));
        
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, new Color(238,207,161));
        renderer.setSeriesShapesVisible(0, false);
        renderer.setSeriesItemLabelsVisible(0,false);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

		NumberFormat format = NumberFormat.getNumberInstance();
        format.setMaximumFractionDigits(2);
        
        XYItemLabelGenerator generator =
        	new StandardXYItemLabelGenerator("{0}", format, format) ;
        renderer.setBaseItemLabelGenerator(generator);
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBaseItemLabelPaint(new Color(188,210,238));
        
        plot.setRenderer(renderer);
		
        ChartPanel panel = new ChartPanel(chart);
        sn1.setContent(panel);

		this.updateData();
	}
	
	@FXML
	private void handleIndifferenz() throws IOException {
		System.out.println("Indifferenzbutton pressed!");
		
		// erstelle Array mit (Risiko, Rendite, AktienID, Effizienzbewertung, Nutzenwert)
		String functp = this.functiontyp.getValue();
		double averco = Double.parseDouble(this.aversionscoefficient.getText());
		int counter = m1.currentPortfoliosAktienMitKursen.size();
		double [][] temp = new double [counter][5];
		final int[] i = {0};
		
		// Vorbereitung der Daten
		m1.currentPortfoliosAktienMitKursen.forEach( (k,v) -> 
			{
				temp [i[0]][0] = v.getRisk();
				temp [i[0]][1] = v.getSigma();
				temp [i[0]][2] = v.getShare_id();
				temp [i[0]][3] = 0;
				System.out.println(temp[i[0]][0] + " " + temp[i[0]][1] + " " + temp[i[0]][2] + " " + temp[i[0]][3]+ " " + temp[i[0]][4]);	
				i[0] += 1;
			}
		);
		
		// Berechnung der Nutzenwerte
		// Linear
		if(functp == "Linear") {
			for(int j = 0; j < temp.length; j++) {
				temp[j][4] = (temp[j][1] - averco/20 * temp[j][0]);
			}
		};
		
		// Quadratisch
		if(functp == "Quadratisch") {
			for(int j = 0; j < temp.length; j++) {
				temp[j][4] = (temp[j][1] - averco/5 * (temp[j][0] * temp[j][0]));
			}
		};
		
		for(int j = 0; j<counter; j++) {
    		System.out.println(temp[j][0] + " " + temp[j][1] + " " + temp[j][2] + " " + temp[j][3] + " " + temp[j][4]);
    	}
		
		//Sortierung (hoher Nutzenwert, geringes Risiko)
				Arrays.sort(temp, 
		        		new Comparator<double[]>() {
				            @Override
				            public int compare( double[] a,  double[] b) {
				                 int distance = Double.compare(b[4],a[4]);
				                 if(distance == 0) {
				                	 distance = Double.compare(b[0],a[0]);
				                 }
				            return distance;
				            }
		        		}
		        );
		
		// Finde die besten Aktien, die �ber einem gewissen Grenzwert (30%) liegen. Berechnung erfolgt anhand des h�chsten Nutzenwertes.
		double SchwelleProzent = 0.3; 
		double Schwellenwert = (1-SchwelleProzent)*temp[0][4];
		
		for(int j = 0; j<temp.length; j++) {
    		if (temp[j][4] > Schwellenwert) {temp[j][3]=1;}
    		System.out.println(temp[j][0] + " " + temp[j][1] + " " + temp[j][2] + " " + temp[j][3] + " " + temp[j][4]);
    	}

		
		// Erstellung der Datasets
		XYSeriesCollection dataset = new XYSeriesCollection();
		
		// Erzeugung der Indifferenzkurven
		XYSeries series2 = new XYSeries("Kurve1");
		XYSeries series3 = new XYSeries("Kurve2");
		XYSeries series4 = new XYSeries("Kurve3");
		XYSeries series5 = new XYSeries("Kurve4");
		XYSeries series6 = new XYSeries("Kurve5");
		for(double j = 0; j < 1; j+=0.01) {
			if(functp == "Linear") {
				series2.add(j, (-0.20+Schwellenwert) + averco/20 * j);
				series3.add(j, (-0.10+Schwellenwert) + averco/20 * j);
				series4.add(j, (Schwellenwert) + averco/20 * j);
				series5.add(j, (0.10+Schwellenwert) + averco/20 * j);
				series6.add(j, (0.20+Schwellenwert) + averco/20 * j);
			}
			if(functp == "Quadratisch") {
				series2.add(j, (-0.20+Schwellenwert) + averco/5 * (j*j));
				series3.add(j, (-0.10+Schwellenwert) + averco/5 * (j*j));
				series4.add(j, (Schwellenwert) + averco/5 * (j*j));
				series5.add(j, (0.10+Schwellenwert) + averco/5 * (j*j));
				series6.add(j, (0.20+Schwellenwert) + averco/5 * (j*j));
			}
		}
		dataset.addSeries(series2);
		dataset.addSeries(series3);
		dataset.addSeries(series4);
		dataset.addSeries(series5);
		dataset.addSeries(series6);
		
		// Aktien mit Beschriftung
		String [][] Label = new String [counter][2];
		i[0] = 0;
		m1.currentPortfoliosAktienMitKursen.forEach( (k,v) -> 
			{
				Label [i[0]][0] = String.valueOf(v.getShare_id());
				Label [i[0]][1] = v.getName();
				System.out.println(Label[i[0]][0] + " " + Label[i[0]][1]);	
				i[0] += 1;
			}
		);
		
		for(int j = 0; j < temp.length; j++) {
			String Info = null;
			for(int m = 0; m < Label.length; m++) {
				if (temp[j][2] == Integer.valueOf(Label[m][0])) {Info = Label[m][1];}
			}
		
			XYSeries series1 = new XYSeries(Info);
			series1.add(temp[j][0], temp[j][1]);
			dataset.addSeries(series1);
		};
		
		
		for(int j = 0; j < temp.length; j++) {
        	if(temp[j][3] == 1) {m1.analyseErgebnis.put((int)(temp[j][2]), new Boolean(true));}
        	else {m1.analyseErgebnis.put((int)(temp[j][2]), new Boolean(false));}
        }
		
		sn2 = new SwingNode();
		paneWithSwing2.getChildren().add(sn2);
		
		// Erstelle den chart
        JFreeChart chart = ChartFactory.createXYLineChart(
            "Indifferenzkurven",      // chart title
            "Risiko",                 // x axis label
            "Rendite",                // y axis label
            dataset,                  // data
            PlotOrientation.VERTICAL,
            false,                    // include legend
            true,                     // tooltips
            false                     // urls
        );
        chart.setBackgroundPaint(Color.gray);
        chart.getTitle().setPaint(Color.black);
        
        // Customisations
        XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.black);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
       
        NumberAxis domain = (NumberAxis) plot.getDomainAxis();
        domain.setRange(0, 0.71);
        domain.setTickUnit(new NumberTickUnit(0.1));
        NumberAxis range = (NumberAxis) plot.getRangeAxis();
        range.setRange(-0.31, 0.51);
        range.setTickUnit(new NumberTickUnit(0.1));
        
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.white); // Hilfslinie
        renderer.setSeriesPaint(1, Color.white); // Hilfslinie
        renderer.setSeriesPaint(2, Color.red); // Referenzlinie
        renderer.setSeriesPaint(3, Color.white); // Hilfslinie
        renderer.setSeriesPaint(4, Color.white); // Hilfslinie
        renderer.setSeriesShapesVisible(0, false);
        renderer.setSeriesShapesVisible(1, false);
        renderer.setSeriesShapesVisible(2, false);
        renderer.setSeriesShapesVisible(3, false);
        renderer.setSeriesShapesVisible(4, false);
        renderer.setSeriesItemLabelsVisible(0,false);
        renderer.setSeriesItemLabelsVisible(1,false);
        renderer.setSeriesItemLabelsVisible(2,false);
        renderer.setSeriesItemLabelsVisible(3,false);
        renderer.setSeriesItemLabelsVisible(4,false);
        
        NumberFormat format = NumberFormat.getNumberInstance();
        format.setMaximumFractionDigits(2);
        
        XYItemLabelGenerator generator =
        	new StandardXYItemLabelGenerator("{0}", format, format) ;
        renderer.setBaseItemLabelGenerator(generator);
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBaseItemLabelPaint(new Color(188,210,238));
        
        plot.setRenderer(renderer);
        
        ChartPanel panel = new ChartPanel(chart);
        sn2.setContent(panel);

		this.updateData();
	}
	
	@FXML
	private void handleSwitch() throws IOException {
		System.out.println("Switchbutton pressed!");
		m1.analyseErgebnis.put(Integer.valueOf(m1.selectedCurrentSharesStringAnalyse1.substring(0, m1.selectedCurrentSharesStringAnalyse1.indexOf(" "))),! m1.analyseErgebnis.get(Integer.valueOf(m1.selectedCurrentSharesStringAnalyse1.substring(0, m1.selectedCurrentSharesStringAnalyse1.indexOf(" ")))));
		this.updateData();
	}
}