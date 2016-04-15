package org.iclick.doctor.frontend.jFrames;

import org.iclick.doctor.dbaccess.DbConnectionManager;
import org.iclick.doctor.Patient;
import org.iclick.doctor.dbaccess.PatientDataAccessManager;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Prashan
 */
public class SearchConfirmJFrame extends javax.swing.JFrame implements ActionListener {

    ArrayList<String> list = new ArrayList();
    ArrayList<String> list2 = new ArrayList<String>();
    private DefaultTableModel model;
    private DefaultTableModel model2;
    public static String dateNow;
    private Calendar currentDate;
    private int total = 0;
    private String selectedDate;
    DbConnectionManager db = DbConnectionManager.getInstance();
    PatientDataAccessManager patient_da = PatientDataAccessManager.getConnection(db);

    public SearchConfirmJFrame() {
        initComponents();
        name.addActionListener(this);
        XDate.addActionListener(this);
        TableColumn col1 = patientTable.getColumnModel().getColumn(0);
        col1.setPreferredWidth(250);
        TableColumn col2 = patientTable.getColumnModel().getColumn(1);
        col2.setPreferredWidth(100);
        TableColumn col3 = patientTable.getColumnModel().getColumn(2);
        col3.setPreferredWidth(314);
        TableColumn col4 = patientTable.getColumnModel().getColumn(3);
        col4.setPreferredWidth(80);
        TableColumn colo = patientTable2.getColumnModel().getColumn(0);
        colo.setPreferredWidth(76);
        TableColumn col5 = patientTable2.getColumnModel().getColumn(1);
        col5.setPreferredWidth(210);
        TableColumn col6 = patientTable2.getColumnModel().getColumn(2);
        col6.setPreferredWidth(80);
        TableColumn col7 = patientTable2.getColumnModel().getColumn(3);
        col7.setPreferredWidth(298);
        TableColumn col8 = patientTable2.getColumnModel().getColumn(4);
        col8.setPreferredWidth(80);

        model = (DefaultTableModel) patientTable.getModel();
        model2 = (DefaultTableModel) patientTable2.getModel();
        XDate.setFormats(new SimpleDateFormat("yyyy-MM-dd"));
        patientTable.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        patientTable2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
    }

    public void updateTable() {
        System.out.println(list.size());
        int x = 0;
        while (x < (list.size())) {
            model.addRow(new Object[]{list.get(x), list.get(x + 1), list.get(x + 2), list.get(x + 3)});
            x = x + 4;
        }
    }

    public void cleanTable() {

        int rows = model.getRowCount();
        System.out.println("rows" + rows);
        rows = rows - 1;

        for (int i = rows; i >= 0; i--) {
            model.removeRow(i);
        }

    }

    public void updateTable2() {
        System.out.println(list2.size());
        int x = 0;
        int i = 1;
        while (x < (list2.size())) {
            model2.addRow(new Object[]{i, list2.get(x), list2.get(x + 1), list2.get(x + 2), list2.get(x + 3)});
            x = x + 4;
            i++;
        }
    }

    public void cleanTable2() {

        int rows = model2.getRowCount();
        System.out.println("rows" + rows);
        rows = rows - 1;

        for (int i = rows; i >= 0; i--) {
            model2.removeRow(i);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        patientTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        patientTable2 = new javax.swing.JTable();
        XDate = new org.jdesktop.swingx.JXDatePicker();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("SEARCH BY NAME");

        name.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Name");

        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        patientTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Date Of Birth", "Address", "Patient ID"
            }
        ));
        patientTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(patientTable);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("SEARCH BY DATE");

        patientTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Channel No", "Name", "Date of Birth", "Address", "Patient ID"
            }
        ));
        patientTable2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane3.setViewportView(patientTable2);

        XDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XDateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(XDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(XDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String thename = name.getText();
        Patient pa = new Patient(thename, PatientDataAccessManager.getConnection());
        list = pa.searchbyname(thename);
        cleanTable();
        updateTable();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void XDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_XDateActionPerformed

    private void clickAction(java.awt.event.ActionEvent evt) {
    Date dateFrom;

        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFrom = XDate.getDate();
        System.out.println(XDate.getDate());
        selectedDate = formatter.format(dateFrom);
        System.out.println("Date From: " + selectedDate);

        Patient pa = new Patient(patient_da, selectedDate);
        list2 = pa.getNaturalJoinList(patient_da, selectedDate);

        cleanTable2();
        updateTable2();
    }    
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SearchConfirmJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchConfirmJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchConfirmJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchConfirmJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SearchConfirmJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXDatePicker XDate;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField name;
    private javax.swing.JTable patientTable;
    private javax.swing.JTable patientTable2;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == name) {
            System.out.println("awaaaaaaaaaaa");
            jButton1ActionPerformed(event);
        } else if (event.getSource() == XDate) {
            System.out.println("enaga eka gahapn");
            clickAction(event);

        }
    }
}
