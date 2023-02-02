package com.claudiocarige.portfoliclaudio.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.claudiocarige.portfoliclaudio.domain.enums.Profile;

public class UserSS implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	public UserSS(Integer id, String name, String password, Set<Profile> profile) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.authorities = profile.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao()))
				.collect(Collectors.toSet());
	}

	public Integer getId() {
		return id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
