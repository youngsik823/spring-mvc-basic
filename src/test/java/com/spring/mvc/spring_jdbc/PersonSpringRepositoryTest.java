package com.spring.mvc.spring_jdbc;

import com.spring.mvc.jdbc.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonSpringRepositoryTest {
    @Autowired
    PersonSpringRepository repository;

    @Test
    void savePersonTest() {
        //given
        Person p = new Person();
        p.setPersonName("김스프링");
        p.setPersonAge(2);
        //when
        repository.savePerson(p);
    }

    @Test
    void removePersonTest() {
        // given
        long id = 4L;
        // when
        repository.removePerson(id);
    }
}