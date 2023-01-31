package com.claudiocarige.portfoliclaudio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claudiocarige.portfoliclaudio.domian.Employee;
import com.claudiocarige.portfoliclaudio.domian.Person;
import com.claudiocarige.portfoliclaudio.domian.dtos.EmployeeDTO;
import com.claudiocarige.portfoliclaudio.repositories.EmployeeRepository;
import com.claudiocarige.portfoliclaudio.repositories.PersonRepository;
import com.claudiocarige.portfoliclaudio.services.exceptions.DataIntegrityViolationException;
import com.claudiocarige.portfoliclaudio.services.exceptions.ObjectNotFoundException;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository repository;
	
	@Autowired
	private PersonRepository personRepository;
	
	public Employee findById(Integer id) {
		Optional<Employee> employee = repository.findById(id);
		return employee.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado Id: " + id));
	}
	
	public List<Employee> findAll(){
		return repository.findAll();
	}

	public Employee create(EmployeeDTO objDTO) {
		objDTO.setId(null);
		validadorDeCpfEEmail(objDTO);
		Employee newEmployee = new Employee(objDTO);
		return repository.save(newEmployee);
	}
	
	public Employee update(Integer id, EmployeeDTO objDTO) {
		objDTO.setId(id);
		Employee oldObj = findById(id);
		validadorDeCpfEEmail(objDTO);
		oldObj = new Employee(objDTO);
		return repository.save(oldObj);
	}

	private void validadorDeCpfEEmail(EmployeeDTO objDTO) {
		Optional<Person> obj = personRepository.findByCpf(objDTO.getCpf());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
		}
		obj = personRepository.findByEmail(objDTO.getEmail());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
		}
	}
	

}
