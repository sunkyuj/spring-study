package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        int user1price = statefulService1.order("userA", 10000);// A가 10000원 주문
        int user2price = statefulService2.order("userB", 20000);// B가 20000원 주문, 얘가 인스턴스를 바꿔버림

        //int price = statefulService1.getPrice(); // A는 10000원 기대하지만 20000원이 나옴
        System.out.println("user1price = " + user1price);

        Assertions.assertThat(user1price).isEqualTo(10000);
    }
    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}