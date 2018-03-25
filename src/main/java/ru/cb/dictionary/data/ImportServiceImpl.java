package ru.cb.dictionary.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.cb.dictionary.data.model.IdentityCode;
import ru.cb.dictionary.data.repository.IdentityCodesRepository;
import ru.cb.dictionary.in.Extractor;
import ru.cb.dictionary.in.InternalData;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by libragimov on 25.03.2018.
 */
@Service
public class ImportServiceImpl implements ImportService {

    @Autowired
    private IdentityCodesRepository identityCodesRepository;

    @Autowired
    @Qualifier("bic")
    private Extractor<IdentityCode> extractor;

    @Override
    @Transactional
    public List<IdentityCode> save(InternalData data) {
        List<IdentityCode> codes = extractor.extract(data);
        identityCodesRepository.deleteAll();
        return identityCodesRepository.saveAll(codes);
    }
}
