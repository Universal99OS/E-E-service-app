package dao.custom.impl;

import dao.custom.ItemDao;
import dao.util.HibernateUtil;
import dto.CustomerDto;
import dto.ItemDto;
import dto.OrdersDto;
import entity.Customer;
import entity.Item;
import entity.Orders;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao {
    @Override
    public boolean save(ItemDto dto) {
        Item item=new Item(dto.getItemId(),
                dto.getDescription(),
                dto.getName(),
                dto.getCategory(),
                dto.getStatus());

        OrdersDto ordersDto = dto.getOrdersDto();
        CustomerDto customer = ordersDto.getCustomer();

        Customer customerEntity = new Customer(
                customer.getContactNum(),
                customer.getName(),
                customer.getEmail(),
                20
        );
        Orders orders = new Orders(
                ordersDto.getOrderId(),
                ordersDto.getDate()
        );

        orders.setCustomer(customerEntity);

        item.setOrders(orders);

        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(item);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(ItemDto dto) {
        Item item=new Item(dto.getItemId(),
                dto.getDescription(),
                dto.getName(),
                dto.getCategory(),
                dto.getStatus());

        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(item);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(ItemDto dto) {
        Item item=new Item(dto.getItemId(),
                dto.getDescription(),
                dto.getName(),
                dto.getCategory(),
                dto.getStatus());

        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Item item1 = session.find(Item.class, item);
        item1.setItemId(item.getItemId());
        item1.setDescription(item.getDescription());
        item1.setName(item.getName());
        item1.setCategory(item.getCategory());
        item1.setStatus(item.getStatus());
        session.save(item1);
        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public List<ItemDto> getAll() {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("FROM Item");
        List<Item> list = query.list();

        List<ItemDto> itemDtos=new ArrayList<>();

        for (Item item:list) {
            Orders orders = item.getOrders();
            Customer customer = orders.getCustomer();
            CustomerDto cusDto = new CustomerDto(customer.getContactNum(), customer.getName(), customer.getEmail(), customer.getOrderQty());
            OrdersDto ordersDto = new OrdersDto(orders.getOrderId(), orders.getDate(), cusDto);

            itemDtos.add(new ItemDto(
                    item.getItemId(),
                    item.getDescription(),
                    item.getName(),
                    item.getCategory(),
                    item.getStatus(),
                    ordersDto
            ));
        }
        return itemDtos;
    }

    @Override
    public String getNextItemId() {
        Session session = HibernateUtil.getSession();
        Item lastItem = session.createQuery("FROM Item ORDER BY itemId DESC", Item.class)
                .setMaxResults(1)
                .uniqueResult();

        if(lastItem!=null){
            int i = Integer.parseInt(lastItem.getItemId());
            return String.valueOf(++i);
        }else {
            return "1";
        }
    }
}
