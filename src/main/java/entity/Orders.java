package entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Orders {
    @Id
    private String orderId;
    private String date;

    @ManyToOne()
    @JoinColumn(name="contactNum",nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "orders",cascade =CascadeType.ALL)
    List<Item> items;



    public Orders(String orderId, String date) {
        this.orderId = orderId;
        this.date = date;
    }
}
