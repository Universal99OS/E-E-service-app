package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Staff {
    @Id
    private String contactNum;
    private String name;
    private String email;
    private String password;
    private String userType;

    public Staff(String contactNum, String name, String email, String password, String userType) {
        this.contactNum = contactNum;
        this.name = name;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }
}
