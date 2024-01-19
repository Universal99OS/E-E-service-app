package entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
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
}
