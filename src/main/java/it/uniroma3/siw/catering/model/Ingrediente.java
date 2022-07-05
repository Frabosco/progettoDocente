package it.uniroma3.siw.catering.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@SequenceGenerator(name = "ING_SEQUENCE_GENERATOR", allocationSize = 1, sequenceName = "ING_SEQ")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"nome", "origine"}))
public class Ingrediente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ING_SEQUENCE_GENERATOR")
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String origine;
	
	@NotBlank
	private String descrizione;
	
	public Ingrediente() {}

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

	public String getOrigine() {
		return origine;
	}

	public void setOrigine(String origine) {
		this.origine = origine;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	@Override
	public int hashCode() {
		return this.nome.hashCode() + this.origine.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj.getClass() != Ingrediente.class)
			return false;
		Ingrediente that = (Ingrediente) obj;
		return this.nome.equals(that.getNome()) &&
			   this.origine.equals(that.getOrigine());
	}
}
