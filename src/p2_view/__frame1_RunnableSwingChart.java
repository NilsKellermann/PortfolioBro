package p2_view;

import javax.swing.JPanel;

import p2_view.charts.PieChart3DDemo3;
import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
public class __frame1_RunnableSwingChart extends Application{

	    
	    @Override
	    public void start(Stage primaryStage) {
	        Button btn = new Button();
	        btn.setText("Say 'Hello World'");
	        btn.setOnAction(new EventHandler<ActionEvent>() {
	 
	            @Override
	            public void handle(ActionEvent event) {
	                System.out.println("Hello World!");
	            }
	        });
	        SwingNode sn1= new SwingNode();
	        
	        JPanel chartPanel = PieChart3DDemo3.createDemoPanel();
	        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
	        sn1.setContent(chartPanel);
	        
	        
	        StackPane root = new StackPane();
	        root.getChildren().add(sn1);

	 Scene scene = new Scene(root, 300, 250);

	        primaryStage.setTitle("Hello World!");
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    }
	 public static void main(String[] args) {
	        launch(args);
	    }
	}

