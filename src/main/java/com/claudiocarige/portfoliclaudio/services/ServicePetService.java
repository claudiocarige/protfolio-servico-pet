package com.claudiocarige.portfoliclaudio.services;

import com.claudiocarige.portfoliclaudio.domain.ServicesPet;
import com.claudiocarige.portfoliclaudio.domain.dtos.ServicePetDTO;
import com.claudiocarige.portfoliclaudio.domain.enums.Priority;
import com.claudiocarige.portfoliclaudio.domain.enums.Status;
import com.claudiocarige.portfoliclaudio.repositories.ServicesPetRepository;
import com.claudiocarige.portfoliclaudio.services.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ServicePetService {

	private final ServicesPetRepository repository;
	private final ClientService clientService;
	private final EmployeeService employeeService;

	public ServicesPet findById(Integer id) {
		Optional<ServicesPet> servicePet = repository.findById(id);
		return servicePet.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado ID: " + id));
	}

	public List<ServicesPet> finAll() {
		return repository.findAll();
	}

	public ServicesPet create(@Valid ServicePetDTO objDTO) {
		return repository.save(toServicesPet(objDTO));
	}

	public ServicesPet update(Integer id, @Valid ServicePetDTO objDTO) {
		objDTO.setId(id);
		findById(id);
		return repository.save(toServicesPet(objDTO));
	}

	public ServicesPet toServicesPet(ServicePetDTO objDTO) {
		ServicesPet servicesPet = new ServicesPet();
		if (objDTO.getId() != null) {
			servicesPet.setId(objDTO.getId());
		}
		if (objDTO.getStatus().equals(2)) {
			servicesPet.setClosingDate(LocalDate.now());
		}
		servicesPet.setClient(clientService.findById(objDTO.getClient()));
		servicesPet.setEmployee(employeeService.findById(objDTO.getEmployee()));
		servicesPet.setPriority(Priority.toEnum(objDTO.getPriority()));
		servicesPet.setStatus(Status.toEnum(objDTO.getStatus()));
		servicesPet.setTitle(objDTO.getTitle());
		servicesPet.setComments(objDTO.getComments());
		return servicesPet;
	}
}
