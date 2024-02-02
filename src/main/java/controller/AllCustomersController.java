package controller;

import Bo.BoFactory;
import Bo.custom.impl.CustomerBoImpl;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import dao.util.BoType;
import dto.CustomerDto;
import dto.tableModel.CustomerTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AllCustomersController {
    public JFXTextField contactNumId;
    public JFXTextField nameId;
    public JFXTextField emailId;
    public JFXTextField orderQtyId;
    public JFXTextField searchId;
    public TreeTableColumn contactNumCol;
    public TreeTableColumn nameCol;
    public TreeTableColumn emailCol;
    public TreeTableColumn orderQtyCol;
    public TreeTableColumn optionCol;
    public JFXTreeTableView<CustomerTm> table;
    public AnchorPane pane;

    CustomerBoImpl customerBo= BoFactory.getInstance().getBO(BoType.CUSTOMER);

    public void initialize(){
        contactNumCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("contactNum"));
        nameCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));
        emailCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("email"));
        orderQtyCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("orderQty"));
        optionCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("button"));

        loadCustomerTable();
        table.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            setData(newValue);
        });
    }

    private void setData(TreeItem<CustomerTm> newValue) {
        CustomerTm value = newValue.getValue();
        contactNumId.setEditable(false);
        contactNumId.setText(value.getContactNum());
        nameId.setText(value.getName());
        emailId.setText(value.getEmail());
        orderQtyId.setText(String.valueOf(value.getOrderQty()));
    }


    public void loadCustomerTable() {
        List<CustomerTm> allTm = customerBo.getAllTm(this);
        ObservableList<CustomerTm> observableList = FXCollections.observableArrayList();

        for (CustomerTm tm:allTm) {
            observableList.add(tm);
        }

        RecursiveTreeItem<CustomerTm> customerTmRecursiveTreeItem = new RecursiveTreeItem<>(observableList, RecursiveTreeObject::getChildren);
        table.setRoot(customerTmRecursiveTreeItem);
        table.setShowRoot(false);

    }

    public void updateOnAction(ActionEvent actionEvent) {
        boolean update = customerBo.update(new CustomerDto(
                contactNumId.getText(),
                nameId.getText(),
                emailId.getText(),
                Integer.parseInt(orderQtyId.getText())
        ));

        if(update){
            new Alert(Alert.AlertType.CONFIRMATION,"Succefully Updated").show();
            loadCustomerTable();
        }else {
            new Alert(Alert.AlertType.ERROR,"Something went wrong!!!").show();
        }

    }

    public void refreshOnAction(ActionEvent actionEvent) {
        contactNumId.clear();
        nameId.clear();
        emailId.clear();
        orderQtyId.clear();
    }

    public void backBtnOnAction(MouseEvent mouseEvent) {
        Stage stage = (Stage) pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/manageCustomer.fxml"))));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
