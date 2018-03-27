package ru.cb.dictionary.data.search;

/**
 * Created by libragimov on 27.03.2018.
 */
public class Criteria {
    private Field field;
    private Operation operation;
    private Object value;

    public Criteria(Field field, Object value) {
        this.field = field;
        this.value = value;
        this.operation = Operation.EQUAL;
    }

    public Criteria(Field field, Operation operation, Object value) {
        this.field = field;
        this.operation = operation;
        this.value = value;
    }

    public Field getField() {
        return field;
    }

    public Operation getOperation() {
        return operation;
    }

    public Object getValue() {
        return value;
    }
}
