/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iclick.doctor.frontend.jDialogs;

import org.iclick.doctor.dbaccess.DbConnectionManager;
import org.iclick.doctor.Doctor;
import org.iclick.doctor.Patient;
import org.iclick.doctor.dbaccess.DoctorDataAccessManager;
import org.iclick.doctor.dbaccess.PatientDataAccessManager;
import org.iclick.doctor.frontend.jFrames.FrontDeskJFrame;

import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Prashan
 */
public class AppointmentEditorJdialog extends javax.swing.JDialog implements ActionListener {

    private DefaultTableModel model;
    private FrontDeskJFrame obj;
    private DbConnectionManager db = DbConnectionManager.getInstance();
    private PatientDataAccessManager patient_da = PatientDataAccessManager.getConnection(db);
    private DoctorDataAccessManager doctor_da = DoctorDataAccessManager.getInstance();
    private Calendar currentDate = Calendar.getInstance(); //Get the current date
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); //format it as per your requirement
    private String dateNow = formatter.format(currentDate.getTime());
    private ArrayList<String> lastappintment = new ArrayList<String>();
    private int Id = -1;
    private String selectedDate;
    private Date dateFrom;
    private String selecteddoctor;
    private String selectedsarea;
    private String selectedtime;
    private int selecteddoctorid;
    private int ddcount = 0;
    private boolean isupdated = false;

    public AppointmentEditorJdialog(java.awt.Frame parent, boolean modal, FrontDeskJFrame obj) {
        super(parent, modal);
        initComponents();
        PID.addActionListener(this);
        jPanel3.setLayout(new BorderLayout());
        this.obj = obj;
        TableColumn col1 = Table.getColumnModel().getColumn(0);
        TableColumn col2 = Table.getColumnModel().getColumn(1);
        model = (DefaultTableModel) Table.getModel();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        Newbutt = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        PID = new javax.swing.JTextField();
        cancelappointment = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        doc = new javax.swing.JComboBox();
        address = new javax.swing.JLabel();
        first = new javax.swing.JLabel();
        last = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        specialA = new javax.swing.JComboBox();
        jLabel40 = new javax.swing.JLabel();
        list = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        jLabel41 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        Newbutt.setText("New");
        Newbutt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewbuttActionPerformed(evt);
            }
        });

        delete.setText("Delete");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(delete, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                    .addComponent(Newbutt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Newbutt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(114, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Cancel Appointment");

        jPanel3.setLayout(null);

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel33.setText("Patient ID");
        jPanel3.add(jLabel33);
        jLabel33.setBounds(0, 50, 100, 17);

        PID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        PID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PIDActionPerformed(evt);
            }
        });
        jPanel3.add(PID);
        PID.setBounds(130, 40, 40, 30);

        cancelappointment.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        cancelappointment.setText("CANCEL APPOINTMENT");
        cancelappointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelappointmentActionPerformed(evt);
            }
        });
        jPanel3.add(cancelappointment);
        cancelappointment.setBounds(200, 250, 200, 25);

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel3.add(jLabel34);
        jLabel34.setBounds(200, 50, 200, 0);

        doc.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        doc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                docActionPerformed(evt);
            }
        });
        jPanel3.add(doc);
        doc.setBounds(390, 110, 220, 30);

        address.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        address.setForeground(new java.awt.Color(153, 0, 0));
        address.setText("...");
        jPanel3.add(address);
        address.setBounds(410, 50, 270, 17);

        first.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        first.setForeground(new java.awt.Color(153, 0, 0));
        first.setText("...");
        jPanel3.add(first);
        first.setBounds(180, 50, 80, 17);

        last.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        last.setForeground(new java.awt.Color(153, 0, 0));
        last.setText("...");
        jPanel3.add(last);
        last.setBounds(270, 50, 120, 17);

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel38.setText("Doctor");
        jPanel3.add(jLabel38);
        jLabel38.setBounds(320, 120, 80, 17);

        specialA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "OPD", "EYE", "SURGERY", "EAR", "HEART", "SKIN" }));
        specialA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                specialAActionPerformed(evt);
            }
        });
        jPanel3.add(specialA);
        specialA.setBounds(130, 110, 130, 30);

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel40.setText("Last Appointment");
        jPanel3.add(jLabel40);
        jLabel40.setBounds(0, 180, 130, 17);

        Table.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Appointment Date", "Appointment Time"
            }
        ));
        jScrollPane1.setViewportView(Table);

        javax.swing.GroupLayout listLayout = new javax.swing.GroupLayout(list);
        list.setLayout(listLayout);
        listLayout.setHorizontalGroup(
            listLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(listLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 27, Short.MAX_VALUE))
        );
        listLayout.setVerticalGroup(
            listLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(listLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 13, Short.MAX_VALUE))
        );

        jPanel3.add(list);
        list.setBounds(130, 170, 400, 60);

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel41.setText("Specialized Area");
        jPanel3.add(jLabel41);
        jLabel41.setBounds(0, 120, 120, 17);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(271, 271, 271)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(46, 46, 46))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NewbuttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewbuttActionPerformed

        this.setVisible(false);
        AppointmentHandlerJDialog refresh = new AppointmentHandlerJDialog(null, true, this.obj);
        refresh.setLocation(0, 20);
        refresh.setVisible(true);
    }//GEN-LAST:event_NewbuttActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        AppointmentEditorJdialog del = new AppointmentEditorJdialog(null, true, this.obj);
        del.setLocation(0, 20);
        del.setVisible(true);
    }//GEN-LAST:event_deleteActionPerformed

    private void PIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PIDActionPerformed

    private void cancelappointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelappointmentActionPerformed
        // TODO add your handling code here:
        if (Id == -1) {
            JOptionPane.showMessageDialog(new JDialog(), "Please Enter Patient Id");
        } else {
            Patient p2 = new Patient(Id);
            p2.cancellastchannel(Id, patient_da, selecteddoctorid);
            cleanTable();
            
            
            obj.cleanTable();
            obj.updateTable();
            obj.updateTotal();
        }
    }//GEN-LAST:event_cancelappointmentActionPerformed

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
            list.setVisible(true);

        } catch (NullPointerException e) {
            System.out.println("methanai awla");
            list.setVisible(false);

        }
    }//GEN-LAST:event_docActionPerformed

    private void specialAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_specialAActionPerformed
        // TODO add your handling code here:
        selectedsarea = (String) specialA.getSelectedItem();
        System.out.println(selectedsarea);
        deletemodel();
        addmodel();
    }//GEN-LAST:event_specialAActionPerformed

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
            java.util.logging.Logger.getLogger(AppointmentEditorJdialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AppointmentEditorJdialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AppointmentEditorJdialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppointmentEditorJdialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AppointmentEditorJdialog dialog = new AppointmentEditorJdialog(new javax.swing.JFrame(), true, new FrontDeskJFrame());
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Newbutt;
    private javax.swing.JTextField PID;
    private javax.swing.JTable Table;
    private javax.swing.JLabel address;
    private javax.swing.JButton cancelappointment;
    private javax.swing.JButton delete;
    private javax.swing.JComboBox doc;
    private javax.swing.JLabel first;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel last;
    private javax.swing.JPanel list;
    private javax.swing.JComboBox specialA;
    // End of variables declaration//GEN-END:variables

    public void addmodel() {

        ArrayList<String> doctors;
        doctors = doctor_da.getNamesToSpecificArea(selectedsarea);
        ddcount = doctor_da.getSelectedDoctorCount(selectedsarea);
        int i;
        String temp;
        for (i = 0; i < ddcount; i++) {

            doc.addItem(doctors.get(i));
        }
    }

    public void deletemodel() {
        doc.removeAllItems();
    }

    public void updateTable() {
        isupdated = true;
        Doctor d = new Doctor(selecteddoctorid);
        lastappintment = d.getpatientlastappoinmentdetails(doctor_da, Id, selecteddoctorid);
        model.addRow(new Object[]{lastappintment.get(0), lastappintment.get(1)});

    }

    public void cleanTable() {
        if (isupdated = true) {
            int rows = model.getRowCount();
            System.out.println("rows" + rows);
            rows = rows - 1;

            for (int i = rows; i >= 0; i--) {
                model.removeRow(i);
            }
        }

    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == PID) {
            appointmentActionPerformed(event);
        }

    }

    private void appointmentActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        ArrayList<String> array = new ArrayList<String>();
        this.Id = Integer.parseInt(PID.getText());
        array = patient_da.getAllData(Id);
        try {
            first.setText(array.get(0));
            last.setText(array.get(1));
            address.setText(array.get(4));
        } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(new JDialog(), "No such an entry");
        }

    }

}
