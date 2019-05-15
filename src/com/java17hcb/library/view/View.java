package com.java17hcb.library.view;

import com.java17hcb.library.bus.BusBook;
import com.java17hcb.library.bus.BusLibraryCard;
import com.java17hcb.library.bus.BusStaff;
import com.java17hcb.library.entity.Book;
import com.java17hcb.library.entity.LibraryCard;
import com.java17hcb.library.entity.Staff;
import com.java17hcb.library.utils.CurrentStaff;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class View {
    public static void main(String... args) throws ParseException{
        String username = "1742038";
        String password = "123";
        
        Staff staff = BusStaff.getInstance().login(username, password);
        if(staff != null){
            CurrentStaff.setCurrentStaff(staff);
            
            /*String dateInString = "22/12/1990"; 
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date dateOfBirth = formatter.parse(dateInString);
            
            String newUsername = "1742038";
            String newPassword = "123";
            String fullName = "Minh Trí";
            int diploma = Staff.Diploma.DAI_HOC;
            int position = Staff.Position.GIAM_DOC;
            int division = Staff.Division.THU_KHO;
            String address = "17 Nguyễn Văn Cừ";
            String phone = "1274263";
            
            String message = BusStaff.getInstance().createStaff(newUsername, newPassword, fullName, 
                                            dateOfBirth, diploma, position, division, 
                                            address, phone);
            System.out.println(message);*/
            
            
            
            /*String fullName = "Tuấn Anh";
            int type = LibraryCard.Type.X;
            String address = "12 Nguyễn Văn Cừ";
            String email = "example@gmail.com";
            Date createDate = new Date();
            
            String dateInString = "15/8/1980";
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date dateOfBirth = formatter.parse(dateInString);
            
            System.out.println(BusLibraryCard.getInstance().createLibraryCard(fullName, type, dateOfBirth, address, email, createDate));
            
            
            
            String name = "C++ Primer";
            String author = "Stanley B. Lippman, Josée Lajoie, Barbara E. Moo";
            int type = Book.Type.B;
            
            String publishYearString = "12/9/2015";
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date publishYear = formatter.parse(publishYearString);
            
            String publisher = "Pearson";
            long price = 300000;
            int copies = 5;
            
            System.out.println(BusBook.getInstance().importBook(name, type, author, publishYear, publisher, price, copies));*/
            
            
            /*List<Integer> bookIds = new ArrayList<>();
            bookIds.add(31);
            bookIds.add(34);           
            System.out.println(BusLibraryCard.getInstance().rentBooks(2, bookIds));*/
            
            
            
            
            System.out.println(BusLibraryCard.getInstance().checkRentStatus(2));
        }
    }
}
