package com.example.demo.entity;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

public class LigneFacture {
	
	@OneToOne
	private Article article;
	
	private Integer quantite;
	
	@ManyToOne
	private Facture facture;
	

}
