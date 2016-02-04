/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.iclick.doctor;

import java.util.ArrayList;

/**
 *
 * @author Suchira
 */
public class MessageHandler {
    private Message aMessage = new Message();
    
    public ArrayList<String>[] getInbox(){
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<String> ids = new ArrayList<String>();
        ArrayList[] array= aMessage.getSendersList();
        names = array[1];
        ids = array[0];
        
        return array;
    }
    
    public ArrayList[] getMessages(int id, int did){
        ArrayList[] array = aMessage.getMessages(id,did);
        return array;
    }
    
    public void sendReply(int messageId, String message){
        aMessage.sendReply(messageId, message);
    }
    public void sendNew(int patientId, String message, int did){
        aMessage.sendNew(patientId, message, did);
    }
}
