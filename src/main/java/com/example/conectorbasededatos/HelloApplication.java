package com.example.conectorbasededatos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setMinWidth(900);
        stage.setMinHeight(600);
        scene.getStylesheets().add
                (HelloApplication.class.getResource("css/estilo.css").toExternalForm());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}