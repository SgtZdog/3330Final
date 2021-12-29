package com.Kaz42c.FuzzySampler.RulesScene;

import com.Kaz42c.FuzzySampler.Fuzzy.ISetGetter;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.List;

public class DomainMembershipPicker extends HBox {
    protected final List<? extends ISetGetter> iSetGetters;
    protected ChoiceBox<String> choices = new ChoiceBox<>();

    public DomainMembershipPicker(List<? extends ISetGetter> setGetters, String labelText, ChangeListener<Number> listener) {
        iSetGetters = setGetters;
        this.getChildren().add(new Label(labelText));
        this.getChildren().add(choices);
        getChoices().getSelectionModel().selectedIndexProperty().addListener(listener);
        updateList();
        choices.getSelectionModel().select(0);
    }

    public void updateList() {
        for (String item :
                choices.getItems()) {
            boolean shouldRemove = true;
            for (ISetGetter setGetter :
                    iSetGetters) {
                if (setGetter.getName().equals(item)) {
                    shouldRemove = false;
                    break;
                }
            }
            if (shouldRemove) {
                choices.getItems().remove(item);
            }
        }
        for (ISetGetter setGetter :
                iSetGetters) {
            if (!choices.getItems().contains(setGetter.getName())) {
                choices.getItems().add(setGetter.getName());
            }
        }
    }

    public ChoiceBox<String> getChoices() {
        return choices;
    }
}
