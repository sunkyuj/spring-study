package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderForm {
    private String userName;
    private String itemName;
    private int orderQuantity;
}
