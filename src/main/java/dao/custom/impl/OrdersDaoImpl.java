package dao.custom.impl;

import dao.custom.OrdersDao;
import dao.util.HibernateUtil;
import dto.CustomerDto;
import dto.OrdersDto;
import entity.Customer;
import entity.Orders;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OrdersDaoImpl implements OrdersDao {
    @Override
    public boolean save(OrdersDto dto) {
        Orders orders = new Orders(
                dto.getOrderId(),
                dto.getDate());

        CustomerDto customer = dto.getCustomer();
        Customer customerEntity = new Customer(
                customer.getContactNum(),
                customer.getName(),
                customer.getEmail(),
                20
        );

        orders.setCustomer(customerEntity);
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(orders);
        transaction.commit();
        session.close();



        return true;
    }

    @Override
    public boolean delete(OrdersDto dto) {
        return false;
    }

    @Override
    public boolean update(OrdersDto dto) {
        return false;
    }

    @Override
    public List<OrdersDto> getAll() {
        return null;
    }

    @Override
    public String nextOrderID() {
        Session session = HibernateUtil.getSession();
        Orders orders = session.createQuery("FROM Orders ORDER BY orderId DESC", Orders.class)
                .setMaxResults(1)
                .uniqueResult();

        if(orders!=null){
            int i = Integer.parseInt(orders.getOrderId());
            return String.valueOf(++i);
        }else {
            return "1000";
        }
    }
}
