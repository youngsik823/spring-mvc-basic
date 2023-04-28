package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.entity.Score;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ScoreSpringRepositoryTest {
    @Autowired
    ScoreSpringRepository repository;

    @Test
    void findAllTest() {
        List<Score> scoreList = repository.findAll();
        for (Score s : scoreList) {
            System.out.println("s = " + s);
        }
    }

    @Test
    void findOneTest() {
        Score s = repository.findByStuNum(2);
        System.out.println("s = " + s);
        assertEquals(2, s.getStuNum());
    }

    @Test
    void deleteByStuNum() {
        int stu_num = 2;
        repository.deleteByStuNum(stu_num);
    }

    @Test
    void save() {
        Score s = new Score();
        s.setStuName("아이스아메리카노");
        s.setKor(44);
        s.setEng(55);
        s.setMath(66);
        repository.save(s);
    }

    @Test
    void modifyScore() {

    }


}