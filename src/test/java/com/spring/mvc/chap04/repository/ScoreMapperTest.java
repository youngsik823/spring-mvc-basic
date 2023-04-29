package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.entity.Grade;
import com.spring.mvc.chap04.entity.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ScoreMapperTest {

    @Autowired
    ScoreMapper mapper;

    @Test
    @DisplayName("저장")
    void saveTest() {
//        stu_name, kor, eng, math, total, average, grade
        Score s = Score.builder()
                .stuName("윤영식")
                .kor(100)
                .eng(100)
                .math(100)
                .total(300)
                .average(100)
                .grade(Grade.A)
                .build();
        boolean flag = mapper.save(s);

        assertTrue(flag);

    }


}