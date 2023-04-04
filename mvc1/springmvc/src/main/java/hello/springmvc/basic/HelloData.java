package hello.springmvc.basic;

import lombok.Data;

@Data // Getter, Setter, ToString, EqualsAndHashCode, RequiredArgsConstructor 자동으로 만들어줌
public class HelloData {
    private String username;
    private int age;
}
