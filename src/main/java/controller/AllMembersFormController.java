package controller;

import Bo.BoFactory;
import Bo.custom.StaffBo;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import dao.util.BoType;
import dto.StaffDto;
import dto.tableModel.CustomerTm;
import dto.tableModel.StaffTm;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;


public class AllMembersFormController {
    public JFXTextField contactNumId;
    public JFXTextField nameId;
    public JFXTextField emailId;
    public JFXTextField passwordID;
    public JFXTextField searchId;
    public JFXTreeTableView<StaffTm> table;
    public TreeTableColumn contactNumCol;
    public TreeTableColumn nameCol;
    public TreeTableColumn emailCol;
    public TreeTableColumn passCol;
    public TreeTableColumn userTypeCol;
    public TreeTableColumn optionCol;
    public JFXComboBox userTypeComboID;

    StaffBo staffBo= BoFactory.getInstance().getBO(BoType.STAFF);

    public void initialize(){
        contactNumCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("contactNum"));
        nameCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));
        emailCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("email"));
        passCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("password"));
        userTypeCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("userType"));
        optionCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("button"));

        table.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            setData(newValue);
        });


        loadStaffTable();
    }

    private void setData(TreeItem<StaffTm> newValue) {
        StaffTm value = newValue.getValue();
        contactNumId.setEditable(false);
        contactNumId.setText(value.getContactNum());
        nameId.setText(value.getName());
        emailId.setText(value.getEmail());
        passwordID.setText(value.getPassword());

        userTypeComboID.getItems().clear();
        userTypeComboID.getItems().addAll("Admin","Office");
        userTypeComboID.setValue(value.getUserType());
    }


    private void loadStaffTable() {
        List<StaffDto> all = staffBo.getAll();

        ObservableList<StaffTm> list= FXCollections.observableArrayList();

        for (StaffDto dto:all) {
            JFXButton button=new JFXButton("Delete");
            list.add(new StaffTm(
                    dto.getContactNum(),
                    dto.getName(),
                    dto.getEmail(),
                    dto.getPassword(),
                    dto.getUserType(),
                    button

            ));

            button.setOnAction(actionEvent -> {
                boolean delete = staffBo.delete(dto);

                if(delete){
                    new Alert(Alert.AlertType.CONFIRMATION,"Succefully Deleted").show();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Failed to delete").show();
                }

                loadStaffTable();
            });
        }

        RecursiveTreeItem<StaffTm> staffTmRecursiveTreeItem = new RecursiveTreeItem<>(list, RecursiveTreeObject::getChildren);
        table.setRoot(staffTmRecursiveTreeItem);
        table.setShowRoot(false);




    }

    public void updateOnAction(ActionEvent actionEvent) {
        boolean update = staffBo.update(new StaffDto(
                contactNumId.getText(),
                nameId.getText(),
                emailId.getText(),
                passwordID.getText(),
                userTypeComboID.getSelectionModel().getSelectedItem().toString()
        ));

        if(update){
            new Alert(Alert.AlertType.CONFIRMATION,"Succefully Updated").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Something went wrong").show();
        }

        loadStaffTable();
    }

    public void refreshOnAction(ActionEvent actionEvent) {
        contactNumId.clear();
        nameId.clear();
        emailId.clear();
        passwordID.clear();
        userTypeComboID.getItems().clear();
    }

    public void backBtnOnAction(MouseEvent mouseEvent) {
        Stage stage = (Stage) table.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/manageMembersForm.fxml"))));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
