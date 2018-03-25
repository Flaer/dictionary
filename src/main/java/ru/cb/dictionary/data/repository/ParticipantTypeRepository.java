package ru.cb.dictionary.data.repository;

import org.springframework.data.repository.CrudRepository;
import ru.cb.dictionary.data.model.ParticipantType;

/**
 * Created by libragimov on 25.03.2018.
 */
public interface ParticipantTypeRepository extends CrudRepository<ParticipantType, String> {
}
