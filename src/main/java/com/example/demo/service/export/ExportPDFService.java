package com.example.demo.service.export;

import com.example.demo.dto.ClientDTO;
import com.example.demo.dto.FactureDTO;
import com.example.demo.dto.LigneFactureDTO;
import com.example.demo.entity.Facture;
import com.ibm.icu.util.RangeValueIterator.Element;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfNumber;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPage;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.border.TitledBorder;

@Service
public class ExportPDFService {

    public void export(OutputStream os, List<ClientDTO> clients) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, os);
        document.open();
        
        // Rectangle 
        PdfContentByte canvas = writer.getDirectContent();
        Rectangle rect = new Rectangle(36, 36, 559, 806);
        rect.setBorder(Rectangle.BOX);
        rect.setBorderWidth(2);
        canvas.rectangle(rect);
        
        // Titre 
        document.addTitle("Liste des clients");
        Paragraph paragraph1 = new Paragraph("AmazIPI : extraction des données", RED_NORMAL);
        paragraph1.setIndentationLeft(20);
        document.add(paragraph1);
        
        // Ligne de séparation
        DottedLineSeparator dottedline = new DottedLineSeparator();
        dottedline.setOffset(-15);
        dottedline.setGap(5f);
        document.add(dottedline);
        
        // Creation de la table de données 
        PdfPTable tablePDF = new PdfPTable(10);  
        tablePDF.setSpacingBefore(30f);
        tablePDF.setTotalWidth(document.getPageSize().getWidth() - 80);
        
        PdfPCell cellTitle = new PdfPCell(new Phrase("Liste des clients"));
        cellTitle.setBackgroundColor(new BaseColor(140, 221, 8));
        cellTitle.setColspan(10);
        cellTitle.setPadding(5.0f);
        tablePDF.addCell(cellTitle);
        
        tablePDF.setLockedWidth(true);
       
        String[] text = new String[10];
        text[0] = "Numéro de client : ";
        text[1] = "Nom : ";
        text[2] = "Prénom : ";
        text[3] = "Adresse : ";
        text[4] = "Code postal : ";
        text[5] = "Ville : ";
        text[6] = "Email : ";
        text[7] = "Téléphone";
        text[8] = "Date d'inscription : ";
        text[9] = "Ancienneté (an): "; 
        for (int i=0;i<text.length;i++) {
        	PdfPCell cellSubTitle = new PdfPCell(new Phrase(text[i]));
        	cellSubTitle.setBackgroundColor(new BaseColor(180, 120, 45));
            cellSubTitle.setPadding(2.0f);
            tablePDF.addCell(cellSubTitle);
        }
       
        // Filling data
        for(ClientDTO item : clients){
        	tablePDF.addCell(item.getId().toString());
        	tablePDF.addCell(item.getNom());
        	tablePDF.addCell(item.getPrenom());
        	tablePDF.addCell(item.getAdresse());
        	tablePDF.addCell(item.getCP());
        	tablePDF.addCell(item.getVille());
        	tablePDF.addCell(item.getEmail());
        	tablePDF.addCell(item.getTelephone());
        	String dateInscription = new SimpleDateFormat("dd/MM/yyyy").format((item.getDateinscription()));
        	tablePDF.addCell(dateInscription);
        	Date today = Calendar.getInstance().getTime();
			Long anciennete = ExportCSVService.getDateDiff(item.getDateinscription(),today, TimeUnit.DAYS);
            anciennete = anciennete / 365;
            tablePDF.addCell(anciennete.toString());   	
        }
        
        
        PdfPCell cellTotal = new PdfPCell(new Phrase("Nombre de clients : "));
        cellTotal.setBackgroundColor(new BaseColor(180, 221, 222));
        cellTotal.setColspan(9);
        cellTotal.setPadding(5.0f);
        tablePDF.addCell(cellTotal);
        Integer total = clients.size();
        tablePDF.addCell(total.toString()); 
        
        document.add(tablePDF);
        document.close();
        
    }
    
    public static final Font RED_NORMAL = new Font(FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.RED);
    
}
