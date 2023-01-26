package com.claudiocarige.portfoliclaudio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.claudiocarige.portfoliclaudio.domian.Person;
import com.claudiocarige.portfoliclaudio.domian.ServicesPet;

public interface ServicesPetRepository extends JpaRepository<ServicesPet, Integer>{

}
