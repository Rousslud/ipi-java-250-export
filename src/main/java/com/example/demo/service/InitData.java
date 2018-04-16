package com.example.demo.service;

import com.example.demo.entity.Article;
import com.example.demo.entity.Client;
import com.example.demo.entity.Facture;
import com.example.demo.entity.LigneFacture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@Transactional
public class InitData {

    @Autowired
    private EntityManager em;

    public void insertTestData() {
        Client client1 = newClient("PETRI;LLO", "Alexandre");
        em.persist(client1);

        Client client2 = newClient("Dupont", "Jérome");
        em.persist(client2);

        Article article1 = newArticle("Carte mère ASROCK 2345", 79.90);
        em.persist(article1);

        Article article2 = newArticle("Clé USB", 9.90);
        em.persist(article2);

        {
            Facture facture = newFacture(client1);
            em.persist(facture);
            em.persist(newLigneFacture(article1, facture, 1));
        }
        {
            Facture facture = newFacture(client1);
            em.persist(facture);
            em.persist(newLigneFacture(article1, facture, 1));
            em.persist(newLigneFacture(article2, facture, 5));
        }
    }

    private Client newClient(String nom, String prenom) {
        Client client = new Client();
        client.setNom(nom);
        client.setPrenom(prenom);
        return client;
    }

    private Article newArticle(String libelle, double prix) {
        Article article = new Article();
        article.setLibelle(libelle);
        article.setPrix(prix);
        return article;
    }

    private Facture newFacture(Client client) {
        Facture facture = new Facture();
        facture.setClient(client);
        return facture;
    }

    private LigneFacture newLigneFacture(Article article, Facture facture, int quantite) {
        LigneFacture ligneFacture1 = new LigneFacture();
        ligneFacture1.setFacture(facture);
        ligneFacture1.setArticle(article);
        ligneFacture1.setQuantite(quantite);
        return ligneFacture1;
    }
}
