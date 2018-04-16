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
	

}
