package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.entity.Score;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("spring")
@RequiredArgsConstructor
public class ScoreSpringRepository implements ScoreRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Score> findAll() {
        String sql = "SELECT * FROM tbl_score";
        return jdbcTemplate.query(sql, new RowMapper<Score>() {
            @Override
            public Score mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Score(rs);
            }
        });
    }

    @Override
    public List<Score> findAll(String sort) {
        String sql = "SELECT * FROM tbl_score";
        switch (sort) {
            case "num":
                sql += " ORDER BY stu_num";
                break;
            case "name":
                sql += " ORDER BY stu_name";
                break;
            case "avg":
                sql += " ORDER BY average DESC";
                break;
        }

        return jdbcTemplate.query(sql, new RowMapper<Score>() {
            @Override
            public Score mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Score(rs);
            }
        });
    }


    @Override
    public boolean save(Score score) {
        String sql = "INSERT INTO tbl_score " +
                "(stu_name, kor, eng, math, total, average, grade) " +
                " VALUES (?, ?, ?, ?, ?, ?, ?)";
        int save = jdbcTemplate.update(sql,
                score.getStuName(), score.getKor(), score.getEng(), score.getMath()
                , score.getTotal(), score.getAverage(), String.valueOf(score.getGrade()));
        return save == 1;
    }


    @Override
    public boolean deleteByStuNum(int stuNum) {
        String sql = "DELETE FROM tbl_score WHERE stu_num = ?";
        int result = jdbcTemplate.update(sql, stuNum);
        return result == 1;
    }

    @Override // SELECT 할때만 new RowMapper()을 쓴다.
    public Score findByStuNum(int stuNum) {
        String sql = "SELECT * FROM tbl_score WHERE stu_num=?";
        return jdbcTemplate.queryForObject(sql, new RowMapper<Score>() {
            @Override
            public Score mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Score(rs);
            }
        }, stuNum);
    }


    public boolean modifyScore(int kor, int eng, int math, int stuNum) {
        String sql = "UPDATE tbl_score SET kor = ?, eng = ?, math = ? WHERE stu_num=?";
        boolean result = jdbcTemplate.update(sql, kor, eng, math, stuNum) == 1;
        return result;

    }
}