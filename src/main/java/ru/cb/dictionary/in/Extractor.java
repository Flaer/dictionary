package ru.cb.dictionary.in;

import ru.cb.dictionary.data.model.AbstractEntity;

import java.util.List;

/**
 * Created by libragimov on 25.03.2018.
 */
public interface Extractor<E extends AbstractEntity> {

    List<E> extract(InternalData data);
}
