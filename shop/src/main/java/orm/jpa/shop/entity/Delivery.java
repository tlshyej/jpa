package orm.jpa.shop.entity;

import jakarta.persistence.*;
import lombok.*;
import orm.jpa.shop.DeliveryStatus;

@Entity
@Table(name = "TB_DELIVERY")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "DELIVERY_ID")
    private Long deliveryId;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private DeliveryStatus status;

    @Embedded
    @NonNull
    private Address address;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    public void setOrder(Order order) {
        this.order = order;
    }

}
