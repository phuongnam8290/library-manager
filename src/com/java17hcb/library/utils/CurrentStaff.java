package com.java17hcb.library.utils;

import com.java17hcb.library.entity.Staff;

public class CurrentStaff {
    private static Staff currentStaff;
    
    private CurrentStaff(){}
    
    public static Staff getCurrentStaff(){
        return currentStaff;
    }
    
    public static void setCurrentStaff(Staff staff){
        currentStaff = staff;
    }
}
