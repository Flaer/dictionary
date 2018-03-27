package ru.cb.dictionary.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.cb.dictionary.data.model.*;
import ru.cb.dictionary.data.repository.*;
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

    /*public static Specification<IdentityCode> areaCodeEquals(AreaCode areaCode) {
        return (Root<IdentityCode> root, CriteriaQuery<?> query,
                CriteriaBuilder builder) -> builder.equal(root.get("areaCode"), areaCode);
    }

    public static Specification<IdentityCode> participantTypeEquals(ParticipantType type) {
        return (Root<IdentityCode> root, CriteriaQuery<?> query,
                CriteriaBuilder builder) -> builder.equal(root.get("participantType"), type);
    }*/


    @Override
    public List<IdentityCode> searchIdentityCodes(String id, AreaCode areaCode, ParticipantType participantType) {
        Specification<IdentityCode> spec = new MultiCriteriaSpecification(id, areaCode, participantType);
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
