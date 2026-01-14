package com.example.jpa.controller;

import com.example.jpa.domain.Member;
import com.example.jpa.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/list")
    public String getList(@PageableDefault(size=10, sort = "memberId",
        direction = Sort.Direction.ASC)
                            Pageable pageable, Model model) {
        Page<Member> memberPage = memberService.findByAll(pageable);
        model.addAttribute("memberPage", memberPage);
        return "members/list";
    }

    @GetMapping("/new")
    public void getNew(){
    }

    @PostMapping("/new")
    public String postNew(Member member){
        memberService.insert(member);
        return "redirect:/members/list";
    }

    @GetMapping("/edit/{id}")
    public String getEdit(@PathVariable("id") int memberId, Model model) {
        Member member = memberService.findById(memberId);
        model.addAttribute("member",member);
        return "members/edit";
    }

    @PostMapping("/edit/{id}")
    public String postEdit(@ModelAttribute Member member){
        memberService.update(member);
        return "redirect:/members/list";
    }

    @GetMapping("/delete/{memberId}")
    public String getDelete(@PathVariable int MemberId){
        memberService.delete(MemberId);
        return "redirect:/members/list";
    }
}
