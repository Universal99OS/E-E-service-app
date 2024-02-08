package controller;

import Bo.BoFactory;
import Bo.custom.ItemBo;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import dao.util.BoType;
import dto.ItemDto;
import dto.tableModel.ItemTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AllItemsFormController {
    public JFXTextField itemId;
    public JFXTextField nameId;
    public JFXTextField descriptionId;
    public JFXTextField searchId;
    public JFXTreeTableView<ItemTm> table;
    public TreeTableColumn itemCol;
    public TreeTableColumn nameCol;
    public TreeTableColumn desCol;
    public TreeTableColumn categoryCol;
    public TreeTableColumn statusCol;
    public TreeTableColumn optionCol;
    public JFXComboBox categoryComboID;
    public JFXComboBox statusComboId;
    ItemBo itemBo= BoFactory.getInstance().getBO(BoType.ITEM);

    private List<ItemDto> all = null;

    public void initialize(){
        itemCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("itemId"));
        desCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("description"));
        nameCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));
        categoryCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("category"));
        statusCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("status"));
        optionCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("button"));

        loadItemTable();

        table.getSelectionModel().selectedItemProperty().addListener((observableValue, itemTmTreeItem, newValue) -> {
            try {
                setData(newValue);
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        });

    }

    private void setData(TreeItem<ItemTm> newValue) {
        try {
            ItemTm value = newValue.getValue();
            itemId.setEditable(false);
            itemId.setText(value.getItemId());
            nameId.setText(value.getName());
            descriptionId.setText(value.getDescription());

            categoryComboID.getItems().clear();
            categoryComboID.getItems().addAll("Electronic", "Electrical");
            categoryComboID.setValue(value.getCategory());

            statusComboId.getItems().clear();
            statusComboId.getItems().addAll("Pending", "Processing", "Completed", "Closed");
            statusComboId.setValue(value.getStatus());

        }catch (NullPointerException e){
            e.printStackTrace();
        }


    }

    private void loadItemTable() {
        all = itemBo.getAll();

        ObservableList<ItemTm> list= FXCollections.observableArrayList();

        for (ItemDto dto: all) {
            list.add(new ItemTm(
                    dto.getItemId(),
                    dto.getDescription(),
                    dto.getName(),
                    dto.getCategory(),
                    dto.getStatus(),
                    new JFXButton("Delete")
            ));
        }

        TreeItem<ItemTm> recursiveTreeItem = new RecursiveTreeItem<>(list, RecursiveTreeObject::getChildren);
        table.setRoot(recursiveTreeItem);
        table.setShowRoot(false);



    }

    public void backBtnOnAction(MouseEvent mouseEvent) {
        Stage stage = (Stage) table.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/adminForm.fxml"))));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {
        for (ItemDto dto:all) {
            if(dto.getItemId().equals(itemId.getText())){
                dto.setName(nameId.getText());
                dto.setDescription(descriptionId.getText());
                dto.setStatus(statusComboId.getSelectionModel().getSelectedItem().toString());
                dto.setCategory(categoryComboID.getSelectionModel().getSelectedItem().toString());
                itemBo.update(dto);
            }
        }

      loadItemTable();
    }

    public void refreshOnAction(ActionEvent actionEvent) {
    }
}
