package ru.cb.dictionary.ui;

import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by libragimov on 25.03.2018.
 */
public class ViewInitializer {

    public ViewHolder loadView(String url) throws IOException {
        try(InputStream fxmlStream = getClass().getClassLoader().getResourceAsStream(url)) {
            FXMLLoader loader = new FXMLLoader();
            loader.load(fxmlStream);
            return new ViewHolder(loader.getRoot(), loader.getController());
        }
    }
}
