package orm.jpa.shop.entity;

import jakarta.persistence.*;
import lombok.*;
import orm.jpa.shop.OrderStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TB_ORDER")
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class Order {

    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long orderId;

    @Column(name = "ORDER_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    @NonNull
    private Date orderDate;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    @NonNull
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    private Delivery delivery;

    // 연관관계 설정
    public void setMember(Member member) {
        this.member = member;
        member.getOrderList().add(this);
    }

    // 연관관계 설정
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public static Order createOrder(Member member, Delivery delivery, OrderItem ...orderItems) {
        Order order = new Order(new Date(), OrderStatus.ORDER);
        order.setMember(member);
        order.setDelivery(delivery);

        for (OrderItem orderItem : orderItems) {
            order.getOrderItemList().add(orderItem);
        }

        return order;
    }

    public void cancel() {
        this.setStatus(OrderStatus.CANCEL);
        for (OrderItem orderItem : orderItemList) {
            orderItem.cancel();
        }
    }

}
