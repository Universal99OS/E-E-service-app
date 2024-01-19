package dto.tableModel;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CustomerTm extends RecursiveTreeObject<CustomerTm> {
    private String contactNum;
    private String name;
    private String email;
    private int orderQty;
    private JFXButton button;
}
