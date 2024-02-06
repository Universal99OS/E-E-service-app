package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.stage.EmbeddedWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class OTPFormController {


    public JFXTextField otpID;
    public JFXButton submitBtnID;






    

    public void submitBtnOnAction(ActionEvent actionEvent) {
        System.out.println("Submit Button clicked");
    }
    public void backBtnOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) otpID.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/manageMembersForm.fxml"))));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
