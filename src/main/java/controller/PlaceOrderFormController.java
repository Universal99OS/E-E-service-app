package controller;

import Bo.BoFactory;
import Bo.custom.CustomerBo;
import Bo.custom.ItemBo;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import dao.util.BoType;
import dto.CustomerDto;
import dto.tableModel.ItemTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class PlaceOrderFormController {
    public JFXComboBox customersComboBoxId;
    public Text orderId;
    public JFXTextField itemId;
    public JFXTextField itemNameId;
    public JFXComboBox categoryComboBoxId;
    public JFXComboBox statusComboBoxId;
    public JFXTextField descriptionId;
    public JFXTreeTableView tableId;
    public TreeTableColumn itemIdCol;
    public TreeTableColumn nameCol;
    public TreeTableColumn categoryCol;
    public TreeTableColumn statusCol;
    public TreeTableColumn descriptionCol;
    public TreeTableColumn optionCol;
    public AnchorPane pane;
    public JFXTextField cusNameId;

    public List<CustomerDto> customers;

    private ObservableList<ItemTm> itemTms=FXCollections.observableArrayList();

    private CustomerBo customerBo= BoFactory.getInstance().getBO(BoType.CUSTOMER);
    private ItemBo itemBo=BoFactory.getInstance().getBO(BoType.ITEM);

    public void initialize(){
        itemIdCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("itemId"));
        descriptionCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("description"));
        nameCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));
        categoryCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("category"));
        statusCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("status"));
        optionCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("button"));

        loadCustomerId();
        loadCategories();
        loadStatus();
        
        customersComboBoxId.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldvalue, contactNum) ->{
            for (CustomerDto dto:customers) {
                if(dto.getContactNum().equals(contactNum)){
                    cusNameId.setText(dto.getName());
                    cusNameId.setEditable(false);
                }
            }
        } ));

        setItemId();
    }

    private void loadStatus() {
        ObservableList<String> list=FXCollections.observableArrayList();

        list.add("Pending");
        list.add("Processing");
        list.add("Completed");
        list.add("Closed");

        statusComboBoxId.setItems(list);
    }

    private void loadCategories() {
        ObservableList<String> list=FXCollections.observableArrayList();

        list.add("Electronic");
        list.add("Electrical");

        categoryComboBoxId.setItems(list);
    }

    private void setItemId() {
        itemId.setText(itemBo.getNextItemId());
        itemId.setEditable(false);
    }

    private void loadCustomerId() {
        customers=customerBo.getAll();

        ObservableList<String> list= FXCollections.observableArrayList();

        for (CustomerDto dto:customers) {
            list.add(dto.getContactNum());
        }

        customersComboBoxId.setItems(list);
    }


    public void addNewCustomerOnAction(ActionEvent actionEvent) {
    }

    public void addItemOnAction(ActionEvent actionEvent) {
        final String[] cate = {null};
        final String[] sta = {null};

        categoryComboBoxId.getSelectionModel().selectedItemProperty().addListener((observableValue, oldvalue, category) -> {
            cate[0] = (String) category;
        });

        statusComboBoxId.getSelectionModel().selectedItemProperty().addListener((observableValue, o, status) -> {
            sta[0] = (String) status;
        });

        JFXButton button = new JFXButton("Delete");


        ItemTm itemTm = new ItemTm(
                itemId.getText(),
                descriptionId.getText(),
                itemNameId.getText(),
                cate[0],
                sta[0],
                button
        );

        itemTms.add(itemTm);

        TreeItem<ItemTm> recursiveTreeItem = new RecursiveTreeItem<>(itemTms, RecursiveTreeObject::getChildren);
        tableId.setRoot(recursiveTreeItem);
        tableId.setShowRoot(false);

        clearAllFields();
    }

    private void clearAllFields() {
        setItemId();
        descriptionId.clear();
        itemNameId.clear();
        statusComboBoxId.setValue(null);
        statusComboBoxId.setValue(null);

    }


    public void placeOrderOnAction(ActionEvent actionEvent) {
    }

    public void backBtnOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/adminForm.fxml"))));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
