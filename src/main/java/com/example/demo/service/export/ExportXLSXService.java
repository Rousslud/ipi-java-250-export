package com.example.demo.service.export;


import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ClientDTO;

@Service
public class ExportXLSXService {
	public void export(OutputStream os, List<ClientDTO> clients) throws IOException{
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("clients");
		sheet.setColumnWidth(0,500);
		sheet.setColumnWidth(11,500);
		for (int i=1; i<11;i++) {
			sheet.setColumnWidth(i,5000);
		}
		sheet.setColumnWidth(4,10000);
		sheet.setColumnWidth(7,10000);
		sheet.setDefaultRowHeight((short) 500);
		sheet.isPrintGridlines();

	    Font font = workbook.createFont();
        font.setColor(IndexedColors.RED.getIndex());
 
        CellStyle styleBorderLeft = workbook.createCellStyle();
        styleBorderLeft.setBorderBottom(BorderStyle.THICK);
        styleBorderLeft.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        styleBorderLeft.setBorderLeft(BorderStyle.THICK);
        styleBorderLeft.setLeftBorderColor(IndexedColors.GREEN.getIndex());
        styleBorderLeft.setAlignment(HorizontalAlignment.CENTER);
        styleBorderLeft.setVerticalAlignment(VerticalAlignment.CENTER);
        styleBorderLeft.setFont(font);  

        
        CellStyle styleBorderMiddle = workbook.createCellStyle();
        styleBorderMiddle.setBorderBottom(BorderStyle.THICK);
        styleBorderMiddle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        styleBorderMiddle.setAlignment(HorizontalAlignment.CENTER);
        styleBorderMiddle.setVerticalAlignment(VerticalAlignment.CENTER);
        styleBorderMiddle.setFont(font);  


        CellStyle styleBorderRight = workbook.createCellStyle();
        styleBorderRight.setBorderBottom(BorderStyle.THICK);
        styleBorderRight.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        styleBorderRight.setBorderRight(BorderStyle.THICK);
        styleBorderRight.setRightBorderColor(IndexedColors.GREEN.getIndex());
        styleBorderRight.setAlignment(HorizontalAlignment.CENTER);
        styleBorderRight.setVerticalAlignment(VerticalAlignment.CENTER);
        styleBorderRight.setFont(font); 

        
        CellStyle styleBorder = workbook.createCellStyle();
        styleBorder.setBorderBottom(BorderStyle.DASH_DOT);
        styleBorder.setBorderTop(BorderStyle.DASH_DOT);
        styleBorder.setBorderRight(BorderStyle.DASH_DOT);
        styleBorder.setBorderLeft(BorderStyle.DASH_DOT);
        styleBorder.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        styleBorder.setTopBorderColor(IndexedColors.BLACK.getIndex());
        styleBorder.setRightBorderColor(IndexedColors.BLACK.getIndex());
        styleBorder.setLeftBorderColor(IndexedColors.BLACK.getIndex());
               
        XSSFRow rowTitle = sheet.createRow(0);
		XSSFCell cellTitle = rowTitle.createCell(1);
		cellTitle.setCellValue("Numéro de client : ");
		cellTitle.setCellStyle(styleBorderLeft);
		cellTitle = rowTitle.createCell(2);
		cellTitle.setCellStyle(styleBorderMiddle);
		cellTitle.setCellValue("Nom : ");
		cellTitle = rowTitle.createCell(3);
		cellTitle.setCellStyle(styleBorderMiddle);
		cellTitle.setCellValue("Prénom : ");
		cellTitle = rowTitle.createCell(4);
		cellTitle.setCellStyle(styleBorderMiddle);
		cellTitle.setCellValue("Adresse : ");
		cellTitle = rowTitle.createCell(5);
		cellTitle.setCellStyle(styleBorderMiddle);
		cellTitle.setCellValue("Code postal : ");
		cellTitle = rowTitle.createCell(6);
		cellTitle.setCellStyle(styleBorderMiddle);
		cellTitle.setCellValue("Ville : ");
		cellTitle.setCellStyle(styleBorderMiddle);
		cellTitle = rowTitle.createCell(7);
		cellTitle.setCellStyle(styleBorderMiddle);
		cellTitle.setCellValue("Email : ");
		cellTitle = rowTitle.createCell(8);
		cellTitle.setCellStyle(styleBorderMiddle);
		cellTitle.setCellValue("Téléphone : ");
		cellTitle = rowTitle.createCell(9);
		cellTitle.setCellStyle(styleBorderMiddle);
		cellTitle.setCellValue("Date d'inscription : ");
		cellTitle = rowTitle.createCell(10);
		cellTitle.setCellStyle(styleBorderMiddle);
		cellTitle.setCellValue("Ancienneté (an) : ");
		cellTitle.setCellStyle(styleBorderRight);
		int indexRow=2;
		//iterating r number of rows
		for(ClientDTO item : clients){
			XSSFRow row = sheet.createRow(indexRow);
			for (int indexItem=1;indexItem < 11; indexItem++ ){
				XSSFCell cell = row.createCell(indexItem);
				cell.setCellStyle(styleBorder);
				if (indexItem==1) {
					cell.setCellValue(item.getId());
					
				}
				else if (indexItem==2) {
					cell.setCellValue(item.getNom());
				}
				else if (indexItem==3) {
					cell.setCellValue(item.getPrenom());
				}
				else if (indexItem==4){
					cell.setCellValue(item.getAdresse());
				}
				else if (indexItem==5){
					cell.setCellValue(item.getCP());
				}
				else if (indexItem==6){
					cell.setCellValue(item.getVille());
				}
				else if (indexItem==7){
					cell.setCellValue(item.getEmail());
				}
				else if (indexItem==8){
					cell.setCellValue(item.getTelephone());
				}
				else if (indexItem==9){
					String dateInscription = new SimpleDateFormat("dd/MM/yyyy").format((item.getDateinscription()));
					cell.setCellValue(dateInscription);
				}
				else if (indexItem==10){
					Date today = Calendar.getInstance().getTime();
					Long anciennete = ExportCSVService.getDateDiff(item.getDateinscription(),today, TimeUnit.DAYS);
		            anciennete = anciennete / 365;
					cell.setCellValue(anciennete);
				}
				else {
					cell.setCellValue("Something went wrong");
				}
			}
			indexRow++;
		}
		//FileOutputStream fileOut = new FileOutputStream(excelFileName);
		workbook.write(os);
		workbook.close();
	}
}
