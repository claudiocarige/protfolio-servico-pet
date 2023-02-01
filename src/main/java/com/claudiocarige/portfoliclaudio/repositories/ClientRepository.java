package com.claudiocarige.portfoliclaudio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.claudiocarige.portfoliclaudio.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{

}
