package com.claudiocarige.portfoliclaudio.services;

import com.claudiocarige.portfoliclaudio.domain.ServicesPet;
import com.claudiocarige.portfoliclaudio.domain.dtos.ServicePetDTO;

import javax.validation.Valid;
import java.util.List;

public interface ServicePetService {
    ServicesPet findById(Integer id);
    List<ServicesPet> finAll();
    ServicesPet create(@Valid ServicePetDTO objDTO);
    ServicesPet update(Integer id, @Valid ServicePetDTO objDTO);
}
