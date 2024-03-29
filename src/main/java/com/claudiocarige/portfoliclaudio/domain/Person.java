package com.claudiocarige.portfoliclaudio.domain;

import com.claudiocarige.portfoliclaudio.domain.enums.Profile;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Person implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	protected Integer id;
	protected String name; 
	
	@Column(unique = true)
	@EqualsAndHashCode.Include
	protected String cpf;
	
	@Column(unique = true)
	protected String email;
	protected String password;

	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PROFILE")
	protected Set<Integer> profile = new HashSet<>();
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate createDate = LocalDate.now();

	public Person(Integer id, String name, String cpf, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.email = email;
		this.password = password;
	}

	public Set<Profile> getProfile() {
		return profile.stream().map(Profile::toEnum).collect(Collectors.toSet());
	}

	public void addProfile(Profile prof) {
		this.profile.add(prof.getCodigo());
	}

}
