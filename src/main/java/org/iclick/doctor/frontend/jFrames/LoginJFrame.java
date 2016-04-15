package org.iclick.doctor.frontend.jFrames;

import org.iclick.doctor.dbaccess.DbConnectionManager;
import org.iclick.doctor.utils.Encrypt;
import org.iclick.doctor.dbaccess.UserDataAccessManager;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.*;
import javax.swing.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Suchira
 */
public class LoginJFrame extends javax.swing.JFrame implements ActionListener {

    PreparedStatement preparedStatement = null;
    ResultSet rs = null;
    DbConnectionManager connection;
    private int userid;

    public LoginJFrame() {
        initComponents();
        user_name.addActionListener(this);
        Password.addActionListener(this);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        user_name = new javax.swing.JTextField();
        login = new javax.swing.JButton();
        Password = new javax.swing.JPasswordField();

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/iMAGE2.jpg"))); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(560, 250));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Username");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 140, 40));

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel2.setText("Password");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 140, 40));

        user_name.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        user_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user_nameActionPerformed(evt);
            }
        });
        getContentPane().add(user_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 150, 30));

        login.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        login.setText("Login");
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });
        getContentPane().add(login, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, 100, 30));

        Password.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        Password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasswordActionPerformed(evt);
            }
        });
        getContentPane().add(Password, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, 150, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        // TODO add your handling code here:
        String K = Encrypt.cryptWithMD5(Password.getText());
        String query = "Select * From user_login Where Username =? and Password=?";
        try {
            DbConnectionManager.getInstance().getDbConnection().prepareStatement(query);
            preparedStatement.setString(1, user_name.getText());
            /*--------------------------------------------------------*/
            preparedStatement.setString(2, K);//this should be corrected to K
            //System.out.println(Password.getText());
            
           

            String username = user_name.getText();
            UserDataAccessManager userda = new UserDataAccessManager();
            String getusertype = userda.getType(username);
            String userType = getusertype;
            System.out.println(userType);
            String pass = K;
            try {
                rs = preparedStatement.executeQuery();
               // System.out.println(rs);
            } catch (SQLSyntaxErrorException e) {
                System.out.println("Mu horek methanata adala na..");
            }

            if (rs.next()) {
                System.out.println("loksda");
                userid = rs.getInt("User_id");
                System.out.println("user ID "+ userid);
                System.out.println("user Type "+ userType);
                
                if (userType.compareTo("Doctor")==0) {
                    close();
                    DoctorJFrame doc = new DoctorJFrame();
                    doc.setDoctorId(userid);
                    doc.addmodeltime();
                    doc.setLocationRelativeTo(null);
                    doc.setVisible(true);
                    doc.setExtendedState(doc.getExtendedState() | doc.MAXIMIZED_BOTH);

//                  
                } else if (userType.compareTo("FrontDesk")==0) {
                    System.out.println("awa");
                    close();
                    FrontDeskJFrame FD = new FrontDeskJFrame();
                    FD.setLocationRelativeTo(null);
                    FD.setVisible(true);
                    FD.setExtendedState(FD.getExtendedState() | FD.MAXIMIZED_BOTH);

                } else if (userType.compareTo("Attendant")==0) {
                    close();
                    AttendentsJFrame FD = new AttendentsJFrame();
                    FD.setLocationRelativeTo(null);
                    FD.setVisible(true);
                    FD.setSize(1024, 800);
                    FD.setExtendedState(FD.getExtendedState() | FD.MAXIMIZED_BOTH);

                }
            } else {
                System.out.println("Check query");
                 JOptionPane.showMessageDialog(new JDialog(), "Username or Password is wrong");
            }
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_loginActionPerformed

    private void user_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_user_nameActionPerformed

    private void PasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PasswordActionPerformed

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
            java.util.logging.Logger.getLogger(LoginJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField Password;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton login;
    private javax.swing.JTextField user_name;
    // End of variables declaration//GEN-END:variables

    private void close() {
        WindowEvent winclose = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winclose);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == Password) {
            System.out.println("awaaaaaaaaaaa");
            loginActionPerformed(event);
        } else if (event.getSource() == user_name) {
            System.out.println("enaga eka gahapn");
            PasswordActionPerformed(event);

        }
    }

}
