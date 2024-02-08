package Bo.custom;

import Bo.CrudBo;
import dto.OrdersDto;

public interface OrdersBo extends CrudBo<OrdersDto> {
    String nextOrderID();
}
