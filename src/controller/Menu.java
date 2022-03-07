package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Menu {

    private void moveToOtherStage(ActionEvent actionEvent, String fxmlPath) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void moveToPatient(ActionEvent actionEvent) throws IOException {
        moveToOtherStage(actionEvent, "../view/patient.fxml");
    }

    public void moveToTreatment(ActionEvent actionEvent) throws IOException {
        moveToOtherStage(actionEvent, "../view/treatment.fxml");
    }

    public void signOut(ActionEvent actionEvent) throws IOException {
        moveToOtherStage(actionEvent, "../view/login.fxml");
    }
}
