package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // new로 생성하는거 막음
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // 항상 EAGER가 아닌 LAZY로 지연로딩으로 해야됨 (XToOne은 기본이 EAGER)
    @JoinColumn(name = "item_id") // FK, 연관관계의 주인
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY) // 항상 EAGER가 아닌 LAZY로 지연로딩으로 해야됨 (XToOne은 기본이 EAGER)
    @JoinColumn(name = "order_id") // FK, 연관관계의 주인
    private Order order;

    private int orderPrice; // 주문 가격
    private int count; // 주문 수량


    //==생성 메서드==//
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count); // 재고 count 만큼 없애기
        return orderItem;
    }

    //==비즈니스 로직==//
    public void cancel() {
        getItem().addStock(count); // 재고를 주문 수량만큼 다시 늘려줌 (원복)
    }
    //==조회 로직==//

    /**
     * 주문상품 전체 가격 조회
     */
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
