package ru.cb.dictionary.ui;

import com.linuxense.javadbf.DBFException;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.cb.dictionary.data.facade.DataService;
import ru.cb.dictionary.data.facade.ImportService;
import ru.cb.dictionary.data.model.*;
import ru.cb.dictionary.data.search.Criteria;
import ru.cb.dictionary.data.search.Field;
import ru.cb.dictionary.data.search.Operation;
import ru.cb.dictionary.data.search.Pattern;
import ru.cb.dictionary.ui.control.EntityBox;
import ru.cb.dictionary.ui.dialog.AlertMessage;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Created by libragimov on 25.03.2018.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
public class MainController {

    @FXML
    private AnchorPane main;
    @FXML
    private TableView<IdentityCode> table;
    @FXML
    private TextField newnum;
    @FXML
    private EntityBox<AreaCode> rgn;
    @FXML
    private EntityBox<ParticipantType> pzn;
    @FXML
    private Label size;

    @Autowired
    private ActionController actionController;
    @Qualifier("actionView")
    @Autowired
    private ViewHolder actionView;

    @Autowired
    private ImportService importService;
    @Autowired
    private DataService dataService;


    private Pattern pattern = new Pattern();

    private ObservableList<IdentityCode> identityCodes = FXCollections.observableArrayList();

