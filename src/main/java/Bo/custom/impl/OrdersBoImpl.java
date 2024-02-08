package Bo.custom.impl;

import Bo.custom.OrdersBo;
import dao.DaoFactory;
import dao.custom.OrdersDao;
import dao.util.BoType;
import dto.OrdersDto;

import java.util.List;

public class OrdersBoImpl implements OrdersBo {

    OrdersDao ordersDao= DaoFactory.getInstance().getDao(BoType.ORDERS);
    @Override
    public boolean save(OrdersDto dto) {
        ordersDao.save(dto);
        return true;
    }

    @Override
    public boolean update(OrdersDto dto) {
        return false;
    }

    @Override
    public boolean delete(OrdersDto dto) {
        return false;
    }

    @Override
    public List<OrdersDto> getAll() {
        return null;
    }

    @Override
    public String nextOrderID() {
        return ordersDao.nextOrderID();
    }
}
