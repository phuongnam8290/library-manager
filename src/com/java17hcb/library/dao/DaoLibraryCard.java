package com.java17hcb.library.dao;

import com.java17hcb.library.entity.Book;
import com.java17hcb.library.entity.BookRentReceipt;
import com.java17hcb.library.entity.LibraryCard;
import com.java17hcb.library.entity.RentReceipt;
import com.java17hcb.library.entity.Staff;
import com.java17hcb.library.utils.CurrentStaff;
import com.java17hcb.library.utils.HibernateUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DaoLibraryCard {
    private static DaoLibraryCard instance;
    
    private DaoLibraryCard(){}
    
    public static DaoLibraryCard getInstance(){
        if (instance == null){
            instance = new DaoLibraryCard();
        }
        
        return instance;
    }
    
    public boolean createLibraryCard(LibraryCard card){
        SessionFactory sessionFactory = HibernateUtil.getInstance();
        Session session = sessionFactory.getCurrentSession();
        
        Staff currentStaff = CurrentStaff.getCurrentStaff();
        
        try{
            session.beginTransaction();

            Staff staff = session.get(Staff.class, currentStaff.getId());            
            staff.createCard(card);            
            
            session.getTransaction().commit();
        } catch(Exception e){
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        
        return true;
    }
    
    public static final int NUMBER_OF_BOOK_RENTED_LIMIT = 5;
    public static final int NUMBER_OF_DAY_RENTED_LIMIT = 4;
    
    public static final int CARD_LIMIT_REACHED = 0;
    public static final int CARD_HAVE_OVERDUE_BOOK = -2;
    public static final int CARD_ERROR = -3;
    public int checkRentStatus(LibraryCard card) {
        SessionFactory sessionFactory = HibernateUtil.getInstance();
        Session session = sessionFactory.getCurrentSession();
        
          try{
            session.beginTransaction();

            LibraryCard cardInDB = session.get(LibraryCard.class, card.getId());                        
            List<RentReceipt> rentReceipts = cardInDB.getRentReceipts();
            Calendar limitDate = Calendar.getInstance();
            Calendar currentDate = Calendar.getInstance();
            int numberOfBookCanRent = NUMBER_OF_BOOK_RENTED_LIMIT;
            
            for(RentReceipt rentReceipt : rentReceipts){
                //Book in this rent receipt need to be returned before date in limitDate instance
                limitDate.setTime(rentReceipt.getRentDate());
                limitDate.add(Calendar.DATE, + NUMBER_OF_DAY_RENTED_LIMIT);
                                
                //If today is after the limit date. If even once book not yet returned, 
                //this card cannot rent new book.
                if(limitDate.before(currentDate)){                    
                    for(BookRentReceipt receiptDetail : rentReceipt.getBookRentReceipts()){
                        if(receiptDetail.getStatus() == BookRentReceipt.Status.NOT_RETURN)
                            return CARD_HAVE_OVERDUE_BOOK;
                    }
                } else {
                    //If today is before limit date, for each book this card has rented,
                    //decrease number of book this card can rent. 
                    for(BookRentReceipt receiptDetail : rentReceipt.getBookRentReceipts()){
                        if(receiptDetail.getStatus() == BookRentReceipt.Status.NOT_RETURN)
                            numberOfBookCanRent--;
                    }
                }
            }           
            session.getTransaction().commit();
            
            if(numberOfBookCanRent <= 0){
                return CARD_LIMIT_REACHED;
            } else {
                return numberOfBookCanRent;
            }
        } catch(Exception e){
            e.printStackTrace();
            return CARD_ERROR;
        } finally {
            session.close();
        }
    }

    public LibraryCard findCardById(int id) {
        SessionFactory sessionFactory = HibernateUtil.getInstance();
        Session session = sessionFactory.getCurrentSession();
        
        try{
            session.beginTransaction();
            LibraryCard card = session.find(LibraryCard.class, id);
            session.getTransaction().commit();    
            return card;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }        
    }

    /**
     * Create new Rent Receipt of this card in db
     * @param libraryCardId Id of this card
     * @param bookIds List of id of books this card rent
         * @return true if create Rent Receipt successful, false otherwise
     */
    public boolean rentBooks(int libraryCardId, List<Integer> bookIds) {
        SessionFactory sessionFactory = HibernateUtil.getInstance();
        Session session = sessionFactory.getCurrentSession();
        
        try{
            session.beginTransaction();
            LibraryCard card = session.get(LibraryCard.class, libraryCardId);
            List<Book> books = new ArrayList<>();
        
            if (card == null){
                return false;
            }
            for(int bookId : bookIds){
                Book book = session.get(Book.class, bookId);
                if(book == null) {
                    return false;
                } else{
                    books.add(book);
                }            
            }
            
            RentReceipt receipt = new RentReceipt(card, new Date());
            card.addRentReceipt(receipt);
            
            for(Book book : books){
                BookRentReceipt record = new BookRentReceipt(receipt, book);
                receipt.addBookToReceipt(record);
            }           
        session.getTransaction().commit();    
        return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }        
    }
}
