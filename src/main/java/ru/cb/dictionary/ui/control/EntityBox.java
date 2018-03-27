package ru.cb.dictionary.ui.control;

import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tooltip;
import javafx.util.StringConverter;
import ru.cb.dictionary.data.model.NamedEntity;

import java.util.List;

/**
 * Created by libragimov on 27.03.2018.
 */
public class EntityBox<T extends NamedEntity> extends ChoiceBox<T> {

    public EntityBox() {
        setTooltip(new Tooltip("Нажмите DEL для удаления выбранной позиции"));
        setConverter(new StringConverter<T>() {
            @Override
            public String toString(T object) {
                return object.getName();
            }

            @Override
            public T fromString(String string) {
                return null;
            }
        });
    }

    public void setValueById(T value) {
        if(value == null) {
            setValue(null);
            return;
        }
        List<T> items = getItems();
        for(T item: items)
            if(item.getId().equals(value.getId())) {
                setValue(item);
                return;
            }
    }

    public void init(List<T> codes) {
        setItems(FXCollections.observableArrayList(codes));
    }
}
