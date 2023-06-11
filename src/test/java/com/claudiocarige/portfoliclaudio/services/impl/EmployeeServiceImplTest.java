package com.claudiocarige.portfoliclaudio.services.impl;

import com.claudiocarige.portfoliclaudio.domain.Employee;
import com.claudiocarige.portfoliclaudio.domain.dtos.EmployeeDTO;
import com.claudiocarige.portfoliclaudio.domain.enums.Profile;
import com.claudiocarige.portfoliclaudio.repositories.EmployeeRepository;
import com.claudiocarige.portfoliclaudio.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class EmployeeServiceImplTest {

    public static final int ID = 1;
    public static final String NAME = "Marcos";
    public static final String CPF = "89496531504";
    public static final String EMAIL = "ccarige@gmail.com";
    public static final String PASSWORD = "123456";
    public static final LocalDate DATE = LocalDate.now();
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private BCryptPasswordEncoder encoder;

    @Mock
    private PersonRepository personRepository;

    private Employee employee;

    private EmployeeDTO employeeDTO;


    private Optional<Employee> optionalEmployee;

    @BeforeEach
    void setUp() {
        startModel();
    }

    @Test
    void findById() {
        when(employeeRepository.findById(anyInt())).thenReturn(optionalEmployee);

        Employee response = employeeService.findById(ID);
        assertNotNull(response);
        assertEquals(Employee.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(CPF, response.getCpf());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
        assertEquals(employee.getProfile().size(), response.getProfile().size());
        assertTrue(employee.getProfile().containsAll(response.getProfile()));
        assertEquals(DATE, response.getCreateDate());
        verify(employeeRepository, times(1)).findById(ID);
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    public void startModel(){
        employee = new Employee(ID, NAME, CPF, EMAIL, PASSWORD);
        employee.addProfile(Profile.EMPLOYEE);
        employee.addProfile(Profile.ADMIN);
        employeeDTO = new EmployeeDTO(employee);
        optionalEmployee = Optional.of(employee);
    }
}