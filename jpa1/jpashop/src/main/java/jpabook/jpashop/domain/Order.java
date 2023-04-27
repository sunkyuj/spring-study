package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity // DB 테이블에 매핑
@Table(name="orders") // 없으면 그냥 클래스명을 테이블에 매핑
@Getter @Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // 항상 EAGER가 아닌 LAZY로 지연로딩으로 해야됨 (XToOne은 기본이 EAGER)
    @JoinColumn(name = "member_id") // FK, 연관관계의 주인
    private Member member; // 여기 값을 바꾸면 Member의 orders에 변화 생김

    @OneToMany(mappedBy = "order") // OrderItem의  order에 의해 매핑됨
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY) // 항상 EAGER가 아닌 LAZY로 지연로딩으로 해야됨 (XToOne은 기본이 EAGER)
    @JoinColumn(name = "delivery_id") // FK, 연관관계의 주인
    private Delivery delivery;

    private LocalDateTime orderDate; // 주문시간

    @Enumerated(EnumType.STRING) // 절대 오디너리 사용 금지, 항상 STRING 쓰자
    private OrderStatus status; // [ORDER, CANCEL]
}
