package com.example.demo.controller;

import com.example.demo.dto.ClientDTO;
import com.example.demo.dto.FactureDTO;
import com.example.demo.service.ClientService;
import com.example.demo.service.FactureService;
import com.example.demo.service.export.ExportCSVService;
import com.example.demo.service.export.ExportPDFITextService;
import com.example.demo.service.export.ExportPDFService;
import com.example.demo.service.export.ExportXLSXService;
import com.example.demo.service.export.ExportXLSXServiceOnglet;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Controlleur pour réaliser les exports
 */
@Controller
@RequestMapping("/")
public class ExportController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ExportCSVService exportCSVService;

    @Autowired
    private FactureService factureService;

    @Autowired
    private ExportXLSXService exportXLSXService;
    
    @Autowired
    private ExportXLSXServiceOnglet exportXLSXServiceOnglet;

    @Autowired
    private ExportPDFITextService exportPDFITextService;
    
    @Autowired
    private ExportPDFService exportPDFService;

    @GetMapping("/clients/csv")
    public void clientsCSV(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"clients.csv\"");
        List<ClientDTO> clients = clientService.findAllClients();
        exportCSVService.export(response.getWriter(), clients);
    }

    @GetMapping("/clients/xlsx")
    public void clientsXLS(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/xlsx");
        response.setHeader("Content-Disposition", "attachment; filename=\"clients.xlsx\"");
        List<ClientDTO> clients = clientService.findAllClients();
        exportXLSXService.export(response.getOutputStream(), clients);
    }

    @GetMapping("/clients/{id}/factures/xlsx")
    public void facturesDUnClient(@PathVariable("id") Long clientId, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	response.setContentType("text/xlsx");
        response.setHeader("Content-Disposition", "attachment; filename=\"factures client " + clientId + ".xlsx\"");
        ClientDTO client = clientService.findById(clientId);
        exportXLSXServiceOnglet.export(response.getOutputStream(), client);
    }

    @GetMapping("/clients/pdf")
    public void clientsPDF(HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException{
        response.setContentType("text/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"clients.pdf\"");
        List<ClientDTO> clients = clientService.findAllClients();
        exportPDFService.export(response.getOutputStream(), clients);
    }

    @GetMapping("/factures/{id}/pdf")
    public void facturePDF(@PathVariable("id") Long factureId, HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"facture " + factureId + ".pdf\"");
        FactureDTO facture = factureService.findById(factureId);
        exportPDFITextService.export(response.getOutputStream(), facture);
    }

}
