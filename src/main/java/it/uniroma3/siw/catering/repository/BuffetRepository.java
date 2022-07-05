package it.uniroma3.siw.catering.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.model.Chef;

public interface BuffetRepository extends CrudRepository<Buffet, Long> {

	public boolean existsByNome(String nome);

	public List<Buffet> findAllByChef(Chef chef);

}
