package ru.cb.dictionary.in;

import java.io.File;

/**
 * Created by libragimov on 25.03.2018.
 */
public interface Loader {

    InternalData fromFile(File f);
}
