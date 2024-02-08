package Bo;

import java.util.List;

public interface CrudBo<T> extends SuperBo {
    boolean save(T dto);
    boolean update(T dto);

    boolean delete(String id);

    List<T> getAll();
}
