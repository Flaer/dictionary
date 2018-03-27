package ru.cb.dictionary.data.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Код территории Российской Федерации RGN
 * Created by libragimov on 24.03.2018.
 */
@Entity
@Table(name="reg")
public class AreaCode implements NamedEntity {
    @Id
    @Column(name="id", nullable=false, unique=true, length=3)
    private String id;
    private String name;


    public AreaCode() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
