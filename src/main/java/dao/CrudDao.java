package dao;

import java.util.List;

public interface CrudDao <T> extends SuperDao{
    public boolean save(T dto);
    public boolean delete(T dto);
    public boolean update(T dto);

    public List<T> getAll();
}
