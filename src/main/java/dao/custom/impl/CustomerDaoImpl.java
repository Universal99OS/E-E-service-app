package dao.custom.impl;

import dao.custom.CustomerDao;
import dao.util.HibernateUtil;
import dto.CustomerDto;
import entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public boolean save(CustomerDto dto) {
        Customer entity=new Customer(dto.getContactNum(), dto.getName(),dto.getEmail(),dto.getOrderQty());

        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(CustomerDto dto) {
        Customer entity=new Customer(
                dto.getContactNum(),
                dto.getName(),
                dto.getEmail(),
                dto.getOrderQty());
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(CustomerDto dto) {
        Customer entity=new Customer(
               dto.getContactNum(),
               dto.getName(),
                dto.getEmail(),
                dto.getOrderQty()
        );
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = session.find(Customer.class, entity.getContactNum());
        customer.setName(entity.getName());
        customer.setEmail(entity.getEmail());
        customer.setOrderQty(entity.getOrderQty());
        session.save(customer);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<CustomerDto> getAll() {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("FROM Customer");
        List<Customer> list = query.list();

        List<CustomerDto> customerDtoList=new ArrayList<>();

        for (Customer entity:list) {
            customerDtoList.add(new CustomerDto(
                    entity.getContactNum(),
                    entity.getName(),
                    entity.getEmail(),
                    entity.getOrderQty()
            ));
        }

        return customerDtoList;

    }
}
