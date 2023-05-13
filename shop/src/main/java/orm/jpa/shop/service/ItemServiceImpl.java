package orm.jpa.shop.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import orm.jpa.shop.entity.Item;
import orm.jpa.shop.repository.ItemRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Item findItemById(Long itemId) {
        return itemRepository.findById(itemId).orElse(null);
    }

    @Override
    public List<Item> findAllItem() {
        return itemRepository.findAll();
    }

    @Override
    public Item updateItem(Item item) {
        return itemRepository.save(item);
    }
}
