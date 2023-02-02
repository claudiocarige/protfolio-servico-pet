package com.claudiocarige.portfoliclaudio.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.claudiocarige.portfoliclaudio.domain.Person;
import com.claudiocarige.portfoliclaudio.repositories.PersonRepository;
import com.claudiocarige.portfoliclaudio.security.UserSS;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
	
	@Autowired
	private PersonRepository repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Person> user = repository.findByEmail(email);
		if(user.isPresent()) {
			return new UserSS(user.get().getId(), user.get().getEmail(), 
								user.get().getPassword(), user.get().getProfile());
		}
		throw new UsernameNotFoundException(email);
	}

}
