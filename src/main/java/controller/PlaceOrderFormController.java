package controller;

import Bo.BoFactory;
import Bo.custom.CustomerBo;
import Bo.custom.ItemBo;
import Bo.custom.OrdersBo;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import dao.util.BoType;
import dto.CustomerDto;
import dto.ItemDto;
import dto.OrdersDto;
import dto.tableModel.ItemTm;
import entity.Orders;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

    private OrdersBo ordersBo=BoFactory.getInstance().getBO(BoType.ORDERS);

    private int itemIdNum= Integer.parseInt(itemBo.getNextItemId());

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
        setOrderID();
    }

    private void setOrderID() {
        orderId.setText(ordersBo.nextOrderID());
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
        itemId.setText(String.valueOf(itemIdNum));
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
        itemIdNum++;

        JFXButton button = new JFXButton("Delete");


        ItemTm itemTm = new ItemTm(
                itemId.getText(),
                descriptionId.getText(),
                itemNameId.getText(),
                categoryComboBoxId.getSelectionModel().getSelectedItem().toString(),
                statusComboBoxId.getSelectionModel().getSelectedItem().toString(),
                button
        );

        button.setOnAction(actionEvent1 -> {
            itemTms.remove(itemTm);
        });

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
        String string = customersComboBoxId.getSelectionModel().getSelectedItem().toString();
        CustomerDto customer = null;
        for (CustomerDto customerDto:customers) {
            if(customerDto.getContactNum().equals(string)){
                customer=customerDto;
            }

        }
        OrdersDto ordersDto = new OrdersDto(
                orderId.getText(),
                "2024/02/08",
                customer
        );

        ordersBo.save(ordersDto);

        for (ItemTm tm:itemTms) {
            itemBo.save(new ItemDto(
                    tm.getItemId(),
                    tm.getDescription(),
                    tm.getName(),
                    tm.getCategory(),
                    tm.getStatus(),
                    ordersDto
            ));
        }

        setOrderID();


        new Alert(Alert.AlertType.CONFIRMATION,"Succefully Add the order");





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
