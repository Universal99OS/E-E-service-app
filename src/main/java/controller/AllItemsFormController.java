package controller;

import Bo.BoFactory;
import Bo.custom.ItemBo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
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
    public JFXTextField statusID;
    public JFXTextField searchId;
    public JFXTreeTableView<ItemTm> table;
    public TreeTableColumn itemCol;
    public TreeTableColumn nameCol;
    public TreeTableColumn desCol;
    public TreeTableColumn categoryCol;
    public TreeTableColumn statusCol;
    public TreeTableColumn optionCol;

    ItemBo itemBo= BoFactory.getInstance().getBO(BoType.ITEM);

    public void initialize(){
        itemCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("itemId"));
        desCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("description"));
        nameCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));
        categoryCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("category"));
        statusCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("status"));
        optionCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("button"));

        loadItemTable();

    }

    private void loadItemTable() {
        List<ItemDto> all = itemBo.getAll();
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
    }

    public void refreshOnAction(ActionEvent actionEvent) {
    }
}
