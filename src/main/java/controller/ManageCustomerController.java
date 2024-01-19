package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ManageCustomerController {
    public AnchorPane pane;

    public void backButtonOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) pane.getScene().getWindow();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/adminForm.fxml"))));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addNewCustomersOnClick(ActionEvent actionEvent) {
        Stage stage = (Stage) pane.getScene().getWindow();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/AddCustomerForm.fxml"))));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void allCustomersOnClick(ActionEvent actionEvent) {
        Stage stage = (Stage) pane.getScene().getWindow();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/allCustomers.fxml"))));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
