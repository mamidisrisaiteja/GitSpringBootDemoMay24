package org.gfg.springbootdemo.dbDemo.repository;

import org.gfg.springbootdemo.dbDemo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class SpringJdbcDemo implements IPerson{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public int updatePerson(int id, String name) {
        return 0;
    }

    @Override
    public Person addPersonWithPreparedStatement(Person person) {

        jdbcTemplate.execute("insert into person (name,id) values (?,?)", new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1,person.getName());
                ps.setInt(2,person.getId());
                return ps.execute();
            }
        });
        return person;
    }

    @Override
    public Person addPerson(Person person) {
        return null;
    }

    @Override
    public List<Person> getAllPersons() {
// this takes care of creating connection
       return jdbcTemplate.query("select * from person", new RowMapper<Person>() {
           @Override
           public Person mapRow(ResultSet resultSet, int rowNum) throws SQLException {
              return new Person(resultSet.getString("name"), resultSet.getInt("id"));
           }
       });

    }
}
