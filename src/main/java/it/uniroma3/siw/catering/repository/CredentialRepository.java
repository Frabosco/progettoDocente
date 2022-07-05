package it.uniroma3.siw.catering.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.catering.model.Credential;

public interface CredentialRepository extends CrudRepository<Credential, Long> {
	
	public Optional<Credential> findByUsername(String username);

}