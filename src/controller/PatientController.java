package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Patient;
import utils.PatientDatabaseUtils;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;



public class PatientController  implements Initializable {



    //khai báo các trường ở bảng column dữ liệu
    @FXML
    private TableView<Patient> table;
    @FXML
    private TableColumn<Patient, String> idCol;
    @FXML
    private TableColumn<Patient, String> nameCol;
    @FXML
    private TableColumn<Patient, String> genderCol;
    @FXML
    private TableColumn<Patient, String> DateCol;
    @FXML
    private TableColumn<Patient, String> cmndCol;
    @FXML
    private TableColumn<Patient, String> phoneCol;
    @FXML
    private TableColumn<Patient, String> addressCol;
    @FXML
    private TableColumn<Patient, String> jobCol;
    @FXML
    private TableColumn<Patient, String> bloodCol;

    @FXML
    private TextField MaBN;
    @FXML
    private TextField HoTen;
    @FXML
    private DatePicker NgaySinh;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;
    @FXML
    private TextField SoCMND;
    @FXML
    private TextField SDT;
    @FXML
    private TextField DiaChi;
    @FXML
    private TextField NgheNghiep;
    @FXML
    private ChoiceBox NhomMau;
    @FXML
    private ChoiceBox timkiem;

    /* for button */
    @FXML
    private  Button save;
    @FXML
    private  Button cancel;
    @FXML
    private  Button add;
    @FXML
    private  Button refresh;
    @FXML
    private  Button edit;
    @FXML
    private  Button delete;
    @FXML
    private  Button logout;
    @FXML
    private  Button search;


    /* For error */
    @FXML
    private Label errorFullName;
    @FXML
    private Label errorNgaySinh;
    @FXML
    private Label errorCMND;
    @FXML
    private Label errorSDT;
    @FXML
    private Label errorDC;
    @FXML
    private Label errorJob;
    @FXML
    private Label errorGender;
    @FXML
    private Label errorBlood;

    private  Patient select;

    private ObservableList<Patient> listPatients ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listPatients = FXCollections.observableArrayList(PatientDatabaseUtils.getPatients());
        // Mặc định sau khi load giao diện, các ô nhập thông tin bệnh nhân sẽ không nhập được, nút Lưu, Huỷ sẽ không bấm được
        save.setDisable(true);
        cancel.setDisable(true);

        MaBN.setDisable(true);
        setDisableField(true);

