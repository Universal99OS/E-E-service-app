package dao.custom.impl;

import dao.custom.StaffDao;
import dto.StaffDto;

import java.util.List;

public class StaffDaoImpl implements StaffDao {
    @Override
    public boolean save(StaffDto dto) {
        return false;
    }

    @Override
    public boolean delete(StaffDto dto) {
        return false;
    }

    @Override
    public boolean update(StaffDto dto) {
        return false;
    }

    @Override
    public List<StaffDto> getAll() {
        return null;
    }
}
