package com.example.demo.service.export;

import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.demo.dto.ClientDTO;
import com.itextpdf.text.pdf.codec.Base64.OutputStream;

public class ExportXLXSService {
	public void export(OutputStream os, List<ClientDTO> clients) throws IOException{
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("clients");
		
		workbook.write(os);
		workbook.close();
	}

}
