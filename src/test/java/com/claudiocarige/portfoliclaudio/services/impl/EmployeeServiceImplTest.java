package com.claudiocarige.portfoliclaudio.services.impl;

import com.claudiocarige.portfoliclaudio.domain.Employee;
import com.claudiocarige.portfoliclaudio.domain.dtos.EmployeeDTO;
import com.claudiocarige.portfoliclaudio.domain.enums.Profile;
import com.claudiocarige.portfoliclaudio.repositories.EmployeeRepository;
import com.claudiocarige.portfoliclaudio.repositories.PersonRepository;
import com.claudiocarige.portfoliclaudio.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
class EmployeeServiceImplTest {

    public static final int ID = 1;
    public static final String NAME = "Marcos";
    public static final String CPF = "89496531504";
    public static final String EMAIL = "ccarige@gmail.com";
    public static final String PASSWORD = "123456";
    public static final LocalDate DATE = LocalDate.now();
    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado. Id: 1";
    public static final int INDEX = 0;
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
    void whenFindByIdThenReturnAEmployeeInstance() {
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
    void whenFindByIdThenReturnAObjectNotFoundException() {
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(ObjectNotFoundException.class, () -> employeeService.findById(ID));
        verify(employeeRepository, times(1)).findById(ID);
        try{
            employeeService.findById(ID);
        }catch (Exception ex){
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());
        }
    }
    @Test
    void whenFindAllThenRetunrAnListEmployee() {
        when(employeeRepository.findAll()).thenReturn(List.of(employee,
                new Employee(20, "Maria", "15915573720", "dantas@gmail.com", "123456")));
        List<Employee> response = employeeService.findAll();
        assertNotNull(response);
        assertEquals(2, response.size());
        assertEquals(Employee.class, response.get(INDEX).getClass());
        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getName());
        assertEquals(CPF, response.get(INDEX).getCpf());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
        assertEquals(PASSWORD, response.get(INDEX).getPassword());
        assertEquals(employee.getProfile(), response.get(INDEX).getProfile());
        assertEquals(DATE, response.get(INDEX).getCreateDate());
        assertTrue(employee.getProfile().containsAll(response.get(INDEX).getProfile()));
    }

    @Test
    void whenCreateThenReturnSuccesss() {
        when(encoder.encode(PASSWORD)).thenReturn("123456");
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        when(personRepository.findByCpf(CPF)).thenReturn(Optional.empty());
        when(personRepository.findByEmail(EMAIL)).thenReturn(Optional.empty());

        Employee response = employeeService.create(employeeDTO);
        assertEquals(employee, response);
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(CPF, response.getCpf());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
        assertEquals(LocalDate.now(), response.getCreateDate());
        assertTrue(response.getProfile().containsAll(Arrays.asList(Profile.EMPLOYEE, Profile.ADMIN)));
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