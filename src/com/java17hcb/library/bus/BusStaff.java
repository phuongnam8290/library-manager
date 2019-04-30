package com.java17hcb.library.bus;

import com.java17hcb.library.dao.DaoStaff;
import com.java17hcb.library.entity.Staff;
import java.util.Date;

public class BusStaff {
    private static BusStaff instance;
    
    private BusStaff(){}
    
    public static BusStaff getInstance(){
        if(instance == null){
            instance = new BusStaff();
        }
        return instance;
    }
    
    /**
     * Login with username and password
     * @param username
     * @param password
     * @return If recored exist in db, return Staff object match with username & password.
     *         Return null if not found.
     */
    public Staff login(String username, String password){
        return DaoStaff.getInstance().login(username, password);
    }
    
    /**
     * Create new staff object from parameters and save to db
     * @param username staff's username
     * @param password staff's password
     * @param fullName staff's full name
     * @param dateOfBirth staff's date of birth. Format: "dd/MM/yyyy"
     * @param diploma staff's diploma. Use constants from Staff.Diploma
     * @param position staff's position. Use constants from Staff.Position
     * @param division staff's division. Use constants from Staff.Division
     * @param address staff's address
     * @param phone staff's phone
     * @return message to inform save status
     */
    public String createStaff(String username, String password, String fullName, Date dateOfBirth, 
                            int diploma, int position, int division, String address,
                            String phone){
        
        if(DaoStaff.getInstance().isExist(username)){
            return "This account already exist. Please choose another username";
        } else {
            Staff staff = new Staff(username, password, fullName, dateOfBirth, 
                                    diploma, position, division, address, phone);       
            DaoStaff.getInstance().createStaff(staff);
            
            return "Account created!";
        }
    }
}
