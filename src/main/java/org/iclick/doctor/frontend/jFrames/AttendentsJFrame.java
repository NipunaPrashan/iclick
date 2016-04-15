package org.iclick.doctor.frontend.jFrames;

import org.iclick.doctor.*;
import org.iclick.doctor.beans.Prescription;
import org.iclick.doctor.dbaccess.DbConnectionManager;
import org.iclick.doctor.dbaccess.DoctorDataAccessManager;
import org.iclick.doctor.dbaccess.PatientDataAccessManager;
import org.iclick.doctor.dbaccess.PrescriptionDataAccessManager;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Pramod
 */
public class AttendentsJFrame extends javax.swing.JFrame {

    private DbConnectionManager db = DbConnectionManager.getInstance();
    private DoctorDataAccessManager doctor_da = DoctorDataAccessManager.getInstance();
    private DefaultTableModel model;
    private Calendar currentDate = Calendar.getInstance();
    private int total = 0;
    private String selecteddoctor;
    private String selectedsarea;
    private String selectedtime;
    private int selecteddoctorid;
    private int ddcount = 0;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); //format it as per your requirement
    String dateNow = formatter.format(currentDate.getTime());

    public AttendentsJFrame() {
        initComponents();
        TableColumn col1 = patientListTable.getColumnModel().getColumn(0);
        col1.setPreferredWidth(80);
        TableColumn col2 = patientListTable.getColumnModel().getColumn(1);
        col2.setPreferredWidth(287);
        TableColumn col3 = patientListTable.getColumnModel().getColumn(2);
        col3.setPreferredWidth(80);
        model = (DefaultTableModel) patientListTable.getModel();
        date.setText(dateNow);

        currentDate = Calendar.getInstance(); //Get the current date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); //format it as per your requirement
        dateNow = formatter.format(currentDate.getTime());
        patientListTable.setFont(new Font("Times New Roman", Font.PLAIN, 15));
    }

    public void updateTable() {
        ArrayList<String> patient_id_list = new ArrayList<String>();
        ArrayList<String> channelNum = new ArrayList<String>();
        ArrayList<String> patientName = new ArrayList<String>();

        Patient chnPatient = new Patient(PatientDataAccessManager.getConnection(), dateNow);
        patient_id_list = chnPatient.getupdatedList(PatientDataAccessManager.getConnection(), dateNow, selecteddoctorid);
        System.out.println(dateNow);
        System.out.println(selecteddoctorid);
        int y = patient_id_list.size();
        int j = 0;
        total = 0;
        //total = tempChannelList.size();
        for (int i = 0; i < patient_id_list.size(); i++) {

            total += 1;
            Patient p = new Patient(Integer.parseInt(patient_id_list.get(j)));
            patientName.add(p.getPatientRealName(p.getId()));
            j++;
        }

        j = 0;
        int x = patientName.size();
        for (int i = 0; i < patientName.size(); i++) {
            model.addRow(new Object[]{patient_id_list.get(j), patientName.get(j), "        " + (total--)});
            j++;
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        nameLable = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        prescriptionjTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        patientListTable = new javax.swing.JTable();
        date = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        specialA = new javax.swing.JComboBox();
        jLabel38 = new javax.swing.JLabel();
        doc = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        nameLable2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        nameLable3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(null);

        jLabel3.setFont(new java.awt.Font("Baskerville Old Face", 1, 18)); // NOI18N
        jLabel3.setText("Prescribed Doctor   :");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(60, 160, 180, 30);

        nameLable.setBackground(new java.awt.Color(255, 255, 255));
        nameLable.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nameLable.setForeground(new java.awt.Color(0, 0, 102));
        getContentPane().add(nameLable);
        nameLable.setBounds(240, 140, 220, 20);

        prescriptionjTextArea1.setColumns(20);
        prescriptionjTextArea1.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        prescriptionjTextArea1.setRows(5);
        jScrollPane1.setViewportView(prescriptionjTextArea1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(50, 260, 480, 270);

        jButton1.setText("Logout");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(50, 570, 87, 34);

        patientListTable.setAutoCreateRowSorter(true);
        patientListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Patient Id", "Patient Name", "Channel No"
            }
        ));
        patientListTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        patientListTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                patientListTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(patientListTable);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(640, 170, 452, 360);

        date.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        date.setForeground(new java.awt.Color(0, 0, 102));
        getContentPane().add(date);
        date.setBounds(110, 30, 90, 30);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Date  -");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(50, 30, 70, 30);

        jLabel40.setFont(new java.awt.Font("Baskerville Old Face", 1, 18)); // NOI18N
        jLabel40.setText("Specialized Area");
        getContentPane().add(jLabel40);
        jLabel40.setBounds(300, 40, 140, 19);

        specialA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "OPD", "EYE", "SURGERY", "EAR", "HEART", "SKIN" }));
        specialA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                specialAActionPerformed(evt);
            }
        });
        getContentPane().add(specialA);
        specialA.setBounds(470, 40, 130, 30);

        jLabel38.setFont(new java.awt.Font("Baskerville Old Face", 1, 18)); // NOI18N
        jLabel38.setText("Doctor");
        getContentPane().add(jLabel38);
        jLabel38.setBounds(650, 50, 80, 19);

        doc.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        doc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                docActionPerformed(evt);
            }
        });
        getContentPane().add(doc);
        doc.setBounds(740, 40, 220, 30);

        jLabel5.setFont(new java.awt.Font("Baskerville Old Face", 1, 18)); // NOI18N
        jLabel5.setText("Patient name          :");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(60, 130, 170, 30);

        jLabel6.setFont(new java.awt.Font("Baskerville Old Face", 1, 18)); // NOI18N
        jLabel6.setText("Prescribed Medicine");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(60, 220, 200, 30);

        nameLable2.setBackground(new java.awt.Color(255, 255, 255));
        nameLable2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nameLable2.setForeground(new java.awt.Color(0, 0, 102));
        getContentPane().add(nameLable2);
        nameLable2.setBounds(240, 170, 220, 20);

        jLabel7.setFont(new java.awt.Font("Baskerville Old Face", 1, 18)); // NOI18N
        jLabel7.setText("Prescription Date    :");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(60, 190, 180, 30);

        nameLable3.setBackground(new java.awt.Color(255, 255, 255));
        nameLable3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nameLable3.setForeground(new java.awt.Color(0, 0, 102));
        getContentPane().add(nameLable3);
        nameLable3.setBounds(240, 190, 220, 20);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        dispose();
        org.iclick.doctor.frontend.jFrames.LoginJFrame out = new LoginJFrame();
        out.setLocationRelativeTo(null);
        out.setVisible(true);
        //dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void specialAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_specialAActionPerformed
        // TODO add your handling code here:
        selectedsarea = (String) specialA.getSelectedItem();
        // System.out.println(selectedsarea);
        deletemodel();
        addmodel();
    }//GEN-LAST:event_specialAActionPerformed

    private void docActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_docActionPerformed
        // TODO add your handling code here:
       
        selecteddoctor = (String) doc.getSelectedItem();
        System.out.println(selecteddoctor);
        try {

            String[] parts = selecteddoctor.split(" ");
            String firstname = parts[1];
            String lastname = parts[2];
            Doctor d1 = new Doctor(firstname, lastname);
            selecteddoctorid = d1.getselecteddoctorid(firstname, lastname);

            cleanTable();
            updateTable();
            System.out.println("athulata panna");

        } catch (NullPointerException e) {
            System.out.println("methanai awla");
            cleanTable();

        }
    }//GEN-LAST:event_docActionPerformed

    private void patientListTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patientListTableMouseClicked
        // TODO add your handling code here:
        System.out.println("gayya");
        String did = (String) model.getValueAt(patientListTable.getSelectedRow(), 0);
        //System.out.println(did);
        int Patient_ID = Integer.parseInt(did);
