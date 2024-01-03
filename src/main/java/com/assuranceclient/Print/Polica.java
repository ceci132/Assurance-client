/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assuranceclient.Print;

import com.assuranceclient.dto.SADoc;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import java.awt.Rectangle;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

    public Polica(String token, javax.swing.JFrame parent, SADoc currentItem) {
        this.token = token;
        this.currentItem = currentItem;

        try {
            makeDoc();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Polica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void makeDoc() throws FileNotFoundException {
        String path = "E:\\AssuranceClient";
        
        PdfWriter pdfWriter = new PdfWriter(path);
        
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.addNewPage();
        
        document = new Document(pdfDocument);
        
        document.close();
        System.out.println("1");
    }
}
