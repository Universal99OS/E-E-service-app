package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
public class Item{
    @Id
    private String itemId;
    private String description;
    private String name;
    private String category;
    private String status;

    @ManyToOne
    @JoinColumn(name = "orderId",nullable = false)
    Orders orders;

    public Item(String itemId, String description, String name, String category, String status) {
        this.itemId = itemId;
        this.description = description;
        this.name = name;
        this.category = category;
        this.status = status;
    }
}
