package entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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


    public Orders(String orderId, String date) {
        this.orderId = orderId;
        this.date = date;
    }
}
