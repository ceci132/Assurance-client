/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assuranceclient.Print;

import com.assuranceclient.customers.CustomersWindow;
import com.assuranceclient.dto.Customer;
import com.assuranceclient.dto.SADoc;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.BaseColor; 
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Цветан Иванов
 */
public class Polica {

    private String token = "";
    private String jsonStringDocuments = "";
    private ArrayList<SADoc> data = new ArrayList();
    private SADoc currentItem = new SADoc();
    private Rectangle page;
    private Document document;
    public static final String FONT = "fonts/HelveticaRegular.ttf";
    Font font = FontFactory.getFont(FONT, 16, BaseColor.BLACK);

    public Polica(String token, SADoc currentItem) {
        this.token = token;
        this.currentItem = currentItem;

        initCustomers();
        
        try {
            try {
                makeDoc();
            } catch (DocumentException ex) {
                Logger.getLogger(Polica.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Polica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void makeDoc() throws FileNotFoundException, DocumentException {
        document = new Document();
        String fileName = "Polica_" + currentItem.nomer + "_" + getCustomerNmaeFromIds(currentItem.ids_client);
        PdfWriter.getInstance(document, new FileOutputStream(fileName + ".pdf"));

        document.open();

        makeHead();
        
        document.close();
        
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File("E:\\AssuranceClient/" + fileName + ".pdf");
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                // no application registered for PDFs
            }
        }
    }
    
    private void makeHead() throws DocumentException {
        PdfPTable tableH;

        PdfPCell cell;

        tableH = new PdfPTable(1);
        tableH.setWidthPercentage(100);

        cell = new PdfPCell(new Paragraph(new Chunk("Квитанция за платена полица №" + currentItem.nomer, font)));
        cell.setBorder(0);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        tableH.addCell(cell);

        cell = new PdfPCell(new Paragraph(new Chunk("Валидна от:" + currentItem.date_from + " до:" + currentItem.date_to, font)));
        cell.setBorder(0);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        tableH.addCell(cell);

        document.add(tableH);

    }
    
    private ArrayList<Customer> dataCustomers = new ArrayList();
    private String jsonStringCustomers = "";

    private String getCustomerNmaeFromIds(int ids) {
        for (int i = 0; i < dataCustomers.size(); i++) {
            if (dataCustomers.get(i).ids == ids) {
                return dataCustomers.get(i).first_name + " " + dataCustomers.get(i).last_name;
            }
        }

        return "";
    }

    private void initCustomers() {

        try {
            jsonStringCustomers = fillCombo("http://localhost:8080/api/customers");
        } catch (Exception ex) {
            Logger.getLogger(CustomersWindow.class.getName()).log(Level.SEVERE, null, ex);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            dataCustomers = objectMapper.readValue(jsonStringCustomers, new TypeReference<ArrayList<Customer>>() {
            });
        } catch (JsonProcessingException ex) {
            Logger.getLogger(CustomersWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String fillCombo(String uri) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .header("Authorization", "Bearer " + token)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}
