package dao.custom.impl;

import dao.custom.StaffDao;
import dao.util.HibernateUtil;
import dto.StaffDto;
import entity.Staff;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class StaffDaoImpl implements StaffDao {


    @Override
    public boolean save(StaffDto dto) {
        Staff entity = new Staff(
                dto.getContactNum(),
                dto.getName(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getUserType()
        );

        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(StaffDto dto) {
        Staff entity = new Staff(
                dto.getContactNum(),
                dto.getName(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getUserType()
        );
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(StaffDto dto) {
        Staff entity = new Staff(
                dto.getContactNum(),
                dto.getName(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getUserType()
        );

        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<StaffDto> getAll() {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("FROM Staff");
        List<Staff> list = query.list();

        List<StaffDto> list1 = new ArrayList<>();

        for (Staff entity:list) {
            list1.add(new StaffDto(
                    entity.getContactNum(),
                    entity.getName(),
                    entity.getEmail(),
                    entity.getPassword(),
                    entity.getUserType()

            ));
        }
        return list1;
    }

    @Override
    public boolean isValidLoginData(String email, String password) {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("FROM Staff s where s.email=:emailValue AND s.password=:passwordValue");

        query.setParameter("emailValue",email);
        query.setParameter("passwordValue",password);

        List<Staff> list = query.list();
        session.close();

        return  !list.isEmpty();
    }
}
