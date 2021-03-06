package p1_controller;

import java.io.IOException;
import java.util.HashMap;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import p2_view.VC_Rohstoffanalyse;
import p2_view.VC_Shares;
import p2_view.VC_Aktienanalyse;
import p2_view.VC_AssetClasses;
import p2_view.VC_MainMenu;
import p2_view.VC_Portfolios;
import p2_view.VC_Commodities;
import p2_view.VC_CompletePortfolio;
import p2_view.VC_CompletePortfolioCompare;

//import javafx.event.ActionEvent;
//import javafx.event.Event;
//import javafx.event.EventHandler;
import javafx.stage.Stage;
import p0_model.Model;
import p0_model.db_objects.Aktie;
import p0_model.db_objects.Rohstoff;

public class Controller {
	public static Controller c1;
	public Stage prim;
	public Model model;

///////////////////////////////////////
//Konstruktoren und getInstance-Methoden
///////////////////////////////////////
	public Controller() {
	};
	protected Controller(Stage primaryStage, Model model1) throws IOException {
		super();
		this.model = model1;
		this.prim = primaryStage;
		this.prim.setTitle("PortfolioBro");
	}
	public static Controller getInstance() {
		if (c1 == null)
			c1 = new Controller();
		return c1;
	}
	public static Controller getInstance(Stage primaryStage, Model model1) {
		if (c1 == null)
			try {
				c1 = new Controller(primaryStage, model1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return c1;
	}

///////////////////////////////////////
//Methoden zur Erstellung der JavaFX Scene
///////////////////////////////////////	
	public void setSceneToV_Portfolios() throws IOException {

		model.analyseErgebnis = new HashMap<Integer, Boolean>();
		model.analyseErgebnis = new HashMap<Integer, Boolean>();
		model.currentPortfoliosAktienMitKursen =  new HashMap<Integer, Aktie>();
		model.currentPortfoliosRohstoffeMitKursen =  new HashMap<Integer, Rohstoff>();
		model.currentPortfoliosAktienProzente = new HashMap<Integer, Double>();
		model.currentPortfoliosRohstoffeProzente = new HashMap<Integer, Double>();
		//DB-Initialisierung
		model.initializeAssetClasses_allSHeads_allCHeads_allPortfolios();
		
		// View-Initialisierung 
		FXMLLoader loader = new FXMLLoader(Controller.class.getResource("/p2_view/V_Portfolios.fxml"));
		Pane pane1 = loader.load();
		VC_Portfolios vc1 = loader.getController();

		// UI-Elemente und Attribute setzen
		vc1.c1 = getInstance();
		vc1.m1 = getInstance().model;

		vc1.updateData();

		Stage prim=getInstance().prim;
		prim.setScene(new Scene(pane1, 1200, 600));
		prim.setMinWidth(800);
		prim.setMinHeight(400);
		prim.show();
	}
	
	public void setSceneToV_AssetType() throws IOException {
		model.loadSelectedPortfolioData();
		
		FXMLLoader loader = new FXMLLoader(Controller.class.getResource("/p2_view/V_AssetClasses.fxml"));
		Pane pane1 = loader.load();
		VC_AssetClasses vc1 = loader.getController();

		vc1.c1 = getInstance();
		vc1.m1 = getInstance().model;

		vc1.updateData();

		Stage prim=getInstance().prim;
		prim.setScene(new Scene(pane1, 1200, 600));
		prim.setMinWidth(800);
		prim.setMinHeight(400);
		prim.show();
	}

	public void setSceneToUser() throws IOException {
		FXMLLoader loader = new FXMLLoader(Controller.class.getResource("/p2_view/User.fxml"));
		Pane pane1 = loader.load();
		
		Stage prim=getInstance().prim;
		prim.setScene(new Scene(pane1, 1200, 600));
		prim.setMinWidth(800);
		prim.setMinHeight(400);
		prim.show();
	}

	public void setSceneTo1() throws IOException {
		FXMLLoader loader = new FXMLLoader(Controller.class.getResource("/p2_view/swingnodebsp.fxml"));
		Pane pane1 = loader.load();
		
		Stage prim=getInstance().prim;
		prim.setScene(new Scene(pane1, 1200, 600));
		prim.setMinWidth(800);
		prim.setMinHeight(400);
		prim.show();
	}
	public void setSceneToV_Shares() throws IOException {
		// Initialize View (no model)
		FXMLLoader loader = new FXMLLoader(Controller.class.getResource("/p2_view/V_Shares.fxml"));
		Pane pane1 = loader.load();
		VC_Shares vc1 = loader.getController();

		// Set View attributes and UI-Elements
		vc1.c1 = getInstance();
		vc1.m1 = getInstance().model;

		vc1.updateData();
	pane1.getStylesheets().add("p2_view/Stylesheet_Graph.css");	
	
		Stage prim=getInstance().prim;
		prim.setScene(new Scene(pane1, 1200, 600));
		prim.setMinWidth(800);
		prim.setMinHeight(400);
		prim.show();
	}
	public void setSceneToV_Aktienanalyse() throws IOException {
		// Initialize View (no model)
		FXMLLoader loader = new FXMLLoader(Controller.class.getResource("/p2_view/V_Aktienanalyse.fxml"));
		Pane pane1 = loader.load();
		VC_Aktienanalyse vc1 = loader.getController();

		// Set View attributes and UI-Elements
		vc1.c1 = getInstance();
		vc1.m1 = getInstance().model;

		vc1.updateData();

		Stage prim=getInstance().prim;
		prim.setScene(new Scene(pane1, 1200, 600));
		prim.setMinWidth(800);
		prim.setMinHeight(400);
		prim.show();
	}
	
	
	public void setSceneToV_Commodities() throws IOException {
		// Initialize View (no model)
		FXMLLoader loader = new FXMLLoader(Controller.class.getResource("/p2_view/V_Commodities.fxml"));
		Pane pane1 = loader.load();
		VC_Commodities vc1 = loader.getController();

		// Set View attributes and UI-Elements
		vc1.c1 = getInstance();
		vc1.m1 = getInstance().model;

		vc1.updateData();

	pane1.getStylesheets().add("p2_view/Stylesheet_Graph.css");	
		
		Stage prim=getInstance().prim;
		prim.setScene(new Scene(pane1, 1200, 600));
		prim.setMinWidth(800);
		prim.setMinHeight(400);
		prim.show();
	}
	public void setSceneToV_Rohstoffanalyse() throws IOException {
		// Initialize View (no model)
		FXMLLoader loader = new FXMLLoader(Controller.class.getResource("/p2_view/V_Rohstoffanalyse.fxml"));
		Pane pane1 = loader.load();
		VC_Rohstoffanalyse vc1 = loader.getController();

		// Set View attributes and UI-Elements
		vc1.c1 = getInstance();
		vc1.m1 = getInstance().model;

		vc1.updateData();

		Stage prim=getInstance().prim;
		prim.setScene(new Scene(pane1, 1200, 600));
		prim.setMinWidth(800);
		prim.setMinHeight(400);
		prim.show();
	}
	public void setSceneToV_MainMenu() throws IOException {

		// Initialize View (no model)
		FXMLLoader loader = new FXMLLoader(Controller.class.getResource("/p2_view/V_MainMenu.fxml"));
		Pane pane1 = loader.load();
		VC_MainMenu vc1 = loader.getController();

		// Set View attributes and UI-Elements
		vc1.c1 = getInstance();
		vc1.m1 = getInstance().model;

		vc1.updateData();
		
		Stage prim=getInstance().prim;
		prim.setScene(new Scene(pane1, 1200, 600));
		prim.setMinWidth(800);
		prim.setMinHeight(400);
		prim.show();

	}
	public static void setSceneToV_CompletePortfolio() throws IOException {
		
		// Initialize View (no model)
		FXMLLoader loader = new FXMLLoader(Controller.class.getResource("/p2_view/V_CompletePortfolio.fxml"));
		Pane pane1 = loader.load();
		VC_CompletePortfolio vc1 = loader.getController();

		// Set View attributes and UI-Elements
		vc1.c1 = getInstance();
		vc1.m1 = getInstance().model;

		vc1.updateData();
		Stage prim=getInstance().prim;
		prim.setScene(new Scene(pane1, 1200, 600));
		prim.setMinWidth(800);
		prim.setMinHeight(400);
		prim.show();
	}
	
	
	public static void setSceneToV_CompletePortfolioCompare() throws IOException {

		// Initialize View (no model)
		FXMLLoader loader = new FXMLLoader(Controller.class.getResource("/p2_view/V_CompletePortfolioCompare.fxml"));
		Pane pane1 = loader.load();
		VC_CompletePortfolioCompare vc1 = loader.getController();

		// Set View attributes and UI-Elements
		vc1.c1 = getInstance();
		vc1.m1 = getInstance().model;

		vc1.updateData();
		Stage prim=getInstance().prim;
		prim.setScene(new Scene(pane1, 1200, 600));
		prim.setMinWidth(800);
		prim.setMinHeight(400);
		prim.show();
	}

	
//	@Override
//	public void handle(Event event) {
//		// TODO Auto-generated method stub
//		 
//	}
//
//	public void handle(ActionEvent event) {
//		// TODO Auto-generated method stub
//		 
//	}
}