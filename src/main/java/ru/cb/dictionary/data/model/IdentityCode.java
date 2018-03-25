package ru.cb.dictionary.data.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Банковский идентификационынй код BNKSEEK
 * Created by libragimov on 24.03.2018.
 */
@Entity
@Table(name = "bnkseek")
public class IdentityCode extends AbstractEntity {

    @Id
    @Column(name = "newnum", nullable = false, unique = true, length = 9)
    // Банковский идентификационный код (БИК)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rgn", nullable = false)
    private AreaCode areaCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tnp")
    private SettlementType settlementType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uer", nullable = false)
    private ParticipantCalculationType calculationType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pzn")
    private ParticipantType participantType;

    // Код контроля допустимости проведения расчетных операций
    @Column(name = "real", length = 4)
    private String real;
    // Индекс
    @Column(name = "ind", length = 6)
    private String ind;
    // Населенный пункт
    @Column(name = "nnp", length = 25)
    private String nnp;
    // Адрес
    @Column(name = "adr", length = 30)
    private String adr;
    // БИК РКЦ (ГРКЦ)
    @Column(name = "rkc", length = 9)
    private String rkc;
    // Наименование участника расчетов
    @Column(name = "namep", length = 45, nullable = false)
    private String namep;
    // Телефон
    @Column(name = "telef", length = 25)
    private String telef;
    // Регистрационный номер
    @Column(name = "regn", length = 9)
    private String regn;
    // Код ОКПО
    @Column(name = "okpo", length = 8)
    private String okpo;
    // Дата последнего изменения записи
    @Column(name = "dt_izm", nullable = false)
    private LocalDate dtizm;
    // Номер счета
    @Column(name = "ksnp", length = 20)
    private String ksnp;
    // Дата включения информации об участнике расчетов в ЭБД
    @Column(name = "datein", nullable = false)
    private LocalDate datein;
    // Дата контроля
    @Column(name = "date_ch")
    private LocalDate datech;


    public IdentityCode() {
    }

    public String getId() {
        return id;
    }

    public AreaCode getAreaCode() {
        return areaCode;
    }

    public SettlementType getSettlementType() {
        return settlementType;
    }

    public ParticipantCalculationType getCalculationType() {
        return calculationType;
    }

    public ParticipantType getParticipantType() {
        return participantType;
    }

    public String getReal() {
        return real;
    }

    public String getInd() {
        return ind;
    }

    public String getNnp() {
        return nnp;
    }

    public String getAdr() {
        return adr;
    }

    public String getRkc() {
        return rkc;
    }

    public String getNamep() {
        return namep;
    }

    public String getTelef() {
        return telef;
    }

    public String getRegn() {
        return regn;
    }

    public String getOkpo() {
        return okpo;
    }

    public LocalDate getDtizm() {
        return dtizm;
    }

    public String getKsnp() {
        return ksnp;
    }

    public LocalDate getDatein() {
        return datein;
    }

    public LocalDate getDatech() {
        return datech;
    }

    public void setId(@NotNull String id) {
        this.id = id;
    }

    public void setAreaCode(AreaCode areaCode) {
        this.areaCode = areaCode;
    }

    public void setSettlementType(SettlementType settlementType) {
        this.settlementType = settlementType;
    }

    public void setCalculationType(ParticipantCalculationType calculationType) {
        this.calculationType = calculationType;
    }

    public void setParticipantType(ParticipantType participantType) {
        this.participantType = participantType;
    }

    public void setReal(String real) {
        this.real = real;
    }

    public void setInd(String ind) {
        this.ind = ind;
    }

    public void setNnp(String nnp) {
        this.nnp = nnp;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }

    public void setRkc(String rkc) {
        this.rkc = rkc;
    }

    public void setNamep(String namep) {
        this.namep = namep;
    }

    public void setTelef(String telef) {
        this.telef = telef;
    }

    public void setRegn(String regn) {
        this.regn = regn;
    }

    public void setOkpo(String okpo) {
        this.okpo = okpo;
    }

    public void setDtizm(LocalDate dtizm) {
        this.dtizm = dtizm;
    }

    public void setKsnp(String ksnp) {
        this.ksnp = ksnp;
    }

    public void setDatein(LocalDate datein) {
        this.datein = datein;
    }

    public void setDatech(LocalDate datech) {
        this.datech = datech;
    }
}
