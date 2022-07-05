package it.uniroma3.siw.catering.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@SequenceGenerator(name = "BUFFET_SEQUENCE_GENERATOR", allocationSize = 1, sequenceName = "BUFFET_SEQ")
public class Buffet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "BUFFET_SEQUENCE_GENERATOR")
	private Long id;
	
	@Column(unique = true)
	@NotBlank
	private String nome;

	@NotBlank
	private String descrizione;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, 
			    fetch = FetchType.EAGER)
	private List<Piatto> piatti;	
	
	@ManyToOne
	private Chef chef;
	
	public Buffet() {
		this.piatti = new ArrayList<>();
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
	
	public Chef getChef() {
		return this.chef;
	}
	
	public void setChef(Chef chef) {
		this.chef = chef;
	}
	
	public List<Piatto> getPiatti() {
		return this.piatti;
	}
	
	public void setPiatti(List<Piatto> piatti) {
		this.piatti = piatti;
	}
	
	public void addPiatto(Piatto piatto) {
		this.piatti.add(piatto);
	}
	
	public void removePiatto(Piatto piatto) {
		this.piatti.remove(piatto);
	}

	@Override
	public int hashCode() {
		return this.nome.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj.getClass() != Buffet.class)
			return false;
		Buffet that = (Buffet) obj;
		return this.nome.equals(that.getNome());
	}
	
}
