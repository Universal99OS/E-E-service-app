package dao;

import dao.custom.impl.CustomerDaoImpl;
import dao.custom.impl.ItemDaoImpl;
import dao.custom.impl.OrdersDaoImpl;
import dao.custom.impl.StaffDaoImpl;
import dao.util.BoType;

public class DaoFactory {
    private static DaoFactory daoFactory;

    private DaoFactory(){}

    public static DaoFactory getInstance(){

        return daoFactory!=null?daoFactory:(daoFactory=new DaoFactory());
    }

    public <T extends SuperDao>T getDao(BoType type){
        switch (type){
            case CUSTOMER:return (T)new CustomerDaoImpl();
            case ITEM:return (T)new ItemDaoImpl();
            case STAFF:return (T)new StaffDaoImpl();
            case ORDERS:return (T)new OrdersDaoImpl();
        }

        return null;
    }
}
