package p2_view;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.io.IOException;
import db_objects.AktieTableEntry;
import db_objects.PortfolioTableEntry;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import p0_model.Model;
import p1_controller.Controller;

public class VC_Shares {
	public Model m1;
	public Controller c1;
	
	
	@FXML
	private ListView<String> listView1;
	@FXML
	private TabPane tabPane1;
	
	@FXML
	private ScatterChart<Number, Number> scatterChart1;

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
	private TableColumn<AktieTableEntry, Number> industryColumn1 ;
	@FXML
	private TableColumn<AktieTableEntry, String> sigmaColumn1 ;	
	@FXML
	private TableColumn<AktieTableEntry, String> riskColumn1 ;		
	@FXML
	private TableColumn<AktieTableEntry, Number> IdColumn2;
	@FXML
	private TableColumn<AktieTableEntry, String> nameColumn2;
	@FXML
	private TableColumn<AktieTableEntry, Number> industryColumn2 ;
	@FXML
	private TableColumn<AktieTableEntry, String> sigmaColumn2 ;	
	@FXML
	private TableColumn<AktieTableEntry, String> riskColumn2 ;	
	@FXML
	private TableColumn<AktieTableEntry, Number> IdColumn3;
	@FXML
	private TableColumn<AktieTableEntry, String> nameColumn3;
	@FXML
	private TableColumn<AktieTableEntry, Number> industryColumn3 ;
	@FXML
	private TableColumn<AktieTableEntry, String> sigmaColumn3 ;	
	@FXML
	private TableColumn<AktieTableEntry, String> riskColumn3 ;	
	@FXML
	private TableColumn<AktieTableEntry, Number> IdColumn4;
	@FXML
	private TableColumn<AktieTableEntry, String> nameColumn4;
	@FXML
	private TableColumn<AktieTableEntry, Number> industryColumn4 ;
	@FXML
	private TableColumn<AktieTableEntry, String> sigmaColumn4 ;	
	@FXML
	private TableColumn<AktieTableEntry, String> riskColumn4 ;	
	@FXML
	private TableColumn<AktieTableEntry, Number> IdColumn5;
	@FXML
	private TableColumn<AktieTableEntry, String> nameColumn5;
	@FXML
	private TableColumn<AktieTableEntry, Number> industryColumn5 ;
	@FXML
	private TableColumn<AktieTableEntry, String> sigmaColumn5 ;	
	@FXML
	private TableColumn<AktieTableEntry, String> riskColumn5 ;	
	
	
	@FXML
	private Button addbutton1 ;	
	@FXML
	private Button addbutton2 ;
	@FXML
	private Button addbutton3 ;
	@FXML
	private Button addbutton4 ;
	@FXML
	private Button addbutton5 ;
	
	@FXML
	private Button deletebutton1 ;	
	@FXML
	private Button vorleaufigUpdatenButton1 ;


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
	
    protected List<String> asianCurrencyList ;
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
		
		
//		ObservableList<String> fruitList = FXCollections.<String>observableArrayList("Apple", "Banana", "Orange", "Mango");
//		// Create the ListView for the fruits
//		listView1 = new ListView<String>();
//		listView1.getItems().addAll(fruitList);


		// tabPane1.setStyle("-fx-background-color:transparent;");
		tabPane1.getStyleClass().add("floating");

		
		ObservableList<AktieTableEntry> allAktienOhneKurseTE_DAX = m1.allAktienOhneKurseTE.stream()
	            .filter(x -> (x.getIndex().equals("DAX")))
	            .collect(Collectors.toCollection(FXCollections::observableArrayList));
		System.out.println("Tabelle22222" +allAktienOhneKurseTE_DAX);
		tableView1.setItems(allAktienOhneKurseTE_DAX);
		IdColumn1.setCellValueFactory(cellData -> cellData.getValue().get2Share_id());
		nameColumn1.setCellValueFactory(cellData -> cellData.getValue().get2Name());    
		
		ObservableList<AktieTableEntry> allAktienOhneKurseTE_TecDAX = m1.allAktienOhneKurseTE.stream()
	            .filter(x -> (x.getIndex().equals("TecDAX")))
	            .collect(Collectors.toCollection(FXCollections::observableArrayList));
		System.out.println("Tabelle22222" +allAktienOhneKurseTE_TecDAX);
		tableView2.setItems(allAktienOhneKurseTE_TecDAX);
		IdColumn2.setCellValueFactory(cellData -> cellData.getValue().get2Share_id());
		nameColumn2.setCellValueFactory(cellData -> cellData.getValue().get2Name());                            

