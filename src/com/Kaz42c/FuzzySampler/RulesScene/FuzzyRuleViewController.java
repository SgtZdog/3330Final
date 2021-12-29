package com.Kaz42c.FuzzySampler.RulesScene;

import com.Kaz42c.FuzzySampler.Fuzzy.FuzzyRule;
import com.Kaz42c.FuzzySampler.Fuzzy.ISetGetter;
import com.Kaz42c.FuzzySampler.Fuzzy.MamdaniRule;
import javafx.event.ActionEvent;

public class FuzzyRuleViewController extends SetGetterViewController {
    private final FuzzyRule fuzzyRule;
    protected DomainMembershipPicker ASetPicker;
    protected DomainMembershipPicker BSetPicker;

    public FuzzyRuleViewController(RulesSceneController rulesSceneController) {
        super(rulesSceneController);
        label.setText("Rule Name: ");
        deleteButton.setText("Remove Rule");
        fuzzyRule = new MamdaniRule(null, null, nameText.getText());
//        setupTypeDropdown();
        setupSetDropdowns();
        rulesSceneController.getModel().addRule(fuzzyRule);
    }

    private void setupSetDropdowns() {
        ASetPicker = new DomainMembershipPicker(rulesSceneController.getModel().getASets(), "ASet: ", this::updateASet);
        updateASet(null, null, ASetPicker.getChoices().getSelectionModel().getSelectedIndex());
        this.getChildren().add(ASetPicker);
        BSetPicker = new DomainMembershipPicker(rulesSceneController.getModel().getBSets(), "BSet: ", this::updateBSet);
        updateBSet(null, null, BSetPicker.getChoices().getSelectionModel().getSelectedIndex());
        this.getChildren().add(BSetPicker);
        updateDropdowns();
    }

    void updateASet(Object observable, Number oldValue, Number newValue) {
        if (newValue != null && ASetPicker != null) {
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
                fuzzyRule.set_aSet(result);
            } catch (IndexOutOfBoundsException exception) {
                System.err.println("Attempted to set aggregator without finding any ASets.");
            }
        }
    }

    void updateBSet(Object observable, Number oldValue, Number newValue) {
        if (newValue != null && BSetPicker != null) {
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
                fuzzyRule.set_bSet(result);
            } catch (IndexOutOfBoundsException exception) {
                System.err.println("Attempted to set aggregator without finding any BSets.");
            }
        }
    }

    @Override
    void updateModelName(Object observable, String oldValue, String newValue) {
        fuzzyRule.setName(newValue);
    }

    @Override
    void deleteModel(ActionEvent actionEvent) {
        rulesSceneController.Rules.getChildren().remove(this);
        rulesSceneController.getModel().removeRule(fuzzyRule);
        rulesSceneController.updateAllDropdowns();
    }

    @Override
    public void updateDropdowns() {
        ASetPicker.updateList();
        BSetPicker.updateList();
    }
}
