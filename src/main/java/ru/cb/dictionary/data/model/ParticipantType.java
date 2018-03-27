package ru.cb.dictionary.data.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Тип участника расчетов PZN
 * Created by libragimov on 24.03.2018.
 */
@Entity
@Table(name="pzn")
public class ParticipantType implements NamedEntity {
    @Id
    @Column(name="id", nullable=false, unique=true, length=3)
    private String id;
    private String name;
    private String abbreviation;


    public ParticipantType() {
    }

    public ParticipantType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}
