package com.claudiocarige.portfoliclaudio.services;

import com.claudiocarige.portfoliclaudio.domain.Employee;
import com.claudiocarige.portfoliclaudio.domain.Person;
import com.claudiocarige.portfoliclaudio.domain.dtos.EmployeeDTO;
import com.claudiocarige.portfoliclaudio.repositories.EmployeeRepository;
import com.claudiocarige.portfoliclaudio.repositories.PersonRepository;
import com.claudiocarige.portfoliclaudio.services.exceptions.DataIntegrityViolationException;
import com.claudiocarige.portfoliclaudio.services.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

	private final EmployeeRepository repository;
	private final PersonRepository personRepository;
	private final BCryptPasswordEncoder encoder;

	public Employee findById(Integer id) {
		Optional<Employee> employee = repository.findById(id);
		return employee.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. Id: " + id));
	}

	public List<Employee> findAll() {
		return repository.findAll();
	}

	public Employee create(EmployeeDTO objDTO) {
		objDTO.setId(null);
		objDTO.setPassword(encoder.encode(objDTO.getPassword()));
		validadorDeCpfEEmail(objDTO);
		return repository.save(new Employee(objDTO));
	}

	public Employee update(Integer id, @Valid EmployeeDTO objDTO) {
		objDTO.setId(id);
		Employee oldObj = findById(id);
		if(!objDTO.getPassword().equals(oldObj.getPassword())){
			objDTO.setPassword(encoder.encode(objDTO.getPassword()));}
		validadorDeCpfEEmail(objDTO);
		return repository.save(new Employee(objDTO));
	}
   
	public void delete(Integer id) {
		Employee obj = findById(id);
		if (obj.getServicePet().size() > 0){
			throw new DataIntegrityViolationException(
					"Este Funcionário possui ordem de serviço e não pode ser deletado!");
		} else {
			repository.deleteById(id);
		}
	}

	private void validadorDeCpfEEmail(EmployeeDTO objDTO) {
		Optional<Person> obj = personRepository.findByCpf(objDTO.getCpf());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
		}
		obj = personRepository.findByEmail(objDTO.getEmail());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
		}
	}
} 
