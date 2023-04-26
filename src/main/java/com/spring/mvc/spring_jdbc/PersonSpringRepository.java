package com.spring.mvc.spring_jdbc;

import com.spring.mvc.jdbc.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PersonSpringRepository {

    // 스프링 JDBC 활용 - 데이터베이스 접속 설정 정보를
    // 설정파일을 통해 불러와서 사용합니다.
    private final JdbcTemplate jdbcTemplate;

    // 저장 기능
    public void savePerson(Person p) {
        String sql = "INSERT INTO person " +
                "(person_name, person_age) " +
                " VALUES (?, ?)";
        jdbcTemplate.update(sql,
                p.getPersonName(), p.getPersonAge());
    }

    // 삭제 기능
    public void removePerson(long id) {
        String sql = "DELETE FROM person WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    // 수정 기능 1
    public boolean modify(Person p) {
        String sql = "UPDATE person SET " +
                "person_name=?, person_age=? " +
                "WHERE id = ?";
        int result = jdbcTemplate.update(sql,
                p.getPersonName(),
                p.getPersonAge(),
                p.getId());
        return result == 1;
    }
    // 수정 기능 2
    public void updatePerson(long id, String name, String age) {
        String sql = "UPDATE person" +
                "SET id"
    }

}