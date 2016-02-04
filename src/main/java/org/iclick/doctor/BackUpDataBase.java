/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.iclick.doctor;

import java.io.IOException;

/**
 *
 * @author Suchira
 */
public class BackUpDataBase {

    public boolean getBackup(String dbName,String dbUserName, String dbPassword, String path) throws IOException, InterruptedException {

        String executeCmd =  "C:\\xampp\\mysql\\bin\\mysqldump.exe -u" + dbUserName + " " + dbName + " -r " + path ;
//        System.out.println("Path - " + path);
        Process runtimeProcess;
    try {


        runtimeProcess = Runtime.getRuntime().exec(executeCmd);

        int processComplete = runtimeProcess.waitFor();

        if (processComplete == 0) {
            System.out.println("Backup created successfully");
            return true;
        } else {
            System.out.println("Could not create the backup");
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return false;
    }

    public boolean restoreBackup(String dbName,String dbUserName, String dbPassword, String path) throws IOException, InterruptedException {
        
        String[] executeCmd = new String[]{"mysql", "--user=" + dbUserName, "--password=" + dbPassword, dbName,"-e", " source "+path};
        Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
        int processComplete = runtimeProcess.waitFor();

        if (processComplete == 0){
            return true;
        }
        else{
            return false;
        }
    }

}