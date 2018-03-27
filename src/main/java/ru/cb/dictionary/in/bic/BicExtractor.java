package ru.cb.dictionary.in.bic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.cb.dictionary.data.model.*;
import ru.cb.dictionary.data.repository.AreaCodeRepository;
import ru.cb.dictionary.data.repository.ParticipantCalculationTypeRepository;
import ru.cb.dictionary.data.repository.ParticipantTypeRepository;
import ru.cb.dictionary.data.repository.SettlementTypeRepository;
import ru.cb.dictionary.in.Extractor;
import ru.cb.dictionary.in.InternalData;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by libragimov on 25.03.2018.
 */
@Component
@Qualifier("bic")
public class BicExtractor implements Extractor<IdentityCode> {

    @Autowired
    private AreaCodeRepository areaCodeRepository;
    @Autowired
    private ParticipantCalculationTypeRepository participantCalculationTypeRepository;
    @Autowired
    private ParticipantTypeRepository participantTypeRepository;
    @Autowired
    private SettlementTypeRepository settlementTypeRepository;

    public BicExtractor() {
    }

    private LocalDate toDate(Date data) {
        if(data == null)
            return null;
        return data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    @Override
    public List<IdentityCode> extract(@NotNull InternalData data) {
        List<IdentityCode> result = new ArrayList<>();
        for(List<Object> record: data.getValues()) {
            IdentityCode code = new IdentityCode();

            code.setReal((String)record.get(1));
            String participantType = (String) record.get(2);
            Optional<ParticipantType> participantTypeById =
                    participantTypeRepository.findById(participantType);
            if(participantTypeById.isPresent())
                code.setParticipantType(participantTypeById.get());
            String calculationType = (String) record.get(3);
            Optional<ParticipantCalculationType> calculationTypeById =
                    participantCalculationTypeRepository.findById(calculationType);
            if(calculationTypeById.isPresent())
                code.setCalculationType(calculationTypeById.get());
            String areaCode = (String) record.get(4);
            Optional<AreaCode> areaCodeById = areaCodeRepository.findById(areaCode);
            if(areaCodeById.isPresent())
                code.setAreaCode(areaCodeById.get());
            code.setInd((String)record.get(5));
            String settlementType = (String) record.get(6);
            // todo refactor, load once, search in cache
            Optional<SettlementType> settlementTypeById = settlementTypeRepository.findById(settlementType);
            if(settlementTypeById.isPresent())
                code.setSettlementType(settlementTypeById.get());
            code.setNnp((String)record.get(7));
            code.setAdr((String)record.get(8));
            code.setRkc((String)record.get(9));
            code.setNamep((String)record.get(10));
            code.setId((String)record.get(12));
            code.setTelef((String)record.get(18));
            code.setRegn((String)record.get(19));
            code.setOkpo((String)record.get(20));
            code.setDtizm(toDate((Date)record.get(21)));
            code.setKsnp((String) record.get(23));
            code.setDatein(toDate((Date)record.get(24)));
            code.setDatech(toDate((Date)record.get(25)));
            result.add(code);
        }

        return result;
    }
}
