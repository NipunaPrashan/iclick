
package org.iclick.doctor;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Prashan
 */
public class PatientHandler {
   Patient_DA patient_da = Patient_DA.getConnection();
    
   public void makenewpatient(String f,int id,String pass, Patient_DA pda){
       
       Patient p1 = new Patient(f,id,pass,pda); 
       p1.sendData();
   }
   
   public ArrayList<String>[] getpatienthistoryanddetails(ArrayList<String> healthCondition){
       
       ArrayList<String> medi = new ArrayList<String>();
        ArrayList<String> history = new ArrayList<String>();
        
        for(int i=0; i< healthCondition.size(); i++){
            if(i%2==0){
                medi.add(healthCondition.get(i));
            }
            else{
                history.add(healthCondition.get(i));
            }
       
        }
        ArrayList[] array = {medi, history};
       return array;
   }
   
   public ArrayList<String> getPatientList(String name, int did){
       return patient_da.patientList(name,did);
   }
           
    
}
