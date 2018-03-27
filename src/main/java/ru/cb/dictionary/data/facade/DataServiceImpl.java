package ru.cb.dictionary.data.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.cb.dictionary.data.model.*;
import ru.cb.dictionary.data.repository.*;
import ru.cb.dictionary.data.search.Pattern;
import ru.cb.dictionary.data.specification.MultiCriteriaSpecification;

import java.util.List;

/**
 * Created by libragimov on 26.03.2018.
 */
@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private AreaCodeRepository areaCodeRepository;
    @Autowired
    private ParticipantCalculationTypeRepository participantCalculationTypeRepository;
    @Autowired
    private ParticipantTypeRepository participantTypeRepository;
    @Autowired
    private IdentityCodesRepository identityCodesRepository;
    @Autowired
    private SettlementTypeRepository settlementTypeRepository;



    public List<AreaCode> getAreaCodes() {
        return areaCodeRepository.findAllByOrderByNameAsc();
    }

    public List<ParticipantCalculationType> getParticipantCalculationTypes() {
        return participantCalculationTypeRepository.findAllByOrderByNameAsc();
    }

    public List<ParticipantType> getParticipantTypes() {
        return participantTypeRepository.findAllByOrderByNameAsc();
    }

    @Override
    public List<SettlementType> getSettlementTypes() {
        return settlementTypeRepository.findAllByOrderByNameAsc();
    }

    @Override
    public List<IdentityCode> searchIdentityCodes(Pattern pattern) {
        Specification<IdentityCode> spec = new MultiCriteriaSpecification(pattern);
        return identityCodesRepository.findAll(spec);
    }

    public void deleteIdentityCode(IdentityCode entity) {
        identityCodesRepository.delete(entity);
    }

    @Override
    public void saveIdentityCode(IdentityCode entity) {
        identityCodesRepository.save(entity);
    }
}
