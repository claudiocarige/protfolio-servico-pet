package com.claudiocarige.portfoliclaudio.services.impl;

import com.claudiocarige.portfoliclaudio.domain.Client;
import com.claudiocarige.portfoliclaudio.domain.dtos.ClientDTO;
import com.claudiocarige.portfoliclaudio.domain.enums.Profile;
import com.claudiocarige.portfoliclaudio.repositories.ClientRepository;
import com.claudiocarige.portfoliclaudio.repositories.PersonRepository;
import com.claudiocarige.portfoliclaudio.services.exceptions.DataIntegrityViolationException;
import com.claudiocarige.portfoliclaudio.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ClientServiceImplTest {

    public static final int ID = 11;
    public static final String NAME = "Coisinho 01";
    public static final String CPF = "47683364347";
    public static final String EMAIL = "coisinho01@mail.com";
    public static final String PASSWORD = "123456";
    public static final LocalDate DATE = LocalDate.now();
    public static final String OBJECT_NOT_FOUND = "Objeto não encontrado Id: 11";
    public static final int INDEX = 0;
    @InjectMocks
    private ClientServiceImpl clientService;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private BCryptPasswordEncoder encoder;

    @Mock
    private PersonRepository personRepository;

    private Client client;

    private ClientDTO clientDTO;

    private Optional<Client> optionalClient;

    private List<Client> list;

    @BeforeEach
    void setUp() {
        StartModels();
    }

    @Test
    void WhenFindByIdThenReturnAnClientInstanc() {
        when(clientRepository.findById(anyInt())).thenReturn(optionalClient);

        Client response =clientService.findById(ID);
        assertNotNull(response);
        assertEquals(Client.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(CPF, response.getCpf());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
        assertEquals(client.getProfile().size(), response.getProfile().size());
        assertTrue(client.getProfile().containsAll(response.getProfile()));
        assertEquals(DATE, response.getCreateDate());
        verify(clientRepository, Mockito.times(1)).findById(ID);
    }

    @Test
    void whenFindByIdThenReturnAObjectNotFoundException(){
        when(clientRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(ObjectNotFoundException.class, () -> {
           clientService.findById(ID);
        });
        verify(clientRepository, Mockito.times(1)).findById(11);
        try {
            clientService.findById(ID);
        }catch (Exception ex){
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJECT_NOT_FOUND, ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAnListClient() {
        when(clientRepository.findAll()).thenReturn(List.of(client,
                new Client(20, "Maria","15915573720", "dantas@gmail.com", "123456")));
        List<Client> response =clientService.findAll();
        assertNotNull(response);
        assertEquals(2, response.size());
        assertEquals(Client.class, response.get(INDEX).getClass());
        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getName());
        assertEquals(CPF, response.get(INDEX).getCpf());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
        assertEquals(PASSWORD, response.get(INDEX).getPassword());
        assertEquals(client.getProfile().size(), response.get(INDEX).getProfile().size());
        assertTrue(client.getProfile().containsAll(response.get(INDEX).getProfile()));
        assertEquals(DATE, response.get(INDEX).getCreateDate());
    }

    @Test
    void whenCreateThenReturnSuccess() {

        client.getProfile().add(Profile.CLIENT);

        when(encoder.encode(PASSWORD)).thenReturn("123456");
        when(clientRepository.save(any(Client.class))).thenReturn(client);
        when(personRepository.findByCpf(CPF)).thenReturn(Optional.empty());
        when(personRepository.findByEmail(EMAIL)).thenReturn(Optional.empty());

        Client response = clientService.create(clientDTO);

        assertEquals(client, response);
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(CPF, response.getCpf());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
        assertEquals(LocalDate.now(), response.getCreateDate());
        assertTrue(response.getProfile().contains(Profile.CLIENT));
    }

    @Test
    void whenCreateWhitExistingCpfThenReturnAnDataIntegrityViolationException(){
        when(encoder.encode(PASSWORD)).thenReturn("123456");
        when(personRepository.findByCpf(CPF)).thenReturn(Optional.of(client));

        assertThrows(DataIntegrityViolationException.class, () -> {
            clientService.create(clientDTO);
        });
        try {
            optionalClient.get().setId(2);
            clientService.create(clientDTO);
        }catch (Exception ex){
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals("CPF já cadastrado no sistema!", ex.getMessage());
        }

    }

    @Test
    void whenCreateWhitExistingEmailThenReturnAnDataIntegrityViolationException(){
        when(encoder.encode(PASSWORD)).thenReturn("123456");
        when(personRepository.findByCpf(CPF)).thenReturn(Optional.empty());
        when(personRepository.findByCpf(EMAIL)).thenReturn(Optional.of(client));

        try {
            clientService.create(clientDTO);
        }catch (Exception ex){
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals("E-mail já cadastrado no sistema!", ex.getMessage());
        }

    }
    @Test
    void whenUpdateThenReturnSuccess() {

        when(encoder.encode(PASSWORD)).thenReturn("123456");
        when(clientRepository.save(any(Client.class))).thenReturn(client);
        when(personRepository.findByCpf(CPF)).thenReturn(Optional.empty());
        when(personRepository.findByEmail(EMAIL)).thenReturn(Optional.empty());

        Client response = clientService.create(clientDTO);

        assertEquals(client, response);
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(CPF, response.getCpf());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
        assertEquals(LocalDate.now(), response.getCreateDate());
        assertTrue(response.getProfile().containsAll(Arrays.asList(Profile.ADMIN, Profile.CLIENT)));
    }

    @Test
    void delete() {
    }

    public void StartModels(){
        client = new Client(ID, NAME, CPF, EMAIL, PASSWORD);
        client.addProfile(Profile.CLIENT);
        client.addProfile(Profile.ADMIN);
        clientDTO = new ClientDTO(client);
        optionalClient = Optional.of(client);
    }
}