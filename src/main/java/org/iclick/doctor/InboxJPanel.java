/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.iclick.doctor;

import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static DB.DoctorJFrame.dateNow;

/**
 *
 * @author Suchira
 */
public class InboxJPanel extends javax.swing.JPanel {

    /**
     * Creates new form InboxJPanel
     */
    private int currentMId = 0;
    private ArrayList<String> messages = new ArrayList<String>();
    private ArrayList<String> messageId = new ArrayList<>();

    private DefaultTableModel model, model2;
    private ArrayList<String> patientName = new ArrayList<>();    
    private ArrayList<String> patientId = new ArrayList<>();
    public InboxJPanel() {
        initComponents();
        model = (DefaultTableModel)patientNameTable.getModel();
        model2 = (DefaultTableModel)messageIdTable.getModel();
        updateSendersTable();
    }
    
     public void updateSendersTable(){
        ArrayList<String> tempChannelList = new ArrayList<>();
        ArrayList<String> channelNum = new ArrayList<>();
        
        
        ArrayList[] array = MessagesJPanel.messageHandler.getInbox();
        patientName = array[1];
        patientId = array[0];
//         System.out.println(patientId);
        int j = 0;
//        for(int i = 0; i < tableRaws; i++){
//            model.removeRow(i);
//        }
        for (String string : patientName) {
            model.addRow(new Object[]{patientName.get(j)});
            j++;
        }
//        tableRaws = patientName.size();
    }
     
     
    public void updateMessageTable(){    
        
        String pName = (String)model.getValueAt(patientNameTable.getSelectedRow(), 0);
        int pId = Integer.parseInt(this.patientId.get(this.patientName.indexOf(pName)));
        ArrayList<Integer> messageId = new ArrayList<>();
        ArrayList<String> messages = new ArrayList<>();
        ArrayList[] array = MessagesJPanel.messageHandler.getMessages(pId, DoctorJFrame.getDoctorId());
        
        messageId = array[0];
        messages = array[1];
        int j = 0;
//        for(int i = 0; i < tableRaws; i++){
//            model.removeRow(i);
//        }
        for (Integer integer : messageId) {
            model2.addRow(new Object[]{messageId.get(j)});
            j++;
        }
//        tableRaws = patientName.size();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        patientNameTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        messageTextArea = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        replyTextArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        replyButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        messageIdTable = new javax.swing.JTable();

        patientNameTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "All Senders"
            }
        ));
        patientNameTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                patientNameTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(patientNameTable);

        messageTextArea.setColumns(20);
        messageTextArea.setRows(5);
        jScrollPane2.setViewportView(messageTextArea);

        replyTextArea.setColumns(20);
        replyTextArea.setRows(5);
        jScrollPane3.setViewportView(replyTextArea);

        jLabel1.setText("Your Reply");

        replyButton.setText("Reply");
        replyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                replyButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Message");

        messageIdTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "M ID"
            }
        ));
        messageIdTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                messageIdTableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(messageIdTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(replyButton)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane3)))
                        .addContainerGap(25, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)))
                .addComponent(replyButton)
                .addGap(0, 10, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
        

    private void patientNameTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patientNameTableMouseClicked
        // TODO add your handling code here:
        String pName = (String)model.getValueAt(patientNameTable.getSelectedRow(), 0);
        System.out.println("patient Name: " + pName);
        System.out.println("index: " + this.patientName.indexOf(pName));
        System.out.println("index newei: " + this.patientId.get(this.patientName.indexOf(pName)));
//        int pId = (int)this.patientId.get(this.patientName.indexOf(pName));
        ArrayList[] array = MessagesJPanel.messageHandler.getMessages(Integer.parseInt(this.patientId.get(this.patientName.indexOf(pName))),DoctorJFrame.getDoctorId());
        
        messageId = array[0];
        messages = array[1];
        System.out.println("ARRAY 0: " + messageId);
        int j = 0;
        if(model2.getRowCount() != 0){
            System.out.println("Row Count: " + model2.getRowCount());
            for(int i = model2.getRowCount() - 1; i >= 0 ; i--){
                model2.removeRow(i);
            }
            messageTextArea.setText("");
            replyTextArea.setText("");
        }
        for (String string : messageId) {
            model2.addRow(new Object[]{messageId.get(j)});
            System.out.println("ADD wenawa");
            j++;
        }
        
    }//GEN-LAST:event_patientNameTableMouseClicked

    private void messageIdTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_messageIdTableMouseClicked
        // TODO add your handling code here:
        String mId = (String)model2.getValueAt(messageIdTable.getSelectedRow(), 0);
        currentMId = Integer.parseInt(mId);
        System.out.println("index 2: " + this.messageId.indexOf(mId));
        String msg = (this.messages.get(this.messageId.indexOf(mId)));
        
        messageTextArea.setText(msg);
    }//GEN-LAST:event_messageIdTableMouseClicked

    private void replyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_replyButtonActionPerformed
        // TODO add your handling code here:
        if(replyTextArea.getText() != null){
            MessagesJPanel.messageHandler.sendReply(currentMId, replyTextArea.getText());
            JOptionPane.showMessageDialog(new JDialog(), "SENT");
        }
        else{
            JOptionPane.showMessageDialog(new JDialog(), "Nothing to reply");
        }
    }//GEN-LAST:event_replyButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable messageIdTable;
    private javax.swing.JTextArea messageTextArea;
    private javax.swing.JTable patientNameTable;
    private javax.swing.JButton replyButton;
    private javax.swing.JTextArea replyTextArea;
    // End of variables declaration//GEN-END:variables
}
