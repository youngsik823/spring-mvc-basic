package com.spring.mvc.chap05.controller;

import com.spring.mvc.chap05.dto.SignUpRequestDTO;
import com.spring.mvc.chap05.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    
    // 회원 가입 요청
    // 회원가입 양식 요청
    @GetMapping("/sign-up")
    public String signUp() {
        log.info("/members/sign-up GET - forwarding to jsp");
        return "members/sign-up";
    }
    // 회원가입 처리 요청
    @PostMapping("/sign-up")
    public void signUp(SignUpRequestDTO dto) {
    log.info("/members/sign-up POST ! - {}", dto);
        boolean flag = memberService.join(dto);

    }

    // 아이디, 이메일 중복검사
    // 비동기 요청 처리 (REST 방식)
    @GetMapping("/check")
    @ResponseBody
    public ResponseEntity<?> check(String type, String keyword) {
        log.info("/members/check?type={}&keyword={} ASYNC GET!", type, keyword);
        return null;
    }
}