        loadDataToTable();
        table.setItems(listPatients);

    }

    private void loadDataToTable()
    {
        idCol.setCellValueFactory(new PropertyValueFactory<>("code"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        DateCol.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        cmndCol.setCellValueFactory(new PropertyValueFactory<>("soCmnd"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        jobCol.setCellValueFactory(new PropertyValueFactory<>("job"));
        bloodCol.setCellValueFactory(new PropertyValueFactory<>("bloodName"));
    }

    public void addPatient(ActionEvent actionEvent) {
    /* Khi nhấn nút Thêm, mã bệnh nhân sẽ tự tạo, các ô nhập sẽ nhập được, nút thêm, sửa, xoá không bấm được, nút lưu, huỷ sẽ bấm được,
       bảng danh sách sẽ bị disable không chọn được dòng */

        save.setDisable(false);
        cancel.setDisable(false);
        add.setDisable(true);
        edit.setDisable(true);
        delete.setDisable(true);

        MaBN.setText(automaticMa());
        HoTen.setDisable(false);
        NgaySinh.setDisable(false);
        SoCMND.setDisable(false);
        female.setDisable(false);

        male.setDisable(false);
        SDT.setDisable(false);
        DiaChi.setDisable(false);
        NgheNghiep.setDisable(false);
        NhomMau.setDisable(false);

    }

    private  String automaticMa(){
        StringBuilder maBn = new StringBuilder();
        int sizeOfListPatient = PatientDatabaseUtils.getPatients().size();


        appendMaBN(maBn, sizeOfListPatient);
        /*BN0002 -> trung roi
        * x = 2
        * */

        if (PatientDatabaseUtils.findByCode(maBn.toString()) != null){
            maBn = new StringBuilder();
           return automaticMaWhenDuplicate(sizeOfListPatient,maBn);
        }
        return maBn.toString();
    }

    private void appendMaBN(StringBuilder maBn, int size) {
        if (size <10){
            maBn.append("BN000"+(size +1));
        }
        if (size >= 10 && size <100){
            maBn.append("BN00"+(size +1));
        }
        if (size >= 100 && size <1000){
            maBn.append("BN0"+(size +1));
        }
        if (size >=1000){
            maBn.append("BN"+(size +1));
        }
    }

    private  String automaticMaWhenDuplicate(int sizeOfListPatient, StringBuilder MaBn){
        String[] k = PatientDatabaseUtils.getPatients().get(sizeOfListPatient-1).getCode().split("BN");
        int x = Integer.valueOf(k[1]);
        appendMaBN(MaBn, x);

        return  MaBn.toString();
    }


    public void savePatient(ActionEvent actionEvent) {
       if (checkValidate() ) {
           String gender = female.isSelected() ? "Nữ" : (male.isSelected() ? "Nam" : "null");
           String date = NgaySinh.getValue().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
           Patient patient =  PatientDatabaseUtils.findByCode(MaBN.getText()) ;

           if ( patient == null){
               Alert alert = alertInformation("Xác Nhận","Xác nhận thêm bệnh nhân ");
               ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
               ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.NO);
               alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
               Optional<ButtonType> result = alert.showAndWait();
               if (result.get() == buttonTypeYes) {
                   patient = new Patient();
                   patient.setCode(MaBN.getText());
                   setDataForPatient(patient, gender, date);
                   PatientDatabaseUtils.addPatient(patient);
                   Alert alertAdd = alertInformation("Success","Thêm thành công");
                   alertAdd.show();
                   initButton();
                   saveFileToPath();
               }
           }else {
               Alert alert = alertInformation("Xác Nhận","Xác nhận sửa bệnh nhân ");
               ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
               ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.NO);
               alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
               Optional<ButtonType> result = alert.showAndWait();
               if (result.get() == buttonTypeYes) {
                   Patient patientUpdate = new Patient();
                   for (Patient item : listPatients) {
                       if (item == select) {
                           patientUpdate.setCode(select.getCode());
                           setDataForPatient(patientUpdate, gender, date);
                           PatientDatabaseUtils.changePatient(listPatients.indexOf(item),patientUpdate);
                           break;
                       }
                   }
                   Alert alertAdd = alertInformation("Success","Sửa thành công");
                   alertAdd.show();
                   initButton();
                   saveFileToPath();
               }
           }
       }
       else {
            alertError("Error", "Không thành công");
        }
    }

    private void initButton() {
        refreshValue();
        resetValue();

        save.setDisable(true);
        cancel.setDisable(true);

        add.setDisable(false);
        edit.setDisable(false);
        delete.setDisable(false);
    }

    public void cancelPatient(ActionEvent actionEvent) {
        initButton();
        removeValueError();
    }
    
    public void editPatient(ActionEvent actionEvent) {

        if (select != null) {
            setDisableField(false);
            save.setDisable(false);
            cancel.setDisable(false);
            
            edit.setDisable(true);
            add.setDisable(true);

            fillData(select);
        }else {
            alertError("Error", "Vui lòng chọn bệnh nhân để sửa");
        }

    }

    public void handleClickTableView(javafx.scene.input.MouseEvent mouseEvent) {
        select = table.getSelectionModel().getSelectedItem();

    }

    public void deletePatient(ActionEvent actionEvent) {

        Alert alertDel = alertInformation("Xác Nhận","Xác nhận xoá thông tin?");
        
        ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.NO);
        alertDel.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alertDel.showAndWait();

        Patient selectedDel = table.getSelectionModel().getSelectedItem();

       if (selectedDel != null){
           if (result.get() == buttonTypeYes) {

               PatientDatabaseUtils.removePatient(select);
               refreshValue();
               resetValue();
               saveFileToPath();
               Alert alertDel2 = alertInformation("Information","Xoá thành công");
               alertDel2.show();

           } else {
               alertError("Error", "Xoá không thành công");
           }
       }else {
           alertError("Error", "Vui lòng chọn bệnh nhân để xóa");
       }

    }
    
    public void searchPatientByAge(ActionEvent actionEvent) {

        int index = timkiem.getSelectionModel().getSelectedIndex();

        /* index = 0 - Tu 0 - 14 tuoi
        *  index = 1 - Tu 15 - 59 tuoi
        *  index = 2 - tren 60 tuoi
         * */
        if (index < 0){
            Alert alertEdit2 = alertInformation("Information","Vui lòng chọn độ tuổi tìm kiếm");
            alertEdit2.show();
        }else if (index ==0){

            listPatients = FXCollections.observableArrayList(PatientDatabaseUtils.filterByAge(0,14));
            loadDataToTable();
            table.setItems(listPatients);
        }else if (index ==1){

            listPatients = FXCollections.observableArrayList(  PatientDatabaseUtils.filterByAge(15,59));
            loadDataToTable();
            table.setItems(listPatients);

        }else if (index ==2){ listPatients.clear();
            listPatients = FXCollections.observableArrayList(  PatientDatabaseUtils.isAgeMoreThan(60));
            loadDataToTable();
            table.setItems(listPatients);
        }

    }

    public void refreshTable(ActionEvent actionEvent) {
        refreshValue();
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../view/menu.fxml"));
        stage.setTitle("Menu");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    
    private void resetValue() {
        MaBN.clear();
        HoTen.clear();
        SDT.clear();
        DiaChi.clear();
        SoCMND.clear();
        NgheNghiep.clear();
        female.setSelected(false);
        male.setSelected(false);
        NgaySinh.setValue(null);
        NhomMau.setValue("- Chọn nhóm máu -");
    }

    private void setDataForPatient(Patient patient, String gender, String date) {
        patient.setFullName(HoTen.getText());
        patient.setBirthday(NgaySinh.toString());
        patient.setPhoneNumber(SDT.getText());
        patient.setAddress(DiaChi.getText());
        patient.setJob(NgheNghiep.getText());
        patient.setBirthday(date);
        patient.setGender(gender);
        patient.setSoCmnd(SoCMND.getText());
        patient.setBloodName(NhomMau.getSelectionModel().getSelectedItem().toString());
    }

    private  void fillData(Patient patient){

        MaBN.setText(patient.getCode());
        HoTen.setText(patient.getFullName());
        SDT.setText(patient.getPhoneNumber());
        DiaChi.setText(patient.getAddress());
        SoCMND.setText(patient.getSoCmnd());
        NgheNghiep.setText(patient.getJob());
        if (patient.getGender().equals("Nữ")){
            female.setSelected(true);
        } else if (patient.getGender().equals("Nam")) {
            male.setSelected(true);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        NgaySinh.setValue(LocalDate.parse(patient.getBirthday(),formatter));
        NhomMau.setValue(patient.getBloodName());
    }
    
    private  void refreshValue(){
        listPatients.clear();
        listPatients = FXCollections.observableArrayList( PatientDatabaseUtils.getPatients());
        loadDataToTable();
        timkiem.setValue("- Chọn nhóm độ tuổi -");
        table.setItems(listPatients);
    }
    
    private void setDisableField(boolean b) {
        HoTen.setDisable(b);
        NgaySinh.setDisable(b);
        SoCMND.setDisable(b);
        female.setDisable(b);
        male.setDisable(b);
        SDT.setDisable(b);
        DiaChi.setDisable(b);
        NgheNghiep.setDisable(b);
        NhomMau.setDisable(b);
       /* edit.setDisable(b);
        delete.setDisable(b);*/
    }
    
    private  Alert alertInformation(String title , String headerText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        return  alert;

    }

    private  void alertError(String title , String headerText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.show();
    }
    
    private  boolean checkValidate(){
        boolean  alphabetName = DataValidation.textAlphabet(HoTen,errorFullName,"Họ và tên sai định dạng");
        boolean  phoneNumber = DataValidation.dataLength(SDT,errorSDT,"Số điện thoại sai định dạng","10");
        boolean  soCMND = DataValidation.dataLength(SoCMND,errorCMND,"Số CMND sai định dạng","12");
        boolean  alphabetJob = DataValidation.textAlphabet(NgheNghiep,errorJob,"Nghề nghiệp sai định dạng");
        boolean  alphabetDiaChi = DataValidation.textFieldIsNull(DiaChi,errorDC,"Địa chỉ không được để trống");
        boolean  checkGender = DataValidation.checkGender(female,male,errorGender,"Vui lòng chọn giới tính");
        boolean  checkPicker = DataValidation.checkDatePicker(NgaySinh,errorNgaySinh,"Vui lòng chọn ngày sinh");
        boolean  checkBlood = DataValidation.checkBloodGroup(NhomMau,errorBlood,"Vui lòng chọn nhóm máu ");

        if (!alphabetName && !alphabetJob && !alphabetDiaChi
                && !soCMND && !phoneNumber && !checkGender  && !checkPicker  && !checkBlood ){
            return  true;
        }
        return false;
    }

    private  void removeValueError(){
        errorFullName.setText("");
        errorSDT.setText("");
        errorCMND.setText("");
        errorJob.setText("");
        errorDC.setText("");
        errorGender.setText("");
        errorNgaySinh.setText("");
        errorBlood.setText("");
    }



    private  void saveFileToPath(){
        try {
            PatientDatabaseUtils.write("file/patient.txt", listPatients);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



   
}
