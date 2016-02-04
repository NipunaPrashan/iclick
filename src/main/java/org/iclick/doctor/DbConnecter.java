
package org.iclick.doctor;
import java.sql.*;


public class DbConnecter {
    private static DbConnecter singleton = null;
    private Connection con;

  public synchronized static DbConnecter getConnection(){
        if(singleton == null){
            singleton = new DbConnecter();
            return singleton;
        }
        else{
            return singleton;
        }
    }
    
    private DbConnecter(){
        
        try{
          //  Class.forName("com.mysql.jdbc.Driver");
            
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbproject","root","");
           //  con = DriverManager.getConnection("jdbc:mysql://sql5.freemysqlhosting.net:3306/sql561648","sql561648","mT2%uA9*");
           //  con = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/iclick92","iclick","sirisorrybro");
           // st = con.createStatement();
            
            
        }
        catch(Exception ex){
            System.out.println("Error "+ex);
        }
    }
    
   

    Statement createStatement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } 

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }


    
}

