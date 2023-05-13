package orm.jpa.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import orm.jpa.shop.entity.Item;

import java.awt.print.Book;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {


}
