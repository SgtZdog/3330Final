package com.Kaz42c.FuzzySampler.RulesScene;

import com.Kaz42c.FuzzySampler.Fuzzy.DomainMembership;
import javafx.event.ActionEvent;

public class InputSetupViewController extends SetGetterViewController {
    private final DomainMembership input;

    public InputSetupViewController(RulesSceneController rulesSceneController) {
        super(rulesSceneController);
        input = new DomainMembership(0, 1, nameText.getText());
        rulesSceneController.getModel().addInput(input);
        label.setText("Input Name: ");
        deleteButton.setText("Remove Input");
    }

    void updateModelName(Object observable, String oldValue, String newValue) {
        input.setName(newValue);
    }

    void deleteModel(ActionEvent actionEvent) {
        rulesSceneController.getModel().removeInput(input);
        rulesSceneController.Inputs.getChildren().remove(this);
        rulesSceneController.updateAllDropdowns();
    }

    @Override
    public void updateDropdowns() {
    }
}
