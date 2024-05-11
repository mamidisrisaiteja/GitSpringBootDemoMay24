package org.gfg.springbootdemo.dbDemo.repository;

import org.gfg.springbootdemo.dbDemo.model.MyPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonJpaRepository extends JpaRepository<MyPerson,Integer> {
}
