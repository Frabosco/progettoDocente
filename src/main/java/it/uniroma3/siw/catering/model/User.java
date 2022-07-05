package it.uniroma3.siw.catering.model;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "USER_SEQUENCE_GENERATOR", allocationSize = 1, sequenceName = "USER_SEQ")
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "USER_SEQUENCE_GENERATOR")
	private Long id;
	private String nome;
	private String cognome;
	
	public User() {}
	
	public User(String nome, String cognome) {
		this.nome = nome;
		this.cognome = cognome;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

}