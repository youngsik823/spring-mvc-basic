package com.spring.mvc.mybatis;

import com.spring.mvc.jdbc.Person;

public interface PersonMapper {

    // CRUD에 대한 기능 명세
    // 사람 정보 저장
    boolean save(Person p);
    
    // 사람 정부 수정
    boolean change(Person p);

    // 사람 정보 삭제
    boolean remove(long id);

    // 전체 조회
    findAll();
}
