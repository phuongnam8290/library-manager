package com.java17hcb.library.view;

import com.java17hcb.library.bus.BusStaff;
import com.java17hcb.library.entity.Staff;
import com.java17hcb.library.utils.CurrentStaff;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class View {
    public static void main(String... args) throws ParseException{
        String username = "lantis02807";
        String password = "Azure020890";
        
        Staff staff = BusStaff.getInstance().login(username, password);
        if(staff != null){
            CurrentStaff.setCurrentStaff(staff);
            
            String dateInString = "22/12/1990"; 
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date dateOfBirth = formatter.parse(dateInString);
            
            String newUsername = "1742038";
            String newPassword = "123";
            String fullName = "Minh Trí";
            int diploma = Staff.Diploma.DAI_HOC;
            int position = Staff.Position.GIAM_DOC;
            int division = Staff.Division.THU_KHO;
            String address = "17 Nguyễn Văn Cừ";
            String phone = "1274263";
            
            String message = BusStaff.getInstance().createStaff(newUsername, newPassword, fullName, 
                                            dateOfBirth, diploma, position, division, 
                                            address, phone);
            System.out.println(message);
        }
    }
}
