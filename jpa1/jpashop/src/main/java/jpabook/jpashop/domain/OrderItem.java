package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
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

}
