package com.spring.mvc.chap05.controller;

import com.spring.mvc.chap05.dto.LoginRequestDTO;
import com.spring.mvc.chap05.dto.SignUpRequestDTO;
import com.spring.mvc.chap05.service.LoginResult;
import com.spring.mvc.chap05.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import static com.spring.mvc.chap05.service.LoginResult.*;

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
    public String signUp(SignUpRequestDTO dto) {
    log.info("/members/sign-up POST ! - {}", dto);
        boolean flag = memberService.join(dto);

        return "redirect:/board/list";
    }

    // 아이디, 이메일 중복검사
    // 비동기 요청 처리 (REST 방식)
    @GetMapping("/check")
    @ResponseBody
    public ResponseEntity<?> check(String type, String keyword) {
        log.info("/members/check?type={}&keyword={} ASYNC GET!", type, keyword);

        boolean flag = memberService.checkSignUpValue(type, keyword);

        return ResponseEntity.ok().body(flag);
    }

    // 로그인 화면(양식) 요청
    @GetMapping("/sign-in")
    public String signIn() {
        log.info("/members/sign-in GET! - forwarding to jsp");
        return "/members/sign-in";
    }
    // 로그인 검증 요청
    @PostMapping("/sign-in")
    // 리다이렉션시 2번째 응답에 데이터를 보내기 위함
    public String signIn(LoginRequestDTO dto, RedirectAttributes ra,
                         HttpServletResponse response) {
//        Servlet은 HttpServletResponse객체에 Content Type, 응답코드, 응답 메시지등을 담아서 전송함
        log.info("/members/sign-in POST ! - {}", dto);

        LoginResult result = memberService.authenticate(dto);

        // 로그인 성공시
        if (result == SUCCESS) {
            ra.addFlashAttribute("flag", true);
            
            // 쿠키 만들기
            Cookie loginCookie = new Cookie("login", "지워니");
            // 쿠키 셋팅
            loginCookie.setPath("/");
            loginCookie.setMaxAge(60 * 60 * 24); // 하루 지나면 자동 삭제됨
            // 쿠키는 무조건 시간 설정을 해야한다.

            // 쿠키를 응답시에 실어서 클라이언트에게 전송
            response.addCookie(loginCookie);

            return "redirect:/";
        }
        
        // 1회용으로 쓰고 버릴 데이터
        ra.addFlashAttribute("msg", result);
        // jsp로 실패를 보내준다.

        // 로그인 실패시
        // redirect는 요청 URL 적는다 /sign-in
        // 요청 2번
        // 포워딩으로 하면 경로를 가는거다
        // 요청 1번
        return "redirect:/members/sign-in";
    }
}
