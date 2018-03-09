package test;

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
import db_objects.PortfolioTableEntry;
import db_objects.User;

public class Model2 {
	private static Model2 model1;

	public int loggedInUser_id;
	public ObservableList<User> userList = FXCollections.observableArrayList();
	
	public HashMap<Integer, Assetclass> assetclasses = new HashMap<Integer, Assetclass>();

	public ObservableList<PortfolioTableEntry> allPortfolioList = FXCollections.observableArrayList();
	public ObservableList<PortfolioTableEntry> yourPortfolioList = FXCollections.observableArrayList();
	public ObservableList<XYChart.Series<Number, Number>> scatterGraphSeries = FXCollections.observableArrayList();

	public int nextPortfolio_id;
	public PortfolioTableEntry selectedPortfolio;
	public PortfolioTableEntry newPortfolio;

	public static Model2 getInstance() {
		if (model1 == null)
			model1 = new Model2();
		return model1;
	}

	public void dBselectAssetclasses() {
		String url = "jdbc:mysql://localhost:3306/sql2223131";
		String username = "sql2223131";
		String password = "lY1*vS6*";

		System.out.println("Connecting database...");

		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			System.out.println("Database connected!");
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM PB_ASSETCLASS_DATA ORDER BY AC_ID");
			// SELECT
			// Statement stmt = connection.createStatement(); //Statment erzeugen
			// stmt.executeUpdate("CREATE TABLE tasks(id INT, projectid INT, title
			// VARCHAR(35), description VARCHAR(3000), creation_date DATE, deadline_date
			// DATE, author VARCHAR(35), responsible_user VARCHAR(35), type VARCHAR(35),
			// state VARCHAR(35)) engine=innodb;");

			// INSERT INTO tasks VALUES (101, 3, "test1", "description1", "2017-12-29",
			// "2018-02-01", "user1", "user1", "feature", "open");
			// INSERT INTO tasks VALUES (102, 8, "test2", "description2", "2017-12-29",
			// "2018-02-01", "user1", "user3", "feature", "closed");
			// INSERT INTO tasks VALUES (103, 8, "test3", "description3", "2017-12-29",
			// "2018-02-01", "user2", "user4", "bug" ,"open");
			// INSERT INTO tasks VALUES (104, 8, "test4", "description1", "2017-12-29",
			// "2018-02-01", "user2", "user5", "design", "open");
			System.out.println("Content of tasks:");

			// ResultSet rs =stmt.executeQuery("SELECT * FROM USERACCOUNT ORDER BY id");
			// //Abfrage absetzen
			//
			// while(rs.next()) //über die Zeilen gehen
			// System.out.println(rs.getString("id") + " " + rs.getString("projectid")+ " "
			// + rs.getString("title")+ " " +
			// rs.getString("description")+ " " + rs.getString("creation_date")+ " " +
			// rs.getString("deadline_date")+ " " + rs.getString("author")
			// + " " + rs.getString("responsible_user")+ " " + rs.getString("type")+ " " +
			// rs.getString("state")); // Werte auslesen
			//
			// while(rs.next()) //über die Zeilen gehen
			// System.out.println(rs.getInt("id") + " " + rs.getInt("projectid")+ " " +
			// rs.getString("title")+ " " +
			// rs.getString("description")+ " " + rs.getDate("creation_date")+ " " +
			// rs.getDate("deadline_date")+ " " + rs.getString("author")
			// + " " + rs.getString("responsible_user")+ " " + rs.getString("type")+ " " +
			// rs.getString("state")); // Werte auslesen
			// INSERT
			// lol stmt.executeUpdate("INSERT INTO tasks VALUES (101, 3, \"test1\",
			// \"description1\", \"2017-12-29\", \"2018-02-01\", \"user1\", \"user1\",
			// \"feature\", \"open\");");
			// stmt.executeUpdate("INSERT INTO tasks VALUES (102, 8, \"test2\",
			// \"description2\", \"2017-12-29\", \"2018-02-01\", \"user1\", \"user3\",
			// \"feature\", \"closed\");");
			// stmt.executeUpdate("INSERT INTO tasks VALUES (103, 8, \"test3\",
			// \"description3\", \"2017-12-29\", \"2018-02-01\", \"user2\", \"user4\",
			// \"bug\" ,\"open\");");
			// stmt.executeUpdate("INSERT INTO tasks VALUES (104, 8, \"test4\",
			// \"description1\", \"2017-12-29\", \"2018-02-01\", \"user2\", \"user5\",
			// \"design\", \"open\");");
			// stmt.executeUpdate("INSERT INTO tasks VALUES (105, 8, \"test5\",
			// \"description2\", \"2017-12-29\", \"2018-02-01\", \"user3\", \"user3\",
			// \"feature\", \"closed\");");

			// ResultSet rs =stmt.executeQuery("SELECT * FROM tasks ORDER BY id"); //Abfrage
			// absetzen

			System.out.println("Content of tasks after insert:");
			while (rs.next()) {// über die Zeilen gehen
				Assetclass ac1 = new Assetclass(rs.getInt("AC_ID"), rs.getString("NAME"), rs.getDouble("SIGMA"),
						rs.getDouble("RISK"));
				model1.assetclasses.put(rs.getInt("AC_ID"), ac1);
				System.out.println(ac1.toString());
			}
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + model1.assetclasses.size());
			// //DELETE
			// stmt.executeUpdate("DELETE FROM tasks WHERE id=105;");
			//
			// rs =stmt.executeQuery("SELECT * FROM tasks ORDER BY id"); //Abfrage absetzen
			//
			// System.out.println("Content of tasks after delete:");
			// while(rs.next()) //über die Zeilen gehen
			// System.out.println(rs.getInt("id") + " " + rs.getInt("projectid")+ " " +
			// rs.getString("title")+ " " +
			// rs.getString("description")+ " " + rs.getDate("creation_date")+ " " +
			// rs.getDate("deadline_date")+ " " + rs.getString("author")
			// + " " + rs.getString("responsible_user")+ " " + rs.getString("type")+ " " +
			// rs.getString("state")); // Werte auslesen
			//

			System.out.println("a");

			// Statement st;

			// Vector data = new Vector();
			//// try {
			//// st = connection.createStatement();
			//// ResultSet res = st.executeQuery("SELECT col_name FROM table_name");
			// ResultSetMetaData metaData = rs.getMetaData();
			// int columns = metaData.getColumnCount();
			// while (rs.next()) {System.out.println("b");
			// Vector row = new Vector(columns);
			// for (int i = 1; i <= columns; i++) {
			// row.addElement(rs.getObject(i));
			// }
			// data.addElement(row);
			// }
			//// } catch (SQLException e) {
			//// e.printStackTrace();
			//// }
			//
			// System.out.println("c");
			// Vector columnNames = new Vector();
			// columnNames.addElement("col_1");
			// columnNames.addElement("col_name_n");
			// jTable1 = new JTable(data,columnNames);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}