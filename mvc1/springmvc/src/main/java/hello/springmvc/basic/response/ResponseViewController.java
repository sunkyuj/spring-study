package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.text.View;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello!");
        return mav;
    }

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello!");
        return "response/hello"; // view의 논리적 이름(경로)임(response 폴더의 hello.html 찾겠다). 만약 @ResponseBody 쓰면 그냥 텍스트로 박힘
    }

    @RequestMapping("/response/hello") // 컨트롤러의 경로 이름과 뷰의 논리적 이름이 같으면 찾아줌, 근데 불명확해서 비추
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hello!");
    }
}
