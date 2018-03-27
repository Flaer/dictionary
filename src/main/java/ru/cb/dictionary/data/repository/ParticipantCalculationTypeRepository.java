package ru.cb.dictionary.data.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import ru.cb.dictionary.data.model.ParticipantCalculationType;

import java.util.List;

/**
 * Created by libragimov on 25.03.2018.
 */
public interface ParticipantCalculationTypeRepository extends CrudRepository<ParticipantCalculationType, String> {

    @Cacheable(value = "dictionary", key = "#root.target")
    List<ParticipantCalculationType> findAllByOrderByNameAsc();
}
