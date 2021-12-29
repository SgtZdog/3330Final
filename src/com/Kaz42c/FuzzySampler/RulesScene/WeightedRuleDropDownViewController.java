package com.Kaz42c.FuzzySampler.RulesScene;

import com.Kaz42c.FuzzySampler.Fuzzy.ISetGetter;
import com.Kaz42c.FuzzySampler.Fuzzy.WeightedSetGetter;
import com.Kaz42c.FuzzySampler.TextFieldFilters;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;

import java.util.List;

public class WeightedRuleDropDownViewController extends HBox {
    protected final Button deleteButton = new Button("Delete Weighted Rule");
    protected DomainMembershipPicker rulePicker;
    protected WeightedSetGetter weightedSetGetter;
    protected Label weightLabel = new Label("Weight: ");
    protected TextField weightInput = new TextField();
    protected RulesSceneController rulesSceneController;
    protected AggregatorViewController aggregatorViewController;

    public WeightedRuleDropDownViewController(List<? extends ISetGetter> setGetters,
                                              RulesSceneController rulesSceneController, AggregatorViewController aggregatorViewController) {
        this.rulesSceneController = rulesSceneController;
        this.weightedSetGetter = new WeightedSetGetter(null, 0);
        this.aggregatorViewController = aggregatorViewController;
        rulePicker = new DomainMembershipPicker(setGetters, "Rule: ", this::updateRule);
        updateRule(null, null, rulePicker.getChoices().getSelectionModel().getSelectedIndex());
        aggregatorViewController.aggregator.addWeightedSetGetter(weightedSetGetter);
        deleteButton.setOnAction(this::deleteModel);
        this.getChildren().addAll(rulePicker, weightLabel, weightInput, deleteButton);
        setupInput();
    }

    private void setupInput() {
        weightInput.textProperty().addListener(this::updateWeight);
        weightInput.setText(String.valueOf(1));
        weightInput.setPromptText("Please name the item.");
        weightInput.setTextFormatter(new TextFormatter<>(TextFieldFilters::numberFilter));
        weightInput.lengthProperty().addListener(TextFieldFilters.lengthFilterGenerator(weightInput));

    }

    private void updateWeight(Observable observable) {
        weightedSetGetter.setWeight(getInterpretedDouble(weightInput.getText()));
    }

    private void updateRule(Object observable, Number oldValue, Number newValue) {
        if (newValue != null && rulePicker != null) {
            String name = rulePicker.getChoices().getItems().get(((Integer) newValue));
            try {
                ISetGetter result = null;
                for (ISetGetter fuzzyRule :
                        rulesSceneController.getModel().getRules()) {
                    if (fuzzyRule.getName().equals(name)) {
                        result = fuzzyRule;
                        break;
                    }
                }
                weightedSetGetter.setSetGetter(result);
            } catch (IndexOutOfBoundsException exception) {
                System.err.println("Attempted to set aggregator without finding any Rules.");
            }
        }
    }

    void deleteModel(ActionEvent actionEvent) {
        aggregatorViewController.getChildren().remove(this);
        aggregatorViewController.aggregator.removeWeightedSetGetter(weightedSetGetter);
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

    public void updateList() {
        rulePicker.updateList();
    }
}
