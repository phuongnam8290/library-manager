package com.java17hcb.library.bus;

import com.java17hcb.library.dao.DaoLibraryCard;
import com.java17hcb.library.entity.Book;
import com.java17hcb.library.entity.LibraryCard;
import com.java17hcb.library.entity.Staff;
import com.java17hcb.library.utils.CurrentStaff;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BusLibraryCard {
    private static BusLibraryCard instance;
    
    private BusLibraryCard(){}
    
    public static BusLibraryCard getInstance(){
        if (instance == null){
            instance = new BusLibraryCard();
        }
        
        return instance;
    }
    
    public static final int CREATE_SUCCESS = 1;
    public static final int CURRENT_STAFF_DONT_HAVE_PERMISSION = 0;
    public static final int CREATE_UNKNOWN_ERROR = -1;
    
    public int createLibraryCard(String fullName, int type, Date dateOfBirth, String address, String email){
        if(CurrentStaff.getCurrentStaff() == null){
            return CURRENT_STAFF_DONT_HAVE_PERMISSION;
        }
        if(CurrentStaff.getCurrentStaff().getDivision() != Staff.Division.THU_THU){
            return CURRENT_STAFF_DONT_HAVE_PERMISSION;
        }
        
        LibraryCard card = new LibraryCard(fullName, type, dateOfBirth, address, email);
        return DaoLibraryCard.getInstance().createLibraryCard(card);
    }
    
    public LibraryCard findCardById(int id){
        return DaoLibraryCard.getInstance().findCardById(id);
    }
    
    public static final int CARD_LIMIT_REACHED = 0;
    public static final int CARD_EXPIRED = -1;
    public static final int CARD_HAVE_OVERDUE_BOOK = -2;
    public static final int CARD_ERROR = -3;

    /**
     * Check ability to rent new book
     * @param libraryCardId Library card need to check
     * @return The number of book this card can rent or the reason for deny rent new book
     *         0: Card already rent 5 book
     *         -1: Card expired
     *         -2: Card have overdue book
     *         -3: Unknown error
     */
    public int checkRentStatus(int libraryCardId){
        Calendar cal = Calendar.getInstance();
        LibraryCard card = findCardById(libraryCardId);
        
        //Check if card expired
        cal.setTime(card.getExpireDate());
        if(cal.before(Calendar.getInstance())){
            return CARD_EXPIRED;
        }
        
        return DaoLibraryCard.getInstance().checkRentStatus(card);
    }
    
    /**
     * Create new Rent Receipt of this card in db
     * @param libraryCardId Id of this card
     * @param bookIds List of id of books this card rent
         * @return true if create Rent Receipt successful, false otherwise
     */
    public boolean rentBooks(int libraryCardId, List<Integer> bookIds){
        return DaoLibraryCard.getInstance().rentBooks(libraryCardId, bookIds);
    }
    
    public List<Book> getRentedBook(int libraryCardId){
        return DaoLibraryCard.getInstance().getRentedBook(libraryCardId);
    }
    
    public boolean returnBook(int libraryCardId, List<Integer> returnBookIds){
        return DaoLibraryCard.getInstance().returnBook(libraryCardId, returnBookIds);
    }

    public List<LibraryCard> findAllCards() {
        return DaoLibraryCard.getInstance().findAllCards();
    }
    
    public boolean updateCard(LibraryCard modifiedCard){
        return DaoLibraryCard.getInstance().updateCard(modifiedCard);
    }
}
