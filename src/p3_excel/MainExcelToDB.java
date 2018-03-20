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
import p4_excelPOI.ExcelToSListCList;
import p4_excelPOI.ExcelToYearCourseShares2;
import p4_excelPOI.ExcelToYearCourseShares3;

public class MainExcelToDB {
	static HashMap<String, HashMap<String, String>> ShareDaten;
	static HashMap<String, HashMap<String, Double>> allSharesRisRen;
	static HashMap<String, HashMap<Date, Double>> allSharesHalfyearCourses;
	// static HashMap <String, HashMap<Date, Double>> allSharesCourses;

	 static HashMap<String, HashMap<String, String>> CommoditiesDaten;
	 static HashMap<String, HashMap<String, Double>> allCommoditiesRisRen;
	 static HashMap <String, HashMap<Date, Double>> allCommoditiesHalfyearCourses;
	// static HashMap <String, HashMap<Date, Double>> allCommoditiesCourses;

	public static void main(String[] args) {
		ShareDaten = ExcelToSListCList.getListS();
		allSharesRisRen = ExcelToYearCourseShares3.getListS();
		allSharesHalfyearCourses = ExcelToYearCourseShares2.getListS();
		// allSharesCourses =ExcelToYearCourseShares.getListS();

		 CommoditiesDaten = ExcelToSListCList.getListC();
		 allCommoditiesRisRen = ExcelToYearCourseShares3.getListC();
		 allCommoditiesHalfyearCourses = ExcelToYearCourseShares2.getListC();
		// allCommoditiesCourses =ExcelToYearCourseShares.getListC();

		uploadShareHeaders();
		uploadShareYearCourses();
		uploadCommHeaders();
		uploadCommYearCourses();
	}

