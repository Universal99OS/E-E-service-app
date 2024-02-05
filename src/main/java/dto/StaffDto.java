package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StaffDto {
    private String contactNum;
    private String name;
    private String email;
    private String password;
    private String userType;
}
