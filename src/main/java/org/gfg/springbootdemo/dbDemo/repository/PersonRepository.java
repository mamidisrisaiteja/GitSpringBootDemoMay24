package org.gfg.springbootdemo.dbDemo.repository;

import org.gfg.springbootdemo.dbDemo.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepository implements IPerson{
    private Connection connection;
//    @Autowired
//    Connection connection; // create table is calling connection ,
//    this is a field injection and this will be null in the createTablePerson
    //as bean of connection will not be created before bean of the PersonRepo
    //so for this the solution is
    //constructor injection as below
    // in this case the bean of connection created before bean of PersonRepo..
    private static Logger logger= LoggerFactory.getLogger(PersonRepository.class);

@Override
    public int updatePerson(int id,String name){
        Boolean autocommit= null;
        try {
            autocommit=connection.getAutoCommit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int result =0;
        try{
connection.setAutoCommit(false);
           PreparedStatement ps= connection.prepareStatement("update person set name=? where id=?");
        ps.setString(1,name);
            ps.setInt(2,id);
            result=ps.executeUpdate();
            connection.commit();
        }
        catch (SQLException e){

            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
        finally {
            try {
                connection.setAutoCommit(autocommit);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }
    public PersonRepository(Connection connection) {
        this.connection=connection;
        createTablePerson();
    }
    @Override
    public Person addPersonWithPreparedStatement(Person person) {
        PreparedStatement preparedStatement = null;


        try {

            preparedStatement = connection.prepareStatement("insert into person (name,id) VALUES (?, ?);");
            boolean result = preparedStatement.execute("insert into person (name,id) values  ('" + person.getName() + "'," + person.getId() + ");");
            preparedStatement.setString(1,person.getName());
            preparedStatement.setInt(2,person.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return person;
    }
    @Override
    public Person addPerson(Person person)  {

        Statement statement=null;


        try {

            statement = connection.createStatement();
            boolean result=statement.execute("insert into person (name,id) values  ('"+person.getName() +"',"+person.getId()+");");
            logger.info("the result of the above query is {}"+result);
        }
    catch (SQLException e) {
        throw new RuntimeException(e);
    }
        finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return person;

    }
    @Override

    //By which we can connetc to DB
    public List<Person> getAllPersons()  {
        List<Person> list=new ArrayList<>();

Statement statement=null;


        try {
      statement= connection.createStatement();
      ResultSet resultSet=statement.executeQuery("select * from person");
while(resultSet.next()) {
    Person p = new Person(resultSet.getString("name"), resultSet.getInt("id"));
list.add(p);
}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }

    public void createTablePerson(){
        Statement statement=null;
        try {
             statement= connection.createStatement();
             statement.execute("create table if not exists person (name varchar(30),id int)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
