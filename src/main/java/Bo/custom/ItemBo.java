package Bo.custom;

import Bo.CrudBo;
import dto.ItemDto;

public interface ItemBo extends CrudBo<ItemDto> {

   String getNextItemId();
}
