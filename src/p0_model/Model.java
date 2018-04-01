package p0_model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import p0_db_objects.Rohstoff;
import p0_db_objects.RohstoffTableEntry;
import p0_db_objects.Aktie;
import p0_db_objects.AktieTableEntry;
import p0_db_objects.AnlageKlasse;
import p0_db_objects.Portfolio;
import p0_db_objects.PortfolioTableEntry;
import p0_db_objects.Rohstoff;
import p0_db_objects.RohstoffTableEntry;
import test.__________User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Model {
	private static Model model1;
	
	// Allgemein
	public int loggedInUser_id;
	public HashMap<Integer, AnlageKlasse> assetclasses = new HashMap<Integer, AnlageKlasse>();

	// V_Portfolios
	public HashMap<Integer, Portfolio> allPortfolios = new HashMap<Integer, Portfolio>();
	public HashMap<Integer, Portfolio> yourPortfolios = new HashMap<Integer, Portfolio>();
	public ObservableList<PortfolioTableEntry> allPortfolioTE = FXCollections.observableArrayList();
	public ObservableList<PortfolioTableEntry> yourPortfolioTE = FXCollections.observableArrayList();
	// public PortfolioTableEntry newPortfolio;
	public PortfolioTableEntry selectedPortfolio;

	
		// SHARES
	//---Portfolio bearbeiten in V_AssetClasses, V_Shares, V_Aktienanalyse---
	public Portfolio usedPortfolio;
	public HashMap<Integer, Aktie> currentPortfoliosAktienMitKursen = new HashMap<Integer, Aktie>();
	public HashMap<Integer, Double> currentPortfoliosAktienProzente = new HashMap<Integer, Double>();
	public ObservableList<AktieTableEntry> currentPortfoliosAktienMitKursenTE = FXCollections.observableArrayList();

	// V_SHARES
	public HashMap<Integer, Aktie> allAktienOhneKurse = new HashMap<Integer, Aktie>();
	public ObservableList<AktieTableEntry> allAktienOhneKurseTE = FXCollections.observableArrayList();
	public AktieTableEntry selectedAktie;
	public String selectedCurrentSharesString;

	// AktienAnalyse
	public HashMap<Integer, Boolean> analyseErgebnis = new HashMap<Integer, Boolean>();
	public String selectedCurrentSharesStringAnalyse1;
	
	
	// COMMODITIES
	//---Portfolio bearbeiten in V_AssetClasses, V_Shares, V_Aktienanalyse---
	public HashMap<Integer, Rohstoff> currentPortfoliosRohstoffeMitKursen = new HashMap<Integer, Rohstoff>();
	public HashMap<Integer, Double> currentPortfoliosRohstoffeProzente = new HashMap<Integer, Double>();
	public ObservableList<RohstoffTableEntry> currentPortfolionRohstoffeMitKursenTE = FXCollections.observableArrayList();

	// V_COMMODITIES
	public HashMap<Integer, Rohstoff> allRohstoffeOhneKurse = new HashMap<Integer, Rohstoff>();
	public ObservableList<RohstoffTableEntry> allRohstoffeOhneKurseTE = FXCollections.observableArrayList();
	public RohstoffTableEntry selectedRohstoff;
	public String selectedCurrentCommodityString;

	// RohstoffeAnalyse
	public HashMap<Integer, Boolean> analyseErgebnis2 = new HashMap<Integer, Boolean>();
	public String selectedCurrentCommoditiesStringAnalyse1;
	
	//COULD HAVE
    //public ObservableList<__________User> userList = FXCollections.observableArrayList();
	
//Konstruktor
	public static Model getInstance() {
		if (model1 == null)
			model1 = new Model();
		return model1;
	}
	
	
//TabellenEntry-Listen konvertieren	
	public void transferCurrentCommoditiesHashmapToObservableList() {
		currentPortfoliosRohstoffeMitKursen
				.forEach((k, v) -> this.currentPortfolionRohstoffeMitKursenTE.add(new RohstoffTableEntry(v.getShare_id(),
						v.getName(), v.getIndustry(), "", v.getSigma(), v.getRisk(), null)));
	}

	public void transferAllCommoditiesHashmapToObservableList() {
		allRohstoffeOhneKurse.forEach((k, v) -> this.allRohstoffeOhneKurseTE.add(new RohstoffTableEntry(v.getShare_id(),
				v.getName(), v.getIndustry(), "", v.getSigma(), v.getRisk(), null)));
	}

	public void transferAllPortfolioHashmapToObservableList() {
		this.allPortfolioTE = FXCollections.observableArrayList();
		System.out.println("Liste wird in Tabellenform gebracht:" + this.allPortfolios);
		this.allPortfolios.forEach((k,
				v) -> this.allPortfolioTE.add(new PortfolioTableEntry(v.getPortfolio_id(), v.getName(), v.getUser_id(),
						v.getCapital(), v.getShare_dist(), v.getComm_dist(), v.getCurr_dist(), v.getEstate_dist(),
						v.getBond_dist() /*
											 * , v.getSigma_full(), v.getRisk_full(), v.getSigma_share(),
											 * v.getRisk_share(), v.getSigma_comm(), v.getRisk_comm()
											 */)));
		System.out.println("Liste wurde in Tabellenform gebracht:" + this.allPortfolioTE);
	}

//Methoden zur Bearbeitung der Model-Daten
	public int calculateNextPortfolioID() {

		final Comparator<Portfolio> comp = (p1, p2) -> Integer.compare(p1.getPortfolio_id(), p2.getPortfolio_id());
		Portfolio oldest = this.allPortfolios.values().stream().max(comp).get();
		return oldest.getPortfolio_id() + 1;
	}

	public void addToCurrentPRohstoffe(Integer i1, Rohstoff a1, Double d1) {
		this.currentPortfoliosRohstoffeMitKursen.put(i1, a1);
		this.currentPortfoliosRohstoffeProzente.put(i1, d1);
	}

	public void deleteFromCurrentPRohstoffe(Integer i1) {
		this.currentPortfoliosRohstoffeMitKursen.remove(i1);
		this.currentPortfoliosRohstoffeProzente.remove(i1);
	}
	
	
	
	//////////////////////////////////////////////
	//////////////////////////////////////////////
	// DB-Zugriffe
	//////////////////////////////////////////////
	//////////////////////////////////////////////
	public void initializeAssetClasses_allSHeads_allCHeads_allPortfolios() {
		// String url = "jdbc:mysql://localhost:3306/sql2223131";
		// String username = "sql2223131";
		// String password = "lY1*vS6*";
		String url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11225625";
		String username = "sql11225625";
		String password = "WNjKXk31lH";
		System.out.println("Connecting database...");

		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			System.out.println("Database connected!");
			Statement stmt = connection.createStatement();
			//////////////////////////////////////////////
			// LOAD FROM DATABASE: ALLE AKTIENHEADER IN ALL-AKTIENOHNEKURSE
			//////////////////////////////////////////////
			ResultSet rs = stmt.executeQuery("SELECT * FROM PB_COMM_HEAD");
			while (rs.next()) {// ueber die Zeilen gehen
				Rohstoff a1 = new Rohstoff(rs.getInt("COMM_ID"), rs.getString("NAME"), rs.getString("CATEGORY")
					, rs.getDouble("SIGMA"), rs.getDouble("RISK"), null);
				this.allRohstoffeOhneKurse.put(rs.getInt("COMM_ID"), a1);
				System.out.println(a1.toString());
			}
			System.out.println("Aktien geladen, Anzahl:" + this.allRohstoffeOhneKurse.size());

			//////////////////////////////////////////////
			// LOAD FROM DATABASE: FILL ALL-PORTFOLIOS
			//////////////////////////////////////////////
			rs = stmt.executeQuery("SELECT * FROM PB_PORTFOLIO");
			while (rs.next()) {// ueber die Zeilen gehen
				Portfolio p1 = new Portfolio(rs.getInt("PORTFOLIO_ID"), rs.getString("NAME"), rs.getInt("USER_ID"),
						rs.getDouble("INVESTMENT"), rs.getDouble("SHARE_DIST"), rs.getDouble("COMM_DIST"),
						rs.getDouble("CURR_DIST"), rs.getDouble("ESTATE_DIST"), rs.getDouble("BOND_DIST"),
						rs.getDouble("SIGMA_FULL"), rs.getDouble("RISK_FULL"), rs.getDouble("SIGMA_SHARE"),
						rs.getDouble("RISK_SHARE"), rs.getDouble("SIGMA_COMM"), rs.getDouble("RISK_COMM"));
				this.allPortfolios.put(rs.getInt("PORTFOLIO_ID"), p1);
				System.out.println(p1.toString());
			}

			this.transferAllPortfolioHashmapToObservableList();
			
			//////////////////////////////////////////////
			// LOAD FROM DATABASE: ALLE AKTIENHEADER IN ALL-AKTIENOHNEKURSE
			//////////////////////////////////////////////
			ResultSet rs2 = stmt.executeQuery("SELECT * FROM PB_SHARE_HEAD");
			while (rs2.next()) {// ueber die Zeilen gehen
				Aktie a1 = new Aktie(rs2.getInt("SHARE_ID"), rs2.getString("NAME"), rs2.getString("INDUSTRY"),
						rs2.getString("INDEX"), rs2.getDouble("SIGMA"), rs2.getDouble("RISK"), null);
				this.allAktienOhneKurse.put(rs2.getInt("SHARE_ID"), a1);
				System.out.println(a1.toString());
			}
			System.out.println("Aktien geladen, Anzahl:" + this.allAktienOhneKurse.size());

			//////////////////////////////////////////////
			// LOAD FROM DATABASE: FILL ALL-PORTFOLIOS
			//////////////////////////////////////////////
			rs2 = stmt.executeQuery("SELECT * FROM PB_PORTFOLIO");
			while (rs2.next()) {// ueber die Zeilen gehen
				Portfolio p1 = new Portfolio(rs2.getInt("PORTFOLIO_ID"), rs2.getString("NAME"), rs2.getInt("USER_ID"),
						rs2.getDouble("INVESTMENT"), rs2.getDouble("SHARE_DIST"), rs2.getDouble("COMM_DIST"),
						rs2.getDouble("CURR_DIST"), rs2.getDouble("ESTATE_DIST"), rs2.getDouble("BOND_DIST"),
						rs2.getDouble("SIGMA_FULL"), rs2.getDouble("RISK_FULL"), rs2.getDouble("SIGMA_SHARE"),
						rs2.getDouble("RISK_SHARE"), rs2.getDouble("SIGMA_COMM"), rs2.getDouble("RISK_COMM"));
				this.allPortfolios.put(rs2.getInt("PORTFOLIO_ID"), p1);
				System.out.println(p1.toString());
			}

			this.transferAllPortfolioHashmapToObservableList();
			
			//////////////////////////////////////////////
			// LOAD FROM DATABASE: FILL ASSETCLASSES
			//////////////////////////////////////////////
			rs = stmt.executeQuery("SELECT * FROM PB_ASSETCLASS_DATA ORDER BY AC_ID");
			while (rs.next()) {// ueber die Zeilen gehen
				AnlageKlasse ac1 = new AnlageKlasse(rs.getInt("AC_ID"), rs.getString("NAME"), rs.getDouble("SIGMA"),
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
	
	//BESCHREIBUNG:
	// Aufrufzeitpunkte:
	// -->Beim View-Wechsel von V_PORTFOLIO zu V_ASSETCLASS (Wenn neues Portf.
	//////erschaffen wird passiert nichts (die Schleife wird
	//////erst garnicht betreten))
	// -->Bei jeder Änderung der usedPortfolio-Shares
	//////////////////////////
	// DB AKTIEN DES USEDPORTFOLIOS LADEN
	//////////////////////////
	public void loadSelectedPortfolioData() {
		String url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11225625";
		String username = "sql11225625";
		String password = "WNjKXk31lH";
		System.out.println("Connecting database...");

		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			System.out.println("Database connected!");
			Statement stmt = connection.createStatement();
			//////////////////////////////////////////////
			// Alle Aktien von Portfolio Nr 1 Laden.
			//////////////////////////////////////////////
			//// 1.Mit PB_PORTF_SHARE Liste mit Ids der Aktien des Portfolios erstellen
			List<Integer> shareList = new ArrayList<Integer>();
			ResultSet rs = stmt.executeQuery(
					"SELECT * FROM PB_PORTF_COMM WHERE PORTFOLIO_ID =" + this.usedPortfolio.getPortfolio_id() + ";");// selectedPortfolio.getPortfolio_ID;
			while (rs.next()) {
				shareList.add(rs.getInt("COMM_ID"));
				this.currentPortfoliosRohstoffeProzente.put(rs.getInt("COMM_ID"), rs.getDouble("PERCENT"));
				System.out.println("" + shareList);
			}
			//// 2. SELECT-Befehl-String der nur diese Aktien-header aus der DB läd
			//// zusammensetzen
			String chosenSharesString = "COMM_ID=0";
			for (Integer i : shareList) {
				chosenSharesString = chosenSharesString + " OR COMM_ID =" + i.intValue() + " ";
			}
			System.out.println(chosenSharesString);
			//// 3. SELECT-Befehl asuführen
			rs = stmt.executeQuery("SELECT * FROM PB_COMM_HEAD WHERE " + chosenSharesString + ";");// selectedPortfolio.getPortfolio_ID;
			while (rs.next()) {
				this.currentPortfoliosRohstoffeMitKursen.put(rs.getInt("COMM_ID"),
						new Rohstoff(rs.getInt("COMM_ID"), rs.getString("NAME"), rs.getString("CATEGORY"), rs.getDouble("SIGMA"), rs.getDouble("RISK"), null));
				System.out.println("111111111111111111currentPortAkt" + currentPortfoliosRohstoffeMitKursen);
			}
			//////////////////////////////////////////////
			// ZU JEDER AKTIE DIE ERSTELLT WURDE AUCH DIE KURSLISTE LADEN UND ERSTELLEN.
			//////////////////////////////////////////////
			Iterator<Entry<Integer, Rohstoff>> it = currentPortfoliosRohstoffeMitKursen.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<Integer, Rohstoff> pair = it.next();
				System.out.println(pair.getKey() + " = " + pair.getValue());

				rs = stmt.executeQuery("SELECT * FROM PB_COMM_COURSE WHERE COMM_ID=" + pair.getKey() + ";");// selectedPortfolio.getPortfolio_ID;
				while (rs.next()) {
					currentPortfoliosRohstoffeMitKursen.get(pair.getKey()).addToHashOfHalfYear(rs.getDate("DATE"),
							rs.getDouble("COURSE"));
				}
				// it.remove(); // avoids a ConcurrentModificationException
				System.out.println("Aktuelles Portfolio und Aktien darin:" + this.currentPortfoliosRohstoffeMitKursen);
			}
			sortBothCurrentPortfoliosRohstoffe();
			//////////////////////////////////////////////
			// ENDE
			//////////////////////////////////////////////
			
			//////////////////////////////////////////////
			// Alle Aktien von Portfolio Nr 1 Laden.
			//////////////////////////////////////////////
			//// 1.Mit PB_PORTF_SHARE Liste mit Ids der Aktien des Portfolios erstellen
			List<Integer> shareList2 = new ArrayList<Integer>();
			ResultSet rs2 = stmt.executeQuery(
					"SELECT * FROM PB_PORTF_SHARE WHERE PORTFOLIO_ID =" + this.usedPortfolio.getPortfolio_id() + ";");// selectedPortfolio.getPortfolio_ID;
			while (rs2.next()) {
				shareList2.add(rs2.getInt("SHARE_ID"));
				this.currentPortfoliosAktienProzente.put(rs2.getInt("SHARE_ID"), rs2.getDouble("PERCENT"));
				System.out.println("" + shareList2);
			}
			//// 2. SELECT-Befehl-String der nur diese Aktien-header aus der DB läd
			//// zusammensetzen
			String chosenSharesString2 = "SHARE_ID=0";
			for (Integer i : shareList2) {
				chosenSharesString2 = chosenSharesString2 + " OR SHARE_ID =" + i.intValue() + " ";
			}
			System.out.println(chosenSharesString2);
			//// 3. SELECT-Befehl asuführen
			rs2 = stmt.executeQuery("SELECT * FROM PB_SHARE_HEAD WHERE " + chosenSharesString2 + ";");// selectedPortfolio.getPortfolio_ID;
			while (rs2.next()) {
				this.currentPortfoliosAktienMitKursen.put(rs2.getInt("SHARE_ID"),
						new Aktie(rs2.getInt("SHARE_ID"), rs2.getString("NAME"), rs2.getString("INDUSTRY"),
								rs2.getString("INDEX"), rs2.getDouble("SIGMA"), rs2.getDouble("RISK"), null));
				System.out.println("111111111111111111currentPortAkt" + currentPortfoliosAktienMitKursen);
			}
			//////////////////////////////////////////////
			// ZU JEDER AKTIE DIE ERSTELLT WURDE AUCH DIE KURSLISTE LADEN UND ERSTELLEN.
			//////////////////////////////////////////////
			Iterator<Entry<Integer, Aktie>> it2 = currentPortfoliosAktienMitKursen.entrySet().iterator();
			while (it2.hasNext()) {
				Map.Entry<Integer, Aktie> pair = it2.next();
				System.out.println(pair.getKey() + " = " + pair.getValue());

				rs2 = stmt.executeQuery("SELECT * FROM PB_SHARE_COURSE WHERE SHARE_ID=" + pair.getKey() + ";");// selectedPortfolio.getPortfolio_ID;
				while (rs2.next()) {
					currentPortfoliosAktienMitKursen.get(pair.getKey()).addToHashOfHalfYear(rs2.getDate("DATE"),
							rs2.getDouble("COURSE"));
				}
				// it.remove(); // avoids a ConcurrentModificationException
				System.out.println("Aktuelles Portfolio und Aktien darin:" + this.currentPortfoliosAktienMitKursen);
			}
			sortBothCurrentPortfoliosAktien();
			//////////////////////////////////////////////
			// ENDE
			//////////////////////////////////////////////
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	////////////////////////////////////////
	// DB NEUE SHARES INS PORTFOLIO- DB_UPDATE_METHODEN
	////////////////////////////////////////
	public void updatePB_PORTF_COMMwithPercents() {
		String url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11225625";
		String username = "sql11225625";
		String password = "WNjKXk31lH";
		System.out.println("Connecting database...");

		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			System.out.println("Database connected!");

			PreparedStatement statement;
			statement = connection.prepareStatement(
					"DELETE FROM PB_PORTF_COMM WHERE PORTFOLIO_ID =" + this.usedPortfolio.getPortfolio_id() + ";");
			System.out.println(
					"DELETE FROM PB_PORTF_COMM WHERE PORTFOLIO_ID =" + this.usedPortfolio.getPortfolio_id() + ";");
			statement.execute();
			for (Entry<Integer, Rohstoff> entry : this.currentPortfoliosRohstoffeMitKursen.entrySet()) {
				Rohstoff value = entry.getValue();

				System.out.println("REPLACE INTO `PB_PORTF_COMM` VALUES (1,neu,12);");
				statement = connection.prepareStatement("REPLACE INTO `PB_PORTF_COMM` VALUES ("
						+ this.usedPortfolio.getPortfolio_id() + "," + value.getShare_id() + ","
						+ (this.currentPortfoliosRohstoffeProzente.get(entry.getKey()) == null ? "0.0"
								: this.currentPortfoliosRohstoffeProzente.get(entry.getKey()))
						+ ");");
				statement.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	////////////////
	// DB UPDATE PORTFOLIO
	/////////////////
	public void updateDB_PORTFOLIO_updatePortfolioTE(Portfolio portf1) {
		String url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11225625";
		String username = "sql11225625";
		String password = "WNjKXk31lH";
		System.out.println("Connecting database...");
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			System.out.println("Database connected!");

			PreparedStatement statement;
			String String1 = "REPLACE INTO `PB_PORTFOLIO` VALUES (" + portf1.getPortfolio_id() + "," + portf1.getName()
					+ "," + portf1.getUser_id() + "," + portf1.getCapital() + "," + portf1.getShare_dist() + ","
					+ portf1.getComm_dist() + "," + portf1.getCurr_dist() + "," + portf1.getEstate_dist() + ","
					+ portf1.getBond_dist() + "," + portf1.getSigma_full() + "," + portf1.getRisk_full() + ","
					+ portf1.getSigma_share() + "," + portf1.getRisk_share() + "," + portf1.getSigma_comm() + ","
					+ portf1.getRisk_comm() + ");";
			System.out.println(String1);
			statement = connection.prepareStatement("REPLACE INTO `PB_PORTFOLIO` VALUES (" + portf1.getPortfolio_id()
					+ ",'" + portf1.getName() + "'," + portf1.getUser_id() + "," + portf1.getCapital() + ","
					+ portf1.getShare_dist() + "," + portf1.getComm_dist() + "," + portf1.getCurr_dist() + ","
					+ portf1.getEstate_dist() + "," + portf1.getBond_dist() + "," + portf1.getSigma_full() + ","
					+ portf1.getRisk_full() + "," + portf1.getSigma_share() + "," + portf1.getRisk_share() + ","
					+ portf1.getSigma_comm() + "," + portf1.getRisk_comm() + ");");
			statement.execute();
			// }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/////////// UPDATE PORTFOLIO_TE
		transferAllPortfolioHashmapToObservableList();
	}

	/////////////////////////////
	// DB UPDATE PORTFOLIO
	/////////////////////////////
	public void deleteDB_Portfolio_updatePortfolioTE(PortfolioTableEntry portfEntry1) {
		String url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11225625";
		String username = "sql11225625";
		String password = "WNjKXk31lH";
		System.out.println("Connecting database...");
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			System.out.println("Database connected!");
			PreparedStatement statement;
			String String1 = "DELETE from `PB_PORTFOLIO` where id=11111111111;";
			System.out.println(String1);
			statement = connection.prepareStatement(
					"DELETE from `PB_PORTFOLIO` where `PORTFOLIO_ID`=" + portfEntry1.get2Portfolio_id() + ";");
			statement.execute();
			// }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		transferAllPortfolioHashmapToObservableList();
	}

	////////////////////////////////////////
	// AKTIEN DES USED PORTFOLIOS -SORTIEREN METHODEN
	////////////////////////////////////////
	public void sortBothCurrentPortfoliosRohstoffe() {
		this.currentPortfoliosRohstoffeMitKursen = this.currentPortfoliosRohstoffeMitKursen.entrySet().stream()
				.sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
						(oldValue, newValue) -> oldValue, LinkedHashMap::new));
		this.currentPortfoliosRohstoffeProzente = this.currentPortfoliosRohstoffeProzente.entrySet().stream()
				.sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
						(oldValue, newValue) -> oldValue, LinkedHashMap::new));

	}
	
	//TabellenEntry-Listen konvertieren	
		public void transferCurrentShareHashmapToObservableList() {
			currentPortfoliosAktienMitKursen
					.forEach((k, v) -> this.currentPortfoliosAktienMitKursenTE.add(new AktieTableEntry(v.getShare_id(),
							v.getName(), v.getIndustry(), v.getIndex(), v.getSigma(), v.getRisk(), null)));
		}

		public void transferAllShareHashmapToObservableList() {
			allAktienOhneKurse.forEach((k, v) -> this.allAktienOhneKurseTE.add(new AktieTableEntry(v.getShare_id(),
					v.getName(), v.getIndustry(), v.getIndex(), v.getSigma(), v.getRisk(), null)));
		}

		public void addToCurrentPAktien(Integer i1, Aktie a1, Double d1) {
			this.currentPortfoliosAktienMitKursen.put(i1, a1);
			this.currentPortfoliosAktienProzente.put(i1, d1);
		}

		public void deleteFromCurrentPAktien(Integer i1) {
			this.currentPortfoliosAktienMitKursen.remove(i1);
			this.currentPortfoliosAktienProzente.remove(i1);
		}

		////////////////////////////////////////
		// DB NEUE SHARES INS PORTFOLIO- DB_UPDATE_METHODEN
		////////////////////////////////////////
		public void updatePB_PORTF_SHAREwithPercents() {
			String url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11225625";
			String username = "sql11225625";
			String password = "WNjKXk31lH";
			System.out.println("Connecting database...");

			try (Connection connection = DriverManager.getConnection(url, username, password)) {
				System.out.println("Database connected!");

				PreparedStatement statement;
				statement = connection.prepareStatement(
						"DELETE FROM PB_PORTF_SHARE WHERE PORTFOLIO_ID =" + this.usedPortfolio.getPortfolio_id() + ";");
				System.out.println(
						"DELETE FROM PB_PORTF_SHARE WHERE PORTFOLIO_ID =" + this.usedPortfolio.getPortfolio_id() + ";");
				statement.execute();
				for (Entry<Integer, Aktie> entry : this.currentPortfoliosAktienMitKursen.entrySet()) {
					Aktie value = entry.getValue();

					System.out.println("REPLACE INTO `PB_PORTF_SHARE` VALUES (1,neu,12);");
					statement = connection.prepareStatement("REPLACE INTO `PB_PORTF_SHARE` VALUES ("
							+ this.usedPortfolio.getPortfolio_id() + "," + value.getShare_id() + ","
							+ (this.currentPortfoliosAktienProzente.get(entry.getKey()) == null ? "0.0"
									: this.currentPortfoliosAktienProzente.get(entry.getKey()))
							+ ");");
					statement.execute();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}



		////////////////////////////////////////
		// AKTIEN DES USED PORTFOLIOS -SORTIEREN METHODEN
		////////////////////////////////////////
		public void sortBothCurrentPortfoliosAktien() {
			this.currentPortfoliosAktienMitKursen = this.currentPortfoliosAktienMitKursen.entrySet().stream()
					.sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
							(oldValue, newValue) -> oldValue, LinkedHashMap::new));
			this.currentPortfoliosAktienProzente = this.currentPortfoliosAktienProzente.entrySet().stream()
					.sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
							(oldValue, newValue) -> oldValue, LinkedHashMap::new));

		}

}