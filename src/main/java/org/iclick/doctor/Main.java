/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iclick.doctor;
import org.iclick.doctor.frontend.jFrames.LoginJFrame;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {

    public static String dateNow;
    public static String finalDay;
    
    public static void main(String[] args) throws ParseException, UnsupportedLookAndFeelException {
 //     UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());
        Calendar currentDate;
        currentDate = Calendar.getInstance(); //Get the current date
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); //format it as per your requirement
        dateNow = formatter.format(currentDate.getTime());

        Date dt1 = formatter.parse(dateNow);
        DateFormat format2 = new SimpleDateFormat("EEEE");
        finalDay = format2.format(dt1);
        System.out.println(finalDay);
        LoginJFrame l = new LoginJFrame();
        l.setLocationRelativeTo(null);
        l.setVisible(true);

    }

}
