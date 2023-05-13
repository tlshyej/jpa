package orm.jpa.shop.service;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import orm.jpa.shop.OrderStatus;
import orm.jpa.shop.entity.*;
import orm.jpa.shop.repository.ItemRepository;
import orm.jpa.shop.repository.MemberRepository;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class OrderServiceImplTest {

    @Autowired OrderService orderService;
    @Autowired MemberRepository memberRepository;
    @Autowired ItemRepository itemRepository;

    @Test
    void order() {
        Member member = createMember();
        int stockQuantity = 10;
        int orderCount = 2;
        Item item = createBookItem("JPA", 10, 10000);

        Long orderId = orderService.order(member.getMemberId(), item.getItemId(), orderCount);

        Order findOrder = orderService.findOrderByOrderId(orderId);
        assertEquals(findOrder.getStatus(), OrderStatus.ORDER);
        assertEquals(findOrder.getOrderItemList().size(), 1);
        assertEquals(item.getStockQuantity(), stockQuantity - orderCount);
    }

    private Member createMember() {
        Member member = Member.builder()
                                .memberName("JPA")
                                .address(Address.builder()
                                                .zipcode("우편번호")
                                                .city("도시")
                                                .street("도로명")
                                                .build())
                                .orderList(new ArrayList<>())
                                .build();

        return memberRepository.save(member);
    }

    private Item createBookItem(String name, int stockQuantity, int price) {
        Item book = new ItemBook(name, stockQuantity, price);
        return itemRepository.save(book);
    }

    @Test
    void cancelOrder() {
        Member member = createMember();
        int stockQuantity = 10;
        int orderCount = 2;
        Item item = createBookItem("JPA", 10, 10000);

        Long orderId = orderService.order(member.getMemberId(), item.getItemId(), orderCount);
        assertEquals(item.getStockQuantity(), stockQuantity - orderCount);

        orderService.cancelOrder(orderId);
        assertEquals(item.getStockQuantity(), stockQuantity);
    }

}