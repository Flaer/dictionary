package ru.cb.dictionary.data.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.cb.dictionary.data.model.IdentityCode;
import ru.cb.dictionary.data.repository.IdentityCodesRepository;
import ru.cb.dictionary.in.Extractor;
import ru.cb.dictionary.in.InternalData;
import ru.cb.dictionary.in.Loader;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
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

    @Autowired
    private Loader loader;

    @Override
    @Transactional
    public List<IdentityCode> upload(File file) throws IOException {
        InternalData internalData = loader.fromFile(file);
        List<IdentityCode> codes = extractor.extract(internalData);
        identityCodesRepository.deleteAll();
        return identityCodesRepository.saveAll(codes);
    }
}
