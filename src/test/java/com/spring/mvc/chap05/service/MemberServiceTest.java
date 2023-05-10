package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.SignUpRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    @Autowired
    MemberService memberService

   @Test
   @DisplayName("SignUpDTO를 전달하면 회원가입에 성공해야 한다.")
    void joinTest() {
       //given
        SignUpRequestDTO dto = new SignUpRequestDTO();
       dto.setAccount("kukukaka");
       dto.setPassword("lalalala12343");
       dto.setName("루피");
       dto.setEmail("aaa@ddd.com");
       //when
       memberService.join(dto);
   }
}