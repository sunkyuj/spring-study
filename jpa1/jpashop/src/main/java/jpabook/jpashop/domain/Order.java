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
public class Order { // cascade 때문에 Order만 persist 하면 OrderItems, Delivery 다 persist 됨
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // 항상 EAGER가 아닌 LAZY로 지연로딩으로 해야됨 (XToOne은 기본이 EAGER)
    @JoinColumn(name = "member_id") // FK, 연관관계의 주인
    private Member member; // 여기 값을 바꾸면 Member의 orders에 변화 생김

    @OneToMany(mappedBy = "order", // OrderItem의  order에 의해 매핑됨
               cascade = CascadeType.ALL) // 모든 경우에 CASCADE 해서 다 같이 묶여서 처리 (ALL, DELETE, UPDATE 등 있음)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY,  // 항상 EAGER가 아닌 LAZY로 지연로딩으로 해야됨 (XToOne은 기본이 EAGER)
              cascade = CascadeType.ALL) // 모든 경우에 CASCADE 해서 다 같이 묶여서 처리 (ALL, DELETE, UPDATE 등 있음)
    @JoinColumn(name = "delivery_id") // FK, 연관관계의 주인
    private Delivery delivery;

    private LocalDateTime orderDate; // 주문시간

    @Enumerated(EnumType.STRING) // 절대 오디너리 사용 금지, 항상 STRING 쓰자
    private OrderStatus status; // [ORDER, CANCEL]

    //==연관관계 메서드==// <-- 양뱡향일때 좋음
    public void setMember(Member member) {
        this.member = member; // Order(얘 자신)의 member에 Member 넣어줌
        member.getOrders().add(this); // Member의 orders에 Order 추가
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem); // Order(얘 자신)의 OrderItems에 OrderItem 추가
        orderItem.setOrder(this); // OrderItem에는 이 Order를 넣어줌
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery; // Order(얘 자신)의 delivery에 Delivery 넣어줌
        delivery.setOrder(this); // Delivery에는 이 Order를 넣어줌
    }
}
