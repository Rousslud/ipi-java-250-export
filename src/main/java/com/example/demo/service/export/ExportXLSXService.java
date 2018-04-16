package com.example.demo.service.export;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
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
		//String excelFileName = "clientsXLSX";
		XSSFRow rowTitle = sheet.createRow(0);
		XSSFCell cellTitle = rowTitle.createCell(0);
		cellTitle.setCellValue("Nom");
		cellTitle = rowTitle.createCell(1);
		cellTitle.setCellValue("Prénom");
		int index=1;
		//iterating r number of rows
		for(ClientDTO item : clients){
			XSSFRow row = sheet.createRow(index);
			for (int index2=0;index2 < 2; index2++ ){
				XSSFCell cell = row.createCell(index2);
				if (index2==0) {
					cell.setCellValue(item.getNom());
				}
				else {
					cell.setCellValue(item.getPrenom());
				}
			}
			index++;
		}
		//FileOutputStream fileOut = new FileOutputStream(excelFileName);
		workbook.write(os);
		workbook.close();
	}

}
