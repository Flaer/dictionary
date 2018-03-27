package ru.cb.dictionary.ui;

import javafx.scene.Parent;

/**
 * Created by libragimov on 25.03.2018.
 */
public class ViewHolder {
    private Parent view;
    private Object controller;

    public ViewHolder(Parent view, Object controller) {
        this.view = view;
        this.controller = controller;
    }

    public Parent getView() {
        return view;
    }

    public Object getController() {
        return controller;
    }
}