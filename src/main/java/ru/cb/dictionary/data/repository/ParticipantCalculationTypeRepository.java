package ru.cb.dictionary.data.repository;

import org.springframework.data.repository.CrudRepository;
import ru.cb.dictionary.data.model.ParticipantCalculationType;

import java.util.List;

/**
 * Created by libragimov on 25.03.2018.
 */
public interface ParticipantCalculationTypeRepository extends CrudRepository<ParticipantCalculationType, String> {

    @Override
    List<ParticipantCalculationType> findAll();
    List<ParticipantCalculationType> findAllByOrderByNameAsc();
}
