package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
    private final LogDemoService logDemoService;
    private final MyLogger  myLogger;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();
        System.out.println("myLogger = " + myLogger.getClass()); // 확인해보면 가짜 프록시 객체를 생성한걸 확인할 수 있다
        // myLoggerProvider.getObject를 호출하는 시점까지 request scope 빈의 생성(요청)을 지연시킴
        //MyLogger myLogger = myLoggerProvider.getObject(); // 진짜 필요한 시점인 이때 MyLogger 처음 만들어짐
        myLogger.setRequestURL(requestURL);
        myLogger.log("controller test");
        logDemoService.logic("testID");
        return "ok";

    }

}
