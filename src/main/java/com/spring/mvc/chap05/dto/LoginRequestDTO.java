package com.spring.mvc.chap05.dto;

import lombok.*;

// 클라이언트 name과 같은 이름으로 해야한다.
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Setter
@Getter
//@NoArgsConstructor, @Setter 필수
public class LoginRequestDTO {

    private String account;
    private String password;
    private boolean autoLogin;
}
