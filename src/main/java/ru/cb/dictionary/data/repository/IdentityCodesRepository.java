package ru.cb.dictionary.data.repository;

import org.springframework.data.repository.CrudRepository;
import ru.cb.dictionary.data.model.IdentityCode;

/**
 * Репозиторий доступа к БИК
 * Created by libragimov on 24.03.2018.
 */
public interface IdentityCodesRepository extends CrudRepository<IdentityCode, String> {
}
