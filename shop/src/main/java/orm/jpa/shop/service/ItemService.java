package orm.jpa.shop.service;

import orm.jpa.shop.entity.Item;

import java.util.List;

public interface ItemService {

    Item saveItem(Item item);

    Item findItemById(Long itemId);

    List<Item> findAllItem();

    Item updateItem(Item item);

}
