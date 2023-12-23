package hello.login;

import hello.login.web.argumentresolver.LoginMemberArgumentResolver;
import hello.login.web.filter.LogFilter;
import hello.login.web.filter.LoginCheckFilter;
import hello.login.web.interceptor.LogInterceptor;
import hello.login.web.interceptor.LoginCheckInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginMemberArgumentResolver()); // HandlerMethodArgumentResolver 등록
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1) // 필터와 인터셉터의 순서를 지정할 수 있음
                .addPathPatterns("/**") // 모든 요청에 인터셉터 적용
                .excludePathPatterns("/css/**", "/*.ico", "/error"); // 인터셉터 제외 경로

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/**") // 모든 요청에 인터셉터 적용
                .excludePathPatterns("/", "/members/add", "/login", "/logout", "/css/**", "/*.ico", "/error"); // 인터셉터 제외 경로
    }

//    @Bean // L ogInterceptor 추가 후엔 사용할 필요 없음
    public FilterRegistrationBean<Filter> logFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LogFilter()); // 필터 등록
        registrationBean.setOrder(1); // 필터 순서
        registrationBean.addUrlPatterns("/*"); // 모든 요청에 필터 적용
        return registrationBean;
    }

//    @Bean // LoginCheckInterceptor 추가 후엔 사용할 필요 없음
    public FilterRegistrationBean<Filter> loginCheckFilter () {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LoginCheckFilter()); // 필터 등록
        registrationBean.setOrder(2); // 필터 순서
        registrationBean.addUrlPatterns("/*"); // 모든 요청에 필터 적용
        return registrationBean;
    }
}
