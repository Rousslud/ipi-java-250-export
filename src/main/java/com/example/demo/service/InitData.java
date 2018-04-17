package com.example.demo.service;

import com.example.demo.entity.Article;
import com.example.demo.entity.Client;
import com.example.demo.entity.Facture;
import com.example.demo.entity.LigneFacture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;

@Service
@Transactional
public class InitData {

    @Autowired
    private EntityManager em;

    public void insertTestData() throws ParseException {

        Client client1 = newClient("PETRI;LLO", "Alexandre",  "rue du Pommier", "69000", newDate("01-01-2001"), "alexandre.petrillo@email.fr", "0701020304", "Lyon");
        em.persist(client1);

        Client client2 = newClient("Durand", "Je;an",  "avenue Jacques Chirac", "75009", newDate("06-05-2008"), "jean.durand@email.fr", "0711223344", "Paris");
        em.persist(client2);
        
        Client client3 = newClient("Dupond", "Emile",  "13 ; Boulevard des belges", "76000", newDate("15-08-2010"), "dupondetdupond@email.fr", "0799887766", "Rouen");
        em.persist(client3);
        
        Client client4 = newClient("Dugenou", "Francis",  "Impasse du pré", "38;150", newDate("27-02-2016"), "dugenouetco@email.fr", "0712349876", "Village Pommé");
        em.persist(client4);
        
        Client client5 = newClient("Dupate", "Albert",  "rue du four à pain", "12870", newDate("06-10-2012"), "dubon;pate@email.fr", "0744993355", "Village gourmand");
        em.persist(client5);

        Article article1 = newArticle("Carte mère ASROCK 2345", 79.90, "Informatique", "Composants");
        em.persist(article1);

        Article article2 = newArticle("Vidéoprojecteur ArtlII", 179.90, "Image et Son","Vidéoprojecteurs");
        em.persist(article2);
        
        Article article3 = newArticle("Xbox One", 399.90, "Jeux","consoles");
        em.persist(article3);
        
        Article article4 = newArticle("Sony Alpha 6000 + P16-50mm", 459.90, "Photo","Hybrides");
        em.persist(article4);
        
        Article article5 = newArticle("Sony SEL55210", 259.90, "Photo","Objectifs");
        em.persist(article5);
        
        Article article6 = newArticle("Config ACER M1576", 1059.90, "Informatique","Ordinateur de bureau");
        em.persist(article6);
        
        Article article7 = newArticle("Mémoire RAM Corsair 4GB DDR5", 79.90, "Informatique","Composants");
        em.persist(article7);
        
        Article article8 = newArticle("Processeur core I7 M550", 459.90, "Informatique","Composants");
        em.persist(article8);
        
        Article article9 = newArticle("Disque dur SSD Corsair LE 512Go", 259.90, "Informatique","Stockage");
        em.persist(article9);
        
        Article article10 = newArticle("Tour Thermaltake Kandalf", 179.90, "Informatique","Composants");
        em.persist(article10);
        
        Article article11 = newArticle("Carte Graphique Nvidia Quatro QX540 2Go DDR5", 759.90, "Informatique","Stockage");
        em.persist(article11);
        
        Article article12 = newArticle("Carte Son Soundblaster 8.1 Pro", 109.90, "Informatique","Stockage");
        em.persist(article12);
        
        Article article13 = newArticle("Ecran LG 24 pouces UltraHD Led ", 159.90, "Informatique","Ecrans");
        em.persist(article13);
        
        Article article14 = newArticle("Souris Logitech MX518 Gamer", 39.90, "Informatique","Périphériques");
        em.persist(article14);
        
        Article article15 = newArticle("Clavier Logitech Pro P785 Gamer", 49.90, "Informatique","Périphériques");
        em.persist(article15);

        {
            Facture facture = newFacture(client1, newDate("05-04-2018"));
            em.persist(facture);
            em.persist(newLigneFacture(article1, facture, 1));
            em.persist(newLigneFacture(article7, facture, 4));
            em.persist(newLigneFacture(article8, facture, 1));
            em.persist(newLigneFacture(article9, facture, 2));
            em.persist(newLigneFacture(article10, facture, 1));
            em.persist(newLigneFacture(article11, facture, 1));
            em.persist(newLigneFacture(article12, facture, 1));
            em.persist(newLigneFacture(article13, facture, 2));
            em.persist(newLigneFacture(article14, facture, 1));
            em.persist(newLigneFacture(article15, facture, 1));
        }
        {
            Facture facture = newFacture(client1, newDate("05-04-2018"));
            em.persist(newLigneFacture(article2, facture, 1));
            em.persist(newLigneFacture(article3, facture, 1));
            em.persist(facture);
        }
        {
            Facture facture = newFacture(client2, newDate("01-04-2018"));
            em.persist(facture);
            em.persist(newLigneFacture(article2, facture, 1));
        }
        
        {
            Facture facture = newFacture(client3, newDate("25-03-2017"));
            em.persist(facture);
            em.persist(newLigneFacture(article3, facture, 1));
        }
        
        {
            Facture facture = newFacture(client4, newDate("15-03-2017"));
            em.persist(facture);
            em.persist(newLigneFacture(article4, facture, 1));
            em.persist(newLigneFacture(article5, facture, 1));
        }
        
        {
            Facture facture = newFacture(client5, newDate("05-02-2017"));
            em.persist(facture);
            em.persist(newLigneFacture(article6, facture, 1));
        }
        
        {
            Facture facture = newFacture(client5, newDate("18-01-2017"));
            em.persist(facture);
            em.persist(newLigneFacture(article4, facture, 1));
            em.persist(newLigneFacture(article5, facture, 1));
        }
    }

    private Client newClient(String nom, String prenom, String adresse, String cP, Date dateinscription, String email, String telephone, String ville) {
        Client client = new Client();
        client.setNom(nom);
        client.setPrenom(prenom);
        client.setAdresse(adresse);
        client.setCP(cP);
        client.setDateinscription(dateinscription);
        client.setEmail(email);
        client.setTelephone(telephone);
        client.setVille(ville);
        return client;
    }

    private Article newArticle(String libelle, double prix, String mainCategorie, String subCategorie) {
        Article article = new Article();
        article.setLibelle(libelle);
        article.setPrix(prix);
        article.setMainCategorie(mainCategorie);
        article.setSubCategorie(subCategorie);
        return article;
    }

    private Facture newFacture(Client client, Date datecommande) {
        Facture facture = new Facture();
        facture.setClient(client);
        facture.setDatecommande(datecommande);
        return facture;
    }

    private LigneFacture newLigneFacture(Article article, Facture facture, int quantite) {
        LigneFacture ligneFacture1 = new LigneFacture();
        ligneFacture1.setFacture(facture);
        ligneFacture1.setArticle(article);
        ligneFacture1.setQuantite(quantite);
        return ligneFacture1;
    }
    
    private Date newDate(String dateInString) throws ParseException {
    	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    	Date date = sdf.parse(dateInString);
    	return date;
    }
}
