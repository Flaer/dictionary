package ru.cb.dictionary.data.search;

/**
 * Created by libragimov on 27.03.2018.
 */
public enum Field {

    ID("id"), AREA("areaCode"), PARTICIPANT_TYPE("participantType");

    private String value;

    Field(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
