package ru.cb.dictionary.ui;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import org.springframework.beans.factory.annotation.Autowired;
import ru.cb.dictionary.data.DataService;
import ru.cb.dictionary.data.model.*;
import ru.cb.dictionary.ui.control.EntityBox;
import ru.cb.dictionary.ui.control.LimitedTextField;
import ru.cb.dictionary.ui.dialog.AlertMessage;

import javax.annotation.PostConstruct;

/**
 * Created by libragimov on 26.03.2018.
 */
public class ActionController {

    @FXML
    protected LimitedTextField newnum;
    @FXML
    protected EntityBox<AreaCode> reg;
    @FXML
    protected EntityBox<SettlementType> tnp;
    @FXML
    protected EntityBox<ParticipantCalculationType> uer;
    @FXML
    protected EntityBox<ParticipantType> pzn;
    @FXML
    protected LimitedTextField real;
    @FXML
    protected LimitedTextField ind;
    @FXML
    protected LimitedTextField nnp;
    @FXML
    protected LimitedTextField adr;
    @FXML
    protected LimitedTextField rkc;
    @FXML
    protected LimitedTextField namep;
    @FXML
    protected LimitedTextField telef;
    @FXML
    protected LimitedTextField regn;
    @FXML
    protected LimitedTextField okpo;
    @FXML
    protected DatePicker dtIzm;
    @FXML
    protected LimitedTextField ksnp;
    @FXML
    protected DatePicker dateIn;
    @FXML
    protected DatePicker dateCh;


    @Autowired
    private DataService dataService;
    protected IdentityCode code;
    private Parent view;

    public void setView(Parent view) {
        this.view = view;
    }

    public Parent getView() {
        return view;
    }

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void init() {
        reg.init(dataService.getAreaCodes());
        tnp.init(dataService.getSettlementTypes());
        uer.init(dataService.getParticipantCalculationTypes());
        pzn.init(dataService.getParticipantTypes());
        newnum.setMaxLength(9);
        real.setMaxLength(4);
        ind.setMaxLength(6);
        nnp.setMaxLength(25);
        adr.setMaxLength(30);
        rkc.setMaxLength(9);
        namep.setMaxLength(45);
        telef.setMaxLength(25);
        regn.setMaxLength(9);
        okpo.setMaxLength(8);
        ksnp.setMaxLength(20);
    }

    public void prepareView(IdentityCode code) {
        this.code = code;
        // блокировка редактирования id
        newnum.setEditable((code.getId() == null));

        newnum.setText(code.getId());
        reg.setValueById(code.getAreaCode());
        tnp.setValueById(code.getSettlementType());
        uer.setValueById(code.getCalculationType());
        pzn.setValueById(code.getParticipantType());
        real.setText(code.getReal());
        ind.setText(code.getInd());
        nnp.setText(code.getNnp());
        adr.setText(code.getAdr());
        rkc.setText(code.getRkc());
        namep.setText(code.getNamep());
        telef.setText(code.getTelef());
        regn.setText(code.getRegn());
        okpo.setText(code.getOkpo());
        dtIzm.setValue(code.getDtizm());
        ksnp.setText(code.getKsnp());
        dateIn.setValue(code.getDatein());
        dateCh.setValue(code.getDatech());
    }

    private static final String VALIDATION_ERROR = "Поле %s должно быть заполнено.\r\n";

    public boolean validate() {
        StringBuilder errors = new StringBuilder();
        if (newnum.getText() == null || newnum.getText().isEmpty())
            errors.append(String.format(VALIDATION_ERROR, "NEWNUM"));
        if (reg.getValue() == null)
            errors.append(String.format(VALIDATION_ERROR, "REG"));
        if (uer.getValue() == null)
            errors.append(String.format(VALIDATION_ERROR, "UER"));
        if (namep.getText() == null || namep.getText().isEmpty())
            errors.append(String.format(VALIDATION_ERROR, "NAMEP"));
        if (dtIzm.getValue() == null)
            errors.append(String.format(VALIDATION_ERROR, "DT_IZM"));
        if (dateIn.getValue() == null)
            errors.append(String.format(VALIDATION_ERROR, "DATE_IN"));
        if(errors.length() > 0) {
            new AlertMessage().show(errors.toString());
            return false;
        }
        return true;
    }

    public IdentityCode fill() {
        code.setId(newnum.getText());
        code.setAreaCode(reg.getValue());
        code.setSettlementType(tnp.getValue());
        code.setCalculationType(uer.getValue());
        code.setParticipantType(pzn.getValue());
        code.setReal(real.getText());
        code.setInd(ind.getText());
        code.setNnp(nnp.getText());
        code.setAdr(adr.getText());
        code.setRkc(rkc.getText());
        code.setNamep(namep.getText());
        code.setTelef(telef.getText());
        code.setRegn(regn.getText());
        code.setOkpo(okpo.getText());
        code.setDtizm(dtIzm.getValue());
        code.setKsnp(ksnp.getText());
        code.setDatein(dateIn.getValue());
        code.setDatech(dateCh.getValue());
        return code;
    }
}
