package Bo.custom;

import Bo.CrudBo;
import dto.StaffDto;

public interface StaffBo extends CrudBo<StaffDto> {
    boolean isValidLoginData(String email, String password);


}
