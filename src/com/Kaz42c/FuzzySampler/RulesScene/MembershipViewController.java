package com.Kaz42c.FuzzySampler.RulesScene;

import com.Kaz42c.FuzzySampler.Fuzzy.GaussianMembershipFunction;
import com.Kaz42c.FuzzySampler.Fuzzy.MembershipFunction;
import com.Kaz42c.FuzzySampler.Fuzzy.TrapezoidalMembershipFunction;
import com.Kaz42c.FuzzySampler.TextFieldFilters;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public abstract class MembershipViewController extends SetGetterViewController {
    protected MembershipFunction membershipFunction;
    protected ChoiceBox<String> membershipType;
    protected VBox membershipSettings;

    public MembershipViewController(RulesSceneController rulesSceneController) {
        super(rulesSceneController);
        setupTypeDropdown();
        initializeGaussianMembership();
    }

    protected void setupTypeDropdown() {
        membershipType = new ChoiceBox<>(FXCollections.observableArrayList("Gaussian", "Trapezoidal"));
        membershipType.setValue(membershipType.getItems().get(0));
        membershipType.getSelectionModel().selectedIndexProperty().addListener(this::updateModelType);
        this.getChildren().add(membershipType);
    }

    void updateModelType(Object observable, Number oldValue, Number newValue) {
        removeExistingMembership();
        switch (newValue.intValue()) {
            case 0:
                initializeGaussianMembership();
                break;
            case 1:
                initializeTrapezoidalMembership();
                break;
        }
        rulesSceneController.reSize();
    }

    abstract protected void removeExistingMembership();

    abstract protected void addNewMembership();

    private void initializeGaussianMembership() {
        generateNewMembershipSettingsBox();
        HBox tempHBox;
        TextField tempTextField;
        membershipFunction = new GaussianMembershipFunction(1d, 1d, .3d, null, nameText.getText());
        tempHBox = new HBox();
        membershipSettings.getChildren().add(tempHBox);
        tempTextField = buildInput(tempHBox, "A");
        tempTextField.textProperty().addListener((observable, oldValue, newValue) -> ((GaussianMembershipFunction) membershipFunction).set_a(getInterpretedDouble(newValue)));
        tempTextField.setText(String.valueOf(1d));
        tempHBox = new HBox();
        membershipSettings.getChildren().add(tempHBox);
        tempTextField = buildInput(tempHBox, "B");
        tempTextField.textProperty().addListener((observable, oldValue, newValue) -> ((GaussianMembershipFunction) membershipFunction).set_b(getInterpretedDouble(newValue)));
        tempTextField.setText(String.valueOf(1d));
        tempHBox = new HBox();
        membershipSettings.getChildren().add(tempHBox);
        tempTextField = buildInput(tempHBox, "C");
        tempTextField.textProperty().addListener((observable, oldValue, newValue) -> ((GaussianMembershipFunction) membershipFunction).set_c(getInterpretedDouble(newValue)));
        tempTextField.setText(String.valueOf(.3d));
        addNewMembership();
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

    private void initializeTrapezoidalMembership() {
        generateNewMembershipSettingsBox();
        HBox tempHBox;
        TextField tempTextField;
        membershipFunction = new TrapezoidalMembershipFunction(0d, 1d, 2d, 3d, null, nameText.getText());
        tempHBox = new HBox();
        membershipSettings.getChildren().add(tempHBox);
        tempTextField = buildInput(tempHBox, "A");
        tempTextField.textProperty().addListener((observable, oldValue, newValue) -> ((TrapezoidalMembershipFunction) membershipFunction).set_a(getInterpretedDouble(newValue)));
        tempTextField.setText(String.valueOf(0));
        tempHBox = new HBox();
        membershipSettings.getChildren().add(tempHBox);
        tempTextField = buildInput(tempHBox, "B");
        tempTextField.textProperty().addListener((observable, oldValue, newValue) -> ((TrapezoidalMembershipFunction) membershipFunction).set_b(getInterpretedDouble(newValue)));
        tempTextField.setText(String.valueOf(1));
        tempHBox = new HBox();
        membershipSettings.getChildren().add(tempHBox);
        tempTextField = buildInput(tempHBox, "C");
        tempTextField.textProperty().addListener((observable, oldValue, newValue) -> ((TrapezoidalMembershipFunction) membershipFunction).set_c(getInterpretedDouble(newValue)));
        tempTextField.setText(String.valueOf(2));
        tempHBox = new HBox();
        membershipSettings.getChildren().add(tempHBox);
        tempTextField = buildInput(tempHBox, "D");
        tempTextField.textProperty().addListener((observable, oldValue, newValue) -> ((TrapezoidalMembershipFunction) membershipFunction).set_d(getInterpretedDouble(newValue)));
        tempTextField.setText(String.valueOf(3));
        addNewMembership();
    }

    TextField buildInput(HBox hbox, String name) {
        TextField result = new TextField();
        hbox.getChildren().add(new Label(name + ": "));
        hbox.getChildren().add(result);
        result.setTextFormatter(new TextFormatter<>(TextFieldFilters::numberFilter));
        result.lengthProperty().addListener(TextFieldFilters.lengthFilterGenerator(result));
        return result;
    }

    private void generateNewMembershipSettingsBox() {
        this.getChildren().remove(membershipSettings);
        membershipSettings = new VBox();
        this.getChildren().add(membershipSettings);
    }

    @Override
    void updateModelName(Object observable, String oldValue, String newValue) {
        membershipFunction.setName(newValue);
    }

    @Override
    void deleteModel(ActionEvent actionEvent) {
        rulesSceneController.ASets.getChildren().remove(this);
        removeExistingMembership();
    }
}
