package ru.cb.dictionary.data.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import ru.cb.dictionary.data.model.IdentityCode;
import ru.cb.dictionary.data.search.Criteria;
import ru.cb.dictionary.data.search.Operation;
import ru.cb.dictionary.data.search.Pattern;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by libragimov on 26.03.2018.
 */
public class MultiCriteriaSpecification implements Specification<IdentityCode> {

    private Pattern pattern;

    public MultiCriteriaSpecification(Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    @Nullable
    public Predicate toPredicate(Root<IdentityCode> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        for(Criteria criteria: pattern.getCriterias()) {
            if(criteria.getValue() == null)
                continue;

            if(Operation.EQUAL.equals(criteria.getOperation()))
                predicates.add(criteriaBuilder.equal(root.get(criteria.getField().getValue()), criteria.getValue()));
            if(Operation.LIKE.equals(criteria.getOperation()))
                predicates.add(criteriaBuilder.like(root.get(criteria.getField().getValue()), "%" + criteria.getValue() + "%"));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
