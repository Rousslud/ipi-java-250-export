package com.example.demo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class Client {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String nom; 
	@Column
	private String prenom;
	@Column
	private String codepostal;
	@Column
	private String adresse;
	@Column
	private String ville;
	@Column
	private String email;
	@Column
	private double telephone;
	@OneToMany(mappedBy = "client")
	private Set<Facture> factures = new HashSet();
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getCodepostal() {
		return codepostal;
	}
	public void setCodepostal(String codepostal) {
		this.codepostal = codepostal;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getTelephone() {
		return telephone;
	}
	public void setTelephone(double telephone) {
		this.telephone = telephone;
	}
	
	
	
	

}
