package com.example.demo.service.export;

import com.example.demo.dto.FactureDTO;
import com.example.demo.dto.LigneFactureDTO;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImage;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

import org.junit.Test;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class ExportPDFITextService {

    public void export(OutputStream os, FactureDTO facture) throws IOException, DocumentException {  	
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, os);
        document.open();
        
        // Titre 
        Paragraph paragraph1 = new Paragraph("AmazIPI", RED_NORMAL);
        paragraph1.setIndentationLeft(400);
        document.add(paragraph1);
        
        Image img = Image.getInstance(IMG);
        /*img.setAbsolutePosition(400, 400);*/
        document.add(img);
        
        // Ligne de séparation
        DottedLineSeparator dottedline = new DottedLineSeparator();
        dottedline.setOffset(-5);
        dottedline.setGap(5f);
        document.add(dottedline);
        
        Font font = FontFactory.getFont(FontFactory.COURIER, 12, BaseColor.BLACK);
        Paragraph factureNb = new Paragraph("Facture n°" + facture.getId(), font);
        factureNb.setIndentationLeft(400);
        Paragraph nbClient = new Paragraph("Numéro Client : " + facture.getClient().getId(), font);
        Paragraph nomClient = new Paragraph("Nom : " + facture.getClient().getNom(), font);
        Chunk prenomClient = new Chunk("Prenom : " + facture.getClient().getPrenom(), font);
        Paragraph Adresse = new Paragraph("Adresse : " + facture.getClient().getAdresse() + ", " + facture.getClient().getCP() + " " + facture.getClient().getVille(), font);
        Paragraph Email = new Paragraph("Email : " + facture.getClient().getEmail(), font);
        Paragraph Telephone = new Paragraph("Téléphone : " + facture.getClient().getTelephone(), font);
        String dateInscription = new SimpleDateFormat("dd/MM/yyyy").format((facture.getClient().getDateinscription()));
        Paragraph Inscrit = new Paragraph("Inscrit depuis le : " + dateInscription, font);
        
        PdfContentByte canvas = writer.getDirectContent();
        Rectangle rect = new Rectangle(15, 15, 580, 820);
        rect.setBorder(Rectangle.BOX);
        rect.setBorderWidth(2);
        canvas.rectangle(rect);
        
        String dateCommande = new SimpleDateFormat("dd/MM/yyyy").format((facture.getDatecommande()));
        PdfPTable tablePDF = new PdfPTable(6);  
        tablePDF.setSpacingBefore(30f);
        tablePDF.setTotalWidth(document.getPageSize().getWidth() - 80);
        tablePDF.setLockedWidth(true);
        
        PdfPCell cellTitle = new PdfPCell(new Phrase("Date commande : " + dateCommande));
        cellTitle.setColspan(6); 
        tablePDF.addCell(cellTitle);
        PdfPCell cell = new PdfPCell(new Phrase("Catégorie"));
        tablePDF.addCell(cell);
        cell.setPhrase(new Phrase("Sous-catégorie"));
        tablePDF.addCell(cell);
        cell.setPhrase(new Phrase("Désignation"));
        tablePDF.addCell(cell);
        cell.setPhrase(new Phrase("Prix unitaire (€)"));
        tablePDF.addCell(cell);
        cell.setPhrase(new Phrase("Quantité"));
        tablePDF.addCell(cell);
        cell.setPhrase(new Phrase("Total (€)"));
        tablePDF.addCell(cell);
        
        double totalFacture = 0.0;
        
        for(LigneFactureDTO item : facture.getLigneFactures()){
        	tablePDF.addCell(item.getMainCategorie());
        	tablePDF.addCell(item.getSubCategorie());
        	tablePDF.addCell(item.getDesignation());
        	tablePDF.addCell(item.getPrixUnitaire().toString());
        	tablePDF.addCell(item.getQuantite().toString());
        	double totalinter=item.getQuantite()*item.getPrixUnitaire();
        	tablePDF.addCell(Double.toString(totalinter));
        	totalFacture = totalFacture + item.getQuantite()*item.getPrixUnitaire();
        }
        PdfPCell cellTotal = new PdfPCell(new Phrase("Total intermédiaire : "));
        cellTotal.setColspan(5); 
        tablePDF.addCell(cellTotal);
        tablePDF.addCell(Double.toString(round(totalFacture,2)));
        
        Date today = Calendar.getInstance().getTime();
        Long anciennete = ExportCSVService.getDateDiff(facture.getClient().getDateinscription(),today, TimeUnit.DAYS);
        anciennete = anciennete / 365;
        
        double remise=0.0;
        if (anciennete <=5) {
        	remise = 1.0;
        }
        else if (anciennete >5 && anciennete <=10) {
        	remise = 2.0;
        }
        else {
        	remise = 3.0;
        }
        
        cellTotal.setPhrase(new Phrase("Remise d'ancienneté : " + anciennete + "ans ==> " + remise + "%"));
        tablePDF.addCell(cellTotal);
        Double remiseCalcul = remise/100 * totalFacture;
        tablePDF.addCell(Double.toString(round(remiseCalcul,2)));
        
        cellTotal.setPhrase(new Phrase("Prix final : "));
        tablePDF.addCell(cellTotal);
        double realTotal=totalFacture-remiseCalcul;
        tablePDF.addCell(Double.toString(round(realTotal,2)));
        
        document.add(factureNb);
        document.add(nbClient);
        document.add(nomClient);
        document.add(prenomClient);
        document.add(Adresse);
        document.add(Email);
        document.add(Telephone);
        document.add(Inscrit);
        document.add(tablePDF);
        
        if (anciennete >5) {
            Paragraph thanks = new Paragraph("AmazIPI vous remercie pour votre commande et votre fidélité!", font);
            thanks.setIndentationLeft(200);
            document.add(thanks);
        }
        else {
        	 Paragraph thanks = new Paragraph("AmazIPI vous remercie pour votre commande!", font);
             thanks.setIndentationLeft(200);
             document.add(thanks);
        } 
        
        document.close();
    }
    
    public static final Font RED_NORMAL = new Font(FontFamily.HELVETICA, 16, Font.NORMAL, BaseColor.RED);
    
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
    
    public static final String IMG = "src/main/resources/images/amazIPI.jpg";
} 