package org.gfg.springbootdemo.dbDemo.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import org.gfg.springbootdemo.CustomeException;
import org.gfg.springbootdemo.dbDemo.model.MyPerson;
import org.gfg.springbootdemo.dbDemo.repository.PersonJpaRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyPersonService {

    @Autowired
    private PersonJpaRepository personJpaRepository;

    @PersistenceContext  // which is saving the data into DB
    private EntityManager  em;
@Transactional
    public MyPerson saveMyPerson(MyPerson person) throws CustomeException {
        // it gives u way to create an object of this class using builder method
       // MyPerson person=MyPerson.builder().lName("Mamidi").fName("Jasmitha").age(31).build();
//      person.setId(7);
       MyPerson person1= personJpaRepository.save(person);
    personJpaRepository.getById(person.getId());// gettingId

    Session session=(Session)em.getDelegate();
    MyPerson myPerson2=(MyPerson)session.get(MyPerson.class,person.getId());
       if(person1.getAge()==31){
           throw new CustomeException("not allowed");
       }
       return person1;


    }
}
