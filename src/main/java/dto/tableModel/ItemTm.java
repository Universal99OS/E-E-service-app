package dto.tableModel;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ItemTm extends RecursiveTreeObject<ItemTm> {

    private String itemId;
    private String description;
    private String name;
    private String category;
    private String status;
    private JFXButton button;

}
