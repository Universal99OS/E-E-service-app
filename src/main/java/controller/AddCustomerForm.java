package controller;

import Bo.BoFactory;
import Bo.custom.CustomerBo;
import Bo.custom.impl.CustomerBoImpl;
import com.jfoenix.controls.JFXTextField;
import dao.util.BoType;
import dto.CustomerDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AddCustomerForm {
    public JFXTextField contactNumTextField;
    public JFXTextField nameTextField;
    public JFXTextField emailTextField;
    public AnchorPane pane;

    CustomerBo customerBo=BoFactory.getInstance().getBO(BoType.CUSTOMER);



    public void addBtnOnAction(ActionEvent actionEvent) {
        boolean save = customerBo.save(new CustomerDto(contactNumTextField.getText(),
                nameTextField.getText(),
                emailTextField.getText(),
                1));

        if(save){
            new Alert(Alert.AlertType.CONFIRMATION,"Succefuly saved").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Something went wrong").show();
        }
    }

    public void backButtonOnAction(MouseEvent mouseEvent) {
        Stage stage = (Stage) pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/manageCustomer.fxml"))));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
