package com.claudiocarige.portfoliclaudio.services;

import com.claudiocarige.portfoliclaudio.domain.Client;
import com.claudiocarige.portfoliclaudio.domain.dtos.ClientDTO;

import java.util.List;

public interface ClientService {
    Client findById(Integer id);
    List<Client> findAll();
    Client create(ClientDTO objDTO);
    Client update(Integer id, ClientDTO objDTO);
    void delete(Integer id);
}
