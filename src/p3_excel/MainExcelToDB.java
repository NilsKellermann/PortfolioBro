package p3_excel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import p4_excelPOI.ExcelPOI_Headerdata_S_C;
import p4_excelPOI.ExcelPOI_AllCourses_S_C;
import p4_excelPOI.ExcelPOI_AssetClasses;
import p4_excelPOI.ExcelPOI_HalfyearCourses_S_C;
import p4_excelPOI.ExcelPOI_Headerdata2_S_C;

public class MainExcelToDB extends Application implements ExcelPOI_AllCourses_S_C, ExcelPOI_AssetClasses,
		ExcelPOI_HalfyearCourses_S_C, ExcelPOI_Headerdata_S_C, ExcelPOI_Headerdata2_S_C {
	static HashMap<String, HashMap<String, String>> ShareDaten;
	static HashMap<String, HashMap<String, Double>> allSharesRisRen;
	static HashMap<String, HashMap<Date, Double>> allSharesHalfyearCourses;
	static HashMap<String, HashMap<Date, Double>> allSharesCourses;

	static HashMap<String, HashMap<String, String>> CommoditiesDaten;
	static HashMap<String, HashMap<String, Double>> allCommoditiesRisRen;
	static HashMap<String, HashMap<Date, Double>> allCommoditiesHalfyearCourses;
	static HashMap<String, HashMap<Date, Double>> allCommoditiesCourses;

	Label l1 = new Label(
			"In dieser simplen GUI werden die Header- und Kurs-Daten der Aktien und Rohstoffe aus den Excel-Dateien in die Datenbank geladen.");
	Label l2 = new Label("Der Upload dauert etwa eine halbe Minute.");
	Label l3 = new Label("");
	Label l4 = new Label("");

	public static void main(String[] args) {
		launch();

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("Excel-Import - GUI");

		FlowPane root = new FlowPane();
		root.setHgap(10);
		root.setVgap(10);
		root.setPadding(new Insets(15, 15, 15, 15));

		Button b1 = new Button("Upload starten");
		b1.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
		b1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				ShareDaten = ExcelPOI_Headerdata_S_C.getListS();
				allSharesRisRen = ExcelPOI_Headerdata2_S_C.getListS();
				l3.setText("Die Aktien-Header wurden in der Datenbank eingefügt.");
				allSharesHalfyearCourses = ExcelPOI_HalfyearCourses_S_C.getListS();
				// allSharesCourses = ExcelPOI_AllCourses_S_C.getListS();

				l3.setText(
						"Die Aktien-Header wurden in der Datenbank eingefügt. Die Aktien-Kurse wurden in der Datenbank eingefügt.");
				CommoditiesDaten = ExcelPOI_Headerdata_S_C.getListC();
				allCommoditiesRisRen = ExcelPOI_Headerdata2_S_C.getListC();
				l4.setText("Die Rohstoff-Header wurden in der Datenbank eingefügt.");
				allCommoditiesHalfyearCourses = ExcelPOI_HalfyearCourses_S_C.getListC();
				// allCommoditiesCourses =ExcelPOI_AllCourses_S_C.getListC();

				l4.setText(
						"Die Rohstoff-Header wurden in der Datenbank eingefügt. Die Rohstoff-Kurse wurden in der Datenbank eingefügt.");
				uploadShareHeaders();
				uploadShareYearCourses();
				uploadCommHeaders();
				uploadCommYearCourses();

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText("Die Daten in der Datenbank wurden aktualisiert.");
				alert.setContentText("");
				alert.showAndWait();
			}
		});

		VBox vbox = new VBox(20, b1, l1, l2, l3, l4);
		root.getChildren().add(vbox);

		primaryStage.setScene(new Scene(root, 1000, 150));
		primaryStage.show();

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void uploadShareHeaders() {
		// String url = "jdbc:mysql://localhost:3306/sql2223131";
		// String username = "sql2223131";
		// String password = "lY1*vS6*";
		String url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11225625";
		String username = "sql11225625";
		String password = "WNjKXk31lH";
		 

		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			 
			Statement stmt;
			Iterator it;
			////////////////////////////////////////
			//////////////////////////////////////// ShareDaten durchlaufen
			stmt = connection.createStatement();
			it = ShareDaten.entrySet().iterator();
			int i = 1;
			while (it.hasNext()) {
				Map.Entry<String, HashMap<String, Double>> pair = (Map.Entry) it.next();
				 
				////////////////////////////////////////
				//////////////////////////////////////// aus HashMap allSharesRisRen das Risiko
				//////////////////////////////////////// und die Rendite herausholen
				Double rendite = null;
				Double risk = null;
				if (allSharesRisRen.get(pair.getKey()) != null) {
					rendite = allSharesRisRen.get(pair.getKey()).get("Renditepa"); // !!Stimmt so, Name vertauscht beim
																					// ExcelImport
				}
				if (allSharesRisRen.get(pair.getKey()) != null) {
					risk = allSharesRisRen.get(pair.getKey()).get("Risikopa"); // " " "
				}
				////////////////////////////////////////
				//////////////////////////////////////// DB UPLOAD
				if (!(risk == null || rendite == null)) {
					System.out.println("REPLACE INTO `PB_SHARE_HEAD` VALUES ( " + i + ",'" + pair.getKey() + "','"
							+ pair.getValue().get("Branche") + "','" + pair.getValue().get("Index") + "'," + rendite
							+ "," + risk + ");");
					stmt.execute("REPLACE INTO `PB_SHARE_HEAD` VALUES ( " + i + ",'" + pair.getKey() + "','"
							+ pair.getValue().get("Branche") + "','" + pair.getValue().get("Index") + "'," + rendite
							+ "," + risk + ");");
				}
				i = i + 1;
			}
			it.remove();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void uploadShareYearCourses() {
		// String url = "jdbc:mysql://localhost:3306/sql2223131";
		// String username = "sql2223131";
		// String password = "lY1*vS6*";
		String url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11225625";
		String username = "sql11225625";
		String password = "WNjKXk31lH";
		 

		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			 
			Iterator it;
			Iterator it2;
			// stmt = connection.createStatement();
			it = ShareDaten.entrySet().iterator();
			int i = 1;
			////////////////////////////////////////
			//////////////////////////////////////// ShareDaten durchlaufen
			while (it.hasNext()) {
				Map.Entry<String, HashMap<String, Double>> pair = (Map.Entry) it.next();
				 

				if (allSharesHalfyearCourses.get(pair.getKey()) != null) {
					 
					String share1name = pair.getKey();
					HashMap<Date, Double> share1HalfyearData = allSharesHalfyearCourses.get(pair.getKey());
					it2 = share1HalfyearData.entrySet().iterator();
					int i2 = 1;
					// Date date = null;
					Double course = null;
					////////////////////////////////////////
					//////////////////////////////////////// HalfyearData konvertieren
					while (it2.hasNext()) {
						 
						Map.Entry<Date, Double> pair2 = (Map.Entry<Date, Double>) it2.next();
						Date dateUnbearbeitet = pair2.getKey();
						java.sql.Date dateBearbeitet = new java.sql.Date(dateUnbearbeitet.getTime());
						course = pair2.getValue();
						 
						 

						////////////////////////////////////////
						//////////////////////////////////////// DB UPLOAD
						if (!(dateBearbeitet == null || course == null)) {
							System.out.println("REPLACE INTO `PB_SHARE_COURSE` VALUES ( " + i + ",'" + share1name
									+ "','" + dateBearbeitet + "','" + course + ");");
							PreparedStatement statement = connection
									.prepareStatement("REPLACE INTO `PB_SHARE_COURSE` VALUES (?,?,?,0.0);");
							statement.setInt(1, i);
							statement.setDate(2, dateBearbeitet);
							statement.setDouble(3, course);
							statement.execute();
							i2 = i2 + 1;
						}
					}
				}
				it.remove();
				i = i + 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void uploadCommHeaders() {
		// String url = "jdbc:mysql://localhost:3306/sql2223131";
		// String username = "sql2223131";
		// String password = "lY1*vS6*";
		String url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11225625";
		String username = "sql11225625";
		String password = "WNjKXk31lH";
		 

		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			 
			Statement stmt;
			Iterator it;
			////////////////////////////////////////
			////////////////////////////////////////
			stmt = connection.createStatement();
			it = CommoditiesDaten.entrySet().iterator();
			int i = 1;
			while (it.hasNext()) {
				Map.Entry<String, HashMap<String, Double>> pair = (Map.Entry<String, HashMap<String, Double>>) it
						.next();
				 
				////////////////////////////////////////
				////////////////////////////////////////
				Double rendite = null;
				Double risk = null;
				if (allCommoditiesRisRen.get(pair.getKey()) != null) {
					rendite = allCommoditiesRisRen.get(pair.getKey()).get("Renditepa");
				}
				if (allCommoditiesRisRen.get(pair.getKey()) != null) {
					risk = allCommoditiesRisRen.get(pair.getKey()).get("Risikopa");
				}
				////////////////////////////////////////
				//////////////////////////////////////// DB UPLOAD
				System.out.println("REPLACE INTO `PB_COMM_HEAD` VALUES ( " + i + ",'" + pair.getKey() + "','"
						+ pair.getValue().get("Kategorie") + "'," + rendite + "," + risk + ");");
				if (!(risk == null || rendite == null)) {
					System.out.println("REPLACE INTO `PB_COMM_HEAD` VALUES ( " + i + ",'" + pair.getKey() + "','"
							+ pair.getValue().get("Kategorie") + "'," + rendite + "," + risk + ");");
					stmt.execute("REPLACE INTO `PB_COMM_HEAD` VALUES ( " + i + ",'" + pair.getKey() + "','"
							+ pair.getValue().get("Kategorie") + "'," + rendite + "," + risk + ");");
				}
				i = i + 1;
			}
			it.remove();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void uploadCommYearCourses() {
		// String url = "jdbc:mysql://localhost:3306/sql2223131";
		// String username = "sql2223131";
		// String password = "lY1*vS6*";
		String url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11225625";
		String username = "sql11225625";
		String password = "WNjKXk31lH";
		 

		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			 
			// Statement stmt;
			Iterator it;
			Iterator it2;
			// stmt = connection.createStatement();
			it = CommoditiesDaten.entrySet().iterator();
			int i = 1;
			////////////////////////////////////////
			//////////////////////////////////////// CommoditiesDaten durchlaufen
			while (it.hasNext()) {
				Map.Entry<String, HashMap<String, Double>> pair = (Map.Entry) it.next();
				 

				if (allCommoditiesHalfyearCourses.get(pair.getKey()) != null) {
					 
					String share1name = pair.getKey();
					HashMap<Date, Double> share1HalfyearData = allCommoditiesHalfyearCourses.get(pair.getKey());
					it2 = share1HalfyearData.entrySet().iterator();
					int i2 = 1;
					Double course = null;
					////////////////////////////////////////
					//////////////////////////////////////// entnehmen der HalfyearData aus
					//////////////////////////////////////// Commoditynamen-Hashmap ---- durchlaufen
					while (it2.hasNext()) {
						 
						Map.Entry<Date, Double> pair2 = (Map.Entry) it2.next();
						Date dateUnbearbeitet = pair2.getKey();
						java.sql.Date dateBearbeitet = new java.sql.Date(dateUnbearbeitet.getTime());
						course = pair2.getValue();
						 
						 

						////////////////////////////////////////
						//////////////////////////////////////// DB UPLOAD
						if (!(dateBearbeitet == null || course == null)) {
							System.out.println("REPLACE INTO `PB_COMM_COURSE` VALUES ( " + i + ",'" + share1name + "','"
									+ dateBearbeitet + "','" + course + ");");
							PreparedStatement statement = connection
									.prepareStatement("REPLACE INTO `PB_COMM_COURSE` VALUES (?,?,?,0.0);");
							statement.setInt(1, i);
							statement.setDate(2, dateBearbeitet);
							statement.setDouble(3, course);
							statement.execute();
							i2 = i2 + 1;
						}
					}
				}
				it.remove();
				i = i + 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
