package com.java17hcb.library.dao;

import com.java17hcb.library.entity.Staff;
import com.java17hcb.library.utils.HibernateUtil;
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
}