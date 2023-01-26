package com.claudiocarige.portfoliclaudio;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.claudiocarige.portfoliclaudio.domain.enums.Priority;
import com.claudiocarige.portfoliclaudio.domain.enums.Profile;
import com.claudiocarige.portfoliclaudio.domain.enums.Status;
import com.claudiocarige.portfoliclaudio.domian.Client;
import com.claudiocarige.portfoliclaudio.domian.Employee;
import com.claudiocarige.portfoliclaudio.domian.ServicesPet;
import com.claudiocarige.portfoliclaudio.repositories.ClientRepository;
import com.claudiocarige.portfoliclaudio.repositories.EmployeeRepository;
import com.claudiocarige.portfoliclaudio.repositories.ServicesPetRepository;

@SpringBootApplication
public class PortfolioClaudioApplication implements CommandLineRunner{
	
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private ServicesPetRepository petRpository;

	public static void main(String[] args) {
		SpringApplication.run(PortfolioClaudioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Employee employee1 = new Employee(null,"Claudio Carigé", "57256176880", "ccarige@mail.com", "123456");
		Employee employee2 = new Employee(null,"Fulano de tal 01", "41024767760", "fulano02@mail.com", "123456");
		Employee employee3 = new Employee(null,"Fulano de tal 02", "76778547880", "fulano03@mail.com", "123456");
		Employee employee4 = new Employee(null,"Fulano de tal 03", "66721243490", "fulano04@mail.com", "123456");
		Employee employee5 = new Employee(null,"Fulano de tal 04", "30453251900", "fulano05@mail.com", "123456");
		employee1.addProfile(Profile.ADMIN);
		Client client1 = new Client(null, "Coisinho 01", "47683364347", "coisinho01@mail.com", "123456");
		Client client2 = new Client(null, "Coisinho 02", "72612538631", "coisinho02@mail.com", "123456");
		Client client3 = new Client(null, "Coisinho 03", "88635033035", "coisinho03@mail.com", "123456");
		Client client4 = new Client(null, "Coisinho 04", "45481236605", "coisinho04@mail.com", "123456");
		Client client5 = new Client(null, "Coisinho 05", "31812557000", "coisinho05@mail.com", "123456");
		Client client6 = new Client(null, "Coisinho 06", "55961264890", "coisinho06@mail.com", "123456");
		
		ServicesPet ServPet1 = new ServicesPet(null, Priority.MEDIA, Status.ANDAMENTO, "Tosa", "Tosa a seco", client1, employee1);
		ServicesPet ServPet2 = new ServicesPet(null, Priority.ALTA, Status.ABERTO, "Banho", "Banho simples", client2, employee3);
		ServicesPet ServPet3 = new ServicesPet(null, Priority.BAIXA, Status.ABERTO, "Banho", "Banho completo", client3, employee2);
		
		clientRepository.saveAll(Arrays.asList(client1,client2,client3,client4,client5,client6));
		employeeRepository.saveAll(Arrays.asList(employee1, employee2, employee3, employee4, employee5));
		petRpository.saveAll(Arrays.asList(ServPet1, ServPet2, ServPet3));
		
	}

}
