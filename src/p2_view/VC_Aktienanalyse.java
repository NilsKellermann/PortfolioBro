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

import db_objects.Assetclass;
import db_objects.PortfolioTableEntry;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.StackPane;
import p0_model.Model;
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
	private ListView<String> listView1;
	@FXML
	private ListView<String> listView2;
//	@FXML
//	private ScatterChart<Number, Number> sc;
//	@FXML
//	private TableView<PortfolioTableEntry> pTable;
//	@FXML
//	private TableColumn<PortfolioTableEntry, Number> IdColumn;
//	@FXML
//	private TableColumn<PortfolioTableEntry, String> nameColumn;

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

	}
	
	public void updateData() {

		
		/////////////////Liste füllen		
		/////////////////////////
		simpleStringList= new ArrayList<>();
		if(m1.analyseErgebnis.isEmpty()) {m1.currentPortfoliosAktienMitKursen.forEach( (k,v) -> m1.analyseErgebnis.put(k, new Boolean(true)));
		}
		
		m1.currentPortfoliosAktienMitKursen.forEach( (k,v) -> 
		{if(m1.analyseErgebnis.isEmpty() || m1.analyseErgebnis.get(k).booleanValue() == true) //0.0 != m1.currentPortfoliosAktienProzente.get(k))
		{simpleStringList.add(v.getShare_id() + " " + v.getName() + "     (" +m1.currentPortfoliosAktienProzente.get(k) + "% )");}});

	    simpleStringList.add("CNH");

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
		//Liste füllen		
		simpleStringList2= new ArrayList<>();
		m1.currentPortfoliosAktienMitKursen.forEach( (k,v) -> 
		{if(! m1.analyseErgebnis.isEmpty() && (m1.analyseErgebnis.get(k) == null ||m1.analyseErgebnis.get(k).booleanValue() == false))
		{simpleStringList2.add(v.getShare_id() + " " + v.getName() + "     (" +m1.currentPortfoliosAktienProzente.get(k) + "% )");}});

	    simpleStringList2.add("CNH");

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
		/////////////////Liste füllen ENDE	
		/////////////////////////
		
		/////////////////
		/////////////////////////
		
		
		///1. Swingnode füllen mit Graph
		
//		sn1 = new SwingNode();
//		paneWithSwing1.getChildren().add(sn1);
//		updatePieChart();//selbstprogrammiert
//		
		///1.1 Daten aus model holen und per Schleife in die Steffenform bringen
//		///1.2 Graph erschaffen und mit den Daten füllen. als Chartpanel panel abspeichern und wie unten einfüllen
		
		
//		//updatePiechart inhalt:
//		ChartPanel panel = new ChartPanel(chart1);
//		panel.setPreferredSize(new java.awt.Dimension(500, 270));
//		panel.setMouseWheelEnabled(true);
//		sn1.setContent(panel);
		
		
		
		//2. Tabelle füllen
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		
//		
//		
//		//Fill Portfolio-table & add listener
//		pTable.setItems(m1.yourPortfolioTE);
//		IdColumn.setCellValueFactory(cellData -> cellData.getValue().getPortfolio_id());
//		nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
//		pTable.getSelectionModel().selectedItemProperty()
//		.addListener((observable, oldValue, newValue) -> handleSaveSelectedAndUseSelectedToFill(newValue));
//		
//		// Clear person details.
//		handleSaveSelectedAndUseSelectedToFill(null);
//		
//		//Fill Scatter Chart
//		sc.setTitle("Sigma-r Diagramm der Assetklassen");
//
//		ObservableList<XYChart.Series<Number, Number>> scatterGraphSeries = FXCollections.observableArrayList();
//		for (Assetclass ac1 : m1.assetclasses.values()) {
//			XYChart.Series series1 = new XYChart.Series();
//			series1.getData().add(new XYChart.Data<Double, Double>(ac1.getSigma(), ac1.getRisk()));
//			series1.setName(ac1.getName());
//			scatterGraphSeries.add(series1);
//		}
//		sc.setData(scatterGraphSeries);
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

//	@FXML
//	private void handleUnfertigesPortfolioErstellen() {
//		try {
//			boolean alreadyExists = m1.yourPortfolioTE.stream()
//					.anyMatch(t -> t.get2Name().equals(this.newNameTextField.getText()));
//			if (alreadyExists == true) {
//				System.out.println("Portfolio name already in use.");
//			} else {
//				PortfolioTableEntry p1 = new PortfolioTableEntry(m1.nextPortfolio_id, this.newNameTextField.getText(),
//						m1.loggedInUser_id, Double.parseDouble(this.newInvestmentTextField.getText()),
//						Double.parseDouble(this.newShareDistTextField.getText()),
//						Double.parseDouble(this.newCommDistTextField.getText()),
//						Double.parseDouble(this.newCurrDistTextField.getText()),
//						Double.parseDouble(this.newEstateDistTextField.getText()),
//						Double.parseDouble(this.newBondDistTextField.getText()));
//
//				if (p1.get2Name() == "") {
//					System.out.println("Das Namensfeld kann nicht leer bleiben.");
//					return;
//				}
//				if (p1.get2Share_dist() + p1.get2Comm_dist() + p1.get2Curr_dist() + p1.get2Estate_dist()
//						+ p1.get2Bond_dist() != 100) {
//					System.out.println("Die Summe der Prozentwerte muss 100% sein.");
//					return;
//				}
//				m1.nextPortfolio_id = m1.nextPortfolio_id + 1;
//				m1.yourPortfolioTE.add(p1);
//				m1.allPortfolioList.add(p1);
//				System.out.println("Portfolio created.");
//			}
//		} catch (java.lang.NumberFormatException e) {
//			System.out.println("Fehler. Der Inhalt der Textviews muss überprüft werden.");
//		}
//	}

	@FXML
	private void handleWeiter() throws IOException {
		System.out.println("Weiterbutton pressed!");
		
		//zusammenrechnen
		List<Double> doubleList= new ArrayList<>();
		m1.currentPortfoliosAktienProzente.forEach( (k,v) -> {if (m1.analyseErgebnis.get(k) == true) {doubleList.add(new Double(v.doubleValue()));}});
		double prozentSumme = doubleList.stream().collect(Collectors.summingDouble(Double::doubleValue));;
		if(prozentSumme == 100.0) {
			//in db speichern und aus datenbank rauslöschen die aussortierten
		//TODOOOO	//////////777777777777777777777777777777
		////////////////////////////////////////
		///////////////////////////////////////
			this.c1.setSceneToV_AssetType();}

		else {
			// Show a predefined Warning notification
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Die Summe der Prozentzahlen der nicht aussortierten Aktien beträgt nicht 100%. (" + prozentSumme + ")" );
			alert.setContentText("");
			alert.showAndWait();
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
		///// 
//		m1.currentPortfoliosAktienMitKursen; 

		//System.out.println(m1.currentPortfoliosAktienMitKursen.size());
		int counter = m1.currentPortfoliosAktienMitKursen.size();
		double [][] temp = new double [counter][4];
		final int[] i = {0};

		m1.currentPortfoliosAktienMitKursen.forEach( (k,v) -> 
			{
				temp [i[0]][0] = v.getSigma();
				temp [i[0]][1] = v.getRisk();
				temp [i[0]][2] = v.getShare_id();
				temp [i[0]][3] = 1;
				//System.out.println(v.getShare_id() + " " + v.getName());
				System.out.println(temp[i[0]][0] + " " + temp[i[0]][1] + " " + temp[i[0]][2] + " " + temp[i[0]][3]);	
				i[0] += 1;
			}
		);
		
		//Sortierung
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
		
		//Klassifizierung
		for(int j = 0; j < temp.length-1; j++) {
			
			if(temp[j][0] <= temp[j+1][0] && temp[j+1][1] <= temp[j][1] && (temp[j][3] == 1 && temp[j+1][3] == 1)) {
				
				double [][] temp2 = {{temp[j+1][0],temp[j+1][1],temp[j+1][3]}};
				
				for (int m = j+1; m < temp.length-1; m++) {
					temp[m][0] = temp[m+1][0];
					temp[m][1] = temp[m+1][1];
					temp[m][3] = temp[m+1][3];
				};
				
				temp[temp.length-1][0] = temp2[0][0];
				temp[temp.length-1][1] = temp2[0][1];
				temp[temp.length-1][3] = 0;
				
				j--;
			}
		};
		
		for(int j = 0; j<counter; j++) {
    		System.out.println(temp[j][0] + " " + temp[j][1] + " " + temp[j][2] + " " + temp[j][3]);
		}
		
		XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries series2 = new XYSeries("Kurve1");
		for(int j = 0; j < temp.length; j++) {
			if(temp[j][3]==1) {
				series2.add(temp[j][0],temp[j][1]);
				if(temp[j+1][3]==1) {
					series2.add(temp[j+1][0],temp[j][1]);
				}
				else series2.add(100,temp[j][1]);
			}
		};
		dataset.addSeries(series2);
		
		
		String [][] Label = new String [counter][2];
		i[0] = 0;
		m1.currentPortfoliosAktienMitKursen.forEach( (k,v) -> 
			{
				Label [i[0]][0] = String.valueOf(v.getShare_id());
				Label [i[0]][1] = v.getName();
				//System.out.println(v.getShare_id() + " " + v.getName());
				System.out.println(Label[i[0]][0] + " " + Label[i[0]][1]);	
				i[0] += 1;
			}
		);
		
		for(int j = 0; j < temp.length; j++) {
			String Info = null;
			//Info = m1.currentPortfoliosAktienMitKursen.values(3,3);
			for(int m = 0; m < Label.length; m++) {
				if (temp[j][2] == Integer.valueOf(Label[m][0])) {Info = Label[m][1];}
			}
		
		/*
			if (temp[j][2] ==)
			//if (temp[i][2] == 0) {Info="BMW";};
			if (temp[j][2] == 1) {Info="Honda";};
			if (temp[j][2] == 2) {Info="Toyota";};
			if (temp[j][2] == 3) {Info="McLaren";};
			if (temp[j][2] == 4) {Info="Suzuki";};
			if (temp[j][2] == 5) {Info="Opel";};
			if (temp[j][2] == 6) {Info="Ferrari";};
			if (temp[j][2] == 7) {Info="Daihatsu";};
			if (temp[j][2] == 8) {Info="Mercedes";};
			if (temp[j][2] == 9) {Info="Renault";};
			if (temp[j][2] == 13) {Info="Daihatsu";};
			if (temp[j][2] == 14) {Info="Mercedes";};
			if (temp[j][2] == 27) {Info="Renault";};
			*/
			XYSeries series1 = new XYSeries(Info);
			series1.add(temp[j][0], temp[j][1]);
			dataset.addSeries(series1);
		};
		
		sn1 = new SwingNode();
		paneWithSwing1.getChildren().add(sn1);
		
		// create the chart...
        JFreeChart chart = ChartFactory.createXYLineChart(
            "Süd-Ost-Bereich",      // chart title
            "Risiko",                      // x axis label
            "Rendite",                      // y axis label
            dataset,                  // data
            PlotOrientation.VERTICAL,
            false,                     // include legend
            true,                     // tooltips
            false                     // urls
        );
        chart.setBackgroundPaint(Color.gray);
        chart.getTitle().setPaint(Color.black);

        
     // get a reference to the plot for further customisation...
        XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.black);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        
        NumberAxis domain = (NumberAxis) plot.getDomainAxis();
        domain.setRange(-0, 0.7);
        domain.setTickUnit(new NumberTickUnit(0.1));
        NumberAxis range = (NumberAxis) plot.getRangeAxis();
        range.setRange(-0, 0.7);
        range.setTickUnit(new NumberTickUnit(0.1));
        
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, new Color(238,207,161));
        renderer.setSeriesShapesVisible(0, false);
        renderer.setSeriesItemLabelsVisible(0,false);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

		NumberFormat format = NumberFormat.getNumberInstance();
        format.setMaximumFractionDigits(2); // etc.
        
        XYItemLabelGenerator generator =
        	new StandardXYItemLabelGenerator("{0}", format, format) ;
        renderer.setBaseItemLabelGenerator(generator);
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBaseItemLabelPaint(new Color(188,210,238));
        
        plot.setRenderer(renderer);
		
        ChartPanel panel = new ChartPanel(chart);
        sn1.setContent(panel);
//		---> m1.analyseErgebnis<id, TOPFLOPBOOLEAN>
		////
		/*
        for(int j = 0; j < temp.length; j++) {
        	if(temp[j][2] == )
        	m1.analyseErgebnis.put(6, new Boolean(true));
        }
        */
		////Grafik erschaffen und füllen
		this.updateData();
	}
	
	@FXML
	private void handleSuedOstNormal() throws IOException {
		System.out.println("Weiterbutton pressed!");
		m1.analyseErgebnis.put(6, new Boolean(true));
		m1.analyseErgebnis.put(7, new Boolean(true));
		m1.analyseErgebnis.put(8, new Boolean(false));

				this.updateData();	
	}
	
	@FXML
	private void handleIndifferenz() throws IOException {
		System.out.println("Weiterbutton pressed!");
		this.c1.setSceneToV_Shares();
	}
}