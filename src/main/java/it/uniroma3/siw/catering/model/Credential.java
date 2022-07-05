package it.uniroma3.siw.catering.model;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "CRED_SEQUENCE_GENERATOR", allocationSize = 1, sequenceName = "CRED_SEQ")
@Table(name = "credentials")
public class Credential {
	
	public static final String DEFAULT_ROLE = "DEFAULT";
	public static final String ADMIN_ROLE = "ADMIN";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CRED_SEQUENCE_GENERATOR")
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String role;
	
	@OneToOne(cascade = CascadeType.ALL)
	private User user;

	public Credential() {}
	
	public Credential(String username, String password, String role, User user) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.user = user;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) { this.username = username; }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}