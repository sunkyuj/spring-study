package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind. annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody // ok 라는 문자가 그대로 페이지에 박힘
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge
    ) {
        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    @ResponseBody // ok 라는 문자가 그대로 페이지에 박힘
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username, // 요청파라미터랑 변수명이 같으면 생략 가능
            @RequestParam int age
    ) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody // ok 라는 문자가 그대로 페이지에 박힘
    @RequestMapping("/request-param-v4") // 좀 과할수도? v3가 적당하지않나, 스프링 고수는 괜춘
    public String requestParamV4(String username, int age) { // 단순타입(String,int,Integer) 요청파라미터랑 변수명이 같아야함
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody // ok 라는 문자가 그대로 페이지에 박힘
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username, // 필수값, 없으면 에러남, username= <- 이런식으로 하면 빈 문자열 ""가 들어와서 통과함
            @RequestParam(required = false) Integer age // int는 null 못 넣음, Integer는 객체라 nullable
    ) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody // ok 라는 문자가 그대로 페이지에 박힘
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(defaultValue = "guest") String username, // 필수값, 없으면 에러남
            @RequestParam(defaultValue = "-1") int age
    ) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody // ok 라는 문자가 그대로 페이지에 박힘
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    /*
    * 1. HelloData 객체 생성
    * 2. 요청 파라미터의 이름으로 HelloData 객체의 프로퍼티를 찾는다.
    * 3. 해당 프로퍼티의 이름으로 setter를 호출해서 파라미터의 값을 바인딩 (param: username -> then find setUsername())
    * */
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}", helloData);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) { // @ModelAttribute 생략 가능,
        // int String Integer 같은 단순타입은 RequestParam
        // 나머지는 @ModelAttribute (argument resolver로 지정해둔 타입 외)
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}", helloData);
        return "ok";
    }
}