    /**
     * Инициализация контроллера от JavaFX.
     * Метод вызывается после того как FXML загрузчик произвел инъекции полей.
     * <p>
     * Обратите внимание, что имя метода <b>обязательно</b> должно быть "initialize",
     * в противном случае, метод не вызовется.
     * <p>
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

    private void tableColumnsInit() {
        TableColumn<IdentityCode, String> idColumn = new TableColumn<>("NEWNUM");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<IdentityCode, String> regColumn = new TableColumn<>("REG");
        regColumn.setCellValueFactory((TableColumn.CellDataFeatures<IdentityCode, String> param) -> {
            AreaCode areaCode = param.getValue().getAreaCode();
            return new SimpleObjectProperty<>(areaCode == null ? "" : areaCode.getName());
        });

        TableColumn<IdentityCode, String> tnpColumn = new TableColumn<>("TNP");
        tnpColumn.setCellValueFactory((TableColumn.CellDataFeatures<IdentityCode, String> param) -> {
            SettlementType settlementType = param.getValue().getSettlementType();
            return new SimpleObjectProperty<>(settlementType == null ? "" : settlementType.getName());
        });

        TableColumn<IdentityCode, String> uerColumn = new TableColumn<>("UER");
        uerColumn.setCellValueFactory((TableColumn.CellDataFeatures<IdentityCode, String> param) -> {
            ParticipantCalculationType calculationType = param.getValue().getCalculationType();
            return new SimpleObjectProperty<>(calculationType == null ? "" : calculationType.getName());
        });

        TableColumn<IdentityCode, String> pznColumn = new TableColumn<>("PZN");
        pznColumn.setCellValueFactory((TableColumn.CellDataFeatures<IdentityCode, String> param) -> {
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

    private void showActionDialog(String title, IdentityCode code) {

        boolean newCode = code.getId() == null;
        Dialog<IdentityCode> dialog = new Dialog<>();

        dialog.setResizable(false);
        dialog.setTitle(title);
        actionController.prepareView(code);
        dialog.getDialogPane().setContent(actionView.getView());
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        dialog.setResultConverter((ButtonType buttonType) -> {

            if (buttonType == ButtonType.OK) {
                return actionController.fill();
            }
            return null;
        });

        final Button okButton = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
        okButton.addEventFilter(ActionEvent.ACTION, ae -> {
            if (!actionController.validate()) {
                ae.consume(); //not valid
            }
        });

        Optional<IdentityCode> result = dialog.showAndWait();
        if(result.isPresent()) {
            dataService.saveIdentityCode(result.get());
            if (newCode)
                identityCodes.add(result.get());
            table.refresh();
        }
    }

    private void tableContextMenuInit() {
        table.setRowFactory((TableView<IdentityCode> tableView) -> {
            final TableRow<IdentityCode> row = new TableRow<>();
            final ContextMenu rowMenu = new ContextMenu();

            MenuItem addItem = new MenuItem("Добавить");
            addItem.setOnAction((ActionEvent event) -> {
                showActionDialog("Добавить", new IdentityCode());
                updateAmount();
            });

            MenuItem editItem = new MenuItem("Изменить");
            editItem.setOnAction((ActionEvent event) ->
                    showActionDialog("Изменить", row.getItem()));

            MenuItem removeItem = new MenuItem("Удалить");
            removeItem.setOnAction((ActionEvent event) -> {
                        table.getItems().remove(row.getItem());
                        dataService.deleteIdentityCode(row.getItem());
                        updateAmount();
                    }
            );

            rowMenu.getItems().addAll(addItem, editItem, removeItem);

            // only display context menu for non-null items:
            row.contextMenuProperty().bind(
                    Bindings.when(Bindings.isNotNull(row.itemProperty()))
                            .then(rowMenu)
                            .otherwise((ContextMenu) null));
            return row;
        });
    }

    private void rgnBoxInit() {
        rgn.init(dataService.getAreaCodes());
        rgn.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends AreaCode> observable, AreaCode oldValue, AreaCode newValue) ->
                    searchByCriteria(new Criteria(Field.AREA, newValue))
        );
    }

    private void pznBoxInit() {
        pzn.setItems(FXCollections.observableArrayList(dataService.getParticipantTypes()));
        pzn.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends ParticipantType> observable, ParticipantType oldValue, ParticipantType newValue) ->
                    searchByCriteria(new Criteria(Field.PARTICIPANT_TYPE, newValue))
        );
    }

    /**
     * На этом этапе уже произведены все возможные инъекции.
     */
    @SuppressWarnings("unchecked")
    @PostConstruct
    public void init() {
        newnum.setTooltip(new Tooltip("Введите код или его чать и нажмите Enter"));
        // Столбцы таблицы
        tableColumnsInit();
        tableContextMenuInit();
        rgnBoxInit();
        pznBoxInit();
        // init pattern
        pattern.addCriteria(new Criteria(Field.ID, Operation.LIKE, null));
        pattern.addCriteria(new Criteria(Field.PARTICIPANT_TYPE, Operation.EQUAL, null));
        pattern.addCriteria(new Criteria(Field.AREA, Operation.EQUAL, null));
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
            try {
                List<IdentityCode> codes = importService.upload(file);
                fillTable(codes);
            } catch (DBFException | IOException e) {
                new AlertMessage().show("Не удалось импортировать файл.");
            }
        }
    }

    @FXML
    public void onNewnumKeyPressed(KeyEvent event) {
        if (KeyCode.ENTER.equals(event.getCode())) {
            searchByCriteria(new Criteria(Field.ID, Operation.LIKE, newnum.getText()));
        }
    }

    @FXML
    public void onRgnKeyPressed(KeyEvent event) {
        if (KeyCode.DELETE.equals(event.getCode())) {
            rgn.getSelectionModel().clearSelection();
            searchByCriteria(new Criteria(Field.AREA, null));
        }
    }

    @FXML
    public void onPznKeyPressed(KeyEvent event) {
        if (KeyCode.DELETE.equals(event.getCode())) {
            pzn.getSelectionModel().clearSelection();
            searchByCriteria(new Criteria(Field.PARTICIPANT_TYPE, null));
        }
    }

    public void updateAmount() {
        size.setText("Количество: " + identityCodes.size());
    }

    public void fillTable(List<IdentityCode> codes) {
        // для больших списков данных ввести пагинацию или загрузку видимой области
        identityCodes.clear();
        identityCodes.addAll(codes);
        table.setItems(identityCodes);
        updateAmount();
    }

    public void searchByCriteria(Criteria criteria) {
        pattern.addCriteria(criteria);
        List<IdentityCode> codes = dataService.searchIdentityCodes(pattern);
        fillTable(codes);
    }
}
