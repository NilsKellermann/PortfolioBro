package p0_model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import db_objects.Assetclass;
import db_objects.Portfolio;
import db_objects.PortfolioTableEntry;
import db_objects.User;

public class Model {
	private static Model model1;

	public int loggedInUser_id;
	public ObservableList<User> userList = FXCollections.observableArrayList();
	
	public HashMap<Integer, Assetclass> assetclasses = new HashMap<Integer, Assetclass>();

	public HashMap<Integer, Portfolio> allPortfolios = new HashMap<Integer, Portfolio>();
	public HashMap<Integer, Portfolio> yourPortfolios = new HashMap<Integer, Portfolio>();
	public ObservableList<PortfolioTableEntry> allPortfolioList = FXCollections.observableArrayList();
	public ObservableList<PortfolioTableEntry> yourPortfolioList = FXCollections.observableArrayList();
	public ObservableList<XYChart.Series<Number, Number>> scatterGraphSeries = FXCollections.observableArrayList();

	public int nextPortfolio_id;
	public PortfolioTableEntry selectedPortfolio;
	public PortfolioTableEntry newPortfolio;

	public static Model getInstance() {
		if (model1 == null)
			model1 = new Model();
		return model1;
	}

	public void selectDBAssetclasses() {
//		String url = "jdbc:mysql://localhost:3306/sql2223131";
//		String username = "sql2223131";
//		String password = "lY1*vS6*"; 
    
		String url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11225625";
		String username = "sql11225625";
		String password = "WNjKXk31lH";

		System.out.println("Connecting database...");

		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			System.out.println("Database connected!");
			
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM PB_ASSETCLASS_DATA ORDER BY AC_ID");
			
			while (rs.next()) {// über die Zeilen gehen
				Assetclass ac1 = new Assetclass(rs.getInt("AC_ID"), rs.getString("NAME"), rs.getDouble("SIGMA"),
						rs.getDouble("RISK"));
				model1.assetclasses.put(rs.getInt("AC_ID"), ac1);
				System.out.println(ac1.toString());
			}
			System.out.println("Assetclasses geladen, Anzahl:" + model1.assetclasses.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
