package org.gfg.springbootdemo.dbDemo.repository;

import org.gfg.springbootdemo.dbDemo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
@Repository

public class NamedParameterJdbcDemo implements IPerson{

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public int updatePerson(int id, String name) {
        MapSqlParameterSource mapSqlParameterSource=new MapSqlParameterSource();
        mapSqlParameterSource.addValue("name",name);
        mapSqlParameterSource.addValue("id",id);
       return  namedParameterJdbcTemplate.update("insert into person (name,id) values (:name,:id)",mapSqlParameterSource);

    }

    @Override
    public Person addPersonWithPreparedStatement(Person person) {
        MapSqlParameterSource mapSqlParameterSource=new MapSqlParameterSource();
        mapSqlParameterSource.addValue("name",person.getName());
        mapSqlParameterSource.addValue("id",person.getId());
        namedParameterJdbcTemplate.update("insert into person (name,id) values (:name,:id)",mapSqlParameterSource);

//        namedParameterJdbcTemplate.execute("insert into person (name,id) VALUES (:name,:id", mapSqlParameterSource, new PreparedStatementCallback<Object>() {
//            @Override
//            public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
//                return ps.execute();
//            }
//        });
        return person;
    }

    @Override
    public Person addPerson(Person person) {
        return null;
    }

    @Override
    public List<Person> getAllPersons() {
        return null;
    }
}
