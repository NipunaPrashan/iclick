package org.iclick.doctor.dbaccess;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author nipuna
 */
public class UserDataAccessManager {

    private static DbConnectionManager dbCon = DbConnectionManager.getInstance();
    private Statement st;
    private ResultSet rs, rs1;
    private static PrescriptionDataAccessManager singleton = null;

    public String getType(String username) {
        String type = new String();
        try {
            st = dbCon.getInstance().createStatement();
            String query = "SELECT User_type FROM `user_login` WHERE Username = '"+username+"'";
            rs = st.executeQuery(query);
            System.out.println("Records from the database");
            while (rs.next()) {
                String name = rs.getString("User_type");
                type = name;

            }

        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        return type;

    }

}
