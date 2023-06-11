package com.example.marisa.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class WindowLoader extends Application {

    private static Scene scene;
    private static Object controller;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("FXMLMain"));
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static void openNewWindow(String fxml, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WindowLoader.class.getResource(fxml + ".fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(parent));
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent parent = fxmlLoader.load(WindowLoader.class.getResource(fxml + ".fxml").openStream());
        controller = fxmlLoader.getController();
        return parent;
    }

    public static void main(String[] args) {
        launch();
    }

    public static Object getController() {
        return controller;
    }
}