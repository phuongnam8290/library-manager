package com.java17hcb.library.dao;

import com.java17hcb.library.entity.Book;
import com.java17hcb.library.entity.BookRentReceipt;
import com.java17hcb.library.entity.LibraryCard;
import com.java17hcb.library.entity.RentReceipt;
import com.java17hcb.library.entity.ReturnReceipt;
import com.java17hcb.library.entity.Staff;
import com.java17hcb.library.utils.CurrentStaff;
import com.java17hcb.library.utils.HibernateUtil;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
            if(book == null || book.getRemainCopy() <= 0) {
                return false;
            } else{
                book.setRemainCopy(book.getRemainCopy() - 1);
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
    
    public boolean returnBook(int libraryCardId, List<Integer> returnBookIds) {
        SessionFactory sessionFactory = HibernateUtil.getInstance();
        Session session = sessionFactory.getCurrentSession();
        
        try{
        session.beginTransaction();
        LibraryCard card = session.get(LibraryCard.class, libraryCardId);
        ReturnReceipt returnReceipt = new ReturnReceipt();
        List<BookRentReceipt> currentRentBook = new ArrayList<>();       
        
        for(RentReceipt receipt : card.getRentReceipts()){
            for(BookRentReceipt record : receipt.getBookRentReceipts()){
                if(record.getStatus() == BookRentReceipt.Status.NOT_RETURN)
                    currentRentBook.add(record);
            }
        }
        
        Stream<BookRentReceipt> returnBookStream = currentRentBook.stream().filter(c -> returnBookIds.contains(c.getBook().getId()));
        //Stream<BookRentReceipt> lostBookStream = currentRentBook.stream().filter(c -> lostBookIds.contains(c.getBook().getId()));
        
        List<BookRentReceipt> returnBook = getArrayListFromStream(returnBookStream);
        //List<BookRentReceipt> lostBook = getArrayListFromStream(lostBookStream);
        
        for(BookRentReceipt record : returnBook){
            record.setStatus(BookRentReceipt.Status.RETURNED);
            Book book = record.getBook();
            book.setRemainCopy(book.getRemainCopy() + 1);
            returnReceipt.addBookToReceipt(record);
            returnReceipt.setLateFee(returnReceipt.getLateFee() + calculateLateFee(record));
        }
        
//        for(BookRentReceipt record : lostBook){
//            record.setStatus(BookRentReceipt.Status.LOSTED);
//            returnReceipt.addBookToReceipt(record);
//            returnReceipt.setLostFee(returnReceipt.getLostFee() + record.getBook().getPrice());
//        }
        
//        card.setFinesFee(card.getFinesFee() + returnReceipt.getLateFee() + returnReceipt.getLostFee());
        session.save(returnReceipt);
        session.getTransaction().commit();    
        return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }        
    }
    
    private static <T> ArrayList<T> getArrayListFromStream(Stream<T> stream) 
    { 
        // Convert the Stream to List 
        List<T> list = stream.collect(Collectors.toList()); 
  
        // Create an ArrayList of the List 
        ArrayList<T> arrayList = new ArrayList<T>(list); 
  
        // Return the ArrayList 
        return arrayList; 
    }
    
    public static final long LATE_FEE_PER_DAY = 1000;
    private static long calculateLateFee(BookRentReceipt record){
        Calendar rentDate = Calendar.getInstance();
        Calendar returnDate = Calendar.getInstance();
        
        rentDate.setTime(record.getRentReceipt().getRentDate());
        long daysBetween = ChronoUnit.DAYS.between(rentDate.toInstant(), returnDate.toInstant()); 
        if(daysBetween <= 4)
            return 0;
        else
            return (daysBetween - 4) * LATE_FEE_PER_DAY;
    }
}
