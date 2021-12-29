package com.Kaz42c.FuzzySampler.RulesScene;

import com.Kaz42c.FuzzySampler.Fuzzy.AndInputOperator;
import com.Kaz42c.FuzzySampler.Fuzzy.ISetGetter;
import com.Kaz42c.FuzzySampler.Fuzzy.InputOperator;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;

public class InputOperatorViewController extends SetGetterViewController {
    protected DomainMembershipPicker ASetPicker;
    protected DomainMembershipPicker BSetPicker;
    protected ChoiceBox<String> operatorType;
    private InputOperator inputOperator;

    public InputOperatorViewController(RulesSceneController rulesSceneController) {
        super(rulesSceneController);
        label.setText("Input Operator Name: ");
        deleteButton.setText("Remove Input Operator");
        setupTypeDropdown();
        setupInputDropdown();
        initializeAndOperator();
    }

    protected void setupTypeDropdown() {
        operatorType = new ChoiceBox<>(FXCollections.observableArrayList("And", "Or"));
        operatorType.setValue(operatorType.getItems().get(0));
        operatorType.getSelectionModel().selectedIndexProperty().addListener(this::updateModelType);
        this.getChildren().add(operatorType);
    }

    private void setupInputDropdown() {
        ASetPicker = new DomainMembershipPicker(rulesSceneController.getModel().getASets(), "ASet 1: ", this::updateASet);
        updateASet(null, null, ASetPicker.getChoices().getSelectionModel().getSelectedIndex());
        this.getChildren().add(ASetPicker);
        BSetPicker = new DomainMembershipPicker(rulesSceneController.getModel().getASets(), "ASet 2: ", this::updateBSet);
        updateBSet(null, null, BSetPicker.getChoices().getSelectionModel().getSelectedIndex());
        this.getChildren().add(BSetPicker);
        updateDropdowns();
    }

    void updateASet(Object observable, Number oldValue, Number newValue) {
        if (newValue != null && ASetPicker != null && inputOperator != null) {
            try {
                String name = ASetPicker.getChoices().getItems().get(((Integer) newValue));
                ISetGetter result = null;
                for (ISetGetter aSet :
                        rulesSceneController.getModel().getASets()) {
                    if (aSet.getName().equals(name)) {
                        result = aSet;
                        break;
                    }
                }
                inputOperator.set_aInputGetter(result);
            } catch (IndexOutOfBoundsException exception) {
                System.err.println("Attempted to set aggregator without finding any ASets.");
            }
        }
    }

    void updateBSet(Object observable, Number oldValue, Number newValue) {
        if (newValue != null && BSetPicker != null && inputOperator != null) {
            try {
                String name = BSetPicker.getChoices().getItems().get(((Integer) newValue));
                ISetGetter result = null;
                for (ISetGetter bSet :
                        rulesSceneController.getModel().getBSets()) {
                    if (bSet.getName().equals(name)) {
                        result = bSet;
                        break;
                    }
                }
                inputOperator.set_bInputGetter(result);
            } catch (IndexOutOfBoundsException exception) {
                System.err.println("Attempted to set aggregator without finding any ASets.");
            }
        }
    }

    void updateModelType(Object observable, Number oldValue, Number newValue) {
        removeExistingOperator();
        switch (newValue.intValue()) {
            case 0:
                initializeAndOperator();
                break;
            case 1:
                initializeOrOperator();
                break;
        }
    }

    void initializeAndOperator() {
        inputOperator = new AndInputOperator(null, null, nameText.getText());
        updateASet(null, null, ASetPicker.getChoices().getSelectionModel().selectedIndexProperty().getValue());
        updateBSet(null, null, BSetPicker.getChoices().getSelectionModel().selectedIndexProperty().getValue());
        addNewOperator();
    }

    void initializeOrOperator() {
        inputOperator = new AndInputOperator(null, null, nameText.getText());
        updateASet(null, null, ASetPicker.getChoices().getSelectionModel().selectedIndexProperty().getValue());
        updateBSet(null, null, BSetPicker.getChoices().getSelectionModel().selectedIndexProperty().getValue());
        addNewOperator();
    }

    @Override
    void updateModelName(Object observable, String oldValue, String newValue) {
        inputOperator.setName(newValue);
    }

    @Override
    void deleteModel(ActionEvent actionEvent) {
        rulesSceneController.InputOperators.getChildren().remove(this);
        removeExistingOperator();
    }

    private void removeExistingOperator() {
        rulesSceneController.getModel().removeInputOperator(inputOperator);
        rulesSceneController.updateAllDropdowns();
    }

    private void addNewOperator() {
        rulesSceneController.getModel().addInputOperator(inputOperator);
        rulesSceneController.updateAllDropdowns();
    }

    @Override
    public void updateDropdowns() {
        ASetPicker.updateList();
        BSetPicker.updateList();
    }
}
