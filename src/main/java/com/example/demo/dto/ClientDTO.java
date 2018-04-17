package com.example.demo.dto;



import java.util.Date;

import javax.persistence.Column;

/**
 * Created by Kayne on 09/04/2018.
 */
public class ClientDTO {

    private Long id;

    private String prenom;

    private String nom;
    
    private String adresse;
    
    private String CP;
    
    private String ville;
    
    private String email;
    
    private String telephone;
    
    private Date dateinscription;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCP() {
		return CP;
	}

	public void setCP(String cP) {
		CP = cP;
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Date getDateinscription() {
		return dateinscription;
	}

	public void setDateinscription(Date date) {
		this.dateinscription = date;
	}
    
    
}
