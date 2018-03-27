package ru.cb.dictionary.data.search;

import java.util.*;

/**
 * Created by libragimov on 27.03.2018.
 */
public class Pattern {

    /** key - field name, value - full criteria */
    private Map<Field, Criteria> criterias = new EnumMap<>(Field.class);

    public Collection<Criteria> getCriterias() {
        return criterias.values();
    }

    /** warning! adding overrides existing criteria */
    public void addCriteria(Criteria criteria) {
        criterias.put(criteria.getField(), criteria);
    }
}
