package com.Kaz42c.FuzzySampler;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    public static Stage stage;
    public static Scene rulesScene;
    public static Scene outputScene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        primaryStage.setTitle("Fuzzy Sampler");
        rulesScene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("RulesScene/RulesScene.fxml")));
        rulesScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("fuzzy.css")).toExternalForm());
        outputScene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("OutputScene/OutputScene.fxml")));
        outputScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("fuzzy.css")).toExternalForm());
        primaryStage.setScene(rulesScene);
        primaryStage.show();
    }
}
