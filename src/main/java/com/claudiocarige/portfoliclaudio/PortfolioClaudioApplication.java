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

	public static void main(String[] args) {
		SpringApplication.run(PortfolioClaudioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}

}
