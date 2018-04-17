package com.example.demo.dto;


/**
 * Created by Kayne on 09/04/2018.
 */
public class LigneFactureDTO {

    private String designation;
    private Integer quantite;
    private Double prixUnitaire;
    private String mainCategorie;
    private String subCategorie;

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(Double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

	public String getMainCategorie() {
		return mainCategorie;
	}

	public void setMainCategorie(String mainCategorie) {
		this.mainCategorie = mainCategorie;
	}

	public String getSubCategorie() {
		return subCategorie;
	}

	public void setSubCategorie(String subCategorie) {
		this.subCategorie = subCategorie;
	}


    
    
}