		ObservableList<AktieTableEntry> allAktienOhneKurseTE_MDAX = m1.allAktienOhneKurseTE.stream()
	            .filter(x -> (x.getIndex().equals("MDAX")))
	            .collect(Collectors.toCollection(FXCollections::observableArrayList));
		System.out.println("Tabelle33333" +allAktienOhneKurseTE_MDAX);
		tableView3.setItems(allAktienOhneKurseTE_MDAX);
		IdColumn3.setCellValueFactory(cellData -> cellData.getValue().get2Share_id());
		nameColumn3.setCellValueFactory(cellData -> cellData.getValue().get2Name()); 
		
		ObservableList<AktieTableEntry> allAktienOhneKurseTE_Nikkei = m1.allAktienOhneKurseTE.stream()
	            .filter(x -> (x.getIndex().equals("Nikkei 225")))
	            .collect(Collectors.toCollection(FXCollections::observableArrayList));
		System.out.println("Tabelle44444" +allAktienOhneKurseTE_Nikkei);
		tableView4.setItems(allAktienOhneKurseTE_Nikkei);
		IdColumn4.setCellValueFactory(cellData -> cellData.getValue().get2Share_id());
		nameColumn4.setCellValueFactory(cellData -> cellData.getValue().get2Name());
		
		ObservableList<AktieTableEntry> allAktienOhneKurseTE_DowJones = m1.allAktienOhneKurseTE.stream()
	            .filter(x -> (x.getIndex().equals("Dow Jones")))
	            .collect(Collectors.toCollection(FXCollections::observableArrayList));
		System.out.println("Tabelle55555" +allAktienOhneKurseTE_DowJones);
		tableView5.setItems(allAktienOhneKurseTE_DowJones);
		IdColumn5.setCellValueFactory(cellData -> cellData.getValue().get2Share_id());
		nameColumn5.setCellValueFactory(cellData -> cellData.getValue().get2Name());

		///////////////////////
		//5x Table Listener
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
		asianCurrencyList= new ArrayList<>();
		
		m1.currentPortfoliosAktienMitKursen.forEach( (k,v) -> asianCurrencyList.add(v.getShare_id() + " " + v.getName() + " (" +v.getIndex() + ")"));

	    asianCurrencyList.add("CNH");
        asianCurrencyList.add("JPY");
        asianCurrencyList.add("HKD");
        asianCurrencyList.add("KRW");
        asianCurrencyList.add("SGD");

		listView1.itemsProperty().bind(listProperty);
		listProperty.set(FXCollections.observableArrayList(asianCurrencyList));        

		listView1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		        System.out.println("ListView selection changed from oldValue = " 
		                + oldValue + " to newValue = " + newValue);
		        m1.selectedCurrentSharesString = newValue;
		    }
		});
		
	}
	
	
	////////////////////////////////////////////////////////
	// Handle-Methoden
	////////////////////////////////////////////////////////	
	@FXML
	private void handleDeleteSelected() {
	    System.out.println(" Removing from HashMap" +m1.currentPortfoliosAktienMitKursen );
		m1.currentPortfoliosAktienMitKursen.remove(Integer.valueOf(m1.selectedCurrentSharesString.substring(0, m1.selectedCurrentSharesString.indexOf(" "))));
		
		updateData();
	}
	
			private void handleSaveSelected (AktieTableEntry aktie1) {
			if (aktie1 != null) {
				m1.selectedAktie = aktie1;
//				idLabel.setText("" + m1.selectedAktie.get2Portfolio_id());
//				nameLabel.setText(m1.selectedAktie.get2Name());
				System.out.println("Selected Aktie:" + aktie1);
			} else {
				//
				m1.selectedAktie = null;
//				idLabel.setText("");
//				nameLabel.setText("");
			}
		}

			@FXML
			private void handleAdd() {
				m1.currentPortfoliosAktienMitKursen.put(new Integer(m1.selectedAktie.getShare_id()), m1.allAktienOhneKurse.get(m1.selectedAktie.getShare_id()));
				System.out.println("hinzugefügte Aktie: " + m1.currentPortfoliosAktienMitKursen);
				
				
				
//				List sortedKeys=new ArrayList(m1.currentPortfoliosAktienMitKursen.keySet());
			//	Collections.sort(m1.currentPortfoliosAktienMitKursen.keySet());
				m1.sortCurrentPortfoliosAktien();
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
				};
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
		this.c1.setSceneToUser();
	}
	
	@FXML
	private void handleVorleaufigUpdaten() throws IOException {
		System.out.println("VorleaufigUpdaten pressed!");
		m1.updatePB_PORTF_SHAREmit12();
	}

}