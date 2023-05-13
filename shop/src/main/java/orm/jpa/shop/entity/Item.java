package orm.jpa.shop.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 상속 관계: 단일 테이블 전략
@DiscriminatorColumn(name = "DTYPE") // 구분 컬럼 지정
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public abstract class Item {

//    enum DType {
//        ALBUM, BOOK, MOVIE
//    }

    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long itemId;

    @Column(name = "ITEM_NAME")
    @NonNull
    private String itemName;

    @Column(name = "STOCK_QUANTITY")
    @NonNull
    private Integer stockQuantity;

    @Column(name = "ITEM_PRICE")
    @NonNull
    private int price;

//    @Enumerated
//    @Column(name = "D_Type")
//    private DType dtype;

    public void addStock(int quantity) {
        stockQuantity += quantity;
    }

    public void removeStock(int quantity) {
        int count = stockQuantity - quantity;
        if (count > -1) {
            stockQuantity = count;
        }
        else {
            throw new IllegalArgumentException("no stock");
        }
    }

}
