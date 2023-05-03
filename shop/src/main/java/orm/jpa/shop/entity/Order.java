package orm.jpa.shop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import orm.jpa.shop.OrderStatus;

import java.util.Date;

@Entity
@Table(name = "TB_ORDER")
@Getter
public class Order {

    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long orderId;

    @Column(name = "ORDER_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public void setMember(Member member) {
        this.member = member;
        member.getOrderList().add(this);
    }

}
