package com.claudiocarige.portfoliclaudio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.claudiocarige.portfoliclaudio.domian.Person;

public interface PersonRepository extends JpaRepository<Person, Integer>{

}
