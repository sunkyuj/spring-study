package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional // readOnly = false
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) { // id 명확히 전달할 것, 인자 너무 많으면 dto 로 받아도 됨
        Item findItem = itemRepository.findOne(itemId);
        findItem.change(name, price, stockQuantity);

        // repo.save, repo.merge 호출할 필요 없음
        // Transactional에 의해 트랜젝션이 커밋이 되면 flush -> jpa가 변경 감지
        // merge는 put 비슷하게 빈 값을 null로 처리하기 때문에 위험
        // 엔티티를 변경할 때는 항상 변경감지를 사용하세요

    }




}
