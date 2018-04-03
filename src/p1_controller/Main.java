package p1_controller;

import javafx.application.Application;
import javafx.stage.Stage;
import p0_model.Model;
import p0_model.db_objects.Portfolio;
import p0_model.db_objects.PortfolioTableEntry;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
//Model initialisieren
		Model m1 = Model.getInstance();
		m1.loggedInUser_id = 1000;
		m1.initializeAssetClasses_allSHeads_allCHeads_allPortfolios();
		m1.transferCurrentCommoditiesHashmapToObservableList();
		m1.transferAllCommoditiesHashmapToObservableList();
		m1.transferCurrentShareHashmapToObservableList();
		m1.transferAllShareHashmapToObservableList();
		m1.transferAllPortfolioHashmapToObservableList();	
		m1.initializeAssetClasses_allSHeads_allCHeads_allPortfolios();		
		
//Controller initialisieren
		Controller c1= Controller.getInstance(primaryStage, m1);
		c1.setSceneToV_Portfolios();
		
//JavaFX Fenster mit erster Scene f�llen
		c1.setSceneToV_MainMenu();
		// setSceneToV_Shares();
		// setSceneTo1();

	}

}
