package ru.cb.dictionary.data.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.cb.dictionary.data.model.AreaCode;
import ru.cb.dictionary.data.model.IdentityCode;
import ru.cb.dictionary.data.model.IdentityCode_;
import ru.cb.dictionary.data.model.ParticipantType;

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

    private String id;
    private AreaCode areaCode;
    private ParticipantType participantType;

    public MultiCriteriaSpecification(String id, AreaCode areaCode, ParticipantType participantType) {
        this.id = id;
        this.areaCode = areaCode;
        this.participantType = participantType;
    }

    @Override
    public Predicate toPredicate(Root<IdentityCode> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (id != null)
            predicates.add(criteriaBuilder.like(root.get(IdentityCode_.id), "%" + id + "%"));

        if (areaCode != null)
            predicates.add(criteriaBuilder.equal(root.get(IdentityCode_.areaCode), areaCode));

        if (participantType != null)
            predicates.add(criteriaBuilder.equal(root.get(IdentityCode_.participantType), participantType));

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
