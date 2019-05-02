package com.java17hcb.library.bus;

import com.java17hcb.library.dao.DaoLibraryCard;
import com.java17hcb.library.entity.LibraryCard;
import com.java17hcb.library.entity.Staff;
import com.java17hcb.library.utils.CurrentStaff;
import java.util.Calendar;
import java.util.Date;

public class BusLibraryCard {
    private static BusLibraryCard instance;
    
    private BusLibraryCard(){}
    
    public static BusLibraryCard getInstance(){
        if (instance == null){
            instance = new BusLibraryCard();
        }
        
        return instance;
    }
    
    public boolean createLibraryCard(String fullName, int type, Date dateOfBirth, String address, String email, Date createDate){
        if(CurrentStaff.getCurrentStaff().getDivision() != Staff.Division.THU_THU){
            return false;
        }
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(createDate);
        cal.add(Calendar.MONTH, +6);
        Date expireDate = cal.getTime();
        
        LibraryCard card = new LibraryCard(fullName, type, dateOfBirth, address, email, createDate, expireDate, 0);
        return DaoLibraryCard.getInstance().createLibraryCard(card);
    }
}
