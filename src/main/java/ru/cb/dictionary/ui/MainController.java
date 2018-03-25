package ru.cb.dictionary.ui;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import ru.cb.dictionary.data.ImportService;
import ru.cb.dictionary.data.model.*;
import ru.cb.dictionary.in.InternalData;
import ru.cb.dictionary.in.Loader;

import javax.annotation.PostConstruct;
import java.io.File;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by libragimov on 25.03.2018.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
public class MainController {

    @FXML
    private AnchorPane main;
    @FXML
    private TableView<IdentityCode> table;

    @Autowired
    private Loader loader;
    @Autowired
    ImportService importService;

    private ObservableList<IdentityCode> data;

    /**
     * Инициализация контроллера от JavaFX.
     * Метод вызывается после того как FXML загрузчик произвел инъекции полей.
     *
     * Обратите внимание, что имя метода <b>обязательно</b> должно быть "initialize",
     * в противном случае, метод не вызовется.
     *
     * Также на этом этапе еще отсутствуют бины спринга
     * и для инициализации лучше использовать метод,
     * описанный аннотацией @PostConstruct,
     * который вызовется спрингом, после того, как им будут произведены все инъекции.
     * {@link MainController#init()}
     */
    @FXML
    public void initialize() {
        // Этап инициализации JavaFX
    }

    /**
     * На этом этапе уже произведены все возможные инъекции.
     */
    @SuppressWarnings("unchecked")
    @PostConstruct
    public void init() {
        // Столбцы таблицы
        TableColumn<IdentityCode, String> idColumn = new TableColumn<>("NEWNUM");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<IdentityCode, String> regColumn = new TableColumn<>("REG");
        regColumn.setCellValueFactory((TableColumn.CellDataFeatures<IdentityCode , String> param) -> {
                AreaCode areaCode = param.getValue().getAreaCode();
                return new SimpleObjectProperty<>(areaCode == null ? "" : areaCode.getName());
        });

        TableColumn<IdentityCode, String> tnpColumn = new TableColumn<>("TNP");
        tnpColumn.setCellValueFactory((TableColumn.CellDataFeatures<IdentityCode , String> param) -> {
                SettlementType settlementType = param.getValue().getSettlementType();
                return new SimpleObjectProperty<>(settlementType == null ? "" : settlementType.getName());
        });

        TableColumn<IdentityCode, String> uerColumn = new TableColumn<>("UER");
        uerColumn.setCellValueFactory((TableColumn.CellDataFeatures<IdentityCode , String> param) -> {
                ParticipantCalculationType calculationType = param.getValue().getCalculationType();
                return new SimpleObjectProperty<>(calculationType == null ? "" : calculationType.getName());
        });

        TableColumn<IdentityCode, String> pznColumn = new TableColumn<>("PZN");
        pznColumn.setCellValueFactory((TableColumn.CellDataFeatures<IdentityCode , String> param) -> {
                ParticipantType participantType = param.getValue().getParticipantType();
                return new SimpleObjectProperty<>(participantType == null ? "" : participantType.getName());
        });

        TableColumn<IdentityCode, String> realColumn = new TableColumn<>("REAL");
        realColumn.setCellValueFactory(new PropertyValueFactory<>("real"));

        TableColumn<IdentityCode, String> indColumn = new TableColumn<>("IND");
        indColumn.setCellValueFactory(new PropertyValueFactory<>("ind"));

        TableColumn<IdentityCode, String> nnpColumn = new TableColumn<>("NNP");
        nnpColumn.setCellValueFactory(new PropertyValueFactory<>("nnp"));

        TableColumn<IdentityCode, String> adrColumn = new TableColumn<>("ADR");
        adrColumn.setCellValueFactory(new PropertyValueFactory<>("adr"));

        TableColumn<IdentityCode, String> rkcColumn = new TableColumn<>("RKC");
        rkcColumn.setCellValueFactory(new PropertyValueFactory<>("rkc"));

        TableColumn<IdentityCode, String> namepColumn = new TableColumn<>("NAMEP");
        namepColumn.setCellValueFactory(new PropertyValueFactory<>("namep"));

        TableColumn<IdentityCode, String> telefColumn = new TableColumn<>("TELEF");
        telefColumn.setCellValueFactory(new PropertyValueFactory<>("telef"));

        TableColumn<IdentityCode, String> regnColumn = new TableColumn<>("REGN");
        regnColumn.setCellValueFactory(new PropertyValueFactory<>("regn"));

        TableColumn<IdentityCode, String> okpoColumn = new TableColumn<>("OKPO");
        okpoColumn.setCellValueFactory(new PropertyValueFactory<>("okpo"));

        TableColumn<IdentityCode, LocalDate> dtizmColumn = new TableColumn<>("DT_IZM");
        dtizmColumn.setCellValueFactory(new PropertyValueFactory<>("dtizm"));

        TableColumn<IdentityCode, String> ksnpColumn = new TableColumn<>("KSNP");
        ksnpColumn.setCellValueFactory(new PropertyValueFactory<>("ksnp"));

        TableColumn<IdentityCode, LocalDate> dateinColumn = new TableColumn<>("DATE_IN");
        dateinColumn.setCellValueFactory(new PropertyValueFactory<>("datein"));

        TableColumn<IdentityCode, LocalDate> datechColumn = new TableColumn<>("DATE_CH");
        datechColumn.setCellValueFactory(new PropertyValueFactory<>("datech"));


        table.getColumns().setAll(idColumn, regColumn, tnpColumn, uerColumn, pznColumn, realColumn,
                indColumn, nnpColumn, adrColumn, rkcColumn, namepColumn, telefColumn, regnColumn,
                okpoColumn, dtizmColumn, ksnpColumn, dateinColumn, datechColumn);
    }

    /**
     * Метод, вызываемый при нажатии на кнопку "Добавить".
     * Привязан к кнопке в FXML файле представления.
     */
    @FXML
    public void loadDBF() {
        Stage stage = (Stage) main.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выбрать DBF-файл для импорта");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.dir"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("DBF", "*.dbf"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            InternalData internalData = loader.fromFile(file);
            List<IdentityCode> codes = importService.save(internalData);
            fillTable(codes);
        }
    }

    public void fillTable(List<IdentityCode> codes) {
        data = FXCollections.observableArrayList(codes);
        table.setItems(data);
    }
}
