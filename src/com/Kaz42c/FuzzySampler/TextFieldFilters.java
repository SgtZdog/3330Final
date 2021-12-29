package com.Kaz42c.FuzzySampler;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class TextFieldFilters {
    public static final int INPUT_NAME_LENGTH = 20;

    private TextFieldFilters() {
    }

    public static TextFormatter.Change letterFilter(TextFormatter.Change change) {
        TextFormatter.Change result = null;
        if (change.getText().matches("[A-z]*")) {
            result = change;
        }
        return result;
    }

    public static TextFormatter.Change numberFilter(TextFormatter.Change change) {
        TextFormatter.Change result = null;
        if (change.getText().matches("[0-9.]*")) {
            result = change;
        }
        return result;
    }

    public static ChangeListener<Number> lengthFilterGenerator(TextField nameText) {
        return ((observable1, oldValue, newValue) -> {
            if (newValue.intValue() > oldValue.intValue() &&
                    nameText.getText().length() > INPUT_NAME_LENGTH) {
                nameText.setText(nameText.getText(0, INPUT_NAME_LENGTH));
            }
        });

    }
}
