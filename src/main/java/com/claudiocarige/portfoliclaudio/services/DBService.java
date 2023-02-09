package com.claudiocarige.portfoliclaudio.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.claudiocarige.portfoliclaudio.domain.Client;
import com.claudiocarige.portfoliclaudio.domain.Employee;
import com.claudiocarige.portfoliclaudio.domain.ServicesPet;
import com.claudiocarige.portfoliclaudio.domain.enums.Priority;
import com.claudiocarige.portfoliclaudio.domain.enums.Profile;
import com.claudiocarige.portfoliclaudio.domain.enums.Status;
import com.claudiocarige.portfoliclaudio.repositories.ClientRepository;
import com.claudiocarige.portfoliclaudio.repositories.EmployeeRepository;
import com.claudiocarige.portfoliclaudio.repositories.ServicesPetRepository;

@Service
public class DBService {
	
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private ServicesPetRepository petRpository;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public void startDB() {
		
		Employee employee1 = new Employee(null,"Claudio Carigé", "57256176880", "ccarige@mail.com", encoder.encode("123456"));
		Employee employee2 = new Employee(null,"Fulano de tal 01", "41024767760", "fulano01@mail.com", encoder.encode("123456"));
		Employee employee3 = new Employee(null,"Fulano de tal 02", "76778547880", "fulano02@mail.com", encoder.encode("123456"));
		Employee employee4 = new Employee(null,"Fulano de tal 03", "66721243490", "fulano03@mail.com", encoder.encode("123456"));
		Employee employee5 = new Employee(null,"Fulano de tal 04", "30453251900", "fulano04@mail.com", encoder.encode("123456"));
		Employee employee6 = new Employee(null,"Fulano de tal 05", "57714481412", "fulano05@mail.com", encoder.encode("123456"));
		Employee employee7 = new Employee(null,"Fulano de tal 06", "30405329105", "fulano06@mail.com", encoder.encode("123456"));
		Employee employee8 = new Employee(null,"Fulano de tal 07", "45642347306", "fulano07@mail.com", encoder.encode("123456"));
		Employee employee9 = new Employee(null,"Fulano de tal 08", "22931346101", "fulano08@mail.com", encoder.encode("123456"));
		Employee employee10 = new Employee(null,"Fulano Teste Crud", "50552726605", "testecrud@mail.com", encoder.encode("24681012"));
		employee1.addProfile(Profile.ADMIN);
		employee10.addProfile(Profile.ADMIN);
		Client client1 = new Client(null, "Coisinho 01", "47683364347", "coisinho01@mail.com", encoder.encode("123456"));
		Client client2 = new Client(null, "Coisinho 02", "72612538631", "coisinho02@mail.com", encoder.encode("123456"));
		Client client3 = new Client(null, "Coisinho 03", "88635033035", "coisinho03@mail.com", encoder.encode("123456"));
		Client client4 = new Client(null, "Coisinho 04", "45481236605", "coisinho04@mail.com", encoder.encode("123456"));
		Client client5 = new Client(null, "Coisinho 05", "31812557000", "coisinho05@mail.com", encoder.encode("123456"));
		Client client6 = new Client(null, "Coisinho 06", "55961264890", "coisinho06@mail.com", encoder.encode("123456"));
		
		ServicesPet ServPet1 = new ServicesPet(null, Priority.MEDIA, Status.ANDAMENTO, "Tosa", "Tosa a seco", client1, employee1);
		ServicesPet ServPet2 = new ServicesPet(null, Priority.ALTA, Status.ABERTO, "Banho", "Banho simples", client2, employee3);
		ServicesPet ServPet3 = new ServicesPet(null, Priority.BAIXA, Status.ABERTO, "Banho", "Banho completo", client3, employee2);
		ServicesPet ServPet4 = new ServicesPet(null, Priority.ALTA, Status.ANDAMENTO, "Consulta", "Consulta periódica", client4, employee4);
		ServicesPet ServPet5 = new ServicesPet(null, Priority.ALTA, Status.ABERTO, "Cirurgia", "Cirurgia de emergência", client6, employee7);
		
		employeeRepository.saveAll(Arrays.asList(employee1, employee2, employee3, employee4, employee5, employee6, employee7, employee8, employee9, employee10));
		clientRepository.saveAll(Arrays.asList(client1,client2,client3,client4,client5,client6));
		petRpository.saveAll(Arrays.asList(ServPet1, ServPet2, ServPet3, ServPet4, ServPet5));
	}
}
