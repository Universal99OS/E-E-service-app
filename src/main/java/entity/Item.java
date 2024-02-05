package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

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

    public Item(String itemId, String description, String name, String category, String status) {
        this.itemId = itemId;
        this.description = description;
        this.name = name;
        this.category = category;
        this.status = status;
    }
}
