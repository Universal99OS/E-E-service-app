package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemDto {
    private String itemId;
    private String description;
    private String name;
    private String category;
    private String status;
    private OrdersDto ordersDto;
}
