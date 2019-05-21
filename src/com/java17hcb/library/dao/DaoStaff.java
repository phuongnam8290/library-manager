package com.java17hcb.library.dao;

import com.java17hcb.library.entity.Book;
import com.java17hcb.library.entity.BookRentReceipt;
import com.java17hcb.library.entity.FinesReceipt;
import com.java17hcb.library.entity.LibraryCard;
import com.java17hcb.library.entity.LiquidateHistory;
import com.java17hcb.library.entity.LostHistory;
import com.java17hcb.library.entity.RentReceipt;
import com.java17hcb.library.entity.Staff;
import com.java17hcb.library.utils.CurrentStaff;
import com.java17hcb.library.utils.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DaoStaff {
    private static DaoStaff instance;
    
    private DaoStaff(){}
    
    public static DaoStaff getInstance(){
        if(instance == null){
            instance = new DaoStaff();
        }
        return instance;
    }
    
    /**
     * Login with username and password
     * @param username
     * @param password
     * @return If record exist in db, return Staff object match with username & password.
     *         Return null if not found.
     */
    public Staff login(String username, String password){
        SessionFactory sessionFactory = HibernateUtil.getInstance();
        Session session = sessionFactory.getCurrentSession();
        
        Staff staff = null;
        try{
            session.beginTransaction();
            
            String sql = "FROM Staff S "
                    + "WHERE S.username = :username "
                    + "AND S.password = :password";
            
            Query query = session.createQuery(sql);
            query.setParameter("username", username);
            query.setParameter("password", password);
            
            staff = (Staff)(query.getSingleResult());
            
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
        
        return staff;
    }
    
    /**
     * Check if account already exist in db or not
     * @param username
     * @return true if account exist, false if not
     */
    public boolean isExist(String username){
        SessionFactory sessionFactory = HibernateUtil.getInstance();
        Session session = sessionFactory.getCurrentSession();
        
        Staff staff = null;
        try{
            session.beginTransaction();
            
            String sql = "FROM Staff S "
                    + "WHERE S.username = :username ";
            
            Query query = session.createQuery(sql);
            query.setParameter("username", username);
            
            staff = (Staff)(query.getSingleResult());
            
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        
        return staff != null;
    }
    
    /**
     * Save new staff record to db
     * @param staff staff record need to save
     */
    public void createStaff(Staff staff){
        SessionFactory sessionFactory = HibernateUtil.getInstance();
        Session session = sessionFactory.getCurrentSession();
        
        try{
            session.beginTransaction();      
            session.save(staff);           
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    public static final int PAYMENT_SUCCESS = 1;
    public static final int PAYMENT_LARGER_THAN_FINES = -1;
    public static final int PAYMENT_LIBRARY_CARD_NOT_EXIST = -2;
    public static final int PAYMENT_UNKNOWN_ERROR = -3;
    
    public int createFinesReceipt(int libraryCardId, long payment) {
        SessionFactory sessionFactory = HibernateUtil.getInstance();
        Session session = sessionFactory.getCurrentSession();
        
        Staff currentStaff = CurrentStaff.getCurrentStaff();
        try{
            session.beginTransaction();
            LibraryCard card = session.get(LibraryCard.class, libraryCardId);
            if (card == null){
                return PAYMENT_LIBRARY_CARD_NOT_EXIST;
            }
            if(payment > card.getFinesFee()){
                return PAYMENT_LARGER_THAN_FINES;
            } else{
                Staff staff = session.get(Staff.class, currentStaff.getId());
                FinesReceipt receipt = new FinesReceipt(staff, card, card.getFinesFee(), payment);
                card.setFinesFee(card.getFinesFee() - payment);
                session.save(receipt);
            }
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            return PAYMENT_UNKNOWN_ERROR;
        } finally {
            session.close();
        }
        return PAYMENT_SUCCESS;
    }

    public static final int LIQUIDATE_SUCCESS = 1;
    public static final int LIQUIDATE_BOOK_NOT_EXIST = -1;
    public static final int LIQUIDATE_NOT_ENOUGH_COPY = -2;
    public static final int LIQUIDATE_UNKNOWN_ERROR = -3;
    
    public int liquidateBook(int bookId, int reason, int copy) {
        SessionFactory sessionFactory = HibernateUtil.getInstance();
        Session session = sessionFactory.getCurrentSession();
        
        Staff currentStaff = CurrentStaff.getCurrentStaff();
        try{
            session.beginTransaction();
            Book book = session.get(Book.class, bookId);
            if (book == null){
                return LIQUIDATE_BOOK_NOT_EXIST;
            }
            if (book.getRemainCopy() < copy){
                return LIQUIDATE_NOT_ENOUGH_COPY;
            }
            
            Staff staff = session.get(Staff.class, currentStaff.getId());
            LiquidateHistory history = new LiquidateHistory(book, staff, reason, copy);
            book.setRemainCopy(book.getRemainCopy() - copy);
            
            session.save(history);
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            return LIQUIDATE_UNKNOWN_ERROR;
        } finally {
            session.close();
        }
        return LIQUIDATE_SUCCESS;
    }
    
    public static final int RECORD_LOST_SUCCESS = 1;
    public static final int RECORD_LOST_BOOK_NOT_EXIST = -1;
    public static final int RECORD_LOST_LIBRARY_CARD_NOT_EXIST = -2;
    public static final int RECORD_LOST_LIBRARY_CARD_NOT_RENT_THIS_BOOK = -3;
    public static final int RECORD_LOST_BOOK_PRICE_LOWER_THAN_FINES_FEE = -4;
    public static final int RECORD_LOST_UNKNOWN_ERROR = -5;
    
    public int recordLostBook(int libraryCardId, int bookId, long finesFee){
        SessionFactory sessionFactory = HibernateUtil.getInstance();
        Session session = sessionFactory.getCurrentSession();
        
        Staff currentStaff = CurrentStaff.getCurrentStaff();
        try{
            session.beginTransaction();
            Book book = session.get(Book.class, bookId);
            LibraryCard card = session.get(LibraryCard.class, libraryCardId);
            if (book == null){
                return RECORD_LOST_BOOK_NOT_EXIST;
            }
            if (card == null){
                return RECORD_LOST_LIBRARY_CARD_NOT_EXIST;
            }
            
            List<BookRentReceipt> currentRentBook = new ArrayList<>();     
            for(RentReceipt receipt : card.getRentReceipts()){
                for(BookRentReceipt record : receipt.getBookRentReceipts()){
                    if(record.getStatus() == BookRentReceipt.Status.NOT_RETURN)
                        currentRentBook.add(record);
                }
            }
            
            boolean cardRentCurrentBook = false;
            for(BookRentReceipt record : currentRentBook){
                if(record.getBook().getId() == bookId){
                    cardRentCurrentBook = true;
                    record.setStatus(BookRentReceipt.Status.LOSTED);
                }
            }
            if(!cardRentCurrentBook){
                return RECORD_LOST_LIBRARY_CARD_NOT_RENT_THIS_BOOK;
            }
            
            if (book.getPrice() < finesFee){
                return RECORD_LOST_BOOK_PRICE_LOWER_THAN_FINES_FEE;
            }
            
            Staff staff = session.get(Staff.class, currentStaff.getId());
            LostHistory history = new LostHistory(book, card, staff, finesFee);
            book.setRemainCopy(book.getRemainCopy() - 1);
            card.setFinesFee(card.getFinesFee() + finesFee);
            
            session.save(history);
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            return RECORD_LOST_UNKNOWN_ERROR;
        } finally {
            session.close();
        }
        return RECORD_LOST_SUCCESS;
    }
}
