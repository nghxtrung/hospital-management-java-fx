package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.UserDatabaseUtils;

public class Login {


     @FXML
     public TextField userName;

     @FXML
     public PasswordField passWord;


    public void onSubmit(ActionEvent actionEvent) {

        try {
            if (UserDatabaseUtils.checkExistUser(userName.getText(),passWord.getText())){
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("../view/menu.fxml"));
                stage.setTitle("Menu");
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Tên tài khoản hoặc mật khẩu không đúng, vui lòng kiểm tra lại");
                alert.show();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
