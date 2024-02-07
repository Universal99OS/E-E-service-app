package dto.tableModel;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StaffTm extends RecursiveTreeObject<StaffTm> {
    private String contactNum;
    private String name;
    private String email;
    private String password;
    private String userType;
    private JFXButton button;
}
