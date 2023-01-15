package com.study.garibi.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
    @Autowired
    MemberService memberService;

    @GetMapping("/")
    public String main() {
        return "main";
    }
    @GetMapping("/join")
    public String login(Member member) {
        memberService.joinUser(member);
        return "welcome";
    }
    @GetMapping("/call")
    public String chat() {
        return "call";
    }
}
