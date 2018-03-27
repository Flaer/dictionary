package ru.cb.dictionary.data;

import ru.cb.dictionary.data.model.*;

import java.util.List;

/**
 * Created by libragimov on 26.03.2018.
 */
public interface DataService {
    List<AreaCode> getAreaCodes();
    List<ParticipantCalculationType> getParticipantCalculationTypes();
    List<ParticipantType> getParticipantTypes();
    List<SettlementType> getSettlementTypes();

    // todo refactor to SearchPattern
    List<IdentityCode> searchIdentityCodes(String id, AreaCode areaCode, ParticipantType participantType);

    void deleteIdentityCode(IdentityCode entity);

    void saveIdentityCode(IdentityCode entity);
}
