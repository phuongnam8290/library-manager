package com.java17hcb.library.utils;

import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class FormVerifier {
    public static void verifyInput(JButton btnConfirm, String inputString, JLabel err, String textFieldName) {
        if(inputString.trim().isEmpty()){
            err.setText(textFieldName + " cannot be empty");
            btnConfirm.setEnabled(false);
        } else if(inputString.length() <= 5){
            err.setText(textFieldName + " must greater than 5 character");
            btnConfirm.setEnabled(false);
        } else{
            err.setText("");
        }
    }
    
    public static void verifyEmail(JButton btnConfirm, JTextField input, JLabel err){
        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        String inputString = input.getText();
        if(!inputString.matches(emailPattern)){
            err.setText("This e-mail address is not valid");
            btnConfirm.setEnabled(false);
        } else {
            err.setText("");
        }
    }
    
    public static boolean enableBtnConfirm(List<String> errMessages) {       
        System.out.println("ErrMessagesList");
        for(String err : errMessages){
            System.out.println("\'" + err + "\'");
            if(!err.isEmpty()){
                return false;
            }
        }
        return true;
    } 
}
