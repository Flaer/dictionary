package ru.cb.dictionary.data.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Код типа населенного пункта TNP
 * Created by libragimov on 24.03.2018.
 */
@Entity
@Table(name="tnp")
public class SettlementType extends AbstractEntity {
    @Id
    @Column(name="id", nullable=false, unique=true, length=3)
    private String id;
    private String name;
    @Column(name="short_name")
    private String shortName;


    public SettlementType() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }
}
