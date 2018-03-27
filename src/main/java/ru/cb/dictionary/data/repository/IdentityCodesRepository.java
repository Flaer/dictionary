package ru.cb.dictionary.data.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.Nullable;
import ru.cb.dictionary.data.model.IdentityCode;

import java.util.List;

/**
 * Репозиторий доступа к БИК
 * Created by libragimov on 24.03.2018.
 */
public interface IdentityCodesRepository extends CrudRepository<IdentityCode, String>, JpaSpecificationExecutor<IdentityCode> {

    @Override
    <S extends IdentityCode> List<S> saveAll(Iterable<S> entities);

    @Override
    List<IdentityCode> findAll(@Nullable Specification<IdentityCode> spec);

    @Override
    List<IdentityCode> findAll();
}
