package com.Kaz42c.FuzzySampler.RulesScene;

import com.Kaz42c.FuzzySampler.Fuzzy.ISetGetter;

public class ASetViewController extends MembershipViewController {
    protected DomainMembershipPicker inputPicker;

    public ASetViewController(RulesSceneController rulesSceneController) {
        super(rulesSceneController);
        label.setText("ASet Name: ");
        deleteButton.setText("Remove ASet");
        setupInputDropdown();
    }

    private void setupInputDropdown() {
        inputPicker = new DomainMembershipPicker(rulesSceneController.getModel().getInputs(), "Input: ", this::updateInput);
        updateInput(null, null, inputPicker.getChoices().getSelectionModel().getSelectedIndex());
        updateDropdowns();
        this.getChildren().add(inputPicker);
    }

    void updateInput(Object observable, Number oldValue, Number newValue) {
        if (newValue != null && inputPicker != null) {
            try {
                String name = inputPicker.getChoices().getItems().get(((Integer) newValue));
                ISetGetter result = null;
                for (ISetGetter input :
                        rulesSceneController.getModel().getInputs()) {
                    if (input.getName().equals(name)) {
                        result = input;
                        break;
                    }
                }
                membershipFunction.set_inputGetter(result);
            } catch (IndexOutOfBoundsException exception) {
                System.err.println("Attempted to set aggregator without finding any inputs.");
            }
        }
    }

    @Override
    protected void addNewMembership() {
        rulesSceneController.getModel().addASet(membershipFunction);
        if (inputPicker != null) {
            updateInput(null, null, inputPicker.getChoices().getSelectionModel().getSelectedIndex());
            this.getChildren().remove(inputPicker);
            this.getChildren().add(inputPicker);
        }
    }

    @Override
    protected void removeExistingMembership() {
        rulesSceneController.getModel().removeASet(membershipFunction);
        rulesSceneController.updateAllDropdowns();
    }

    public void updateDropdowns() {
        inputPicker.updateList();
    }
}
