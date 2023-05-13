package orm.jpa.shop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("BOOK")
@NoArgsConstructor
public class ItemBook extends Item {

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "ISBN")
    private String isbn;

    public ItemBook(String name, int stockQuantity, int price) {
        super(name, stockQuantity, price);
    }
}
