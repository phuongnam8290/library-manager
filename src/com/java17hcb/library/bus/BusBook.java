package com.java17hcb.library.bus;

import com.java17hcb.library.dao.DaoBook;
import com.java17hcb.library.entity.Book;
import com.java17hcb.library.entity.Staff;
import com.java17hcb.library.utils.CurrentStaff;
import java.util.Date;

public class BusBook {
    private static BusBook instance;
    
    private BusBook(){}
    
    public static BusBook getInstance(){
        if (instance == null){
            instance = new BusBook();
        }
        
        return instance;
    }
    
    public Book findBookById(int id){
        return DaoBook.getInstance().findBookById(id);
    }
    
    public boolean importBook(String name, int type, String author, Date publishYear, String publisher, long price, int copies){
        if(CurrentStaff.getCurrentStaff().getDivision() != Staff.Division.THU_KHO){
            return false;
        }
        
        //TODO: check publish year < 8 year
        Book book = new Book(name, type, author, publishYear, publisher, price, copies);
        return DaoBook.getInstance().importBook(book, copies);
    }
}
