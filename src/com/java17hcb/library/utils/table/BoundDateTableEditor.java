package com.java17hcb.library.utils.table;

import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.tableeditors.DateTableEditor;
import java.time.LocalDate;

public class BoundDateTableEditor extends DateTableEditor{
    public static int TYPE_CARD_DOB = 0;
    public static int TYPE_CARD_CREATE = 1;
    public static int TYPE_CARD_EXPIRE = 2;
    
    public BoundDateTableEditor(int type){
        super();
        DatePickerSettings settings = getDatePicker().getSettings();
        if(type == TYPE_CARD_DOB){
            LocalDate today = LocalDate.now();
            settings.setDateRangeLimits(today.minusYears(55), today.minusYears(18));
        }
        settings.setFormatForDatesCommonEra("dd-MM-yyyy");
        settings.setAllowKeyboardEditing(false);
    }
}
