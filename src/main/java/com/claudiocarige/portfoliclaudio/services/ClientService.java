package com.claudiocarige.portfoliclaudio.services;

import com.claudiocarige.portfoliclaudio.domain.Client;
import com.claudiocarige.portfoliclaudio.domain.Person;
import com.claudiocarige.portfoliclaudio.domain.dtos.ClientDTO;
import com.claudiocarige.portfoliclaudio.repositories.ClientRepository;
import com.claudiocarige.portfoliclaudio.repositories.PersonRepository;
import com.claudiocarige.portfoliclaudio.services.exceptions.DataIntegrityViolationException;
import com.claudiocarige.portfoliclaudio.services.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

	private final ClientRepository repository;

	private final PersonRepository personRepository;
	
	private final BCryptPasswordEncoder encoder;

	public Client findById(Integer id) {
		Optional<Client> employee = repository.findById(id);
		return employee.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado Id: " + id));
	}

	public List<Client> findAll() {
		return repository.findAll();
	}

	public Client create(ClientDTO objDTO) {
		objDTO.setId(null);
		objDTO.setPassword(encoder.encode(objDTO.getPassword()));
		validadorDeCpfEEmail(objDTO);
		return repository.save(new Client(objDTO));
	}

	public Client update(Integer id, ClientDTO objDTO) {
		objDTO.setId(id);
		findById(id);
		validadorDeCpfEEmail(objDTO);
		return repository.save(new Client(objDTO));
	}

	public void delete(Integer id) {
		Client obj = findById(id);
		if (obj.getServicesPet().isEmpty()) {
			throw new DataIntegrityViolationException("Este Cliente possui ordem de serviço e não pode ser deletado!");
		} else {
			repository.deleteById(id);
		}
	}

	private void validadorDeCpfEEmail(ClientDTO objDTO) {
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
