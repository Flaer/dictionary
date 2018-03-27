package ru.cb.dictionary.data.facade;

import ru.cb.dictionary.data.model.IdentityCode;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by libragimov on 25.03.2018.
 */
public interface ImportService {

    List<IdentityCode> upload(File file) throws IOException;
}
