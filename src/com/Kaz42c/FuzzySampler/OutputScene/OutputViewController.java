package com.Kaz42c.FuzzySampler.OutputScene;

import com.Kaz42c.FuzzySampler.Fuzzy.Defuzzifier;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class OutputViewController extends HBox {
    protected final Defuzzifier defuzzifier;
    protected final Label defuzzifierName = new Label();
    protected final Label outputValue = new Label("0");

    public OutputViewController(Defuzzifier defuzzifier) {
        this.defuzzifier = defuzzifier;
        defuzzifierName.getStyleClass().add("nameLabel");
        defuzzifierName.setText(defuzzifier.getName() + ": ");
        getChildren().addAll(defuzzifierName, outputValue);
    }

    public void updateValue() {
        try {
            outputValue.setText(String.valueOf(defuzzifier.get()[0].domain));
        } catch (NullPointerException | IllegalArgumentException e) {
            new Alert(Alert.AlertType.ERROR, "Looks like something didn't get set! Go back to rules and try again.", ButtonType.CLOSE).showAndWait();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Unknown exception occurred.", ButtonType.CLOSE).showAndWait();
        }
    }
}
