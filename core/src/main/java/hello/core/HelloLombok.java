package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter// 알아서 게터 만들어줌
@Setter// 알아서 세터 만들어줌
@ToString// 알아서 toString 만들어줌
public class HelloLombok {
    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("asdf"); // 알아서 세터 만들어줌

        String name = helloLombok.getName(); // 알아서 게터 만들어줌
        System.out.println("name = " + name);
    }
}
