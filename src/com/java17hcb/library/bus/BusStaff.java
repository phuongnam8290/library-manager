package com.java17hcb.library.bus;

import com.java17hcb.library.dao.DaoStaff;
import com.java17hcb.library.entity.Staff;
import com.java17hcb.library.utils.CurrentStaff;
import java.util.Date;
import java.util.List;

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
    
    public List<Staff> findAllStaff(){
        return DaoStaff.getInstance().findAllStaff();
    }
    
    public static final int PAYMENT_SUCCESS = 1;
    public static final int CURRENT_STAFF_DONT_HAVE_PERMISSION = 0;
    public static final int PAYMENT_LARGER_THAN_FINES = -1;
    public static final int PAYMENT_LIBRARY_CARD_NOT_EXIST = -2;
    public static final int PAYMENT_UNKNOWN_ERROR = -3;
    
    /**
     * Create Fines Receipt for this library card;
     * @param libraryCardId Id for this card
     * @param payment Payment for this card
     * @return 1: Transaction success
     *         0: Current Staff user don't have permission to create Fine Receipt
     *        -1: Payment larger than current Fines
     */
    public int createFinesReceipt(int libraryCardId, long payment){
        if(CurrentStaff.getCurrentStaff().getDivision() != Staff.Division.THU_QUY){
            return CURRENT_STAFF_DONT_HAVE_PERMISSION;
        } else {
            return DaoStaff.getInstance().createFinesReceipt(libraryCardId, payment);
        }
    }
    
    public static final int LIQUIDATE_SUCCESS = 1;
    public static final int LIQUIDATE_BOOK_NOT_EXIST = -1;
    public static final int LIQUIDATE_NOT_ENOUGH_COPY = -2;
    public static final int LIQUIDATE_UNKNOWN_ERROR = -3;
    
    /**
     * Create Liquidate History for this book
     * @param bookId id of this book
     * @param reason Reason to liquidate this book, use value in static class LiquidateHistory.Reason
     * @param copy Number of books need to liquidate
     * @return 1: Liquidate successfully
     *         0: Current Staff user don't have permission to create Liquidate History
     *        -1: Book not exist
     *        -2: Not enough copy
     *        -3: Unknown error
     */
    public int liquidateBook(int bookId, int reason, int copy){
        if(CurrentStaff.getCurrentStaff().getDivision() != Staff.Division.THU_KHO){
            return CURRENT_STAFF_DONT_HAVE_PERMISSION;
        } else {
            return DaoStaff.getInstance().liquidateBook(bookId, reason, copy);
        }
    }
    
    public static final int RECORD_LOST_SUCCESS = 1;
    public static final int RECORD_LOST_BOOK_NOT_EXIST = -1;
    public static final int RECORD_LOST_LIBRARY_CARD_NOT_EXIST = -2;
    public static final int RECORD_LOST_LIBRARY_CARD_NOT_RENT_THIS_BOOK = -3;
    public static final int RECORD_LOST_BOOK_PRICE_LOWER_THAN_FINES_FEE = -4;
    public static final int RECORD_LOST_UNKNOWN_ERROR = -5;
    
    /**
     * Create Lost History for this book and this library card
     * @param libraryCardId id of this library card
     * @param bookId id of this book
     * @param finesFee fines fee this card need to pay
     * @return 1: Record successfully
     *         0: Current Staff user don't have permission to create Liquidate History
     *        -1: Book not exist
     *        -2: Library card not exist
     *        -3: Book price lower than fines fee
     *        -4: Unknown error
     */
    public int recordLostBook(int libraryCardId, int bookId, long finesFee){
        if(CurrentStaff.getCurrentStaff().getDivision() != Staff.Division.THU_THU){
            return CURRENT_STAFF_DONT_HAVE_PERMISSION;
        } else {
            return DaoStaff.getInstance().recordLostBook(libraryCardId, bookId, finesFee);
        }
    }
    
    public boolean updateStaff(Staff modifiedStaff){
        return DaoStaff.getInstance().updateStaff(modifiedStaff);
    }
}
