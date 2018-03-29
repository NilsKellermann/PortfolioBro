package p1_controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.time.format.DateTimeFormatter;

import db_objects.Portfolio;
import db_objects.PortfolioTableEntry;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.stage.Stage;
import p0_model.Model;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Model m1 = Model.getInstance();
		
		// m1.userList.add(new User(1, "User1", "v1", "n1"));
		// m1.userList.add(new User(1, "User2", "v2", "n2"));
		
		m1.yourPortfolioTE.add(new PortfolioTableEntry(1000, "Portfolio1", 1000, 0, 0, 0, 0, 0, 0));
		m1.yourPortfolioTE.add(new PortfolioTableEntry(1001, "Portfolio2", 1000, 0, 0, 0, 0, 0, 0));
		m1.yourPortfolioTE.add(new PortfolioTableEntry(1002, "Portfolio3", 1000, 0, 0, 0, 0, 0, 0));
		m1.yourPortfolioTE.add(new PortfolioTableEntry(1003, "Portfolio4", 1000, 0, 0, 0, 0, 0, 0));
		m1.allPortfolioTE.add( new PortfolioTableEntry(1000, "Portfolio1", 1000, 0, 0, 0, 0, 0, 0));
		m1.allPortfolioTE.add( new PortfolioTableEntry(1001, "Portfolio2", 1000, 0, 0, 0, 0, 0, 0));
		m1.allPortfolioTE.add( new PortfolioTableEntry(1002, "Portfolio3", 1000, 0, 0, 0, 0, 0, 0));
		m1.allPortfolioTE.add( new PortfolioTableEntry(1003, "Portfolio4", 1000, 0, 0, 0, 0, 0, 0));
		m1.allPortfolios.put(1001, new Portfolio(1001, "Portfolio1", 1000, 0.0, 1, 7, 82, 0.0, 0.0, 0, 0, 0, 0, 0, 0));
		m1.allPortfolios.put(1002, new Portfolio(1002, "Portfolio2", 1000, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0, 0, 0, 0, 0, 0));		

		m1.loggedInUser_id = 1000;


		m1.initializeAssetClasses_allSHeads_allCHeads_allPortfolios();
		m1.transferCurrentShareHashmapToObservableList();
		m1.transferAllShareHashmapToObservableList();
		m1.transferAllPortfolioHashmapToObservableList();		
		System.out.println("1Liste wurde in Tabellenform gebracht:(LEER WEIL ERST BEIM WECHSEL IN V_ASSETCLASS GEF�LLT WIRD)" + m1.currentPortfoliosAktienMitKursen );
		System.out.println("2Liste wurde in Tabellenform gebracht:(LEER WEIL ERST BEIM WECHSEL IN V_ASSETCLASS GEF�LLT WIRD)" + m1.currentPortfoliosAktienMitKursenTE );
		System.out.println("3Alle Aktienheader der Datenbank:"+ m1.allAktienOhneKurse);
		System.out.println("4all-Aktien-Liste wurde in Tabellenform gebracht:" +  m1.allAktienOhneKurseTE );
		System.out.println("5Portfolios geladen, Anzahl:" + m1.allPortfolios);
		System.out.println("6PortfoliosTE geladen, Anzahl:" + m1.allPortfolioTE);
		
		Controller c1= Controller.getInstance(primaryStage, m1);
		c1.setSceneToV_Portfolios();
		System.out.println(m1.allAktienOhneKurse);
		System.out.println(m1.currentPortfoliosAktienMitKursen);

		m1.initializeAssetClasses_allSHeads_allCHeads_allPortfolios();

		Controller.getInstance(primaryStage, m1);

		c1.setSceneToV_MainMenu();
		// setSceneToV_Shares();
		// setSceneTo1();

	}
		


}
