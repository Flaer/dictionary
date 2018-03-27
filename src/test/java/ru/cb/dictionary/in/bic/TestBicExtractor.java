package ru.cb.dictionary.in.bic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import ru.cb.dictionary.data.model.IdentityCode;
import ru.cb.dictionary.in.Extractor;
import ru.cb.dictionary.in.InternalData;
import ru.cb.dictionary.ui.MainController;
import ru.cb.dictionary.ui.ViewHolder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by libragimov on 25.03.2018.
 */
@DataJpaTest
@RunWith(SpringRunner.class)
@ComponentScan({"ru.cb.dictionary"})
@EntityScan(basePackages={"ru.cb.dictionary.data","ru.cb.dictionary.in"})
public class TestBicExtractor {

    @Autowired
    @Qualifier("bic")
    private Extractor extractor;

    @Qualifier("mainView")
    @MockBean
    private ViewHolder mainView;
    @MockBean
    private MainController mainController;

    private Date toDate(String data) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy");
        return dateFormat.parse(data);
    }

    @Test
    public void testExtract() throws ParseException {
        InternalData data = new InternalData();
        List<List<Object>> values = new ArrayList<>();
        Object[] test = {"D(X3VkT$", "", "20", "5", "01", "659322", "1",
                "БИЙСК", "УЛ.СОЦИАЛИСТИЧЕСКАЯ,1", "040147000",
                "АО \"НАРОДНЫЙ ЗЕМЕЛЬНО-ПРОМЫШЛЕННЫЙ БАНК\"",
                "НАРОДНЫЙ ЗЕМЕЛЬНО-ПРОМЫШЛЕННЫЙ", "040147781", "700161781", "101073", "02",
                "", "", "(3854)312685,304918", "2873", "33992867", toDate("27/4/2016"), "",
                "30101810700000000781", toDate("1/7/1994"), toDate("30/12/1899"), "", toDate("30/12/1899")};
        ArrayList<Object> record = new ArrayList<>();
        record.addAll(Arrays.asList(test));

        values.add(record);
        data.setValues(values);
        List<IdentityCode> code = extractor.extract(data);
    }
}
