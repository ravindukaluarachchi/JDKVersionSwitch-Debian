/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdkversionswitch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author ravindu
 */
public class JDKVersionSwitch extends Application {
    
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
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        TableView table = new TableView();
        table.setEditable(true);
        
        TableColumn<String,CheckBox> column1 = new TableColumn("");
        //column1.setCellFactory(CheckBoxTableCell.forTableColumn(TableColumn<String, CheckBox> param) -> new CheckBoxTableCell<>());
        column1.setCellValueFactory(new MapValueFactory("1"));
        column1.setEditable(true);
        column1.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<String,CheckBox>>() {

            @Override
            public void handle(TableColumn.CellEditEvent<String,CheckBox> event) {
                System.out.println("--> " + event.getNewValue().isSelected());
                 
            }
        });
        
        TableColumn column2 = new TableColumn("JDK Version");
        column2.setCellFactory(TextFieldTableCell.forTableColumn());
        column2.setCellValueFactory(new MapValueFactory("2"));
        column2.setMinWidth(400);
        column2.setEditable(true);
        
        column2.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<String,String>>() {

            @Override
            public void handle(TableColumn.CellEditEvent<String,String> event) {
                System.out.println("--> " + event.getNewValue());
                 
            }
        });
        
       
        
 
        
        table.getColumns().setAll(column1,column2);
        root.getChildren().add(table);
        
        
        getData().stream().forEach((map) -> {
            table.getItems().add(map);
        });
        
        
        primaryStage.setMinHeight(300);
        primaryStage.setMinWidth(500);
        primaryStage.setResizable(false);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    private List<Map> getData(){
        Map m1 = new HashMap();
        m1.put("1", true);
        m1.put("2", "open jdk 7");
        
        Map m2 = new HashMap();
        m2.put("1", false);
        m2.put("2", "oracle jdk 8");
        
        return Arrays.asList(m1,m2 );
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*try {
            Process proc = Runtime.getRuntime().exec(" update-alternatives --config javac ");
            InputStream in = proc.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while((line = br.readLine()) != null){
                System.out.println(line);
            }
            
            proc.getOutputStream().write(2);
            proc.getOutputStream().flush();
            
            while((line = br.readLine()) != null){
                System.out.println(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(JDKVersionSwitch.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        launch(args);
    }
    
}
