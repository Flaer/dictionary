package ru.cb.dictionary.data.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import ru.cb.dictionary.data.model.ParticipantType;

import java.util.List;

/**
 * Created by libragimov on 25.03.2018.
 */
public interface ParticipantTypeRepository extends CrudRepository<ParticipantType, String> {

    @Cacheable(value = "dictionary", key = "root.target")
    List<ParticipantType> findAllByOrderByNameAsc();
}
