package com.example.demo.entity;

import java.util.Date;

import javax.persistence.*;

/**
 * Created by Kayne on 09/04/2018.
 */
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String prenom;

    @Column
    private String nom;
    
    @Column
    private String adresse;
    
    @Column
    private String CP;
    
    @Column
    private String ville;
    
    @Column
    private String email;
    
    @Column
    private String telephone;
    
    @Column
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

	public void setDateinscription(Date dateinscription2) {
		this.dateinscription = dateinscription2;
	}
    
    
}
