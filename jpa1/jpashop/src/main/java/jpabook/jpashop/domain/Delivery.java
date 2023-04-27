package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY) // 항상 EAGER가 아닌 LAZY로 지연로딩으로 해야됨 (XToOne은 기본이 EAGER)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) // 절대 오디너리 사용 금지, 항상 STRING 쓰자
    private DeliveryStatus status; // READY, COMP

}
