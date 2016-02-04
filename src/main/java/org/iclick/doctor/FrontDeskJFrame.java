package org.iclick.doctor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * @author Prashan
 */
public class FrontDeskJFrame extends javax.swing.JFrame {

	public static String dateNow;
	/**
	 * Creates new form FrontDeskJFrame
	 */
	private DefaultTableModel model;
	private DefaultTableModel model2;
	private Calendar currentDate;
	private int total = 0;
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.ButtonGroup buttonGroup1;
	private javax.swing.ButtonGroup buttonGroup2;
	private javax.swing.ButtonGroup buttonGroup3;
	private javax.swing.JTextField conpass;
	private javax.swing.JTextField cpass;
	private javax.swing.JTable doctorlistTable;
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton5;
	private javax.swing.JButton jButton7;
	private javax.swing.JButton jButton8;
	private javax.swing.JButton jButton9;
	private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
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
	private javax.swing.JMenuItem jMenuItem7;
	private javax.swing.JPopupMenu jPopupMenu1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTextField jTextField2;
	private javax.swing.JTextField npass;
	private javax.swing.JTable patientListTable;
	public FrontDeskJFrame() {
		initComponents();
		jLabel2.setVisible(false);
		jLabel7.setVisible(false);
		jLabel8.setVisible(false);
		conpass.setVisible(false);
		npass.setVisible(false);
		cpass.setVisible(false);
		jButton1.setVisible(false);

		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					cleanTable();
					updateTable();
				}
			}
		});
		TableColumn col1 = patientListTable.getColumnModel().getColumn(0);
		col1.setPreferredWidth(80);
		TableColumn col2 = patientListTable.getColumnModel().getColumn(1);
		col2.setPreferredWidth(80);
		model = (DefaultTableModel) patientListTable.getModel();
		model2 = (DefaultTableModel) doctorlistTable.getModel();

		currentDate = Calendar.getInstance(); //Get the current date
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); //format it as per your requirement
		dateNow = formatter.format(currentDate.getTime());
		patientListTable.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		updateTable();
		updateTotal();
	}

	public static void main(String args[]) {
		FrontDeskJFrame the = new FrontDeskJFrame();
		the.setResizable(true);
		the.setVisible(true);
        /* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6)



         is not available, stay with the default look and feel.
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
			java.util.logging.Logger.getLogger(FrontDeskJFrame.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(FrontDeskJFrame.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(FrontDeskJFrame.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(FrontDeskJFrame.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
		}
		//</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FrontDeskJFrame().setVisible(true);
//            }
//        });
	}

	public void updateTable() {
		System.out.println("updating");
		ArrayList<String> doctorList = new ArrayList<String>();
		Doctor doctor = new Doctor(Doctor_DA.getConnection(), dateNow);
		doctorList = doctor.getTodayDoctorList(DB.finalDay);

		ArrayList<String> tempChannelList = new ArrayList<String>();
		ArrayList<String> channelPatientId = new ArrayList<String>();

		ArrayList<String> patientName = new ArrayList<String>();

		Patient chnlList = new Patient(Patient_DA.getConnection(), dateNow);
		//   tempChannelList = chnlList.getIDList(Patient_DA.getConnection(), dateNow);

		total = 0;
		for (int i = 0; i < tempChannelList.size(); i++) {

			total += 1;
			channelPatientId.add(tempChannelList.get(i));
			Patient p = new Patient(Integer.parseInt(channelPatientId.get(i)));
			patientName.add(p.getPatientRealName(p.getId()));

		}

		int i = 0;
		for (int k = 0; k < (doctorList.size()) / 4; k++) {
			model2.addRow(new Object[]{doctorList.get(i), "      " + doctorList.get(i + 1), doctorList.get(i + 2), "  " +

					"     " + doctorList.get(i + 3)});
			i = i + 4;
		}
	}

	public void cleanTable() {

		int rows = model.getRowCount();
		int rows2 = model2.getRowCount();
		System.out.println("rows" + rows);
		rows = rows - 1;
		rows2 = rows2 - 1;

		for (int i = rows; i >= 0; i--) {
			model.removeRow(i);
		}

		for (int i = rows2; i >= 0; i--) {
			model2.removeRow(i);
		}

	}

	public void cleanTable2() {

		int rows2 = model.getRowCount();
		rows2 = rows2 - 1;

		for (int i = rows2; i >= 0; i--) {
			model.removeRow(i);
		}

	}

	public void updateTotal() {

	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jMenuItem1 = new javax.swing.JMenuItem();
		jMenu1 = new javax.swing.JMenu();
		jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
		jPopupMenu1 = new javax.swing.JPopupMenu();
		jTextField2 = new javax.swing.JTextField();
		buttonGroup1 = new javax.swing.ButtonGroup();
		buttonGroup2 = new javax.swing.ButtonGroup();
		buttonGroup3 = new javax.swing.ButtonGroup();
		jLabel1 = new javax.swing.JLabel();
		jMenu4 = new javax.swing.JMenu();
		jMenuItem2 = new javax.swing.JMenuItem();
		jMenuItem5 = new javax.swing.JMenuItem();
		jLabel3 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		patientListTable = new javax.swing.JTable();
		jButton2 = new javax.swing.JButton();
		jButton5 = new javax.swing.JButton();
		jButton7 = new javax.swing.JButton();
		jButton8 = new javax.swing.JButton();
		jButton9 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		jScrollPane2 = new javax.swing.JScrollPane();
		doctorlistTable = new javax.swing.JTable();
		jLabel4 = new javax.swing.JLabel();
		cpass = new javax.swing.JTextField();
		jLabel2 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		jLabel8 = new javax.swing.JLabel();
		conpass = new javax.swing.JTextField();
		npass = new javax.swing.JTextField();
		jButton1 = new javax.swing.JButton();
		jMenuBar1 = new javax.swing.JMenuBar();
		jMenu2 = new javax.swing.JMenu();
		jMenuItem7 = new javax.swing.JMenuItem();
		jMenuItem3 = new javax.swing.JMenuItem();
		jMenuItem4 = new javax.swing.JMenuItem();
		jMenu3 = new javax.swing.JMenu();
		jMenuItem6 = new javax.swing.JMenuItem();

		jMenuItem1.setText("jMenuItem1");

		jMenu1.setText("jMenu1");

		jCheckBoxMenuItem1.setSelected(true);
		jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

		jTextField2.setText("jTextField2");

		jLabel1.setText("jLabel1");

		jMenu4.setText("jMenu4");

		jMenuItem2.setText("jMenuItem2");

		jMenuItem5.setText("jMenuItem5");

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setPreferredSize(new java.awt.Dimension(1470, 700));
		setResizable(false);

		jLabel3.setFont(new java.awt.Font("Calisto MT", 1, 26)); // NOI18N
		jLabel3.setText("Today Available Doctors");

		patientListTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		patientListTable.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][]{

				},
				new String[]{
						"CH:Number", "Patient ID", "Patient"
				}
		));
		jScrollPane1.setViewportView(patientListTable);
		if (patientListTable.getColumnModel().getColumnCount() > 0) {
			patientListTable.getColumnModel().getColumn(0).setMinWidth(20);
			patientListTable.getColumnModel().getColumn(0).setPreferredWidth(70);
			patientListTable.getColumnModel().getColumn(0).setMaxWidth(150);
			patientListTable.getColumnModel().getColumn(1).setMinWidth(50);
			patientListTable.getColumnModel().getColumn(1).setPreferredWidth(150);
			patientListTable.getColumnModel().getColumn(1).setMaxWidth(250);
		}

		jButton2.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
		jButton2.setText("Register A Patient");
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		jButton5.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
		jButton5.setText("Appointment Handler");
		jButton5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton5ActionPerformed(evt);
			}
		});

		jButton7.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
		jButton7.setText("Search");
		jButton7.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton7ActionPerformed(evt);
			}
		});

		jButton8.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
		jButton8.setText("Edit Patient Details");
		jButton8.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton8ActionPerformed(evt);
			}
		});

		jButton9.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
		jButton9.setText("Search Doctor");
		jButton9.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton9ActionPerformed(evt);
			}
		});

		jButton3.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
		jButton3.setText("Register A Doctor");
		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});

		doctorlistTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		doctorlistTable.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][]{

				},
				new String[]{
						"Doctor ID", "Doctor First Name", "Doctor Last Name", "Specialized Area"
				}
		));
		doctorlistTable.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				doctorlistTableMouseClicked(evt);
			}
		});
		jScrollPane2.setViewportView(doctorlistTable);

		jLabel4.setFont(new java.awt.Font("Calisto MT", 1, 26)); // NOI18N
		jLabel4.setText("Current Patient Queue");

		cpass.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

		jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		jLabel2.setText("New Password");

		jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		jLabel7.setText("Current Password");

		jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		jLabel8.setText("Confirm Password");

		conpass.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

		npass.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

		jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jButton1.setText("Submit");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jMenu2.setText("File");
		jMenu2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

		jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
		jMenuItem7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
		jMenuItem7.setText("Refresh");
		jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem7ActionPerformed(evt);
			}
		});
		jMenu2.add(jMenuItem7);

		jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event
                .InputEvent.CTRL_MASK));
		jMenuItem3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
		jMenuItem3.setText("Logout");
		jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem3ActionPerformed(evt);
			}
		});
		jMenu2.add(jMenuItem3);

		jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event
                .InputEvent.CTRL_MASK));
		jMenuItem4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
		jMenuItem4.setText("Exit");
		jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem4ActionPerformed(evt);
			}
		});
		jMenu2.add(jMenuItem4);

		jMenuBar1.add(jMenu2);

		jMenu3.setText("Edit");
		jMenu3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
		jMenu3.setIconTextGap(6);

		jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event
                .InputEvent.CTRL_MASK));
		jMenuItem6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
		jMenuItem6.setText("Change Password");
		jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem6ActionPerformed(evt);
			}
		});
		jMenu3.add(jMenuItem6);

		jMenuBar1.add(jMenu3);

		setJMenuBar(jMenuBar1);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addGap(70, 70, 70)
												.addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        440, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(50, 50, 50)
												.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        360, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment
                                                        .LEADING)
														.addGroup(layout.createSequentialGroup()
																.addGap(109, 109, 109)
																.addComponent(jButton1))
														.addGroup(layout.createSequentialGroup()
																.addGap(23, 23, 23)
																.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																		.addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
																				.addGroup(layout.createSequentialGroup()
																						.addComponent(jLabel8)
																						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																						.addComponent(conpass, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
																				.addGroup(layout.createSequentialGroup()
																						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																								.addComponent(jLabel7)
																								.addComponent(jLabel2))
																						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																								.addComponent(cpass, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addComponent(npass, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
										.addComponent(jLabel6)
										.addGroup(layout.createSequentialGroup()
												.addGap(140, 140, 140)
												.addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 343,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(117, 117, 117)
												.addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 300,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(30, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addComponent(jLabel6)
								.addGap(100, 100, 100)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing
                                                .GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing
                                                .GroupLayout.PREFERRED_SIZE))
								.addGap(30, 30, 30)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment
                                                        .BASELINE)
														.addComponent(cpass, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing
                                                                        .GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel7))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment
                                                        .BASELINE)
														.addComponent(jLabel2)
														.addComponent(npass, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing
                                                                        .GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment
                                                        .BASELINE)
														.addComponent(jLabel8)
														.addComponent(conpass, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing
                                                                        .GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(jButton1)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(20, 20, 20)
												.addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(20, 20, 20)
												.addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(20, 20, 20)
												.addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(20, 20, 20)
												.addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(20, 20, 20)
												.addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
										.addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax
                                                .swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax
                                                .swing.GroupLayout.PREFERRED_SIZE))
								.addContainerGap())
		);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
		// TODO add your handling code here:
		close();
		RegisterAPatientJDialog reg = new RegisterAPatientJDialog(this, rootPaneCheckingEnabled);
		reg.setLocation(0, 20);
		reg.setVisible(true);

	}//GEN-LAST:event_jButton2ActionPerformed

	private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
		// TODO add your handling code here:
		AppointmentHandlerJDialog ap = new AppointmentHandlerJDialog(this, true, this);
		ap.setLocation(0, 20);
		ap.setVisible(true);

	}//GEN-LAST:event_jButton5ActionPerformed

	private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
		// TODO add your handling code here:
		SearchConfirmJFrame search = new SearchConfirmJFrame();
		search.setLocation(0, 20);
		search.setVisible(true);
	}//GEN-LAST:event_jButton7ActionPerformed

	private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
		// TODO add your handling code here:
		EditPatientJDialog ap = new EditPatientJDialog(this, true);
		ap.setLocation(0, 20);
		ap.setVisible(true);
		ap.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}//GEN-LAST:event_jButton8ActionPerformed

	private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
		// TODO add your handling code here:
		doctorsearchJFrame ds = new doctorsearchJFrame();
		ds.setVisible(true);
	}//GEN-LAST:event_jButton9ActionPerformed

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
		// TODO add your handling code here:
		DoctorRegisterJFrame reg = new DoctorRegisterJFrame();

		reg.setVisible(true);
	}//GEN-LAST:event_jButton3ActionPerformed

	private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
		// TODO add your handling code here:
		close();
		dispose();
		LoginJFrame logout = new LoginJFrame();
		logout.setLocationRelativeTo(null);
		logout.setVisible(true);
	}//GEN-LAST:event_jMenuItem3ActionPerformed

	private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
		// TODO add your handling code here:
		close();
		dispose();
	}//GEN-LAST:event_jMenuItem4ActionPerformed

	private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed

		jLabel3.setVisible(false);
		jLabel4.setVisible(false);
		jButton2.setVisible(false);
		jButton3.setVisible(false);
		jButton5.setVisible(false);
		jButton7.setVisible(false);
		jButton8.setVisible(false);
		jButton3.setVisible(false);
		jButton9.setVisible(false);

		jLabel2.setVisible(true);
		jLabel7.setVisible(true);
		jLabel8.setVisible(true);
		conpass.setVisible(true);
		npass.setVisible(true);
		cpass.setVisible(true);
		jButton1.setVisible(true);

	}//GEN-LAST:event_jMenuItem6ActionPerformed

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
		// TODO add your handling code here:
		String currentpass = cpass.getText();
		String newpass = npass.getText();
		String confirmpass = conpass.getText();

		if (currentpass.isEmpty()) {
			JOptionPane.showMessageDialog(new JDialog(), "Enter current password and retry");
		} else if (newpass.isEmpty()) {
			JOptionPane.showMessageDialog(new JDialog(), "Enter new password and retry");
		} else if (confirmpass.isEmpty()) {
			JOptionPane.showMessageDialog(new JDialog(), "Enter confirm password and retry");
		} else if (confirmpass.compareTo(newpass) == 1) {
			JOptionPane.showMessageDialog(new JDialog(), "New password and Confirm password is not equal");
		} else if (confirmpass.compareTo(newpass) == 0) {
			User frontdest = new FrontDesk();
			String hashedpass = Encrypt.cryptWithMD5(newpass);
			String currenthashedpass = Encrypt.cryptWithMD5(currentpass);
			frontdest.changePassword("FrontDesk", currenthashedpass, hashedpass);

			jLabel3.setVisible(true);
			jLabel4.setVisible(true);
			jButton2.setVisible(true);
			jButton3.setVisible(true);
			jButton5.setVisible(true);
			jButton7.setVisible(true);
			jButton8.setVisible(true);
			jButton3.setVisible(true);
			jButton9.setVisible(true);

			jLabel2.setVisible(false);
			jLabel7.setVisible(false);
			jLabel8.setVisible(false);
			conpass.setVisible(false);
			npass.setVisible(false);
			cpass.setVisible(false);
			jButton1.setVisible(false);

		}
	}//GEN-LAST:event_jButton1ActionPerformed

	private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
		// TODO add your handling code here:
		close();
		dispose();
		FrontDeskJFrame refresh = new FrontDeskJFrame();
		refresh.setExtendedState(refresh.getExtendedState() | refresh.MAXIMIZED_BOTH);
		refresh.setVisible(true);
	}//GEN-LAST:event_jMenuItem7ActionPerformed

	private void doctorlistTableMouseClicked(java.awt.event.MouseEvent evt)
    {//GEN-FIRST:event_doctorlistTableMouseClicked
		// TODO add your handling code here:\
		String did = (String) model2.getValueAt(doctorlistTable.getSelectedRow(), 0);
		int dID = Integer.parseInt(did);
		Doctor d = new Doctor();
		ArrayList<String> list = new ArrayList<String>();
		list = d.fillPatientTable(dID, dateNow);
		cleanTable2();
		int j = 0;
		int i = 1;
		for (int k = 0; k < (list.size() / 3); k++) {
			model.addRow(new Object[]{"        " + i++, "        " + list.get(j), list.get(j + 1), list.get(j + 2)});
			j = j + 3;
		}
	}//GEN-LAST:event_doctorlistTableMouseClicked
	// End of variables declaration//GEN-END:variables

	private void close() {
		WindowEvent winclose = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winclose);
	}

}
