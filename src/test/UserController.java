package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import db_objects.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import p0_model.Model;

public class UserController {



	@FXML
	public static TextField tf_user_id=new TextField();
	@FXML
	public static TextField tf_username=new TextField();
	@FXML
	public static TableView<ObservableList<String>> tv_user=new TableView<ObservableList<String>>();
	@FXML
	public static TableView<User> tv_user2=new TableView<User>();
	@FXML
	public static Button bu_save=new Button();

	public void reload() {
		// String url = "jdbc:mysql://sql2.freemysqlhosting.net:3306/sql2223131";
		// String username = "sql2223131";
		// String password = "lY1*vS6*";
		//
		// System.out.println("Connecting database...");
		//
		//
		// try (Connection connection = DriverManager.getConnection(url
		// , username, password
		// )) {
		// System.out.println("Database connected!");
		//
		// //SELECT
		// Statement stmt = connection.createStatement(); //Statment erzeugen
		// ResultSet rs =stmt.executeQuery("SELECT * FROM tasks ORDER BY id"); //Abfrage
		// absetzen
		//
		// System.out.println("Content of tasks after insert:");
		// while(rs.next()) //über die Zeilen gehen
		// System.out.println(rs.getInt("id") + " " + rs.getInt("projectid")+ " " +
		// rs.getString("title")+ " " +
		// rs.getString("description")+ " " + rs.getDate("creation_date")+ " " +
		// rs.getDate("deadline_date")+ " " + rs.getString("author")
		// + " " + rs.getString("responsible_user")+ " " + rs.getString("type")+ " " +
		// rs.getString("state")); // Werte auslesen
		//
		// /* ResultSet*/ rs =stmt.executeQuery("SELECT * FROM tasks ORDER BY id");
		//
		// ResultSetMetaData md = rs.getMetaData();
		// int columnCount = md.getColumnCount();
		// Vector columns = new Vector(columnCount);
		//
		// //store column names
		// for(int i=1; i<=columnCount; i++)
		// columns.add(md.getColumnName(i));
		//
		// Vector data = new Vector();
		// Vector row;
		//
		// while (rs.next()) {
		//
		// row = new Vector(columnCount);
		// for(int i=1; i<=columnCount; i++)
		// {
		// row.add(rs.getString(i));
		// }
		// data.add(row);
		//
		// //Debugging
		// // System.out.println("First Name = " + rs.getString("FirstName"));
		// // System.out.println("Last Name = " + rs.getString("LastName"));
		// }
		//
		// //Display in JTable
		// DefaultTableModel tableModel = new DefaultTableModel(data, columns);
		// jTable1.setModel(tableModel);
		// // jTable1 = new JTable(data, columns);
		//
		// jScrollPane1= new JScrollPane(jTable1);
		// add(jScrollPane1);
		// System.out.println("d");
		// rs.close();
		// System.out.println("d");
		// stmt.close();
		// System.out.println("d");
		// connection.close();
		// System.out.println("d");
		//
		// } catch (SQLException e) {
		// System.out.println("SQLException: " + e.getMessage());
		// System.out.println("SQLState: " + e.getSQLState());
		// System.out.println("VendorError: " + e.getErrorCode());
		// throw new IllegalStateException("Cannot connect the database!", e);
		// }
		// }


		
//Userlist		

//		ObservableList <ObservableList<String>> data = FXCollections.observableArrayList();
//		ObservableList<String> row = FXCollections.observableArrayList();
//		for (User temp : Model.userList) { // Iterate Row
//			row.add("" + temp.user_id);
//			row.add(temp.username.toString());
//			row.add(temp.vorname.toString());
//			row.add(temp.nachname.toString());
//		}
//		System.out.println("Row [1] added " + row);
//		data.addAll(row);
//
//	//	this.tv_user.setItems(data);
//
//		this.tv_user2.setItems(Model.userList);
	}}
//}
