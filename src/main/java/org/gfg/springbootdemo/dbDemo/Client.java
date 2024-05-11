package org.gfg.springbootdemo.dbDemo;

public class Client {
    public static void main(String[] args) {
        Person person=new Person("Jayansh",01);
        System.out.println(person.toString());
        System.out.println(person.getId());
    }
}