	public static void uploadShareHeaders() {
		// String url = "jdbc:mysql://localhost:3306/sql2223131";
		// String username = "sql2223131";
		// String password = "lY1*vS6*";
		String url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11225625";
		String username = "sql11225625";
		String password = "WNjKXk31lH";
		System.out.println("Connecting database...");

		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			System.out.println("Database connected!");
			Statement stmt;
			Iterator it;
			Iterator it2;
			////////////////////////////////////////
			//////////////////////////////////////// ShareDaten durchlaufen
			stmt = connection.createStatement();
			it = ShareDaten.entrySet().iterator();
			int i = 1;
			while (it.hasNext()) {
				Map.Entry<String, HashMap<String, Double>> pair = (Map.Entry) it.next();
				System.out.println(pair.getKey() + " = " + pair.getValue());
				////////////////////////////////////////
				//////////////////////////////////////// Nebenbei aus allShareRisRen die Daten über Rendite und Risiko rausholen
				Double rendite = null;
				Double risk = null;
				if (allSharesRisRen.get(pair.getKey()) != null) {
					rendite = allSharesRisRen.get(pair.getKey()).get("Renditepa");
				}
				if (allSharesRisRen.get(pair.getKey()) != null) {
					risk = allSharesRisRen.get(pair.getKey()).get("Risikopa");
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
			it.remove(); // avoids a ConcurrentModificationException
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static void uploadShareYearCourses() {
		// String url = "jdbc:mysql://localhost:3306/sql2223131";
		// String username = "sql2223131";
		// String password = "lY1*vS6*";
		String url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11225625";
		String username = "sql11225625";
		String password = "WNjKXk31lH";
		System.out.println("Connecting database...");

		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			System.out.println("Database connected!");
			Statement stmt;
			Iterator it;
			Iterator it2;
			stmt = connection.createStatement();
			it = ShareDaten.entrySet().iterator();
			int i = 1;
			////////////////////////////////////////
			//////////////////////////////////////// ShareDaten durchlaufen
			while (it.hasNext()) {
				Map.Entry<String, HashMap<String, Double>> pair = (Map.Entry) it.next();
				System.out.println(pair.getKey() + " = " + pair.getValue());

				if (allSharesHalfyearCourses.get(pair.getKey()) != null) {
					System.out.println("yearCoursesSchleife1");
					String share1name = pair.getKey();
					HashMap<Date, Double> share1HalfyearData = allSharesHalfyearCourses.get(pair.getKey());
					it2 = share1HalfyearData.entrySet().iterator();
					int i2 = 1;
					Date date = null;
					Double course = null;
					////////////////////////////////////////
					//////////////////////////////////////// HalfyearData den jeweiligen Sharenamen-Hashmap rausholen---- &durchlaufen
					while (it2.hasNext()) {
						System.out.println("yearCoursesSchleife2");
						Map.Entry<Date, Double> pair2 = (Map.Entry) it2.next();
						Date dateUnbearbeitet = pair2.getKey();
						java.sql.Date dateBearbeitet = new java.sql.Date(dateUnbearbeitet.getTime());
						course = pair2.getValue();
						System.out.println(course);
						System.out.println(dateUnbearbeitet);
						
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
				it.remove(); // avoids a ConcurrentModificationException
				i = i + 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void uploadCommHeaders() {
		// String url = "jdbc:mysql://localhost:3306/sql2223131";
		// String username = "sql2223131";
		// String password = "lY1*vS6*";
		String url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11225625";
		String username = "sql11225625";
		String password = "WNjKXk31lH";
		System.out.println("Connecting database...");

		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			System.out.println("Database connected!");
			Statement stmt;
			Iterator it;
			Iterator it2;
			////////////////////////////////////////
			//////////////////////////////////////// ShareDaten durchlaufen
			stmt = connection.createStatement();
			it = CommoditiesDaten.entrySet().iterator();
			int i = 1;
			while (it.hasNext()) {
				Map.Entry<String, HashMap<String, Double>> pair = (Map.Entry) it.next();
				System.out.println(pair.getKey() + " = " + pair.getValue());
				////////////////////////////////////////
				//////////////////////////////////////// Nebenbei aus allShareRisRen die Daten über Rendite und Risiko rausholen
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
						+ pair.getValue().get("Kategorie") + "'," + rendite
						+ "," + risk + ");");
				if (!(risk == null || rendite == null)) {
					System.out.println("REPLACE INTO `PB_COMM_HEAD` VALUES ( " + i + ",'" + pair.getKey() + "','"
							+ pair.getValue().get("Kategorie") + "'," + rendite
							+ "," + risk + ");");
					stmt.execute("REPLACE INTO `PB_COMM_HEAD` VALUES ( " + i + ",'" + pair.getKey() + "','"
							+ pair.getValue().get("Kategorie") + "'," + rendite
							+ "," + risk + ");");
				}
				i = i + 1;
			}
			it.remove(); // avoids a ConcurrentModificationException
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void uploadCommYearCourses() {
		// String url = "jdbc:mysql://localhost:3306/sql2223131";
		// String username = "sql2223131";
		// String password = "lY1*vS6*";
		String url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11225625";
		String username = "sql11225625";
		String password = "WNjKXk31lH";
		System.out.println("Connecting database...");

		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			System.out.println("Database connected!");
			Statement stmt;
			Iterator it;
			Iterator it2;
			stmt = connection.createStatement();
			it = CommoditiesDaten.entrySet().iterator();
			int i = 1;
			////////////////////////////////////////
			//////////////////////////////////////// CommoditiesDaten durchlaufen
			while (it.hasNext()) {
				Map.Entry<String, HashMap<String, Double>> pair = (Map.Entry) it.next();
				System.out.println(pair.getKey() + " = " + pair.getValue());

				if (allCommoditiesHalfyearCourses.get(pair.getKey()) != null) {
					System.out.println("yearCoursesSchleife1");
					String share1name = pair.getKey();
					HashMap<Date, Double> share1HalfyearData = allCommoditiesHalfyearCourses.get(pair.getKey());
					it2 = share1HalfyearData.entrySet().iterator();
					int i2 = 1;
					Date date = null;
					Double course = null;
					////////////////////////////////////////
					//////////////////////////////////////// HalfyearData den jeweiligen Commoditynamen-Hashmap rausholen---- &durchlaufen
					while (it2.hasNext()) {
						System.out.println("yearCoursesSchleife2");
						Map.Entry<Date, Double> pair2 = (Map.Entry) it2.next();
						Date dateUnbearbeitet = pair2.getKey();
						java.sql.Date dateBearbeitet = new java.sql.Date(dateUnbearbeitet.getTime());
						course = pair2.getValue();
						System.out.println(course);
						System.out.println(dateUnbearbeitet);
						
						////////////////////////////////////////
						//////////////////////////////////////// DB UPLOAD
						if (!(dateBearbeitet == null || course == null)) {
							System.out.println("REPLACE INTO `PB_COMM_COURSE` VALUES ( " + i + ",'" + share1name
									+ "','" + dateBearbeitet + "','" + course + ");");
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
				it.remove(); // avoids a ConcurrentModificationException
				i = i + 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
