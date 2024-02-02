package entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Customer {
    @Id
    private String contactNum;
    private String name;
    private String email;
    private int orderQty;

    @OneToMany(mappedBy = "customer")
    List<Orders> orders;

    public Customer(String contactNum, String name, String email, int orderQty) {
        this.contactNum = contactNum;
        this.name = name;
        this.email = email;
        this.orderQty = orderQty;
    }
}
