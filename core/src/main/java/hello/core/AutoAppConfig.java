package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        //basePackages = "hello.core.member", // 지정하지 않으면 컴포넌트 스캔이 붙은 녀석(AutoAppConfig)의 패키지(hello.core)가 된다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        // AppConfig를 추가시키면 안되니까 exclude 해준거임
)
public class AutoAppConfig {

    /*
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    */
}
