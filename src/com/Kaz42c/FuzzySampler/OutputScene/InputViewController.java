package com.Kaz42c.FuzzySampler.OutputScene;

import com.Kaz42c.FuzzySampler.Fuzzy.DomainMembership;
import com.Kaz42c.FuzzySampler.TextFieldFilters;
import javafx.beans.Observable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;

public class InputViewController extends HBox {
    protected final DomainMembership input;
    protected final Label inputName = new Label();
    protected final TextField inputValue = new TextField("0");

    InputViewController(DomainMembership input) {
        this.input = input;
        inputName.getStyleClass().add("nameLabel");
        inputName.setText(input.getName() + ": ");
        getChildren().addAll(inputName, inputValue);
        inputValue.textProperty().addListener(this::updateModelValue);
        inputValue.setTextFormatter(new TextFormatter<>(TextFieldFilters::numberFilter));
        inputValue.lengthProperty().addListener(TextFieldFilters.lengthFilterGenerator(inputValue));
    }

    private void updateModelValue(Observable observable) {
        input.domain = getInterpretedDouble(inputValue.getText());
        OutputSceneController.getInstance().updateOutputs();
    }

    private double getInterpretedDouble(String newValue) {
        double testValue;
        try {
            testValue = Double.parseDouble(newValue);
        } catch (NumberFormatException e) {
            testValue = 0;
        }
        return testValue;
    }
}
