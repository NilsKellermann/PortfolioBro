package p1_controller;

import db_objects.PortfolioTableEntry;
import javafx.application.Application;
import javafx.stage.Stage;
import p0_model.Model;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}
//blabla
	@Override
	public void start(Stage primaryStage) throws Exception {

		// Model erstellen
		Model m1 = Model.getInstance();
		// Model.userList.add(new User(1, "User1", "v1", "n1"));
		// m1.userList.add(new User(1, "User2", "v2", "n2"));
		m1.yourPortfolioList.add(new PortfolioTableEntry(1000, "Portfolio1", 1000, 0, 0, 0, 0, 0, 0));
		m1.yourPortfolioList.add(new PortfolioTableEntry(1001, "Portfolio2", 1000, 0, 0, 0, 0, 0, 0));
		m1.yourPortfolioList.add(new PortfolioTableEntry(1002, "Portfolio3", 1000, 0, 0, 0, 0, 0, 0));
		m1.yourPortfolioList.add(new PortfolioTableEntry(1003, "Portfolio4", 1000, 0, 0, 0, 0, 0, 0));
		m1.allPortfolioList.add(new PortfolioTableEntry(1000, "Portfolio1", 1000, 0, 0, 0, 0, 0, 0));
		m1.allPortfolioList.add(new PortfolioTableEntry(1001, "Portfolio2", 1000, 0, 0, 0, 0, 0, 0));
		m1.allPortfolioList.add(new PortfolioTableEntry(1002, "Portfolio3", 1000, 0, 0, 0, 0, 0, 0));
		m1.allPortfolioList.add(new PortfolioTableEntry(1003, "Portfolio4", 1000, 0, 0, 0, 0, 0, 0));
		m1.nextPortfolio_id = m1.userList.size() + 1;

		m1.loggedInUser_id = 1000;

		m1.selectDBAssetclasses();

		Controller.getInstance(primaryStage, m1);

		Controller.setSceneToV_MainMenu();
		// setSceneToV_Shares();
		// setSceneTo1();

	}

}
