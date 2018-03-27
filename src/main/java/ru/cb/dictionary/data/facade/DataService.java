package ru.cb.dictionary.data.facade;

import ru.cb.dictionary.data.model.*;
import ru.cb.dictionary.data.search.Pattern;

import java.util.List;

/**
 * Created by libragimov on 26.03.2018.
 */
public interface DataService {
    List<AreaCode> getAreaCodes();
    List<ParticipantCalculationType> getParticipantCalculationTypes();
    List<ParticipantType> getParticipantTypes();
    List<SettlementType> getSettlementTypes();

    List<IdentityCode> searchIdentityCodes(Pattern pattern);

    void deleteIdentityCode(IdentityCode entity);

    void saveIdentityCode(IdentityCode entity);
}
