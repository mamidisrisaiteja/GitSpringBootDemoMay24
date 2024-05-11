package org.gfg.springbootdemo.dbDemo.controller;

import org.gfg.springbootdemo.CustomeException;
import org.gfg.springbootdemo.dbDemo.Person;
import org.gfg.springbootdemo.dbDemo.dto.PersonDto;
import org.gfg.springbootdemo.dbDemo.dto.PersonResponseDto;
import org.gfg.springbootdemo.dbDemo.model.MyPerson;
import org.gfg.springbootdemo.dbDemo.service.MyPersonService;
import org.gfg.springbootdemo.dbDemo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;
    @Autowired
    private MyPersonService myPersonService;

    @GetMapping("/getPersons")
    public ResponseEntity<List<Person>> getPersonData() throws SQLException {
// for this case we dont have any validations

        // we cannot write business logic in the control so we need to call
        // the service method

        //this is th HTTP request
        //http provides a class  to get returned
//busines logic will be kept here
        List<Person> list = personService.getPersonData();
        System.out.println("the list is "+list);
         ResponseEntity<List<Person>> responseEntity=new ResponseEntity<>(list,HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping("/addPerson")
    public ResponseEntity<Person> addPerson(@RequestBody Person person) throws SQLException {
        if(person.getId()==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
Person p=personService.addPerson(person);

        return new ResponseEntity<>(p,HttpStatus.OK);
    }

    @PutMapping("/updatePerson")
    public ResponseEntity<Integer> updatePerson(@RequestParam String name,@RequestParam Integer id){
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(personService.updatePerson(name,id),HttpStatus.OK);
    }

    @PostMapping("/addMyPerson")
    public MyPerson addMyPerson(@RequestBody MyPerson person) throws SQLException, CustomeException {

        return myPersonService.saveMyPerson(person);
    }
}
