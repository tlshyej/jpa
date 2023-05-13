package orm.jpa.shop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "TB_ORDER_ITEM")
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long orderItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    @NonNull
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @Column(name = "ORDER_PRICE")
    @NonNull
    private int orderPrice;

    @Column(name = "COUNT")
    @NonNull
    private int count;

    public void cancle() {
        getItem().addStock(count);
    }

    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem(item, orderPrice, count);
        item.removeStock(count);
        return orderItem;
    }

}
