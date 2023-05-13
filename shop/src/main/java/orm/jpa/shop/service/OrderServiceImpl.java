package orm.jpa.shop.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import orm.jpa.shop.DeliveryStatus;
import orm.jpa.shop.entity.*;
import orm.jpa.shop.repository.MemberRepository;
import orm.jpa.shop.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final MemberService memberService;
    private final ItemService itemService;
    private final OrderRepository orderRepository;

    @Override
    public Long order(Long memberId, Long itemId, int count) {
        Member member = memberService.findByMemberId(memberId);
        Item item = itemService.findItemById(itemId);

        Delivery delivery = Delivery.builder()
                                    .status(DeliveryStatus.READY)
                                    .address(member.getAddress())
                                    .build();

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        Order order = Order.createOrder(member, delivery, orderItem);
        orderRepository.save(order);

        return order.getOrderId();
    }

    @Override
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        order.cancel();
    }

    @Override
    public List<Order> findOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order findOrderByOrderId(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

}
