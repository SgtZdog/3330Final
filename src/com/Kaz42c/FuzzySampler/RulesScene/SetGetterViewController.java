package com.Kaz42c.FuzzySampler.RulesScene;

import com.Kaz42c.FuzzySampler.TextFieldFilters;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public abstract class SetGetterViewController extends VBox {
    protected static char defaultName = 'A';

    protected final Label label = new Label();
    protected final TextField nameText = new TextField();
    protected final Button deleteButton = new Button();

    protected RulesSceneController rulesSceneController;

    public SetGetterViewController(RulesSceneController rulesSceneController) {
        super();
        this.rulesSceneController = rulesSceneController;
        setupNameText();
        if (defaultName == 'z' + 1) {
            defaultName = 'A';
        }
        setupDeleteButton();
        HBox elementDetails = new HBox();
        elementDetails.getChildren().addAll(label, nameText);
        this.getChildren().addAll(elementDetails, deleteButton);
    }

    private void setupDeleteButton() {
        deleteButton.setOnAction(this::deleteModel);
    }

    private void setupNameText() {
        nameText.setText(String.valueOf(defaultName++));
        nameText.textProperty().addListener(this::updateModelName);
        nameText.setPromptText("Please name the item.");
        nameText.setTextFormatter(new TextFormatter<>(TextFieldFilters::letterFilter));
        nameText.lengthProperty().addListener(TextFieldFilters.lengthFilterGenerator(nameText));
    }

    abstract void updateModelName(Object observable, String oldValue, String newValue);

    abstract void deleteModel(ActionEvent actionEvent);

    public abstract void updateDropdowns();
}
