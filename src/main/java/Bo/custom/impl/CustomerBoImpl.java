package Bo.custom.impl;

import Bo.custom.CustomerBo;

import com.jfoenix.controls.JFXButton;
import controller.AllCustomersController;
import dao.DaoFactory;
import dao.custom.CustomerDao;
import dao.util.BoType;
import dto.CustomerDto;
import dto.tableModel.CustomerTm;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.List;


public class CustomerBoImpl implements CustomerBo {

    CustomerDao customerDao=DaoFactory.getInstance().getDao(BoType.CUSTOMER);
    @Override
    public boolean save(CustomerDto dto) {
        return customerDao.save(dto);
    }

    @Override
    public boolean update(CustomerDto dto) {
        return customerDao.update(dto);
    }

    @Override
    public boolean delete(String id) {
        return customerDao.delete(id);
    }

    @Override
    public List<CustomerDto> getAll() {
        return customerDao.getAll();
    }

    @Override
    public List<CustomerTm> getAllTm(AllCustomersController acc) {
        List<CustomerDto> all = getAll();
        List<CustomerTm> list =new ArrayList<>();

        for (CustomerDto dto:all) {
            JFXButton button = new JFXButton("Delete");
            list.add(new CustomerTm(
                    dto.getContactNum(),
                    dto.getName(),
                    dto.getEmail(),
                    dto.getOrderQty(),
                    button
            ));

            button.setOnAction(actionEvent -> {
                boolean delete = delete(dto.getContactNum());
                acc.loadCustomerTable();

                if(delete){
                    new Alert(Alert.AlertType.CONFIRMATION,"Succefully deleted").show();
                }else {
                    new Alert(Alert.AlertType.ERROR,"something went wrong, Please re-check").show();
                }
            });
        }
        return list;
    }
}
