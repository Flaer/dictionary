package ru.cb.dictionary.in.bic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.cb.dictionary.data.facade.DataService;
import ru.cb.dictionary.data.model.*;
import ru.cb.dictionary.in.Extractor;
import ru.cb.dictionary.in.InternalData;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * Created by libragimov on 25.03.2018.
 */
@Component
@Qualifier("bic")
public class BicExtractor implements Extractor<IdentityCode> {

    @Autowired
    private DataService dataService;


    private LocalDate toDate(Date data) {
        if(data == null)
            return null;
        return data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private Map<String, AreaCode> areaCodes = new HashMap<>();
    private Map<String, ParticipantCalculationType> participantCalculationTypes = new HashMap<>();
    private Map<String, ParticipantType> participantTypes = new HashMap<>();
    private Map<String, SettlementType> settlementTypes = new HashMap<>();

    @PostConstruct
    private void prepareCache() {
        for(AreaCode code: dataService.getAreaCodes())
            areaCodes.put(code.getId(), code);
        for(ParticipantCalculationType type:  dataService.getParticipantCalculationTypes())
            participantCalculationTypes.put(type.getId(), type);
        for(SettlementType type: dataService.getSettlementTypes())
            settlementTypes.put(type.getId(), type);
        for(ParticipantType type: dataService.getParticipantTypes())
            participantTypes.put(type.getId(), type);
    }

    @Override
    public List<IdentityCode> extract(@NotNull InternalData data) {

        List<IdentityCode> result = new ArrayList<>();
        for(List<Object> record: data.getValues()) {
            IdentityCode code = new IdentityCode();

            code.setReal((String)record.get(1));

            String participantType = (String) record.get(2);
            if(participantTypes.containsKey(participantType))
                code.setParticipantType(participantTypes.get(participantType));

            String calculationType = (String) record.get(3);
            if(participantCalculationTypes.containsKey(calculationType))
                code.setCalculationType(participantCalculationTypes.get(calculationType));

            String areaCode = (String) record.get(4);
            if(areaCodes.containsKey(areaCode))
                code.setAreaCode(areaCodes.get(areaCode));
            code.setInd((String)record.get(5));

            String settlementType = (String) record.get(6);
            if(settlementTypes.containsKey(settlementType))
                code.setSettlementType(settlementTypes.get(settlementType));

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
