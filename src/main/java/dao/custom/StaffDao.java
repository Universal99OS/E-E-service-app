package dao.custom;

import dao.CrudDao;
import dto.StaffDto;

public interface StaffDao extends CrudDao<StaffDto> {
    boolean isValidLoginData(String email,String password);
}
