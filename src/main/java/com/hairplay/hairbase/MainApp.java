/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hairplay.hairbase;

import com.hairplay.hairbase.transaction.MainTransactionController;
import com.hairplay.hairbase.transaction.SQLiteTransactionManager;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class MainApp extends Application{

    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainTransactionView.fxml"));
        loader.setController(new MainTransactionController(new SQLiteTransactionManager()));
        Pane mainTransactionView = (Pane) loader.load();

        primaryStage.setTitle("Hairbase");
        primaryStage.setScene(new Scene(mainTransactionView, 800, 500));
        primaryStage.show();
    }
    
    public static void loadApp(String[] args) {
        launch(args);
    }
}
