package hello.itemservice.domain.item;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
//@ScriptAssert(lang = "javascript", script = "_this.price * _this.quantity >= 10000", message = "총합이 10000원 넘게 입력해주세요.") // 안 씀, 자바 코드로 검증하는게 나음
public class Item {

    // groups 를 쓰려면 @Validated 를 써야함
    // 근데 groups는 실제로 사용 잘 안함 (지저분하기도 하고, DTO를 사용)
    @NotNull(groups = UpdateCheck.class) // 수정 시에만 검증
    private Long id;

    @NotBlank(groups = {SaveCheck.class, UpdateCheck.class}) // (message = "공백 X") 붙이면 메세지 커스터마이징 가능
    private String itemName;

    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    @Range(min = 1000, max = 1000000, groups = {SaveCheck.class, UpdateCheck.class})
    private Integer price;

    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    @Max(value = 9999, groups = SaveCheck.class) // 저장 시에만 검증
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
