package dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CustomerDto {
    private String contactNum;
    private String name;
    private String email;
    private int orderQty;
}
