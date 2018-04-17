package com.example.demo.service.export;


import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.dialect.DB2390Dialect;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ClientDTO;
import com.example.demo.dto.FactureDTO;
import com.example.demo.dto.LigneFactureDTO;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

@Service
public class ExportXLSXServiceOnglet {
	public void export(OutputStream os, List<FactureDTO> facture, Long clientId) throws IOException{
		XSSFWorkbook workbook = new XSSFWorkbook();
		for(FactureDTO item : facture){
			if (item.getClient().getId()==clientId) {
				int rownb = 0; 
				XSSFSheet sheet = workbook.createSheet("Facture n°" + item.getId() );
				XSSFRow row = sheet.createRow(rownb);
				XSSFCell cellInfoClient = row.createCell(0);
				cellInfoClient.setCellValue("Numéro de client : ");
				cellInfoClient = row.createCell(1);
				cellInfoClient.setCellValue(item.getClient().getId());
				cellInfoClient = row.createCell(9);
				cellInfoClient.setCellValue("Facture n° : ");
				cellInfoClient = row.createCell(10);
				cellInfoClient.setCellValue(item.getId());
				
				row = sheet.createRow(rownb++);
				cellInfoClient = row.createCell(0);
				cellInfoClient.setCellValue("Nom : ");
				cellInfoClient = row.createCell(1);
				cellInfoClient.setCellValue(item.getClient().getNom());
				
				row = sheet.createRow(rownb++);
				cellInfoClient = row.createCell(0);
				cellInfoClient.setCellValue("Prénom : ");
				cellInfoClient = row.createCell(1);
				cellInfoClient.setCellValue(item.getClient().getPrenom());
				
				row = sheet.createRow(rownb++);
				cellInfoClient = row.createCell(0);
				cellInfoClient.setCellValue("Adresse : ");
				cellInfoClient = row.createCell(1);
				cellInfoClient.setCellValue(item.getClient().getAdresse() + ", " + item.getClient().getCP() + " " + item.getClient().getVille());
				
				row = sheet.createRow(rownb++);
				cellInfoClient = row.createCell(0);
				cellInfoClient.setCellValue("Email : ");
				cellInfoClient = row.createCell(1);
				cellInfoClient.setCellValue(item.getClient().getEmail());
				
				row = sheet.createRow(rownb++);
				cellInfoClient = row.createCell(0);
				cellInfoClient.setCellValue("Téléphone : ");
				cellInfoClient = row.createCell(1);
				cellInfoClient.setCellValue(item.getClient().getTelephone());
				
				row = sheet.createRow(rownb++);
				cellInfoClient = row.createCell(0);
				cellInfoClient.setCellValue("Inscrit depuis le : ");
				cellInfoClient = row.createCell(1);
				String dateInscription = new SimpleDateFormat("dd/MM/yyyy").format((item.getClient().getDateinscription()));
				cellInfoClient.setCellValue(dateInscription);
				
				rownb=rownb+2;
				row = sheet.createRow(rownb++);
				XSSFCell cellCommande = row.createCell(0);
				cellCommande.setCellValue("Date commande : ");
		        String dateCommande = new SimpleDateFormat("dd/MM/yyyy").format((item.getDatecommande()));     
		        cellCommande = row.createCell(1);
				cellCommande.setCellValue(dateCommande);
				
				row = sheet.createRow(rownb++);
				XSSFCell cellCommandeTop = row.createCell(0);
				cellCommandeTop.setCellValue("Catégorie : ");
				cellCommandeTop = row.createCell(1);
				cellCommandeTop.setCellValue("Sous-catégorie : ");
				cellCommandeTop = row.createCell(2);
				cellCommandeTop.setCellValue("Désignation : ");
				cellCommandeTop = row.createCell(3);
				cellCommandeTop.setCellValue("Prix unitaire (€) : ");
				cellCommandeTop = row.createCell(4);
				cellCommandeTop.setCellValue("Quantité : ");
				cellCommandeTop = row.createCell(5);
				cellCommandeTop.setCellValue("Total (€) : ");
		        
		        double totalFacture = 0.0;
		        
		        for(LigneFactureDTO itemLigne : item.getLigneFactures()){
		        	row = sheet.createRow(rownb++);
					cellCommande = row.createCell(0);
					cellCommande.setCellValue(itemLigne.getMainCategorie());
					cellCommande = row.createCell(1);
					cellCommande.setCellValue(itemLigne.getSubCategorie());
					cellCommande = row.createCell(2);
					cellCommande.setCellValue(itemLigne.getDesignation());
					cellCommande = row.createCell(3);
					cellCommande.setCellValue(itemLigne.getPrixUnitaire());
					cellCommande = row.createCell(4);
					cellCommande.setCellValue(itemLigne.getQuantite());
					double totalinter=itemLigne.getQuantite()*itemLigne.getPrixUnitaire();
					totalFacture = totalFacture + itemLigne.getQuantite()*itemLigne.getPrixUnitaire();
					cellCommande = row.createCell(5);
					cellCommande.setCellValue(totalinter);	        	
		        }
		        row = sheet.createRow(rownb++);
		        cellCommande = row.createCell(0);
		        cellCommande.setCellValue("Total intermédiaire : ");
		        cellCommande = row.createCell(5);
		        cellCommande.setCellValue(totalFacture);
		        
		        Date today = Calendar.getInstance().getTime();
		        Long anciennete = ExportCSVService.getDateDiff(item.getClient().getDateinscription(),today, TimeUnit.DAYS);
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
		        row = sheet.createRow(rownb++);
		        cellCommande = row.createCell(0);
		        cellCommande.setCellValue("Remise d'ancienneté : " + anciennete + "ans ==> " + remise + "%");
		        cellCommande = row.createCell(5);
		        Double remiseCalcul = remise/100 * totalFacture;
		        cellCommande.setCellValue(remiseCalcul);
	
		        row = sheet.createRow(rownb++);
		        cellCommande = row.createCell(0);
		        cellCommande.setCellValue("Prix final : ");
		        cellCommande = row.createCell(5);
		        double realTotal=totalFacture-remiseCalcul;
		        cellCommande.setCellValue(realTotal);
			}
		
		
		}
		workbook.write(os);
		workbook.close();
	}
}
