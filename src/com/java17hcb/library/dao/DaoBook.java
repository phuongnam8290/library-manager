package com.java17hcb.library.dao;

import com.java17hcb.library.entity.Book;
import com.java17hcb.library.entity.BookImport;
import com.java17hcb.library.entity.Staff;
import com.java17hcb.library.utils.CurrentStaff;
import com.java17hcb.library.utils.HibernateUtil;
import java.util.Date;
import javax.persistence.NoResultException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DaoBook {
    private static DaoBook instance;
    
    private DaoBook(){}
    
    public static DaoBook getInstance(){
        if (instance == null){
            instance = new DaoBook();
        }       
        return instance;
    }
    
    public boolean importBook(Book importedBook, int copies){
        SessionFactory sessionFactory = HibernateUtil.getInstance();
        Session session = sessionFactory.getCurrentSession();
        
        try{
            session.beginTransaction();
            
            Staff staff = session.find(Staff.class, CurrentStaff.getCurrentStaff().getId());
            Book book;
            
            String sql = "FROM Book B "
                    + "WHERE B.name = :name ";
            
            Query query = session.createQuery(sql);
            query.setParameter("name", importedBook.getName());
            
            try{
                book = (Book)(query.getSingleResult());
                book.setRemainCopy(book.getRemainCopy() + copies);
            } catch(NoResultException e){
                e.printStackTrace();
                book = importedBook;
            }
            
            BookImport bookImportRecord = new BookImport(CurrentStaff.getCurrentStaff(), book);
            bookImportRecord.setImportDate(new Date());
            bookImportRecord.setCopy(copies);
            
            staff.importBook(bookImportRecord);
            
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        
        return true;
    }

    public Book findBookById(int id) {
        SessionFactory sessionFactory = HibernateUtil.getInstance();
        Session session = sessionFactory.getCurrentSession();
        
        try{
            session.beginTransaction();
            Book book = session.find(Book.class, id);
            session.getTransaction().commit();    
            return book;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }        
    }
}
