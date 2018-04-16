package com.example.demo.entity;

/*import java.sql.Date;*/
import java.util.HashSet;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

public class Facture {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/*private Date datecommande;
	private Double prixtotal;*/
	
	@OneToMany(mappedBy = "facture")
	private Set<LigneFacture> lignefacture = new HashSet();
	
	@ManyToOne
	private Client client;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<LigneFacture> getLignefacture() {
		return lignefacture;
	}

	public void setLignefacture(Set<LigneFacture> lignefacture) {
		this.lignefacture = lignefacture;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	

}
