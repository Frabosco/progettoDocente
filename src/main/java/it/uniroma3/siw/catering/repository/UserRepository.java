package it.uniroma3.siw.catering.repository;


import it.uniroma3.siw.catering.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}