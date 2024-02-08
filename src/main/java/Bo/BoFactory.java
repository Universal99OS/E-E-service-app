package Bo;

import Bo.custom.impl.CustomerBoImpl;
import Bo.custom.impl.ItemBoImpl;
import Bo.custom.impl.OrdersBoImpl;
import Bo.custom.impl.StaffBoImpl;
import dao.util.BoType;

public class BoFactory {

    private static BoFactory boFactory;

    private BoFactory(){}

    public static BoFactory getInstance(){
        return boFactory!=null? boFactory:(boFactory=new BoFactory());
    }

    public <T extends SuperBo>T getBO(BoType type){
        switch (type){
            case CUSTOMER: return (T)new CustomerBoImpl();
            case ITEM: return (T)new ItemBoImpl();
            case STAFF: return (T)new StaffBoImpl();
            case ORDERS:return (T)new OrdersBoImpl();
        }
        return null;
    }
}
