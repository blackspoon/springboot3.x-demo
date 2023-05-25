package com.clx.springboot30.application.persistent.template;

import com.clx.springboot30.application.model.People;
import com.clx.springboot30.constant.SqlBindKeys;
import com.clx.springboot30.utils.SqlFileReader;
import jakarta.annotation.Resource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class PeopleTemplate {

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String INSERT_PEOPLE = "InsertPeople.sql";
    private static final String UPDATE_PEOPLE = "UpdatePeople.sql";
    private static final String DELETE_PEOPLE = "DeletePeople.sql";
    private static final String SELECT_PEOPLE = "SelectPeople.sql";
    private static final String SELECT_ALL_PEOPLE = "SelectAllPeople.sql";

    /**
     * insert people
     */
    public int insertPeople(People people) {
        MapSqlParameterSource parameter = new MapSqlParameterSource();
        parameter.addValue(SqlBindKeys.PERSON_ID, people.getPersonId());
        parameter.addValue(SqlBindKeys.FIRST_NAME, people.getFirstName());
        parameter.addValue(SqlBindKeys.LAST_NAME, people.getLastName());
        int result = 0;
        result = namedParameterJdbcTemplate.update(SqlFileReader.getSql(INSERT_PEOPLE), parameter);
        return result;
    }

    /**
     * update pople
     */
    public int updatePeople(People people) {
        MapSqlParameterSource parameter = new MapSqlParameterSource();
        parameter.addValue(SqlBindKeys.PERSON_ID, people.getPersonId());
        parameter.addValue(SqlBindKeys.LAST_NAME, people.getLastName());

        int result = 0;
        result = namedParameterJdbcTemplate.update(SqlFileReader.getSql(UPDATE_PEOPLE), parameter);
        return result;
    }

    /**
     * delete people
     */
    public int deletePeople(int personId) {
        MapSqlParameterSource parameter = new MapSqlParameterSource();
        parameter.addValue(SqlBindKeys.PERSON_ID, personId);
        int result = 0;
        result = namedParameterJdbcTemplate.update(SqlFileReader.getSql(DELETE_PEOPLE), parameter);
        return result;
    }

    /**
     * get one People
     */
    public People getPeople(int personId) {
        MapSqlParameterSource parameter = new MapSqlParameterSource();
        parameter.addValue(SqlBindKeys.PERSON_ID, personId);
        RowMapper<People> rowMapper = new BeanPropertyRowMapper<>(People.class);
        try {
            People people = namedParameterJdbcTemplate.queryForObject(SqlFileReader.getSql(SELECT_PEOPLE), parameter, rowMapper);
            return people;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * get All People
     */
    public List<People> getAllPeople() {
        RowMapper<People> rowMapper = new BeanPropertyRowMapper<>(People.class);
        List<People> listPeople = namedParameterJdbcTemplate.query(SqlFileReader.getSql(SELECT_ALL_PEOPLE), new MapSqlParameterSource(), rowMapper);
        return listPeople;
    }

}
