package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor // final 붙은 애들 가지고 생성자 만들어줘서 아래 주석 생략 가능
public class BasicItemController {
    private final ItemRepository itemRepository;

    //    @Autowired // 생성자 하나면 생략 가능
//    public BasicItemController(ItemRepository itemRepository) { // 생성자 주입
//        this.itemRepository = itemRepository;
//    }
    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    //@PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam int quantity,
                       Model model) {
        Item item = new Item(itemName, price, quantity);
        itemRepository.save(item);
        model.addAttribute("item", item);
        return "basic/item";
    }

//    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item) {
        itemRepository.save(item);
//        model.addAttribute("item", item); // ModelAttribute("item") 해서 생략 가능
        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item) { // 이름 생략하면 item으로 됨 (클래스명에서 첫글자 소문자로)
        itemRepository.save(item);
        return "basic/item";
    }
//    @PostMapping("/add")
    public String addItemV4(Item item) { // ModelAttribute도 생략 가능
        itemRepository.save(item);
        return "basic/items";
    }

//    @PostMapping("/add")
    public String addItemV5(Item item) { // ModelAttribute도 생략 가능
        itemRepository.save(item);
        return "redirect:/basic/items/" + item.getId(); // prg, 이러면 새로고침 해도 새로 안 생김
    }

    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) { // ModelAttribute도 생략 가능
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId()); // path에 있으르모 itemId가 치환됨
        redirectAttributes.addAttribute("status", true); // path에 없으므로 쿼리파라미터로 넘어감, 저장완료 텍스트 출력용

        return "redirect:/basic/items/{itemId}"; // itemId로 치환됨
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String editItem(@PathVariable Long itemId, Item item) { // ModelAttribute도 생략 가능
        itemRepository.update(itemId,item);
        return "redirect:/basic/items/{itemId}";
    }

    @PostConstruct
    public void init(){ // 테스트용
        itemRepository.save(new Item("itemA", 10, 10000));
        itemRepository.save(new Item("itemB", 20, 20000));
    }
}
