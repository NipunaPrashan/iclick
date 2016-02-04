/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.iclick.doctor.frontend.jPanels;

import org.iclick.doctor.PatientHandler;
import org.iclick.doctor.jFrames.DoctorJFrame;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Suchira
 */
public class SearchJPanel extends javax.swing.JPanel {

    ArrayList<String> searchList;
    private DefaultTableModel patientTableModel;
    private DoctorJFrame containerFrame;
    /**
     * Creates new form searchJPanel
     */
    public SearchJPanel(JFrame containerFrame) {
        initComponents();
        this.containerFrame = (DoctorJFrame)containerFrame;
        patientTableModel = (DefaultTableModel)patientTable.getModel();
//        setPreferredSize(new Dimension(880, 456));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchTextField = new javax.swing.JTextField();
        searchingButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        patientTable = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(880, 456));

        searchingButton.setText("SEARCH");
        searchingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchingButtonActionPerformed(evt);
            }
        });

        patientTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Patient ID", "First Name", "Last Name", "Gender", "Address"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        patientTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        patientTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                patientTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(patientTable);
        if (patientTable.getColumnModel().getColumnCount() > 0) {
            patientTable.getColumnModel().getColumn(0).setPreferredWidth(10);
            patientTable.getColumnModel().getColumn(3).setPreferredWidth(10);
            patientTable.getColumnModel().getColumn(4).setPreferredWidth(12);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(searchingButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchingButton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchingButtonActionPerformed
        // TODO add your handling code here:
        PatientHandler pHandler = new PatientHandler();
        String searchname = searchTextField.getText();
       // System.out.println(DoctorJFrame.doctorId);
        searchList = pHandler.getPatientList(searchname,DoctorJFrame.doctorId);
        int j = 0;

        patientTableModel.setRowCount(0);

        for (int i =0; i< (searchList.size())/6 ; i++) {
           patientTableModel.addRow(new Object[]{"" + searchList.get(j++), "" + searchList.get(j++), "" +searchList.get(j++), "" + searchList.get(j++), "" + searchList.get(j++),""});
     
        }
    }//GEN-LAST:event_searchingButtonActionPerformed

    private void patientTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patientTableMouseClicked
        // TODO add your handling code here:`
        System.out.println("ID - " + (String)patientTableModel.getValueAt(patientTable.getSelectedRow(), 0));
        int patientID = Integer.parseInt((String)patientTableModel.getValueAt(patientTable.getSelectedRow(), 0));
        DoctorJFrame.currentPatientID = patientID;
        PatientJPanel patientPanel = new PatientJPanel();
        JPanel containerPanel = containerFrame.getContainerPanel();
        containerFrame.setCurrentPatientID();
        containerPanel.setLayout(new BorderLayout());
        containerPanel.removeAll();
        containerPanel.add(patientPanel);
        patientPanel.setBorder(null);
        containerPanel.repaint();
    }//GEN-LAST:event_patientTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable patientTable;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JButton searchingButton;
    // End of variables declaration//GEN-END:variables
}