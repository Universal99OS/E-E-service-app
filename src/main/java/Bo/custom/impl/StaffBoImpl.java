package Bo.custom.impl;

import Bo.custom.StaffBo;
import dao.DaoFactory;
import dao.custom.StaffDao;
import dao.util.BoType;
import dto.StaffDto;

import java.util.List;

public class StaffBoImpl implements StaffBo {
    StaffDao staffDao= DaoFactory.getInstance().getDao(BoType.STAFF);
    @Override
    public boolean save(StaffDto dto) {
        return staffDao.save(dto);
    }

    @Override
    public boolean update(StaffDto dto) {
        return staffDao.update(dto);
    }

    @Override
    public boolean delete(StaffDto dto) {
        return staffDao.delete(dto);
    }

    @Override
    public List<StaffDto> getAll() {
        return staffDao.getAll();
    }

    @Override
    public boolean isValidLoginData(String email, String password) {
        return staffDao.isValidLoginData(email,password);
    }
}
