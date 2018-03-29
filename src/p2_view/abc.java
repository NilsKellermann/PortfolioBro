package p2_view;

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

public class abc extends Application {

    private DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE ;

    @Override
    public void start(Stage primaryStage) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        ObservableList<Data<String, Number>> data = FXCollections.observableArrayList() ;
        ObservableList<Data<String, Number>> data7 = FXCollections.observableArrayList() ;
        SortedList<Data<String, Number>> sortedData = new SortedList<>(data, (data1, data2) -> 
                data1.getXValue().compareTo(data2.getXValue()));
        SortedList<Data<String, Number>> sortedData2 = new SortedList<>(data7, (data1, data2) -> 
        		data1.getXValue().compareTo(data2.getXValue()));
        LineChart<String, Number> chart = new LineChart<>(xAxis, yAxis);
        chart.getData().add(new Series<>(sortedData));
        chart.getData().add(new Series<>(sortedData2));
        chart.setAnimated(false);

        final int dayRange = 60 ;
        LocalDate today = LocalDate.now() ;
        Random rng = new Random();

//        m1.foreach
//        Date input = //new Date();
//        LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        for (int i = 0; i < 20 ; i++) {
            LocalDate date = today.minusDays(rng.nextInt(dayRange));
            String formattedDate = formatter.format(date);
            double value = rng.nextDouble() ;
    //    	LocalDate date = today.minusDays(rng.nextInt(dayRange));
//          String formattedDate = formatter.format(date);
//          double value = rng.nextDouble() ;

            addData(data, formattedDate, value);
       }    
            
            for (int i = 0; i < 20 ; i++) {
                LocalDate date7 = today.minusDays(rng.nextInt(dayRange));
                String formattedDate = formatter.format(date7);
                double value = rng.nextDouble() ;

                addData(data7, formattedDate, value);
        }

        DatePicker datePicker = new DatePicker();
        Spinner<Double> valuePicker = new Spinner<>(0.0, 1.0, 0, 0.1);
        valuePicker.setEditable(true);

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addData(data, formatter.format(datePicker.getValue()), valuePicker.getValue()));

        HBox controls = new HBox(5, datePicker, valuePicker, addButton);
        controls.setAlignment(Pos.CENTER);
        controls.setPadding(new Insets(5));

        BorderPane root = new BorderPane(chart, null, null, controls, null);
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }

    private void addData(ObservableList<Data<String, Number>> data, String formattedDate, double value) {
        Data<String, Number> dataAtDate = data.stream()
            .filter(d -> d.getXValue().equals(formattedDate))
            .findAny()
            .orElseGet(() -> {
                Data<String, Number> newData = new Data<String, Number>(formattedDate, 0.0);
                data.add(newData);
                return newData ;
            }) ;
        dataAtDate.setYValue(dataAtDate.getYValue().doubleValue() + value);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