//        int Patient_ID = (int)model.getValueAt(patientListTable.getSelectedRow(), 0);
        System.out.println(Patient_ID);
        //int Patient_ID = Integer.parseInt(PatientID.getText());
        ArrayList<String> tempCurrent = new ArrayList<String>();
        ArrayList<String> channelPatientId = new ArrayList<String>();

        String curPatientName = null;
        String currentMedi = null;
        String currentId = null;
        String PrescriptionID = null;

        Patient thePatient = new Patient(Patient_ID, PatientDataAccessManager.getConnection());
        Prescription MediCard = new Prescription(PrescriptionDataAccessManager.getConnection());
        curPatientName = thePatient.getPatientRealName(Patient_ID);
        tempCurrent = MediCard.getMedicine(Patient_ID,selecteddoctorid);

        for (int i = 0; i < tempCurrent.size(); i++) {
            if (i == 0) {
                currentMedi = tempCurrent.get(i);
            } else {
                currentId = tempCurrent.get(i);
            }
        }

        nameLable.setText(curPatientName);
        nameLable2.setText(selecteddoctor);
        nameLable3.setText(dateNow);
        //prescriptionjTextArea1.setText("Doctor "+selecteddoctor);
        //prescriptionjTextArea1.setText("Date   "+selecteddoctor);
        //prescriptionjTextArea1.setText("Prescribed Medicine");
        //prescriptionjTextArea1.set
        prescriptionjTextArea1.setText(currentMedi);
    }//GEN-LAST:event_patientListTableMouseClicked

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(AttendentsJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AttendentsJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AttendentsJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AttendentsJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AttendentsJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel date;
    private javax.swing.JComboBox doc;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel nameLable;
    private javax.swing.JLabel nameLable2;
    private javax.swing.JLabel nameLable3;
    private javax.swing.JTable patientListTable;
    private javax.swing.JTextArea prescriptionjTextArea1;
    private javax.swing.JComboBox specialA;
    // End of variables declaration//GEN-END:variables

    private void close() {
        WindowEvent winclose = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winclose);
    }

    public void deletemodel() {
        doc.removeAllItems();
    }

    public void addmodel() {

        ArrayList<String> doctors = new ArrayList<String>();
        doctors = doctor_da.getNamesToSpecificArea(selectedsarea);
        ddcount = doctor_da.getSelectedDoctorCount(selectedsarea);
        int i;
        String temp;
        for (i = 0; i < ddcount; i++) {

            doc.addItem(doctors.get(i));
        }
    }

}
