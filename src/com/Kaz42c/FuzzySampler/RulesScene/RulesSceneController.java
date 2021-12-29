package com.Kaz42c.FuzzySampler.RulesScene;

import com.Kaz42c.FuzzySampler.Main;
import com.Kaz42c.FuzzySampler.OutputScene.OutputSceneController;
import com.Kaz42c.FuzzySampler.ResizableFISSceneController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class RulesSceneController extends ResizableFISSceneController implements Initializable {
    private static RulesSceneController instance;
    @FXML
    VBox Inputs;
    @FXML
    VBox ASets;
    @FXML
    VBox BSets;
    @FXML
    VBox InputOperators;
    @FXML
    VBox Rules;
    @FXML
    VBox Aggregators;
    @FXML
    VBox Defuzzifiers;

    public static RulesSceneController getInstance() {
        return instance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
    }

    @FXML
    public void goToOutput() {
        OutputSceneController.getInstance().update();
        Main.stage.setScene(Main.outputScene);
    }

    @FXML
    public void addInput() {
        Inputs.getChildren().add(new InputSetupViewController(this));
        reSize();
    }

    @FXML
    public void addASet() {
        ASets.getChildren().add(new ASetViewController(this));
        reSize();
    }

    @FXML
    public void addBSet() {
        BSets.getChildren().add(new BSetViewController(this));
        reSize();
    }

    @FXML
    public void addInputOperator() {
        InputOperators.getChildren().add(new InputOperatorViewController(this));
        reSize();
    }

    @FXML
    public void addRule() {
        Rules.getChildren().add(new FuzzyRuleViewController(this));
        reSize();
    }

    @FXML
    public void addAggregator() {
        Aggregators.getChildren().add(new AggregatorViewController(this));
        reSize();
    }

    @FXML
    public void addDefuzzifier() {
        Defuzzifiers.getChildren().add(new DefuzzifierViewController(this));
        reSize();
    }

    @FXML
    public void helpButton() {
        new Alert(Alert.AlertType.INFORMATION, "Application written by Kevin Zemon.", ButtonType.CLOSE).showAndWait();
    }

    public void updateAllDropdowns() {
        for (Node node : Inputs.getChildren()) {
            ((SetGetterViewController) node).updateDropdowns();
        }
        for (Node node : ASets.getChildren()) {
            ((SetGetterViewController) node).updateDropdowns();
        }
        for (Node node : BSets.getChildren()) {
            ((SetGetterViewController) node).updateDropdowns();
        }
        for (Node node : InputOperators.getChildren()) {
            ((SetGetterViewController) node).updateDropdowns();
        }
        for (Node node : Rules.getChildren()) {
            ((SetGetterViewController) node).updateDropdowns();
        }
        for (Node node : Aggregators.getChildren()) {
            ((SetGetterViewController) node).updateDropdowns();
        }
        for (Node node : Defuzzifiers.getChildren()) {
            ((SetGetterViewController) node).updateDropdowns();
        }
    }
}
