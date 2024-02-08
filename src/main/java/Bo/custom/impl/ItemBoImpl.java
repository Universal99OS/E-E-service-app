package Bo.custom.impl;

import Bo.custom.ItemBo;
import dao.DaoFactory;
import dao.custom.ItemDao;
import dao.util.BoType;
import dto.ItemDto;

import java.util.List;

public class ItemBoImpl implements ItemBo {
    ItemDao itemDao= DaoFactory.getInstance().getDao(BoType.ITEM);
    @Override
    public boolean save(ItemDto dto) {

        return itemDao.save(dto);
    }

    @Override
    public boolean update(ItemDto dto) {

        return itemDao.update(dto);
    }

    @Override
    public boolean delete(String id) {
        return itemDao.delete(id);

    }

    @Override
    public List<ItemDto> getAll() {

        return itemDao.getAll();
    }

    @Override
    public String getNextItemId() {
        return itemDao.getNextItemId();
    }
}
