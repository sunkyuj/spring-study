package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 싱글테이블은 한 테이블에 다 때려박기
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items") // Category의 items에 매핑
    private List<Category> categories = new ArrayList<>();

    //==비즈니스 로직==// <- 엔티티 안에 비즈니스 로직이 있는게 객체지향적이고 응집력 좋음
    /*
    * stock 증가
    * */
    public void addStock(int quantity) { // Service에서 지지고 볶고 하는것보다 데이터를 가지고 있는 쪽에서 하는게 좋음
        this.stockQuantity += quantity;
    }

    /*
     * stock 감소
     * */
    public void removeStock(int quantity) { // Service에서 지지고 볶고 하는것보다 데이터를 가지고 있는 쪽에서 하는게 좋음
        int restStock = this.stockQuantity - quantity;
        if(restStock<0){
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
