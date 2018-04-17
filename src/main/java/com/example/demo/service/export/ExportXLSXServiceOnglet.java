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

@Service
public class ExportXLSXServiceOnglet {
	public void export(OutputStream os, ClientDTO client) throws IOException{
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("clients");
      
		XSSFRow row = sheet.createRow(0);
		XSSFCell cellTitle = row.createCell(1);
		cellTitle.setCellValue("Numéro de client : ");
		//FileOutputStream fileOut = new FileOutputStream(excelFileName);
		workbook.write(os);
		workbook.close();
	}
}
