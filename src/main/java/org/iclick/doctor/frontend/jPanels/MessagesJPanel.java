/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iclick.doctor.frontend.jPanels;

import org.iclick.doctor.Message;
import org.iclick.doctor.MessageHandler;
import org.iclick.doctor.jFrames.DoctorJFrame;

import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Suchira
 */
public class MessagesJPanel extends javax.swing.JPanel {

    /**
     * Creates new form MessagesJPanel
     */
    public static MessageHandler messageHandler = new MessageHandler();
    private Component[] container_new;
    private Component[] container_inbox;
    private Component[] container_sent;
    private int i = 0, w = 0;
    private int currentMId = 0;
    private ArrayList<String> messages = new ArrayList<String>();
    private ArrayList<String> messageId = new ArrayList<String>();

    private DefaultTableModel model, model2;
    private ArrayList<String> patientName = new ArrayList<String>();
    private ArrayList<String> patientId = new ArrayList<String>();

    public MessagesJPanel() {
        initComponents();
        model = (DefaultTableModel)patientNameTable.getModel();
        model2 = (DefaultTableModel)messageIdTable.getModel();
        updateSendersTable();
    }
    
    public void updateSendersTable(){
        ArrayList<String> tempChannelList = new ArrayList<String>();
        ArrayList<String> channelNum = new ArrayList<String>();
               
        ArrayList[] array = MessagesJPanel.messageHandler.getInbox();
        patientName = array[1];
        patientId = array[0];
        int j = 0;

        for (String string : patientName) {
            model.addRow(new Object[]{patientName.get(j)});
            j++;
        }
//        tableRaws = patientName.size();
    }
     
     
    public void updateMessageTable(){    
        
        String pName = (String)model.getValueAt(patientNameTable.getSelectedRow(), 0);
        int pId = Integer.parseInt(this.patientId.get(this.patientName.indexOf(pName)));
        ArrayList<Integer> messageId = new ArrayList<Integer>();
        ArrayList<String> messages = new ArrayList<String>();
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

        jSeparator3 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        patientNameTable = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        messageIdTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        messageTextArea = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        replyTextArea = new javax.swing.JTextArea();
        replyButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        Header = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(452, 466));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Inbox");

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

        messageTextArea.setColumns(20);
        messageTextArea.setRows(5);
        jScrollPane2.setViewportView(messageTextArea);

        replyTextArea.setColumns(20);
        replyTextArea.setRows(5);
        jScrollPane3.setViewportView(replyTextArea);

        replyButton.setText("Reply");
        replyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                replyButtonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Message Header");

        Header.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Header.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HeaderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE))
                .addGap(187, 187, 187)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(replyButton)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)))
                    .addComponent(Header, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(157, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(27, 27, 27)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(752, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Header, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(replyButton)))
                .addContainerGap(112, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(110, 110, 110)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(34, 34, 34)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addGap(111, 111, 111)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void patientNameTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patientNameTableMouseClicked
        // TODO add your handling code here:
        String pName = (String) model.getValueAt(patientNameTable.getSelectedRow(), 0);
        System.out.println("patient Name: " + pName);
        System.out.println("index: " + this.patientName.indexOf(pName));
        System.out.println("index newei: " + this.patientId.get(this.patientName.indexOf(pName)));
        //        int pId = (int)this.patientId.get(this.patientName.indexOf(pName));
        ArrayList[] array = MessagesJPanel.messageHandler.getMessages(Integer.parseInt(this.patientId.get(this.patientName.indexOf(pName))),DoctorJFrame.getDoctorId());

        messageId = array[0];
        messages = array[1];
        System.out.println("ARRAY 0: " + messageId);
        int j = 0;
        if (model2.getRowCount() != 0) {
            System.out.println("Row Count: " + model2.getRowCount());
            for (int i = model2.getRowCount() - 1; i >= 0; i--) {
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
        String mId = (String) model2.getValueAt(messageIdTable.getSelectedRow(), 0);
        int messageid = Integer.parseInt(mId);
        currentMId = Integer.parseInt(mId);
        System.out.println("index 2: " + this.messageId.indexOf(mId));
        String msg = (this.messages.get(this.messageId.indexOf(mId)));
        String header = new Message().getHeader(messageid);
        Header.setText(header);
        messageTextArea.setText(msg);
    }//GEN-LAST:event_messageIdTableMouseClicked

    private void replyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_replyButtonActionPerformed
        // TODO add your handling code here:
        if (replyTextArea.getText() != null) {
            MessagesJPanel.messageHandler.sendReply(currentMId, replyTextArea.getText());
            JOptionPane.showMessageDialog(new JDialog(), "SENT");
        } else {
            JOptionPane.showMessageDialog(new JDialog(), "Nothing to reply");
        }
    }//GEN-LAST:event_replyButtonActionPerformed

    private void HeaderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HeaderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HeaderActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable messageIdTable;
    private javax.swing.JTextArea messageTextArea;
    private javax.swing.JTable patientNameTable;
    private javax.swing.JButton replyButton;
    private javax.swing.JTextArea replyTextArea;
    // End of variables declaration//GEN-END:variables
}
