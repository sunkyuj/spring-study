package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Validator;

@Component
public class ItemValidator  implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Item.class.isAssignableFrom(clazz);
        // ItemValidator가 검증할 수 있는 타입인지 확인
        // ex) item == clazz (클래스(clazz)로 넘어오는 것이 item인지 확인)
        // ex) item == subItem (아이템의 자식클래스여도 검증 가능)
    }

    @Override
    public void validate(Object target, org.springframework.validation.Errors errors) {
        Item item = (Item) target;

        // 검증 로직
        if (!StringUtils.hasText(item.getItemName())) {
            // objectName: ModelAttribute 이름, field: 에러가 발생한 필드명, rejectedValue: 사용자가 입력한 값(거절된 값), 나머지 세개는 그냥 false/null, defaultMessage: 기본 메세지
            errors.rejectValue("itemName", "required"); // 위와 동일한 결과
        } // ValidationUtils.rejectIfEmptyOrWhitespace(errors, "itemName", "required"); <- 이거랑 동일함, 이런식으로 한줄로도 가능
        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            errors.rejectValue("price", "range", new Object[]{1000,1000000}, null); // 위와 동일한 결과
        }
        if (item.getQuantity() == null || item.getQuantity() >= 9999) {
            errors.rejectValue("quantity", "max", new Object[]{9999}, null); // 위와 동일한 결과
        }

        // 특정 필드가 아닌 복합 룰 검증
        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < 10000) {
                errors.reject("totalPriceMin", new Object[]{10000,resultPrice}, null); // 위와 동일한 결과
            }
        }

    }
}
