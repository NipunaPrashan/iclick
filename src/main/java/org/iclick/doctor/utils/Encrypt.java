package org.iclick.doctor.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Encrypt {

    private static MessageDigest md;

//   public static String cryptWithMD5(String pass){
//    try {
//        md = MessageDigest.getInstance("MD5");
//        byte[] passBytes = pass.getBytes();
//        md.reset();
//        byte[] digested = md.digest(passBytes);
//        StringBuffer sb = new StringBuffer();
//        for(int i=0;i<digested.length;i++){
//            sb.append(Integer.toHexString(0xff & digested[i]));
//        }
//        return sb.toString();
//    } catch (NoSuchAlgorithmException ex) {
//        Logger.getLogger(Encrypt.class.getName()).log(Level.SEVERE, null, ex);
//    }
//        return null;
//   }
    public static String cryptWithMD5(String string) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(string.getBytes());
            byte[] digest = md5.digest();

            string = byteArrToHexString(digest);
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        }

        return string;
    }

    private static String byteArrToHexString(byte[] bArr) {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < bArr.length; i++) {
            int unsigned = bArr[i] & 0xff;
            if (unsigned < 0x10) {
                sb.append("0");
            }
            sb.append(Integer.toHexString((unsigned)));
        }

        return sb.toString();
    }

}
