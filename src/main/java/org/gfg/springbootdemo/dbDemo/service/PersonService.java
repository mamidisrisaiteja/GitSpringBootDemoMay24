package org.gfg.springbootdemo.dbDemo.service;

import org.gfg.springbootdemo.dbDemo.Person;
import org.gfg.springbootdemo.dbDemo.repository.IPerson;
import org.gfg.springbootdemo.dbDemo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class PersonService {
    @Autowired
    @Qualifier("namedParameterJdbcDemo")
    //private PersonRepository personRepository;
    private IPerson iperson;

    public List<Person> getPersonData() throws SQLException {

        return iperson.getAllPersons();

    }

    public Person addPerson(Person person) throws SQLException {

        //BUSINESS LOGIC
       // return personRepository.addPerson(person);
        return iperson.addPersonWithPreparedStatement(person);
    }

    public int updatePerson(String name,int id){
        return iperson.updatePerson(id,name);
    }
}
