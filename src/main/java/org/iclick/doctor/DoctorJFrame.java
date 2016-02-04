package org.iclick.doctor;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.*;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.tree.DefaultMutableTreeNode;

public class DoctorJFrame extends javax.swing.JFrame {

    private Component[] components;
    private DbConnecter db = DbConnecter.getConnection();
    private Doctor_DA doctor_da = Doctor_DA.getConnection(db);
    public static int currentPatientID = -1;
    private Patient currentPatient;
    public static String dateNow;
    public static String timeNow;
    private Calendar currentDate;
    private DefaultTableModel model;
    private int tableRaws = 0;
    private int firstTimeTable = 0;
    public static int doctorId;
    private String selectedtime;
    private String finalDay = DB.finalDay;
    String[] parts2;
    int nowtimedate;

    public DoctorJFrame() {
        initComponents();
//        containerPanel.setBounds(20, 151, 880, 456);
        model = (DefaultTableModel) patientListTable.getModel();
//        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        currentDate = Calendar.getInstance(); //Get the current date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); //format it as per your requirement
        dateNow = formatter.format(currentDate.getTime());
        components = containerPanel.getComponents();
        patientListTable.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        parts2 = dateNow.split("-");
        nowtimedate = Integer.parseInt(parts2[1] + parts2[2]);
        currentPatientTextField.setText("");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        logoutButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        currentPatientTextField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        docname = new javax.swing.JLabel();
        dochospital = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        todayListButton = new javax.swing.JButton();
        patientButton = new javax.swing.JButton();
        messagesButton = new javax.swing.JButton();
        searchButton = new javax.swing.JButton();
        containerPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        patientListTable = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        Combotime = new javax.swing.JComboBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(1366, 768));

        logoutButton.setText("Logout");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Current Patient");

        currentPatientTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                currentPatientTextFieldActionPerformed(evt);
            }
        });

        jButton1.setText("Enter");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("profile");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(currentPatientTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(docname, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(dochospital, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(logoutButton)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(docname, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dochospital, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(logoutButton)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(currentPatientTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        todayListButton.setText("Todays List");
        todayListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                todayListButtonActionPerformed(evt);
            }
        });

        patientButton.setText("Patient");
        patientButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientButtonActionPerformed(evt);
            }
        });

        messagesButton.setText("Messages");
        messagesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                messagesButtonActionPerformed(evt);
            }
        });

        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(todayListButton)
                .addGap(30, 30, 30)
                .addComponent(patientButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(messagesButton)
                .addGap(30, 30, 30)
                .addComponent(searchButton)
                .addGap(14, 14, 14))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(todayListButton)
                    .addComponent(patientButton)
                    .addComponent(messagesButton)
                    .addComponent(searchButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        patientListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Channel Number", "Patient ID", "Patient Name", "Age"
            }
        ));
        patientListTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                patientListTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(patientListTable);

        javax.swing.GroupLayout containerPanelLayout = new javax.swing.GroupLayout(containerPanel);
        containerPanel.setLayout(containerPanelLayout);
        containerPanelLayout.setHorizontalGroup(
            containerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 883, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(149, Short.MAX_VALUE))
        );
        containerPanelLayout.setVerticalGroup(
            containerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Channelling Time");

        Combotime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CombotimeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(Combotime, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(containerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel4))
                    .addComponent(Combotime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(containerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jMenu1.setText("File");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMenuItem2.setText("Logout");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMenuItem3.setText("Exit");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1074, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        // TODO add your handling code here:
        dispose();
        LoginJFrame log = new LoginJFrame();
        log.setLocationRelativeTo(null);
        log.setVisible(true);
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void currentPatientTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_currentPatientTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_currentPatientTextFieldActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        setCurrentPatientID();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        DoctorProfileJFrame profile = new DoctorProfileJFrame();
        profile.setmobileVanish();
        profile.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void todayListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_todayListButtonActionPerformed

        if (components != null) {
            containerPanel.removeAll();
            for (Component component : components) {
                containerPanel.add(component);
            }
            containerPanel.repaint();
            //            this.setVisible(true);
        }
        updateTable();
    }//GEN-LAST:event_todayListButtonActionPerformed

    private void patientButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientButtonActionPerformed
        if (issetcurrentpatientid()) {

            PatientJPanel patientPanel = new PatientJPanel();

            containerPanel.setLayout(new BorderLayout());
            containerPanel.removeAll();
            containerPanel.add(patientPanel);
            containerPanel.repaint();
            this.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(new JDialog(), "Enter Current Patient");
        }
    }//GEN-LAST:event_patientButtonActionPerformed

    private void messagesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_messagesButtonActionPerformed
        // TODO add your handling code here:
   //     MessagesJPanel messagejpanel = new MessagesJPanel();
        containerPanel.setLayout(new BorderLayout());
        containerPanel.removeAll();
        containerPanel.add(new MessagesJPanel());
        containerPanel.repaint();
        this.setVisible(true);
    }//GEN-LAST:event_messagesButtonActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        // TODO add your handling code here:

        SearchJPanel searchPanel = new SearchJPanel(this);
        containerPanel.setLayout(new BorderLayout());
        containerPanel.removeAll();
        containerPanel.add(searchPanel);
        containerPanel.repaint();
        this.setVisible(true);
    }//GEN-LAST:event_searchButtonActionPerformed

    private void CombotimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CombotimeActionPerformed
        // TODO add your handling code here:
        selectedtime = (String) Combotime.getSelectedItem();
        cleanTable();
        updateTable();
    }//GEN-LAST:event_CombotimeActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        dispose();
        LoginJFrame logout = new LoginJFrame();
        logout.setLocationRelativeTo(null);
        logout.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        close();
        dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void patientListTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patientListTableMouseClicked
        // TODO add your handling code here:
        String pid = (String) model.getValueAt(patientListTable.getSelectedRow(), 1);
        int pID = Integer.parseInt(pid);
        currentPatientID=pID;
        currentPatientTextField.setText(pid);
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
            java.util.logging.Logger.getLogger(DoctorJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DoctorJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DoctorJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DoctorJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DoctorJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox Combotime;
    private javax.swing.JPanel containerPanel;
    private javax.swing.JTextField currentPatientTextField;
    private javax.swing.JLabel dochospital;
    private javax.swing.JLabel docname;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton logoutButton;
    private javax.swing.JButton messagesButton;
    private javax.swing.JButton patientButton;
    private javax.swing.JTable patientListTable;
    private javax.swing.JButton searchButton;
    private javax.swing.JButton todayListButton;
    // End of variables declaration//GEN-END:variables

    public void setCurrentPatientID() {

        currentPatientID = Integer.parseInt(currentPatientTextField.getText());

    }

    private void setContainPanel(JPanel panel) {
        containerPanel.setLayout(new BorderLayout());
//        panel.setLayout(containerPanel.getLayout());
        containerPanel.removeAll();
        containerPanel.add(panel);
        panel.setBorder(null);
        containerPanel.repaint();
//        panel.setVisible(true);

    }

    public static int getDoctorId() {
        return doctorId;
    }
    

    public void setDoctorId(int userId) {
        int id = doctor_da.getdoctorid(userId);
        this.doctorId = id;
        docname.setText("Dr. " + doctor_da.getDoctorName(id));
        dochospital.setText("Hospital" + doctor_da.getDoctorHospital(id));
    }

    public void cleanTable() {

        int rows = model.getRowCount();
        System.out.println("rows" + rows);
        rows = rows - 1;

        for (int i = rows; i >= 0; i--) {
            model.removeRow(i);
        }

    }

    public JPanel getContainerPanel() {
        return containerPanel;
    }

    public void updateTable() {
        ArrayList<String> channelPatientId = new ArrayList<String>();
        ArrayList<String> patientName = new ArrayList<String>();
        ArrayList<Integer> patientAge = new ArrayList<Integer>();

        Patient chnlList = new Patient(Patient_DA.getConnection(), dateNow);
        channelPatientId = chnlList.getIDList(Patient_DA.getConnection(), dateNow, doctorId, selectedtime);

        for (int i = 0; i < channelPatientId.size(); i++) {
            Patient p = new Patient(Integer.parseInt(channelPatientId.get(i)));
            patientName.add(p.getPatientRealName(p.getId()));
            String DOB = p.getDOB(p.getId());
            String[] parts = DOB.split("-");
            String birthyear = parts[0];
            String currentyear = parts2[0];
            int timedate = Integer.parseInt(parts[1] + parts[2]);
            int age = Integer.parseInt(currentyear) - Integer.parseInt(birthyear);
            System.out.println(nowtimedate);
            System.out.println(timedate);
            System.out.println(age);

            if (timedate > nowtimedate) {
                age = age - 1;
                patientAge.add(age);
            } else if (timedate <= nowtimedate) {
                patientAge.add(age);

            }
            age = 0;
        }

        int j = 0;
        int i = 1;

        model.setRowCount(0);

        for (String string : patientName) {
            model.addRow(new Object[]{"                         " + i++,channelPatientId.get(j), "          " + patientName.get(j), "                           " + patientAge.get(j)});
            j++;
        }
        tableRaws = patientName.size();
    }

    private void close() {
        WindowEvent winclose = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winclose);
    }

    public void addmodeltime() {

        ArrayList<String> dates = new ArrayList<String>();
        int datecount;
        dates = doctor_da.gettimes(doctorId, finalDay);
        System.out.println("dId"+ doctorId);
        System.out.println("finalday"+ finalDay);
        datecount = dates.size();
        System.out.println("size"+ datecount);
      
        int i;
        for (i = 0; i < datecount; i++) {

            Combotime.addItem(dates.get(i));
        }
        cleanTable();
        updateTable();
    }

    public void deletemodeltime() {
        Combotime.removeAllItems();
    }

    private boolean issetcurrentpatientid() {
        if (currentPatientID == -1) {
            return false;
        } else {
            return true;
        }
    }

    private void historyTableMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:

        currentPatientID = (Integer) model.getValueAt(patientListTable.getSelectedRow(), 0);
        currentPatientTextField.setText(Integer.toString(currentPatientID));

    }

}
