package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Patient;
import model.Treatment;
import utils.PatientDatabaseUtils;
import utils.TreatmentDatabaseUtils;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class TreatmentController implements Initializable {
    @FXML
    private TextField treatmentCodeTF;
    @FXML
    private Label patientCodeLB;
    @FXML
    private ChoiceBox patientCodeChB;
    @FXML
    private Label treatmentRoomLB;
    @FXML
    private ChoiceBox treatmentRoomChB;
    @FXML
    private Label doctorLB;
    @FXML
    private ChoiceBox doctorChb;
    @FXML
    private Label treatmentDateLB;
    @FXML
    private DatePicker treatmentDP;
    @FXML
    private Label diagnosticLB;
    @FXML
    private TextField diagnosticTF;
    @FXML
    private Label treatmentContentLB;
    @FXML
    private TextField treatmentContentTF;

    @FXML
    private TextField patientCodeTF;
    @FXML
    private TextField patientNameTF;
    @FXML
    private TextField patientBirthdayTF;
    @FXML
    private TextField patientGenderTF;
    @FXML
    private TextField identityNumberTF;
    @FXML
    private TextField phoneNumberTF;
    @FXML
    private TextField addressTF;
    @FXML
    private TextField jobTF;
    @FXML
    private TextField bloodNameTF;

    @FXML
    private Button reloadBtn;
    @FXML
    private Button addBtn;
    @FXML
    private Button ediBtn;
    @FXML
    private Button removeBtn;
    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button exitBtn;

    @FXML
    private TableView<Treatment> treatmentTable;
    @FXML
    private TableColumn<Treatment, String> treatmentCodeCol;
    @FXML
    private TableColumn<Treatment, String> patientCodeCol;
    @FXML
    private TableColumn<Treatment, String> treatmentRoomCol;
    @FXML
    private TableColumn<Treatment, String> doctorCol;
    @FXML
    private TableColumn<Treatment, String> treatmentDateCol;
    @FXML
    private TableColumn<Treatment, String> diagnosticCol;
    @FXML
    private TableColumn<Treatment, String>treatmentContentCol;

    private ObservableList<Treatment> treatmentObservableList;
    private boolean isRowSelected = false;

    @FXML
    private TextField filterTF;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (Patient patient : PatientDatabaseUtils.getPatients()) {
            patientCodeChB.getItems().add(patient.getCode());
        }

        treatmentRoomChB.getItems().add("PDT01");
        treatmentRoomChB.getItems().add("PDT02");
        treatmentRoomChB.getItems().add("PDT03");

        doctorChb.getItems().add("Nguyễn Đức Nam");
        doctorChb.getItems().add("Trần Thị Xuân");
        doctorChb.getItems().add("Đỗ Văn Kiên");

        patientCodeChB.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                if ((Integer) t1 != -1) {
                    String patientCode = (String) patientCodeChB.getItems().get((Integer) t1);
                    patientCodeTF.setText(patientCode);
                    for (Patient patient : PatientDatabaseUtils.getPatients()) {
                        if (patient.getCode().equals(patientCode)) {
                            patientNameTF.setText(patient.getFullName());
                            patientBirthdayTF.setText(patient.getBirthday());
                            patientGenderTF.setText(patient.getGender());
                            identityNumberTF.setText(patient.getSoCmnd());
                            phoneNumberTF.setText(patient.getPhoneNumber());
                            addressTF.setText(patient.getAddress());
                            jobTF.setText(patient.getJob());
                            bloodNameTF.setText(patient.getBloodName());
                            break;
                        }
                    }
                }
            }
        });

        treatmentCodeCol.setCellValueFactory(new PropertyValueFactory<Treatment, String>("treatmentCode"));
        patientCodeCol.setCellValueFactory(new PropertyValueFactory<Treatment, String>("patientCode"));
        treatmentRoomCol.setCellValueFactory(new PropertyValueFactory<Treatment, String>("treatmentRoom"));
        doctorCol.setCellValueFactory(new PropertyValueFactory<Treatment, String>("doctor"));
        treatmentDateCol.setCellValueFactory(new PropertyValueFactory<Treatment, String>("date"));
        diagnosticCol.setCellValueFactory(new PropertyValueFactory<Treatment, String>("diagnostic"));
        treatmentContentCol.setCellValueFactory(new PropertyValueFactory<Treatment, String>("treatmentContent"));
        treatmentObservableList = FXCollections.observableList(TreatmentDatabaseUtils.getTreatment());
        treatmentTable.setItems(treatmentObservableList);

        treatmentTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Treatment>() {
            @Override
            public void changed(ObservableValue<? extends Treatment> observableValue, Treatment treatment, Treatment t1) {
                if (t1 != null) {
                    treatmentCodeTF.setText(t1.getTreatmentCode());
                    patientCodeChB.setValue(t1.getPatientCode());
                    treatmentRoomChB.setValue(t1.getTreatmentRoom());
                    doctorChb.setValue(t1.getDoctor());
                    treatmentDP.setValue(LocalDate.parse(t1.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                    diagnosticTF.setText(t1.getDiagnostic());
                    treatmentContentTF.setText(t1.getTreatmentContent());
                    isRowSelected = true;
                }
            }
        });

        disablePrimaryFunction(false);
        clearInput();
    }

    private void disablePrimaryFunction(boolean active) {
        addBtn.setDisable(active);
        ediBtn.setDisable(active);
        removeBtn.setDisable(active);
        treatmentTable.setDisable(active);

        active = active == true ? false : true;

        patientCodeChB.setDisable(active);
        treatmentRoomChB.setDisable(active);
        doctorChb.setDisable(active);
        treatmentDP.setDisable(active);
        diagnosticTF.setDisable(active);
        treatmentContentTF.setDisable(active);
        saveBtn.setDisable(active);
        cancelBtn.setDisable(active);
    }

    private void clearInput() {
        treatmentCodeTF.setText("");
        patientCodeChB.setValue("");
        treatmentRoomChB.setValue("");
        doctorChb.setValue("");
        treatmentDP.setValue(null);
        diagnosticTF.setText("");
        treatmentContentTF.setText("");

        patientCodeTF.setText("");
        patientNameTF.setText("");
        patientBirthdayTF.setText("");
        patientGenderTF.setText("");
        identityNumberTF.setText("");
        phoneNumberTF.setText("");
        addressTF.setText("");
        jobTF.setText("");
        bloodNameTF.setText("");

        treatmentTable.getSelectionModel().clearSelection();
        isRowSelected = false;
    }

    private void showAlert(String content, String type) {
        Alert alert = null;
        if (type.equals("warning"))
            alert = new Alert(Alert.AlertType.WARNING);
        else if (type.equals("infomation"))
            alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private Optional showComfirmAlert(String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText(content);
        Optional<ButtonType> optional = alert.showAndWait();
        return optional;
    }

    private boolean checkTextFieldEmpty(TextField textField, Label label) {
        if (textField.getText().trim().equals("")) {
            showAlert("Vui lòng không bỏ trống " + label.getText().toLowerCase(), "warning");
            return false;
        }
        return true;
    }

    private boolean checkChoiceBoxEmpty(ChoiceBox choiceBox, Label label) {
        if (choiceBox.getSelectionModel().isEmpty()) {
            showAlert("Vui lòng chọn " + label.getText().toLowerCase(), "warning");
            return false;
        }
        return true;
    }


    private boolean checkDatePickerEmpty(DatePicker datePicker, Label label) {
        if (datePicker.getValue() == null) {
            showAlert("Vui lòng chọn " + label.getText().toLowerCase(), "warning");
            return false;
        }
        return true;
    }

    private void generateTreamentCode() {
        int maxNumber = 1;
        if (TreatmentDatabaseUtils.getTreatment().size() > 0) {
            String maxCodeNumber = TreatmentDatabaseUtils.getTreatment()
                    .get(TreatmentDatabaseUtils.getTreatment().size() - 1).getTreatmentCode();
            maxNumber = Integer.parseInt(maxCodeNumber.substring(4)) + 1;
        }
        treatmentCodeTF.setText("BA000" + maxNumber);
    }

    private boolean checkSelectTableView() {
        if (!isRowSelected) {
            showAlert("Vui lòng chọn bệnh án!", "warning");
            return false;
        }
        return true;
    }


    public void reload(ActionEvent actionEvent) {
        disablePrimaryFunction(false);
        clearInput();
        filterTF.setText("");
        treatmentTable.setItems(treatmentObservableList);
        showAlert("Làm mới thành công!", "infomation");
    }

    public void add(ActionEvent actionEvent) {
        disablePrimaryFunction(true);
        clearInput();
        generateTreamentCode();
    }

    public void edit(ActionEvent actionEvent) {
        if (checkSelectTableView())
            disablePrimaryFunction(true);
    }

    public void remove(ActionEvent actionEvent) {
        if (checkSelectTableView()) {
            if (showComfirmAlert("Bạn có chắc chắn muốn xoá?").get() == ButtonType.OK) {
                int indexTreatment = treatmentTable.getSelectionModel().getSelectedIndex();
                treatmentObservableList.remove(indexTreatment);
                TreatmentDatabaseUtils.writeData();
                showAlert("Bạn đã xoá thành công!", "infomation");
                disablePrimaryFunction(false);
                clearInput();
            }
        }
    }

    private void addAndEdit(boolean active) {
        if (checkChoiceBoxEmpty(patientCodeChB, patientCodeLB) &&
            checkChoiceBoxEmpty(treatmentRoomChB, treatmentRoomLB) &&
            checkChoiceBoxEmpty(doctorChb, doctorLB) &&
            checkDatePickerEmpty(treatmentDP, treatmentDateLB) &&
            checkTextFieldEmpty(diagnosticTF, diagnosticLB) &&
            checkTextFieldEmpty(treatmentContentTF, treatmentContentLB)) {
            String treatmentCode = treatmentCodeTF.getText();
            String patientCode = patientCodeChB.getValue().toString();
            String treatmentRoom = treatmentRoomChB.getValue().toString();
            String doctor = doctorChb.getValue().toString();
            String date = treatmentDP.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            String diagnostic = diagnosticTF.getText();
            String treatmentContent = treatmentContentTF.getText();
            Treatment treatment = new Treatment(treatmentCode, patientCode, treatmentRoom, doctor, date, diagnostic, treatmentContent);
            if (active) {
                if (showComfirmAlert("Bạn có chắc chắn muốn thêm?").get() == ButtonType.OK) {
                    treatmentObservableList.add(treatment);
                    showAlert("Bạn đã thêm thành công!", "infomation");
                }
            } else {
                if (showComfirmAlert("Bạn có chắc chắn muốn sửa?").get() == ButtonType.OK) {
                    int indexTreatment = treatmentTable.getSelectionModel().getSelectedIndex();
                    treatmentObservableList.set(indexTreatment, treatment);
                    showAlert("Bạn đã sửa thành công!", "infomation");
                }
            }
            TreatmentDatabaseUtils.writeData();
            disablePrimaryFunction(false);
            clearInput();
        }
    }

    public void save(ActionEvent actionEvent) {
        if (!isRowSelected)
            addAndEdit(true);
        else
            addAndEdit(false);
    }

    public void cancel(ActionEvent actionEvent) {
        disablePrimaryFunction(false);
        if (!isRowSelected)
            clearInput();
    }

    public void exit(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../view/menu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void search(KeyEvent keyEvent) {
        FilteredList<Treatment> treatmentFilteredList = new FilteredList<>(treatmentObservableList);
        treatmentTable.setItems(treatmentFilteredList);
        treatmentFilteredList.setPredicate(new Predicate<Treatment>() {
            @Override
            public boolean test(Treatment treatment) {
                return treatment.getTreatmentCode().contains(filterTF.getText().toUpperCase());
            }
        });
    }
}
