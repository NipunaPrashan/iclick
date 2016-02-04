package org.iclick.doctor.frontend.jDialogs;

import java.awt.BorderLayout;
import java.awt.Component;
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

import org.freixas.jcalendar.JCalendar;
import org.iclick.doctor.dbaccess.DbConnectionManager;
import org.iclick.doctor.Doctor;
import org.iclick.doctor.Patient;
import org.iclick.doctor.dbaccess.DoctorDataAccessManager;
import org.iclick.doctor.dbaccess.PatientDataAccessManager;
import org.iclick.doctor.jFrames.FrontDeskJFrame;

/**
 *
 * @author Prashan
 */
public class AppointmentHandlerJDialog extends javax.swing.JDialog implements ActionListener {

    /**
     * Creates new form MakeAnAppointmentJDialog
     */
    private DefaultTableModel model;
    private FrontDeskJFrame obj;
    private DbConnectionManager db = DbConnectionManager.getInstance();
    private PatientDataAccessManager patient_da = PatientDataAccessManager.getConnection(db);
    private DoctorDataAccessManager doctor_da = DoctorDataAccessManager.getInstance(db);

    private Calendar currentDate = Calendar.getInstance(); //Get the current date
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); //format it as per your requirement
    private String dateNow = formatter.format(currentDate.getTime());

    private ArrayList<String> channelavalability = new ArrayList<>();
    private int channelNo = 0;
    private int Id = -1;
    private String selectedDate;
    private Date dateFrom;
    private String selecteddoctor;
    private String selectedsarea;
    private String selectedtime;
    private int selecteddoctorid;
    private int ddcount = 0;

    public AppointmentHandlerJDialog(java.awt.Frame parent, boolean modal, FrontDeskJFrame obj) {
        super(parent, modal);
        initComponents();
        jPanel3.setLayout(new BorderLayout());
        this.obj = obj;
        Xdate.setFormats(new SimpleDateFormat("yyyy-MM-dd"));
        PID.addActionListener(this);
        Xdate.addActionListener(this);

        TableColumn col1 = Table.getColumnModel().getColumn(0);
        TableColumn col2 = Table.getColumnModel().getColumn(1);
        model = (DefaultTableModel) Table.getModel();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        javax.swing.ButtonGroup buttonGroup6 = new javax.swing.ButtonGroup();
        buttonGroup7 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        Newbutt = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        PID = new javax.swing.JTextField();
        chNo = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        Xdate = new org.jdesktop.swingx.JXDatePicker();
        jLabel36 = new javax.swing.JLabel();
        doc = new javax.swing.JComboBox();
        nochn = new javax.swing.JLabel();
        address = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        first = new javax.swing.JLabel();
        last = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        specialA = new javax.swing.JComboBox();
        jLabel40 = new javax.swing.JLabel();
        list = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        Combotime = new javax.swing.JComboBox();
        jLabel37 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        Newbutt.setText("New");
        Newbutt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewbuttActionPerformed(evt);
            }
        });

        jButton2.setText("Delete");
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                    .addComponent(Newbutt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Newbutt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(429, Short.MAX_VALUE))
        );

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
        PID.setBounds(120, 40, 40, 30);

        chNo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        chNo.setForeground(new java.awt.Color(255, 0, 51));
        chNo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        chNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chNoActionPerformed(evt);
            }
        });
        jPanel3.add(chNo);
        chNo.setBounds(340, 460, 60, 23);

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel35.setText("Channel Number");
        jPanel3.add(jLabel35);
        jLabel35.setBounds(200, 460, 130, 17);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton1.setText("CHANNEL");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1);
        jButton1.setBounds(200, 410, 200, 25);

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel3.add(jLabel34);
        jLabel34.setBounds(200, 50, 200, 0);

        Xdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XdateActionPerformed(evt);
            }
        });
        jPanel3.add(Xdate);
        Xdate.setBounds(120, 280, 130, 30);

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel36.setText("Select Time");
        jPanel3.add(jLabel36);
        jLabel36.setBounds(300, 290, 80, 17);

        doc.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        doc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                docActionPerformed(evt);
            }
        });
        jPanel3.add(doc);
        doc.setBounds(390, 110, 220, 30);

        nochn.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        nochn.setForeground(new java.awt.Color(153, 0, 0));
        jPanel3.add(nochn);
        nochn.setBounds(180, 350, 60, 20);

        address.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        address.setForeground(new java.awt.Color(153, 0, 0));
        address.setText("...");
        jPanel3.add(address);
        address.setBounds(350, 80, 270, 17);

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel39.setText("Current Channellings :");
        jPanel3.add(jLabel39);
        jLabel39.setBounds(0, 350, 200, 17);

        first.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        first.setForeground(new java.awt.Color(153, 0, 0));
        first.setText("...");
        jPanel3.add(first);
        first.setBounds(120, 80, 80, 17);

        last.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        last.setForeground(new java.awt.Color(153, 0, 0));
        last.setText("...");
        jPanel3.add(last);
        last.setBounds(210, 80, 120, 17);

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
        specialA.setBounds(120, 110, 130, 30);

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel40.setText("Specialized Area");
        jPanel3.add(jLabel40);
        jLabel40.setBounds(0, 120, 120, 17);

        Table.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Available Date", "Available Time"
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5, Short.MAX_VALUE))
        );

        jPanel3.add(list);
        list.setBounds(120, 160, 400, 100);

        Combotime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CombotimeActionPerformed(evt);
            }
        });
        jPanel3.add(Combotime);
        Combotime.setBounds(390, 280, 110, 30);

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel37.setText("Select Date");
        jPanel3.add(jLabel37);
        jLabel37.setBounds(0, 290, 80, 17);

        jButton3.setText("Enter");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3);
        jButton3.setBounds(200, 40, 90, 30);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("New Appointment");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 623, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(282, 282, 282)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(250, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(46, 46, 46))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chNoActionPerformed
        // TODO add your handling code here:
        chNo.setText(Integer.toString(channelNo));

    }//GEN-LAST:event_chNoActionPerformed

    private void NewbuttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewbuttActionPerformed

        this.setVisible(false);
        AppointmentHandlerJDialog refresh = new AppointmentHandlerJDialog(null, true, this.obj);
        refresh.setLocation(0, 20);
        refresh.setVisible(true);
    }//GEN-LAST:event_NewbuttActionPerformed

    private void PIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PIDActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (Id == -1) {
            JOptionPane.showMessageDialog(new JDialog(), "Please Enter Patient Id");
        } else {
            Patient p2 = new Patient(Id, patient_da, selectedDate, selecteddoctorid);
            channelNo = p2.channel(Id, patient_da, selectedDate, selecteddoctorid, selectedtime);
            System.out.println("ch no: " + channelNo);
            if (channelNo == -1) {
                chNo.setText("");
            } else {
                chNo.setText(Integer.toString(channelNo));
            }
            cleanTable();
            Xdate.setDate(null);
            Combotime.setSelectedItem(null);
            specialA.setSelectedItem(null);
            doc.setSelectedItem(null);

            obj.cleanTable();
            obj.updateTable();
            obj.updateTotal();
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    private void channelnoActionPerformed(java.awt.event.ActionEvent evt) {
//        dateFrom = Xdate.getDate();
//        selectedDate = formatter.format(dateFrom);
//        int thecount = patient_da.channelcountbydate(selectedDate, selecteddoctorid);
//        nochn.setText(Integer.toString(thecount));
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        AppointmentEditorJdialog del = new AppointmentEditorJdialog(null, true, this.obj);
        del.setLocation(0, 20);
        del.setVisible(true);

    }//GEN-LAST:event_jButton2ActionPerformed
    private void appointmentActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        ArrayList<String> array = new ArrayList<>();
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
    private void XdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XdateActionPerformed
        dateFrom = Xdate.getDate();
        selectedDate = formatter.format(dateFrom);
        int thecount = patient_da.channelcountbydate(selectedDate, selecteddoctorid, selectedtime);
        System.out.println(selectedDate);
        nochn.setText(Integer.toString(thecount));
        



  

    }//GEN-LAST:event_XdateActionPerformed


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
            deletemodeltime();
            addmodeltime();

        } catch (NullPointerException e) {
            System.out.println("methanai awla");
            deletemodeltime();
            cleanTable();

        }
    }//GEN-LAST:event_docActionPerformed

    private void specialAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_specialAActionPerformed
        // TODO add your handling code here:
        selectedsarea = (String) specialA.getSelectedItem();
        System.out.println(selectedsarea);
        deletemodel();
        addmodel();

    }//GEN-LAST:event_specialAActionPerformed

    private void CombotimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CombotimeActionPerformed
        selectedtime = (String) Combotime.getSelectedItem();
        System.out.println(selectedtime);
        int thecount = patient_da.channelcountbydate(selectedDate, selecteddoctorid, selectedtime);
        System.out.println(selectedDate);
        nochn.setText(Integer.toString(thecount));

    }//GEN-LAST:event_CombotimeActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        ArrayList<String> array = new ArrayList<>();
        this.Id = Integer.parseInt(PID.getText());
        array = patient_da.getAllData(Id);
        try {
            first.setText(array.get(0));
            last.setText(array.get(1));
            address.setText(array.get(4));
        } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(new JDialog(), "No such an entry");
        }

    }//GEN-LAST:event_jButton3ActionPerformed
    public void addmodel() {

        ArrayList<String> doctors = new ArrayList<>();
        doctors = doctor_da.getnamestospecificarea(selectedsarea);
        ddcount = doctor_da.getselecteddDoctorCount(selectedsarea);
        int i;
        String temp;
        for (i = 0; i < ddcount; i++) {

            doc.addItem(doctors.get(i));
        }
    }

    public void deletemodel() {
        doc.removeAllItems();
    }

    public void addmodeltime() {

        ArrayList<String> dates = new ArrayList<>();
        int datecount;
        dates = doctor_da.getdates(selecteddoctorid);
        datecount = dates.size();
        System.out.println(datecount);
        int i;
        for (i = 0; i < datecount; i++) {

            Combotime.addItem(dates.get(i));
        }
    }

    public void deletemodeltime() {
        Combotime.removeAllItems();
    }

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AppointmentHandlerJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AppointmentHandlerJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AppointmentHandlerJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppointmentHandlerJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AppointmentHandlerJDialog dialog = new AppointmentHandlerJDialog(new javax.swing.JFrame(), true, new FrontDeskJFrame());
                dialog.setResizable(false);
                dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                dialog.setVisible(true);

            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox Combotime;
    private javax.swing.JButton Newbutt;
    private javax.swing.JTextField PID;
    private javax.swing.JTable Table;
    private org.jdesktop.swingx.JXDatePicker Xdate;
    private javax.swing.JLabel address;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.JTextField chNo;
    private javax.swing.JComboBox doc;
    private javax.swing.JLabel first;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel last;
    private javax.swing.JPanel list;
    private javax.swing.JLabel nochn;
    private javax.swing.JComboBox specialA;
    // End of variables declaration//GEN-END:variables
Component[] comsPanel3;

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == PID) {
            appointmentActionPerformed(event);
        }

    }

    public void updateTable() {

        Doctor d = new Doctor(selecteddoctorid);
        channelavalability = d.getList(doctor_da, selecteddoctorid);
        int count = d.getavailablecount(doctor_da, selecteddoctorid);
        int j = 0;
        for (int i = 0; i < count; i++) {
            model.addRow(new Object[]{channelavalability.get(j), channelavalability.get(j + 1)});
            j = j + 2;
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

}
