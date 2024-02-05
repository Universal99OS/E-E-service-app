package dao.custom;

import dao.CrudDao;
import dto.ItemDto;

public interface ItemDao extends CrudDao<ItemDto> {
    String getNextItemId();
}
