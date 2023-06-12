package com.claudiocarige.portfoliclaudio.services.impl;

import com.claudiocarige.portfoliclaudio.domain.Person;
import com.claudiocarige.portfoliclaudio.repositories.PersonRepository;
import com.claudiocarige.portfoliclaudio.security.UserSS;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserDetailServiceImplTest {

    public static final String EMAIL = "ccarige@mail.com";
    public static final String PASSWORD = "123456";
    @InjectMocks
    private UserDetailServiceImpl detailService;

    @Mock
    private PersonRepository personRepository;

    private Person user;

    @BeforeEach
    void setUp() {
        startModel();
    }

    @Test
    void loadUserByUsername(){
        when(personRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        UserDetails userDetails = detailService.loadUserByUsername(EMAIL);
        assertNotNull(userDetails);
        assertEquals(UserSS.class, userDetails.getClass());
        assertEquals(EMAIL, userDetails.getUsername());
        assertEquals(PASSWORD, userDetails.getPassword());
    }

    public void startModel(){
        user = Mockito.mock(Person.class);
        when(user.getName()).thenReturn("claudio carigé");
        when(user.getEmail()).thenReturn(EMAIL);
        when(user.getPassword()).thenReturn(PASSWORD);
    }
}