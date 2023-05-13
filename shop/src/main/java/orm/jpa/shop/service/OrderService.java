package orm.jpa.shop.service;

import orm.jpa.shop.entity.Order;

import java.util.List;

public interface OrderService {

    Long order(Long memberId, Long itemId, int count);

    void cancelOrder(Long orderId);

    List<Order> findOrders();

    Order findOrderByOrderId(Long orderId);

}
