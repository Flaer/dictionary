package ru.cb.dictionary.ui;

import org.springframework.beans.factory.annotation.Autowired;
import ru.cb.dictionary.data.model.IdentityCode;
import ru.cb.dictionary.data.repository.IdentityCodesRepository;

/**
 * Created by libragimov on 26.03.2018.
 */
public class CreateController extends ActionController {

    @Autowired
    private IdentityCodesRepository identityCodesRepository;

    @Override
    public void prepareView(IdentityCode code) {
        super.prepareView(code);
        newnum.setEditable(true);
    }
}
