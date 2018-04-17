package com.example.demo.service.export;

import com.example.demo.dto.FactureDTO;
import com.example.demo.dto.LigneFactureDTO;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.junit.Test;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class ExportPDFITextService {

    @Test
    public void export(OutputStream os, FactureDTO facture) throws IOException, DocumentException {  	
        Document document = new Document();
        PdfWriter.getInstance(document, os);
        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Chunk titleFacture = new Chunk("RueDuCommerce.com");
        Paragraph factureNb = new Paragraph("Facture n°" + facture.getId(), font);
        Paragraph nomClient = new Paragraph("Nom : " + facture.getClient().getNom(), font);
        Chunk prenomClient = new Chunk("Prenom : " + facture.getClient().getPrenom(), font);
        
        
        PdfContentByte canvas = writer.getDirectContent();
        Rectangle rect = new Rectangle(36, 36, 559, 806);
        rect.setBorder(Rectangle.BOX);
        rect.setBorderWidth(2);
        canvas.rectangle(rect);
        
        PdfPTable tablePDF = new PdfPTable(3);  
        
        double totalFacture = 0.0;
        
        for(LigneFactureDTO item : facture.getLigneFactures()){
        	tablePDF.addCell(item.getDesignation());
        	tablePDF.addCell(item.getQuantite().toString());
        	tablePDF.addCell(item.getPrixUnitaire().toString());
        	totalFacture = totalFacture + item.getQuantite()*item.getPrixUnitaire();
        }
        tablePDF.addCell("Total : ");
        tablePDF.addCell(" ");
        tablePDF.addCell(Double.toString(totalFacture));
        
        document.add(titleFacture);
        document.add(factureNb);
        document.add(nomClient);
        document.add(prenomClient);
        document.add(tablePDF);
        document.close();
    }
} 