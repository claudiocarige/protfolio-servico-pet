package com.claudiocarige.portfoliclaudio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claudiocarige.portfoliclaudio.domain.ServicesPet;
import com.claudiocarige.portfoliclaudio.repositories.ServicesPetRepository;
import com.claudiocarige.portfoliclaudio.services.exceptions.ObjectNotFoundException;

@Service
public class ServicePetService {
	
	@Autowired
	private ServicesPetRepository repository;
	
	public ServicesPet findById(Integer id) {
		Optional<ServicesPet> servicePet = repository.findById(id);
		return servicePet.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado ID: " + id));
	}

	public List<ServicesPet> finAll() {
		return repository.findAll();
	}

}
