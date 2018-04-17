package com.example.demo.service.export;

import com.example.demo.dto.ClientDTO;
import com.example.demo.dto.FactureDTO;
import com.example.demo.entity.Facture;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service
public class ExportPDFService {

    public void export(OutputStream os, List<ClientDTO> clients) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, os);
        document.open();

        document.add(new Paragraph("Hello"));
        document.close();
    }
}
