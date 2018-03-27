package ru.cb.dictionary.data.repository;

import org.springframework.data.repository.CrudRepository;
import ru.cb.dictionary.data.model.AreaCode;

import java.util.List;

/**
 * Created by libragimov on 25.03.2018.
 */
public interface AreaCodeRepository extends CrudRepository<AreaCode, String> {

    @Override
    List<AreaCode> findAll();
    List<AreaCode> findAllByOrderByNameAsc();
}
