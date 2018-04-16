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

        Client client  = new Client();
        client.setNom("PETRILLO");
        client.setPrenom("Alexandre");
        em.persist(client);
        
        Client client2  = new Client();
        client2.setNom("Villoud");
        client2.setPrenom("Pierre-Julien");
        em.persist(client2);

        Article article = new Article();
        article.setLibelle("Carte mère ASROCK 2345");
        article.setPrix(79.90);
        em.persist(article);
        
        Article article2 = new Article();
        article2.setLibelle("Ram Corsair 4Gb DDR3");
        article2.setPrix(159.90);
        em.persist(article2);
        
        Article article3 = new Article();
        article3.setLibelle("Disque dur SSD Corsair 512Go");
        article3.setPrix(259.90);
        em.persist(article3);

        Facture facture = new Facture();
        facture.setClient(client);
        em.persist(facture);

        LigneFacture ligneFacture1 = new LigneFacture();
        ligneFacture1.setFacture(facture);
        ligneFacture1.setArticle(article);
        ligneFacture1.setQuantite(1);
        em.persist(ligneFacture1);
        
        LigneFacture ligneFacture2 = new LigneFacture();
        ligneFacture2.setFacture(facture);
        ligneFacture2.setArticle(article2);
        ligneFacture2.setQuantite(4);
        em.persist(ligneFacture2);
        
        LigneFacture ligneFacture3 = new LigneFacture();
        ligneFacture3.setFacture(facture);
        ligneFacture3.setArticle(article3);
        ligneFacture3.setQuantite(1);
        em.persist(ligneFacture3);

    }
}