package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService; // dependency injection

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() { // 회원 등록
        return "members/createMemberForm"; // get방식은 그냥 저녀석 템플릿에서 찾아서 뿌려줌
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) { // 회원 등록해서 post 되면 이녀석 호출
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member); // 회원가입 우리가 전에 만들었던거
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
