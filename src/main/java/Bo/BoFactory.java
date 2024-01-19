package Bo;

import Bo.custom.impl.CustomerBoImpl;
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
        }
        return null;
    }
}
