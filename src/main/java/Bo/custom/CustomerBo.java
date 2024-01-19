package Bo.custom;

import Bo.CrudBo;
import Bo.SuperBo;
import controller.AllCustomersController;
import dto.CustomerDto;
import dto.tableModel.CustomerTm;

import java.util.List;

public interface CustomerBo extends CrudBo<CustomerDto> {

    public List<CustomerTm> getAllTm(AllCustomersController acc);
}
