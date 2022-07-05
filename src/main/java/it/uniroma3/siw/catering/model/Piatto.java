package it.uniroma3.siw.catering.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@SequenceGenerator(name = "PIATTO_SEQUENCE_GENERATOR", allocationSize = 1, sequenceName = "PIATTO_SEQ")
public class Piatto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "PIATTO_SEQUENCE_GENERATOR")
	private Long id;
	
	@Column(unique = true)
	@NotBlank
	private String nome;
	
	@NotBlank
	private String descrizione;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Ingrediente> ingredienti;
	
	public Piatto() {
		this.ingredienti = new ArrayList<>();
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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<Ingrediente> getIngredienti() {
		return ingredienti;
	}

	public void setIngredienti(List<Ingrediente> ingredienti) {
		this.ingredienti = ingredienti;
	}
	
	public void addIngrediente(Ingrediente ingrediente) {
		this.ingredienti.add(ingrediente);
	}

	public void removeIngrediente(Ingrediente ingrediente) {
		this.ingredienti.remove(ingrediente);
	}
	
	@Override
	public int hashCode() {
		return this.nome.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj.getClass() != Piatto.class)
			return false;
		Piatto that = (Piatto) obj;
		return this.nome.equals(that.getNome());
	}
}
