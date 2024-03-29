/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.assuranceclient.mainwindow;

import com.assuranceclient.Information.SearchOldDocWindow;
import com.assuranceclient.dto.SADoc;
import com.assuranceclient.notifications.Notification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Цветан Иванов
 */
public class MainWindow extends javax.swing.JFrame {

    private String token;

    /**
     * Creates new form MainWindow
     */
    public MainWindow(String token, javax.swing.JFrame parent) {
        this.token = token;
        initComponents();
        initDocuments();

        DateFormat formatter1 = new SimpleDateFormat("dd.MM.yyyy");

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dataDocs.size(); i++) {
            sb.append(checkIfDateIs10DaysInFuture(formatter1.format(dataDocs.get(i).date_to), dataDocs.get(i)));
        }

        if (!sb.isEmpty()) {
            com.assuranceclient.notifications.Notification noti = new com.assuranceclient.notifications.Notification(this, Notification.Type.WARNING, Notification.Location.BOTTOM_RIGHT, sb.toString());
            noti.showNotification();
        }
    }

    public void setUserRole(int role){
        System.out.println("role -> " +role);
//        if (role != 1) {
//            jMenu4.setVisible(false);
//        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("Операции");

        jMenuItem4.setText("Издаване на документ");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem5.setText("Стари документи");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Клиенти");

        jMenuItem1.setText("Клиенти - добавяне");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Настройки");

        jMenuItem2.setText("Дефиниране на тарифи");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuItem3.setText("Дефиниране на транспортни средства");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Администратор");

        jMenuItem6.setText("Дефиниране на потребители");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem6);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        com.assuranceclient.customers.CustomersWindow ss = new com.assuranceclient.customers.CustomersWindow(token, this);
        ss.setVisible(true);
        this.add(ss);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        com.assuranceclient.Settings.TarifiWindow ss = new com.assuranceclient.Settings.TarifiWindow(token, this);
        ss.setVisible(true);
        this.add(ss);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        com.assuranceclient.Settings.VehicleWindow ss = new com.assuranceclient.Settings.VehicleWindow(token, this);
        ss.setVisible(true);
        this.add(ss);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        com.assuranceclient.Oper.OperWindow ss = new com.assuranceclient.Oper.OperWindow(token, this);
        ss.setVisible(true);
        this.add(ss);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        com.assuranceclient.Information.SearchOldDocWindow ss = new com.assuranceclient.Information.SearchOldDocWindow(token, this);
        ss.setVisible(true);
        this.add(ss);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        com.assuranceclient.Settings.UsersWindow ss = new com.assuranceclient.Settings.UsersWindow(token, this);
        ss.setVisible(true);
        this.add(ss);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    // End of variables declaration//GEN-END:variables

    private String jsonStringDocuments = "";
    private ArrayList<SADoc> dataDocs = new ArrayList();

    private void initDocuments() {

        try {
            jsonStringDocuments = getDocuments("http://localhost:8080/api/documents");
        } catch (Exception ex) {
            Logger.getLogger(SearchOldDocWindow.class.getName()).log(Level.SEVERE, null, ex);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            dataDocs = objectMapper.readValue(jsonStringDocuments, new TypeReference<ArrayList<SADoc>>() {
            });
        } catch (JsonProcessingException ex) {
            Logger.getLogger(SearchOldDocWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String getDocuments(String uri) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .header("Authorization", "Bearer " + token)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public static String checkIfDateIs10DaysInFuture(String dateStr, SADoc doc) {
        LocalDate inputDate = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        LocalDate today = LocalDate.now();

        LocalDate futureDate = today.plusDays(10);
        
        if ((inputDate.isBefore(futureDate) || inputDate.isEqual(futureDate)) && inputDate.isAfter(today) ) {
            return "Срокът на застраховка с №" + doc.nomer + " изтича скоро!";
        }
        return "";
    }
}
