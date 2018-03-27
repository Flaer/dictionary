package ru.cb.dictionary.in.dbf;

import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.cb.dictionary.in.InternalData;
import ru.cb.dictionary.in.Loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * DBF reader
 * Created by libragimov on 25.03.2018.
 */
@Component
public class DBFLoader implements Loader {

    final Logger logger = LoggerFactory.getLogger(DBFLoader.class);

    public InternalData fromFile(File file) throws IOException {
        InternalData result = new InternalData();
        List<String> fields = new ArrayList<>();
        try(DBFReader reader = new DBFReader(new FileInputStream(file), Charset.forName("cp866"))) {
            int numberOfFields = reader.getFieldCount();

            for (int i = 0; i < numberOfFields; i++) {
                DBFField field = reader.getField(i);
                fields.add(field.getName());
            }
            result.setColumnNames(fields);

            Object[] rowObjects;
            List<List<Object>> values = new ArrayList<>();
            while ((rowObjects = reader.nextRecord()) != null) {
                values.add(Arrays.asList(rowObjects));
            }
            result.setValues(values);
        } catch (DBFException | IOException e) {
            logger.error("", e);
            throw e;
        }
        return result;
    }

}
