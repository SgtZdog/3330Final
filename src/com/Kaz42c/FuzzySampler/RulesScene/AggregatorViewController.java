package com.Kaz42c.FuzzySampler.RulesScene;

import com.Kaz42c.FuzzySampler.Fuzzy.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;

import java.util.ArrayList;
import java.util.List;

public class AggregatorViewController extends SetGetterViewController {
    protected Aggregator aggregator;
    protected ChoiceBox<String> operatorType;
    protected Button addWeightedRuleButton = new Button("Add Weighted Rule");
    List<WeightedSetGetter> weightedSetGetters = new ArrayList<>();


    public AggregatorViewController(RulesSceneController rulesSceneController) {
        super(rulesSceneController);
        label.setText("Aggregator Name: ");
        deleteButton.setText("Remove Aggregator");
        setupTypeDropdown();
        initializeSumAggregator();
        this.getChildren().add(addWeightedRuleButton);
        addWeightedRuleButton.setOnAction(this::addWeightedRule);
    }

    private void addWeightedRule(ActionEvent actionEvent) {
        List<FuzzyRule> rules = rulesSceneController.getModel().getRules();
        if (rules.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Create a rule and try again.", ButtonType.CLOSE).showAndWait();
        } else {
            this.getChildren().add(new WeightedRuleDropDownViewController(rules,
                    rulesSceneController, this));
            rulesSceneController.reSize();
        }
    }

    protected void setupTypeDropdown() {
        operatorType = new ChoiceBox<>(FXCollections.observableArrayList("Sum", "Max"));
        operatorType.setValue(operatorType.getItems().get(0));
        operatorType.getSelectionModel().selectedIndexProperty().addListener(this::updateModelType);
        this.getChildren().add(operatorType);
    }

    void updateModelType(Object observable, Number oldValue, Number newValue) {
        removeExistingAggregator();
        switch (newValue.intValue()) {
            case 0:
                initializeSumAggregator();
                break;
            case 1:
                initializeMaxAggregator();
                break;
        }
    }

    void initializeSumAggregator() {
        aggregator = new SumAggregator(weightedSetGetters, nameText.getText());
        updateNewAggregator();
    }

    void initializeMaxAggregator() {
        aggregator = new MaxAggregator(weightedSetGetters, nameText.getText());
        updateNewAggregator();
    }

    private void removeExistingAggregator() {
        rulesSceneController.getModel().removeAggregator(aggregator);
        rulesSceneController.updateAllDropdowns();
    }

    private void updateNewAggregator() {
        rulesSceneController.getModel().addAggregator(aggregator);
        rulesSceneController.updateAllDropdowns();
    }

    @Override
    void updateModelName(Object observable, String oldValue, String newValue) {
        aggregator.setName(newValue);
    }

    @Override
    void deleteModel(ActionEvent actionEvent) {
        rulesSceneController.Aggregators.getChildren().remove(this);
        rulesSceneController.getModel().removeAggregator(aggregator);
        rulesSceneController.updateAllDropdowns();
    }

    @Override
    public void updateDropdowns() {
        for (Node item :
                getChildren()) {
            if (item instanceof WeightedRuleDropDownViewController) {
                ((WeightedRuleDropDownViewController) item).updateList();
            }
        }
    }
}
