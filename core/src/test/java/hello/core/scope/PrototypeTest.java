package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class PrototypeTest {

    @Test
    void prototypeBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);// 호춣 할때마다 새로운 빈이 생김
        System.out.println("prototypeBean1 = " + prototypeBean1);
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);// 호춣 할때마다 새로운 빈이 생김
        System.out.println("prototypeBean2 = " + prototypeBean2);
        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
        ac.close(); // 안 됨, 프로토타입이라 스프링이 호출한 후에 더이상 관리 안함
        prototypeBean1.destroy(); // 이런식으로 클라이언트가 직접 종료시켜야함
        prototypeBean2.destroy();


    }
    @Scope("prototype")
    static class PrototypeBean{
        public void init(){
            System.out.println("prototype.init");
        }
        public void destroy(){
            System.out.println("prototype.destroy");
        }
    }
}
