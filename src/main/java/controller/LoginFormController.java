package controller;

import Bo.BoFactory;
import Bo.custom.StaffBo;
import Email.EmailService;
import com.jfoenix.controls.JFXTextField;
import dao.util.BoType;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;

import javax.mail.MessagingException;
import java.io.IOException;

public class LoginFormController {
    public JFXTextField emailId;
    public JFXTextField passwordId;

    StaffBo staffBo= BoFactory.getInstance().getBO(BoType.STAFF);
    EmailService emailService=new EmailService();

    public void loginOnAction(ActionEvent actionEvent) {
        boolean isValidLoginData = staffBo.isValidLoginData(emailId.getText(), passwordId.getText());

        if(isValidLoginData){

            try {
                Stage stage = (Stage) emailId.getScene().getWindow();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/adminForm.fxml"))));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
//            try {
//                emailService.sendEmail("goldenmart5000@gmail.com","testing","hello world");
//                emailService.sendOtp("goldenmart5000@gmail.com");
//            } catch (MessagingException e) {
//                e.printStackTrace();
//            }
        }else{
            new Alert(Alert.AlertType.ERROR,"Something went wrong, Please Re-try").show();
        }


    }
}
