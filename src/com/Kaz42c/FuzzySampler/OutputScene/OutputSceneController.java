package com.Kaz42c.FuzzySampler.OutputScene;

import com.Kaz42c.FuzzySampler.Fuzzy.Defuzzifier;
import com.Kaz42c.FuzzySampler.Fuzzy.DomainMembership;
import com.Kaz42c.FuzzySampler.Main;
import com.Kaz42c.FuzzySampler.ResizableFISSceneController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class OutputSceneController extends ResizableFISSceneController implements Initializable {
    private static OutputSceneController instance;
    private final List<OutputViewController> outputViewControllers = new LinkedList<>();
    @FXML
    VBox Inputs;
    @FXML
    VBox Outputs;

    public static OutputSceneController getInstance() {
        return instance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
    }

    public void update() {
        Inputs.getChildren().clear();
        Outputs.getChildren().clear();
        outputViewControllers.clear();
        for (DomainMembership input :
                model.getInputs()) {
            Inputs.getChildren().add(new InputViewController(input));
        }
        for (Defuzzifier defuzzifier :
                model.getDefuzzifiers()) {
            OutputViewController outputViewController = new OutputViewController(defuzzifier);
            outputViewControllers.add(outputViewController);
            Outputs.getChildren().add(outputViewController);
        }
    }

    public void updateOutputs() {
        for (OutputViewController outputViewController :
                outputViewControllers) {
            outputViewController.updateValue();
        }
    }

    @FXML
    public void goToRules() {
        Main.stage.setScene(Main.rulesScene);
    }

    @FXML
    public void helpButton() {
        new Alert(Alert.AlertType.INFORMATION, "Application written by Kevin Zemon.", ButtonType.CLOSE).showAndWait();
    }
}
