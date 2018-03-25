package ru.cb.dictionary.data;

import ru.cb.dictionary.data.model.IdentityCode;
import ru.cb.dictionary.in.InternalData;

import java.util.List;

/**
 * Created by libragimov on 25.03.2018.
 */
public interface ImportService {

    List<IdentityCode> save(InternalData data);
}
