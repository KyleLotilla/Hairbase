/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hairplay.hairbase;

import com.hairplay.hairbase.customerListing.CustomerListingViewController;
import com.hairplay.hairbase.transaction.SQLiteTransactionManager;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.StageStyle;

public class MainApp extends Application{

    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainView.fxml"));
        loader.setController(new MainAppController(new SQLiteTransactionManager()));
        Pane mainTransactionView = (Pane) loader.load();
        
        Scene mainScene = new Scene(mainTransactionView, 800, 500);
        mainScene.getStylesheets().clear();
        mainScene.getStylesheets().add(getClass().getResource("styleSheet/mainStyle.css").toExternalForm());
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setTitle("Hairbase");
        primaryStage.setScene(mainScene);
        //primaryStage.setFullScreen(true);
        Image icon = new Image(getClass().getResourceAsStream("images/HAIRPLAY.png"));
        primaryStage.getIcons().add(icon);
        
        //primaryStage.setMaximized(true);
        primaryStage.show();
    }
    
    public static void loadApp(String[] args) {
        launch(args);
    }
}
