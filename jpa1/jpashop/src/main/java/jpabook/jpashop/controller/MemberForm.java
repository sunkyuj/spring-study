package jpabook.jpashop.controller;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/*
* 얘가 약간 DTO같은 느낌
* 만약 얘가 없으면 엔티티에 화면 종속적인 기능이 계속 추가되어 복잡해짐
* */
@Getter @Setter
public class MemberForm {
    @NotEmpty(message = "회원 이름은 필수입니다")
    private String name;

    private String city;
    private String street;
    private String zipcode;
}
