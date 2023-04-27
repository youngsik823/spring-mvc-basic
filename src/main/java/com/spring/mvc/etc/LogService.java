package com.spring.mvc.etc;

import org.springframework.stereotype.Component;

@Component
public class LogService {

    /*
        - 로그: 발생시간, 로그 레벨, 파일저장
        - 로그 라이브러리: logback, log4;, slf4j

        - 로그 레벨
        1. trace : 애플리케이션의 실행흐름 세부정보, 디버깅 목적
        2. debug : 변수값, 파라미터값 내부 정보 출력, 디버깅 목적
        3. info : 운영환경에서 일반적인 작동 정보들, 시스템 상태, 진행중인 작업 정보
        4. warn : 잠재적인 문제상황을 경고
        5. error
        6. fatal
     */

    public void showLog() {
        System.out.println("hello log!!");
    }
}
