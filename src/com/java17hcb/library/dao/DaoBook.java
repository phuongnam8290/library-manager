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
    
    public static final int IMPORT_SUCCESS = 1;
    public static final int IMPORT_UNKNOWN_ERROR = -1;
    
    public int importBook(Book importedBook, int copies){
        SessionFactory sessionFactory = HibernateUtil.getInstance();
        Session session = sessionFactory.getCurrentSession();
        
        try{
            session.beginTransaction();
            
            Staff staff = session.find(Staff.class, CurrentStaff.getCurrentStaff().getId());
            Book book;
            
            String hql = "FROM Book B "
                    + "WHERE B.title = :title ";
            
            Query query = session.createQuery(hql);
            query.setParameter("title", importedBook.getTitle());
            
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
            return IMPORT_UNKNOWN_ERROR;
        } finally {
            session.close();
        }        
        return IMPORT_SUCCESS;
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
    
    public Book findBookByTitle(String title){
        SessionFactory sessionFactory = HibernateUtil.getInstance();
        Session session = sessionFactory.getCurrentSession();
        
        try{
            session.beginTransaction();           
            String hql = "FROM Book B "
                    + "WHERE B.title = :name ";
            
            Query query = session.createQuery(hql);
            query.setParameter("title", title);
            
            try{
                return (Book)(query.getSingleResult());
                
            } catch(NoResultException e){
                e.printStackTrace();
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
}
