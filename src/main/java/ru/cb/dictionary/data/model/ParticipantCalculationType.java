package ru.cb.dictionary.data.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Код типа участника системы электронных расчетов UER
 * Created by libragimov on 24.03.2018.
 */
@Entity
@Table(name="uer")
public class ParticipantCalculationType implements NamedEntity {
    @Id
    @Column(name="id", nullable=false, unique=true, length=2)
    private String id;
    private String name;


    public ParticipantCalculationType() {
        // for entity correct creation
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
