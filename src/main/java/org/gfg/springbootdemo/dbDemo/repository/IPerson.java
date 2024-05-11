package org.gfg.springbootdemo.dbDemo.repository;

import org.gfg.springbootdemo.dbDemo.Person;

import java.util.List;

public interface IPerson {
    int updatePerson(int id,String name);
    Person addPersonWithPreparedStatement(Person person);
    Person addPerson(Person person);

    List<Person> getAllPersons();

}
