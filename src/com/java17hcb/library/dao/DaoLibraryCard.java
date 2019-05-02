package com.java17hcb.library.dao;

import com.java17hcb.library.entity.LibraryCard;
import com.java17hcb.library.entity.Staff;
import com.java17hcb.library.utils.CurrentStaff;
import com.java17hcb.library.utils.HibernateUtil;
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
}
