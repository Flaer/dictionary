package ru.cb.dictionary.ui.dialog;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.cb.dictionary.data.model.IdentityCode;
import ru.cb.dictionary.ui.ActionController;
import ru.cb.dictionary.ui.ViewHolder;

/**
 * Created by libragimov on 28.03.2018.
 */
public class ActionDialog extends Dialog<IdentityCode> {

    @Autowired
    private ActionController actionController;
    @Qualifier("actionView")
    @Autowired
    private ViewHolder actionView;

    public ActionDialog(String title, IdentityCode code) {
        Dialog<IdentityCode> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setResizable(false);
        actionController.prepareView(code);
        dialog.getDialogPane().setContent(actionView.getView());
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        dialog.setResultConverter((ButtonType buttonType) -> {

            if (buttonType == ButtonType.OK) {
                return actionController.fill();
            }
            return null;
        });

        final Button okButton = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
        okButton.addEventFilter(ActionEvent.ACTION, ae -> {
            if (!actionController.validate()) {
                ae.consume(); //not valid
            }
        });
    }
}
