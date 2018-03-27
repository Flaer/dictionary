package ru.cb.dictionary.ui.control;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.TextField;

import javax.validation.constraints.NotNull;

/**
 * Created by libragimov on 27.03.2018.
 */
public class LimitedTextField extends TextField {

    private final IntegerProperty maxLength;

    public LimitedTextField() {
        super();
        this.maxLength = new SimpleIntegerProperty(-1);
    }

    public final Integer getMaxLength() {
        return this.maxLength.getValue();
    }

    public final void setMaxLength(@NotNull Integer maxLength) {
        this.maxLength.setValue(maxLength);
    }

    @Override
    public void replaceText(int start, int end, String insertedText) {
        if (this.getMaxLength() <= 0) {
            super.replaceText(start, end, insertedText);
        } else {
            String currentText = this.getText() == null ? "" : this.getText();
            String finalText = currentText.substring(0, start) + insertedText + currentText.substring(end);
            int numberOfExceedingCharacters = finalText.length() - this.getMaxLength();

            if (numberOfExceedingCharacters <= 0) {
                super.replaceText(start, end, insertedText);
            } else {
                String cutInsertedText = insertedText.substring(
                        0,
                        insertedText.length() - numberOfExceedingCharacters
                );

                super.replaceText(start, end, cutInsertedText);
            }
        }
    }
}