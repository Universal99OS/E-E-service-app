package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminFormController {
    public AnchorPane pane;


    public void clickBtnOnAction(ActionEvent actionEvent) {
        System.out.println("Clicked");
    }

    public void manageCustomerBtnOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage)pane.getScene().getWindow();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/manageCustomer.fxml"))));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void manageItemsBtnOnAction(ActionEvent actionEvent) {
    }

    public void manageMembersrBtnOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage)pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/manageMembersForm.fxml"))));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updatePasswordBtnOnAction(ActionEvent actionEvent) {
    }

    public void viewOrdersBtnOnAction(ActionEvent actionEvent) {
    }

    public void reportsBtnOnAction(ActionEvent actionEvent) {
    }

    public void salesReportsBtnOnAction(ActionEvent actionEvent) {
    }

    public void placeOrderBtnOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage)pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/placeOrderForm.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.show();
    }
}
