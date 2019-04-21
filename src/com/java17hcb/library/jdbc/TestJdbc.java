package com.java17hcb.library.jdbc;

import com.java17hcb.library.entity.Book;
import com.java17hcb.library.entity.BookImport;
import com.java17hcb.library.entity.LibraryCard;
import com.java17hcb.library.entity.Staff;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestJdbc {
	public static void main(String... args) {
            SessionFactory factory = new Configuration()
                                    .configure("hibernate.cfg.xml")
                                    .addAnnotatedClass(Staff.class)
                                    .addAnnotatedClass(Book.class)
                                    .addAnnotatedClass(BookImport.class)
                                    .addAnnotatedClass(LibraryCard.class)
                                    .buildSessionFactory();
            
            Session session = factory.getCurrentSession();
            
            try{
                session.beginTransaction();
                
                /*Staff staff = new Staff("lantis02807", "Azure020890", "Phương Nam", 
                                        new Date(), 4, 4, 4, "10 Cù Chính Lan", "0208900");
                
                session.save(staff);*/
                
                
                
                /*Book book = new Book("Java In Dept", 1, "Joshua", new Date(), 
                                    "O'Relly", 170000, 10);*/
                
                
                
                /*Staff staff = session.get(Staff.class, 1);
                Book book = new Book("Effective Java", 1, "Joshua", new Date(), 
                                    "O'Relly", 50000, 5);
                
                BookImport bookImportRecord = new BookImport(staff, book);
                bookImportRecord.setImportDate(new Date());
                bookImportRecord.setCopy(5);
                
                staff.importBook(bookImportRecord);*/

                
                
                /*Staff staff = session.get(Staff.class, 1);
                LibraryCard card = new LibraryCard("Hoàng Anh", 2, new Date(),
                        "12 Hoàng Hoa Thám", "example.com", new Date(), new Date(),
                        150000);
                
                staff.createCard(card);*/         
                
                
                
                session.getTransaction().commit();
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                session.close();
                factory.close();
            }
	}
}
