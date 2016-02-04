/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iclick.doctor;

import org.iclick.doctor.beans.User;
import org.iclick.doctor.dbaccess.DbConnectionManager;
import org.iclick.doctor.dbaccess.PatientDataAccessManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Suchira
 */
public class FrontDesk extends User {

    private static DbConnectionManager dbCon = DbConnectionManager.getInstance();
    private Statement st;
    private ResultSet rs;

    public FrontDesk(String name, int id, String paswrd) {
        super(name, id, paswrd);
        //ArrayList
    }

    public FrontDesk() {

    }

    public void registerANewPatient(String name, int iD) {

    }

    public void showChannelList() {

    }

    public void changePassword(String name, String oldpass, String newpass) {

        try {
            st = dbCon.getInstance().createStatement();
            String dbpass = null;
            String query = "UPDATE `user_login` SET `Password`='" + newpass + "' WHERE User_type ='" + name + "'";
            String query2 = "SELECT Password FROM `user_login` WHERE User_type ='" + name + "' ";

            rs = st.executeQuery(query2);
            System.out.println("Records from the database");
            while (rs.next()) {
                dbpass = rs.getString("Password");
            }
            if (dbpass.equals(oldpass)) {
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(new JDialog(), "Password Updated!");
            } else {

                st.executeUpdate(query);
                JOptionPane.showMessageDialog(new JDialog(), "Enter the correct Current password");
            }

        } catch (SQLException ex) {
            Logger.getLogger(PatientDataAccessManager.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

}
