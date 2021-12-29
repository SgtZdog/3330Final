package com.Kaz42c.FuzzySampler.RulesScene;

import com.Kaz42c.FuzzySampler.Fuzzy.CentroidDefuzzifier;
import com.Kaz42c.FuzzySampler.Fuzzy.Defuzzifier;
import com.Kaz42c.FuzzySampler.Fuzzy.ISetGetter;
import javafx.event.ActionEvent;

public class DefuzzifierViewController extends SetGetterViewController {
    protected final Defuzzifier defuzzifier = new CentroidDefuzzifier(null, nameText.getText());
    protected DomainMembershipPicker aggregatorPicker;

    public DefuzzifierViewController(RulesSceneController rulesSceneController) {
        super(rulesSceneController);
        label.setText("Defuzzifier Name: ");
        deleteButton.setText("Remove Defuzzifier");
        rulesSceneController.getModel().addDefuzzifier(defuzzifier);
        setupInputDropdown();
    }

    private void setupInputDropdown() {
        aggregatorPicker = new DomainMembershipPicker(rulesSceneController.getModel().getAggregators(), "Aggregator: ", this::updateAggregator);
        updateAggregator(null, null, aggregatorPicker.getChoices().getSelectionModel().getSelectedIndex());
        this.getChildren().add(aggregatorPicker);
    }

    void updateAggregator(Object observable, Number oldValue, Number newValue) {
        if (newValue != null && aggregatorPicker != null) {
            try {
                String name = aggregatorPicker.getChoices().getItems().get(((Integer) newValue));
                ISetGetter result = null;
                for (ISetGetter aggregator :
                        rulesSceneController.getModel().getAggregators()) {
                    if (aggregator.getName().equals(name)) {
                        result = aggregator;
                        break;
                    }
                }
                defuzzifier.set_setGetter(result);
            } catch (IndexOutOfBoundsException exception) {
                System.err.println("Attempted to set aggregator without finding any aggregators.");
            }
        }
    }

    @Override
    void updateModelName(Object observable, String oldValue, String newValue) {
        defuzzifier.setName(newValue);
    }

    @Override
    void deleteModel(ActionEvent actionEvent) {
        rulesSceneController.Defuzzifiers.getChildren().remove(this);
        rulesSceneController.getModel().removeDefuzzifier(defuzzifier);
    }

    @Override
    public void updateDropdowns() {
        aggregatorPicker.updateList();
    }
}
