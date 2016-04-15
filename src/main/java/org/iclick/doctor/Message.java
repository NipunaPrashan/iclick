/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.iclick.doctor;

import org.iclick.doctor.dbaccess.DbConnectionManager;
import org.iclick.doctor.dbaccess.MessageDataAccessManager;
import org.iclick.doctor.frontend.jFrames.DoctorJFrame;

import java.util.ArrayList;

/**
 *
 * @author PRAVINDA PERERA
 */
public class Message {
    private String patientName;
    private int patientId;
    private String message;
    private String reply;
    private MessageDataAccessManager msg = MessageDataAccessManager.getConnection(DbConnectionManager.getInstance());
  
    public ArrayList<String>[] getSendersList(){
        ArrayList[] list;
        list = msg.nameNidList(DoctorJFrame.getDoctorId());
        return list;
    }
    
    //This method return both recieved messsages and cooresponding replies...
    public ArrayList[] getMessages(int id,int did){
        ArrayList[] list = msg.getRecievedMessages(id,did);
        return list;
    }
    
    //Send a reply
    public void sendReply(int messageId, String message){
        msg.reply(messageId, message);
    }
    
    public void sendNew(int patientId, String message, int did){
        msg.send(patientId, message, did);
    }
    public String getHeader(int mid){
       return msg.getMessageHeader(mid);
    }
}
