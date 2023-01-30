package com.claudiocarige.portfoliclaudio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claudiocarige.portfoliclaudio.domian.Employee;
import com.claudiocarige.portfoliclaudio.domian.dtos.EmployeeDTO;
import com.claudiocarige.portfoliclaudio.repositories.EmployeeRepository;
import com.claudiocarige.portfoliclaudio.services.exceptions.ObjectNotFoundException;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository repository;
	
	public Employee findById(Integer id) {
		Optional<Employee> employee = repository.findById(id);
		return employee.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado Id: " + id));
	}
	
	public List<Employee> findAll(){
		return repository.findAll();
	}

	public Employee create(EmployeeDTO objDTO) {
		objDTO.setId(null);
		Employee newEmployee = new Employee(objDTO);
		return repository.save(newEmployee);
	}
	

}
