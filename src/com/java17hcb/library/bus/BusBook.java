package com.java17hcb.library.bus;

import com.java17hcb.library.dao.DaoBook;
import com.java17hcb.library.entity.Book;
import com.java17hcb.library.entity.Staff;
import com.java17hcb.library.utils.CurrentStaff;
import java.util.Date;
import java.util.List;

public class BusBook {
    private static BusBook instance;
    
    private BusBook(){}
    
    public static BusBook getInstance(){
        if (instance == null){
            instance = new BusBook();
        }
        
        return instance;
    }
    
    public List<Book> findAllBooks(){
        return DaoBook.getInstance().findAllBooks();
    }
    
    public List<Book> findAllAvailableBooks(){
        return DaoBook.getInstance().findAllAvailableBooks();
    }
    
    public Book findBookById(int id){
        return DaoBook.getInstance().findBookById(id);
    }
    
    public Book findBookByTitile(String title){
        return DaoBook.getInstance().findBookByTitle(title);
    }
    
    public static final int IMPORT_SUCCESS = 1;
    public static final int CURRENT_STAFF_DONT_HAVE_PERMISSION = 0;
    public static final int IMPORT_UNKNOWN_ERROR = -1;
    
    /**
     * Import new book or increase number of books if this book already exist
     * @param title
     * @param type
     * @param author
     * @param publishYear
     * @param publisher
     * @param price
     * @param copies
     * @return 1: Import success
     *         0: Current Staff user don't have permission
     *        -1: Unknown error
     */
    public int importBook(String title, int type, String author, Date publishYear, String publisher, long price, int copies){
        if(CurrentStaff.getCurrentStaff().getDivision() != Staff.Division.THU_KHO){
            return CURRENT_STAFF_DONT_HAVE_PERMISSION;
        }
        
        //TODO: check publish year < 8 year
        Book book = new Book(title, type, author, publishYear, publisher, price, copies);
        return DaoBook.getInstance().importBook(book, copies);
    }

    public boolean updateBook(Book modifiedBook) {
        return DaoBook.getInstance().updateBook(modifiedBook);
    }
}
