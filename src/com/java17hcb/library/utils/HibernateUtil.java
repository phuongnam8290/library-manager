package com.java17hcb.library.utils;

import com.java17hcb.library.entity.Book;
import com.java17hcb.library.entity.BookImport;
import com.java17hcb.library.entity.BookRentReceipt;
import com.java17hcb.library.entity.FinesReceipt;
import com.java17hcb.library.entity.LibraryCard;
import com.java17hcb.library.entity.LiquidateHistory;
import com.java17hcb.library.entity.LostHistory;
import com.java17hcb.library.entity.RentReceipt;
import com.java17hcb.library.entity.ReturnReceipt;
import com.java17hcb.library.entity.Staff;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    
    private HibernateUtil(){}
    
    public static SessionFactory getInstance(){
        if(sessionFactory == null){
            sessionFactory = new Configuration()
                                    .configure("hibernate.cfg.xml")
                                    .addAnnotatedClass(Staff.class)
                                    .addAnnotatedClass(Book.class)
                                    .addAnnotatedClass(BookImport.class)
                                    .addAnnotatedClass(LibraryCard.class)
                                    .addAnnotatedClass(RentReceipt.class)
                                    .addAnnotatedClass(BookRentReceipt.class)
                                    .addAnnotatedClass(ReturnReceipt.class)
                                    .addAnnotatedClass(LostHistory.class)
                                    .addAnnotatedClass(LiquidateHistory.class)
                                    .addAnnotatedClass(FinesReceipt.class)
                                    .buildSessionFactory();
        }
        
        return sessionFactory;
    } 
}
