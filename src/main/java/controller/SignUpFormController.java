package controller;

import Bo.BoFactory;
import Bo.custom.StaffBo;
import Email.EmailService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.util.BoType;
import dto.StaffDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpFormController {

    public JFXTextField emailId;
    public JFXTextField passwordId;
    public JFXTextField contactNumId;
    public JFXTextField nameId;
    public JFXComboBox userComboId;

    public EmailService emailService=new EmailService();
    public JFXTextField otpID;
    public JFXButton signUpBtnID;
    String otp;

    StaffBo staffBo= BoFactory.getInstance().getBO(BoType.STAFF);

    public void initialize(){
        setUserTypes();
        signUpBtnID.setDisable(true);
    }

    private void setUserTypes() {
        ObservableList<String> list= FXCollections.observableArrayList();

        list.add("Admin");
        list.add("Office");

        userComboId.setItems(list);
    }


    public void signOnAction(ActionEvent actionEvent) {
        Object selectedItem = userComboId.getSelectionModel().getSelectedItem();
        String selectedValue = null;
        try {
            selectedValue = selectedItem.toString();
            System.out.println(selectedValue);
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        if(!passwordId.getText().isEmpty() && !contactNumId.getText().isEmpty() && !nameId.getText().isEmpty() && selectedValue!=null){
            boolean save = staffBo.save(new StaffDto(
                    contactNumId.getText(),
                    nameId.getText(),
                    emailId.getText(),
                    passwordId.getText(),
                    selectedValue
            ));

            if(save){
                new Alert(Alert.AlertType.CONFIRMATION,"Succefully added new staff member").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Something went wrong, Please re-try");
            }
        }else {
            new Alert(Alert.AlertType.ERROR,"Please make sure to fill the all fields").show();
        }



    }

    public void backOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) nameId.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/manageMembersForm.fxml"))));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void confirmOtpOnAction(ActionEvent actionEvent) {
        if(otpID.getText().equalsIgnoreCase(otp)){
            new Alert(Alert.AlertType.CONFIRMATION,"Successs").show();
            signUpBtnID.setDisable(false);
        }else {
            new Alert(Alert.AlertType.ERROR,"Something went wrong").show();
            signUpBtnID.setDisable(true);
        }
    }

    public void sendOtpOnAction(ActionEvent actionEvent) {
        otp = emailService.sendOtp(emailId.getText());
        signUpBtnID.setDisable(true);
    }
}
