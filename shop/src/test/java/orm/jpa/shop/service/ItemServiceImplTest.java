package orm.jpa.shop.service;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import orm.jpa.shop.entity.Item;

@SpringBootTest
@Transactional
class ItemServiceImplTest {

    @Autowired private ItemService itemService;

    @Test
    void saveItem() {

    }

    @Test
    void findAllItem() {

    }

    @Test
    void updateItem() {

    }

}