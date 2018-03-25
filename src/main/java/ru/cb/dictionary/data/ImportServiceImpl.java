package ru.cb.dictionary.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cb.dictionary.data.repository.IdentityCodesRepository;
import ru.cb.dictionary.in.InternalData;

/**
 * Created by libragimov on 25.03.2018.
 */
@Service
public class ImportServiceImpl implements ImportService {

    @Autowired
    private IdentityCodesRepository identityCodesRepository;

    @Override
    public void save(InternalData data) {

    }
}
