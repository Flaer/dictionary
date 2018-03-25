package ru.cb.dictionary.in;

import java.util.List;

/**
 * Created by libragimov on 25.03.2018.
 */
public class InternalData {

    private List<String> columnNames;
    private List<List<Object>> values;

    public InternalData() {
    }

    public InternalData(List<String> columnNames, List<List<Object>> values) {
        this.columnNames = columnNames;
        this.values = values;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public List<List<Object>> getValues() {
        return values;
    }

    public void setColumnNames(List<String> columnNames) {
        this.columnNames = columnNames;
    }

    public void setValues(List<List<Object>> values) {
        this.values = values;
    }
}
