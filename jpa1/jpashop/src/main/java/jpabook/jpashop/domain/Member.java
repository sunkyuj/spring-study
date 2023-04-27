package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member") // Order의 member에 의해 매핑됨 (orders는 주인이 아님)
    private List<Order> orders = new ArrayList<>(); // 여기에 값을 넣는다고 해서 FK인 member에 변화가 일어나지 않음
}
