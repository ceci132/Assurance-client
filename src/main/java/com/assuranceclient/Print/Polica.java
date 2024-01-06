/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assuranceclient.Print;

import com.assuranceclient.Settings.VehicleWindow;
import com.assuranceclient.customers.CustomersWindow;
import com.assuranceclient.dto.Customer;
import com.assuranceclient.dto.SADoc;
import com.assuranceclient.dto.Vehicle;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

    public Polica(String token, SADoc currentItem) throws IOException {
        this.token = token;
        this.currentItem = currentItem;

        initCustomers();
        initVehicles();

        String DEST = "results/documents/" + currentItem.nomer + "_" + getCustomerNameFromIds(currentItem.ids_client, true) + ".pdf";

        File file = new File(DEST);
        file.getParentFile().mkdirs();
        createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException {
        Cell cell;
        Table table;

        Document doc = new Document(new PdfDocument(new PdfWriter(dest)));

        String FONT_FILENAME = "E:\\AssuranceClient\\src\\main\\resources\\fonts\\arial.ttf";
        PdfFont font = PdfFontFactory.createFont(FONT_FILENAME, PdfEncodings.IDENTITY_H);

        doc.setFont(font);

        table = new Table(new float[]{1}).useAllAvailableWidth();

        cell = new Cell(1, 1)
                .add(new Paragraph("Документ №:" + currentItem.nomer))
                .setFont(font)
                .setFontSize(16)
                .setFontColor(DeviceGray.BLACK)
                .setBackgroundColor(DeviceGray.WHITE)
                .setTextAlignment(TextAlignment.CENTER)
                .setBorder(Border.NO_BORDER);

        table.addCell(cell);

        DateFormat formatter1 = new SimpleDateFormat("dd.MM.yyyy");

        cell = new Cell(1, 1)
                .add(new Paragraph("Валидна от: " + formatter1.format(currentItem.date_from) + " до: " + formatter1.format(currentItem.date_to)))
                .setFont(font)
                .setFontSize(16)
                .setFontColor(DeviceGray.BLACK)
                .setBackgroundColor(DeviceGray.WHITE)
                .setTextAlignment(TextAlignment.CENTER)
                .setBorder(Border.NO_BORDER);

        table.addCell(cell);

        doc.add(table);
//------------------------------------------------------------------------------

        table = new Table(new float[]{5, 10}).useAllAvailableWidth();

        cell = new Cell(1, 1)
                .add(new Paragraph("Застраховано лице:"))
                .setFont(font)
                .setFontSize(13)
                .setFontColor(DeviceGray.BLACK)
                .setBackgroundColor(DeviceGray.WHITE)
                .setTextAlignment(TextAlignment.LEFT)
                .setBorder(Border.NO_BORDER);

        table.addCell(cell);

        cell = new Cell(1, 1)
                .add(new Paragraph(getCustomerNameFromIds(currentItem.ids_client, false)))
                .setFont(font)
                .setFontSize(13)
                .setFontColor(DeviceGray.BLACK)
                .setBackgroundColor(DeviceGray.WHITE)
                .setTextAlignment(TextAlignment.LEFT)
                .setBorder(Border.NO_BORDER);

        table.addCell(cell);
//------------------------------------------------------------------------------
        cell = new Cell(1, 1)
                .add(new Paragraph("ЕГН/ЕИК по булстат:"))
                .setFont(font)
                .setFontSize(13)
                .setFontColor(DeviceGray.BLACK)
                .setBackgroundColor(DeviceGray.WHITE)
                .setTextAlignment(TextAlignment.LEFT)
                .setBorder(Border.NO_BORDER);

        table.addCell(cell);

        cell = new Cell(1, 1)
                .add(new Paragraph(getCustomerEGNFromIds(currentItem.ids_client)))
                .setFont(font)
                .setFontSize(13)
                .setFontColor(DeviceGray.BLACK)
                .setBackgroundColor(DeviceGray.WHITE)
                .setTextAlignment(TextAlignment.LEFT)
                .setBorder(Border.NO_BORDER);

        table.addCell(cell);

//------------------------------------------------------------------------------
        cell = new Cell(1, 1)
                .add(new Paragraph("Данни за обекта:"))
                .setFont(font)
                .setFontSize(13)
                .setFontColor(DeviceGray.BLACK)
                .setBackgroundColor(DeviceGray.WHITE)
                .setTextAlignment(TextAlignment.LEFT)
                .setBorder(Border.NO_BORDER);

        table.addCell(cell);

        cell = new Cell(1, 1)
                .add(new Paragraph(getVehicleFromIds(currentItem.ids_vehicle)))
                .setFont(font)
                .setFontSize(13)
                .setFontColor(DeviceGray.BLACK)
                .setBackgroundColor(DeviceGray.WHITE)
                .setTextAlignment(TextAlignment.LEFT)
                .setBorder(Border.NO_BORDER);

        table.addCell(cell);

//------------------------------------------------------------------------------
        cell = new Cell(1, 1)
                .add(new Paragraph("Начин на плащане:"))
                .setFont(font)
                .setFontSize(13)
                .setFontColor(DeviceGray.BLACK)
                .setBackgroundColor(DeviceGray.WHITE)
                .setTextAlignment(TextAlignment.LEFT)
                .setBorder(Border.NO_BORDER);

        table.addCell(cell);

        cell = new Cell(1, 1)
                .add(new Paragraph("В брой на посредника"))
                .setFont(font)
                .setFontSize(13)
                .setFontColor(DeviceGray.BLACK)
                .setBackgroundColor(DeviceGray.WHITE)
                .setTextAlignment(TextAlignment.LEFT)
                .setBorder(Border.NO_BORDER);

        table.addCell(cell);

        doc.add(table);
//------------------------------------------------------------------------------

        table = new Table(new float[]{5, 5, 5, 5}).useAllAvailableWidth();

        cell = new Cell(1, 1)
                .add(new Paragraph("Документ"))
                .setFont(font)
                .setFontSize(9)
                .setFontColor(DeviceGray.BLACK)
                .setBackgroundColor(DeviceGray.WHITE)
                .setTextAlignment(TextAlignment.CENTER);

        table.addCell(cell);

        cell = new Cell(1, 1)
                .add(new Paragraph("Застрахователна премия"))
                .setFont(font)
                .setFontSize(9)
                .setFontColor(DeviceGray.BLACK)
                .setBackgroundColor(DeviceGray.WHITE)
                .setTextAlignment(TextAlignment.CENTER);

        table.addCell(cell);

        cell = new Cell(1, 1)
                .add(new Paragraph("20% ДДС"))
                .setFont(font)
                .setFontSize(9)
                .setFontColor(DeviceGray.BLACK)
                .setBackgroundColor(DeviceGray.WHITE)
                .setTextAlignment(TextAlignment.CENTER);

        table.addCell(cell);

        cell = new Cell(1, 1)
                .add(new Paragraph("Общо"))
                .setFont(font)
                .setFontSize(9)
                .setFontColor(DeviceGray.BLACK)
                .setBackgroundColor(DeviceGray.WHITE)
                .setTextAlignment(TextAlignment.CENTER);

        table.addCell(cell);
//------------------------------------------------------------------------------

        cell = new Cell(1, 1)
                .add(new Paragraph("Полица"))
                .setFont(font)
                .setFontSize(9)
                .setFontColor(DeviceGray.BLACK)
                .setBackgroundColor(DeviceGray.WHITE)
                .setTextAlignment(TextAlignment.LEFT);

        table.addCell(cell);

        cell = new Cell(1, 1)
                .add(new Paragraph(String.valueOf(currentItem.doc_price)))
                .setFont(font)
                .setFontSize(9)
                .setFontColor(DeviceGray.BLACK)
                .setBackgroundColor(DeviceGray.WHITE)
                .setTextAlignment(TextAlignment.RIGHT);

        table.addCell(cell);

        cell = new Cell(1, 1)
                .add(new Paragraph(String.valueOf(calculateVAT(currentItem.doc_price))))
                .setFont(font)
                .setFontSize(9)
                .setFontColor(DeviceGray.BLACK)
                .setBackgroundColor(DeviceGray.WHITE)
                .setTextAlignment(TextAlignment.RIGHT);

        table.addCell(cell);

        double sumAll = calculateVAT(currentItem.doc_price) + currentItem.doc_price;
        cell = new Cell(1, 1)
                .add(new Paragraph(String.valueOf(sumAll)))
                .setFont(font)
                .setFontSize(9)
                .setFontColor(DeviceGray.BLACK)
                .setBackgroundColor(DeviceGray.WHITE)
                .setTextAlignment(TextAlignment.RIGHT);

        table.addCell(cell);

        doc.add(table);
//------------------------------------------------------------------------------

        table = new Table(new float[]{5, 5, 5, 5}).useAllAvailableWidth();

        cell = new Cell(1, 1)
                .add(new Paragraph("1 "))
                .setFont(font)
                .setFontSize(15)
                .setFontColor(DeviceGray.WHITE)
                .setBackgroundColor(DeviceGray.WHITE)
                .setTextAlignment(TextAlignment.LEFT)
                .setBorder(Border.NO_BORDER);

        table.addCell(cell);

        cell = new Cell(1, 1)
                .add(new Paragraph("1 "))
                .setFont(font)
                .setFontSize(15)
                .setFontColor(DeviceGray.WHITE)
                .setBackgroundColor(DeviceGray.WHITE)
                .setTextAlignment(TextAlignment.RIGHT)
                .setBorder(Border.NO_BORDER);

        table.addCell(cell);

        cell = new Cell(1, 1)
                .add(new Paragraph("1 "))
                .setFont(font)
                .setFontSize(15)
                .setFontColor(DeviceGray.WHITE)
                .setBackgroundColor(DeviceGray.WHITE)
                .setTextAlignment(TextAlignment.RIGHT)
                .setBorder(Border.NO_BORDER);

        table.addCell(cell);

        cell = new Cell(1, 1)
                .add(new Paragraph("1 "))
                .setFont(font)
                .setFontSize(15)
                .setFontColor(DeviceGray.WHITE)
                .setBackgroundColor(DeviceGray.WHITE)
                .setTextAlignment(TextAlignment.RIGHT)
                .setBorder(Border.NO_BORDER);

        table.addCell(cell);
//------------------------------------------------------------------------------

        cell = new Cell(1, 1)
                .add(new Paragraph("Подпис..............."))
                .setFont(font)
                .setFontSize(12)
                .setFontColor(DeviceGray.BLACK)
                .setBackgroundColor(DeviceGray.WHITE)
                .setTextAlignment(TextAlignment.LEFT)
                .setBorder(Border.NO_BORDER);

        table.addCell(cell);

        cell = new Cell(1, 1)
                .add(new Paragraph(""))
                .setFont(font)
                .setFontSize(12)
                .setFontColor(DeviceGray.BLACK)
                .setBackgroundColor(DeviceGray.WHITE)
                .setTextAlignment(TextAlignment.RIGHT)
                .setBorder(Border.NO_BORDER);

        table.addCell(cell);

        cell = new Cell(1, 1)
                .add(new Paragraph("Подпис..............."))
                .setFont(font)
                .setFontSize(12)
                .setFontColor(DeviceGray.BLACK)
                .setBackgroundColor(DeviceGray.WHITE)
                .setTextAlignment(TextAlignment.RIGHT)
                .setBorder(Border.NO_BORDER);

        table.addCell(cell);

        cell = new Cell(1, 1)
                .add(new Paragraph(""))
                .setFont(font)
                .setFontSize(12)
                .setFontColor(DeviceGray.BLACK)
                .setBackgroundColor(DeviceGray.WHITE)
                .setTextAlignment(TextAlignment.RIGHT)
                .setBorder(Border.NO_BORDER);

        table.addCell(cell);
//------------------------------------------------------------------------------

        cell = new Cell(1, 1)
                .add(new Paragraph("(Застраховател)"))
                .setFont(font)
                .setFontSize(8)
                .setFontColor(DeviceGray.BLACK)
                .setBackgroundColor(DeviceGray.WHITE)
                .setTextAlignment(TextAlignment.CENTER)
                .setBorder(Border.NO_BORDER);

        table.addCell(cell);

        cell = new Cell(1, 1)
                .add(new Paragraph(""))
                .setFont(font)
                .setFontSize(8)
                .setFontColor(DeviceGray.BLACK)
                .setBackgroundColor(DeviceGray.WHITE)
                .setTextAlignment(TextAlignment.CENTER)
                .setBorder(Border.NO_BORDER);

        table.addCell(cell);

        cell = new Cell(1, 1)
                .add(new Paragraph("(Клиент)"))
                .setFont(font)
                .setFontSize(8)
                .setFontColor(DeviceGray.BLACK)
                .setBackgroundColor(DeviceGray.WHITE)
                .setTextAlignment(TextAlignment.CENTER)
                .setBorder(Border.NO_BORDER);

        table.addCell(cell);

        cell = new Cell(1, 1)
                .add(new Paragraph(""))
                .setFont(font)
                .setFontSize(8)
                .setFontColor(DeviceGray.BLACK)
                .setBackgroundColor(DeviceGray.WHITE)
                .setTextAlignment(TextAlignment.CENTER)
                .setBorder(Border.NO_BORDER);

        table.addCell(cell);

        doc.add(table);

        doc.close();
    }

    private ArrayList<Customer> dataCustomers = new ArrayList();
    private String jsonStringCustomers = "";
    private ArrayList<Vehicle> dataVehicle = new ArrayList();
    private String jsonStringVehicles = "";

    private String getCustomerNameFromIds(int ids, boolean flag_file_name) {
        for (int i = 0; i < dataCustomers.size(); i++) {
            if (dataCustomers.get(i).ids == ids) {
                if (flag_file_name) {
                    return dataCustomers.get(i).first_name + "_" + dataCustomers.get(i).last_name;
                } else {
                    return dataCustomers.get(i).first_name + " " + dataCustomers.get(i).last_name;
                }
            }
        }

        return "";
    }

    private String getCustomerEGNFromIds(int ids) {
        for (int i = 0; i < dataCustomers.size(); i++) {
            if (dataCustomers.get(i).ids == ids) {
                return dataCustomers.get(i).egn;
            }
        }

        return "";
    }

    private String getVehicleFromIds(int ids) {
        for (int i = 0; i < dataVehicle.size(); i++) {
            if (dataVehicle.get(i).ids == ids) {
                return dataVehicle.get(i).mname + " " + dataVehicle.get(i).reg_num;
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

    private void initVehicles() {

        try {
            jsonStringVehicles = fillCombo("http://localhost:8080/api/vehicles");
        } catch (Exception ex) {
            Logger.getLogger(VehicleWindow.class.getName()).log(Level.SEVERE, null, ex);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            dataVehicle = objectMapper.readValue(jsonStringVehicles, new TypeReference<ArrayList<Vehicle>>() {
            });
        } catch (JsonProcessingException ex) {
            Logger.getLogger(VehicleWindow.class.getName()).log(Level.SEVERE, null, ex);
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

    private static double calculateVAT(double amount) {
        double vatRate = 0.20; // 20% VAT rate
        double vat = amount * vatRate;
        return vat;
    }
}
