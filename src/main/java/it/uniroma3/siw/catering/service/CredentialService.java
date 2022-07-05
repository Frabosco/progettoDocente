package it.uniroma3.siw.catering.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.catering.model.Credential;
import it.uniroma3.siw.catering.repository.CredentialRepository;

@Service
public class CredentialService {
	
    @Autowired
    protected PasswordEncoder passwordEncoder;

	@Autowired
	protected CredentialRepository credentialRepository;

	@Transactional
	public Credential getCredential(Long id) {
		Optional<Credential> result = this.credentialRepository.findById(id);
		return result.orElse(null);
	}

	@Transactional
	public Credential getCredential(String username) {
		Optional<Credential> result = this.credentialRepository.findByUsername(username);
		return result.orElse(null);
	}
		
    @Transactional
    public Credential saveCredential(Credential credential) {
        credential.setRole(Credential.DEFAULT_ROLE);
        credential.setPassword(this.passwordEncoder.encode(credential.getPassword()));
        return this.credentialRepository.save(credential);
    }
}
