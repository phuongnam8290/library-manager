package com.java17hcb.library.utils;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class FormVerifier {
    public static void verifyInput(JButton btnConfirm, JTextField input, JLabel err, String textFieldName) {
        String inputString = input.getText();
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
    
    public static boolean enableBtnConfirm(List<String> errMessages) {        
        for(String err : errMessages){
            if(!err.isEmpty()){
                return false;
            }
        }
        return true;
    } 
}
