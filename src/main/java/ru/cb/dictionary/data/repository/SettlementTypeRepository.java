package ru.cb.dictionary.data.repository;

import org.springframework.data.repository.CrudRepository;
import ru.cb.dictionary.data.model.SettlementType;

/**
 * Created by libragimov on 25.03.2018.
 */
public interface SettlementTypeRepository extends CrudRepository<SettlementType, String> {
}
