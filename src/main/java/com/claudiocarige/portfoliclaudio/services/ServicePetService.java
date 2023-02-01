package com.claudiocarige.portfoliclaudio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claudiocarige.portfoliclaudio.domain.Client;
import com.claudiocarige.portfoliclaudio.domain.Employee;
import com.claudiocarige.portfoliclaudio.domain.ServicesPet;
import com.claudiocarige.portfoliclaudio.domain.dtos.ServicePetDTO;
import com.claudiocarige.portfoliclaudio.domain.enums.Priority;
import com.claudiocarige.portfoliclaudio.domain.enums.Status;
import com.claudiocarige.portfoliclaudio.repositories.ServicesPetRepository;
import com.claudiocarige.portfoliclaudio.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class ServicePetService {

	@Autowired
	private ServicesPetRepository repository;

	@Autowired
	private ClientService clientService;

	@Autowired
	private EmployeeService employeeService;

	public ServicesPet findById(Integer id) {
		Optional<ServicesPet> servicePet = repository.findById(id);
		return servicePet.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado ID: " + id));
	}

	public List<ServicesPet> finAll() {
		return repository.findAll();
	}

	public ServicesPet create(@Valid ServicePetDTO objDTO) {
		return repository.save(newServicesPet(objDTO));
	}

	public ServicesPet newServicesPet(ServicePetDTO objDTO) {
		Employee employee = employeeService.findById(objDTO.getEmployee());
		Client client = clientService.findById(objDTO.getClient());

		ServicesPet servicesPet = new ServicesPet();
		if (objDTO.getId() != null) {
			servicesPet.setId(objDTO.getId());
		}
		servicesPet.setClient(client);
		servicesPet.setEmployee(employee);
		servicesPet.setPriority(Priority.toEnum(objDTO.getPriority()));
		servicesPet.setStatus(Status.toEnum(objDTO.getStatus()));
		servicesPet.setTitle(objDTO.getTitle());
		servicesPet.setComments(objDTO.getComments());
		return servicesPet;
	}

}
