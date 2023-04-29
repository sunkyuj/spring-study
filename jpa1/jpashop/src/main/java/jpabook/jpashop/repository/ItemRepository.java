package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager em;

    public void save(Item item) {
        if (item.getId() == null) { // 처음 저장할 땐 id 없으므로 persist
            em.persist(item);
        } else { // update 비슷함, 이미 있으므로 기존의 item에 병합
            em.merge(item);
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class) // jpql, from의 대상이 Entity 객체임, Member.class는 반환 타입
                .getResultList();
    }

    public List<Item> findByName(String name) {
        return em.createQuery("select i from Item i where i.name = :name", Item.class) // jpql
                .setParameter("name", name) // 윗줄에 :name에 파라미터 name이 바인딩 됨
                .getResultList();
    }
}
