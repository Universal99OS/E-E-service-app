package Bo.custom.impl;

import Bo.custom.StaffBo;
import Email.EmailService;
import dao.DaoFactory;
import dao.custom.StaffDao;
import dao.util.BoType;
import dto.StaffDto;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class StaffBoImpl implements StaffBo {
    EmailService emailService=new EmailService();
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
