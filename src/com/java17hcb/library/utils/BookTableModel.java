package com.java17hcb.library.utils;

import com.java17hcb.library.bus.BusBook;
import com.java17hcb.library.entity.Book;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class BookTableModel extends AbstractTableModel {
    private String[] colNames = {"ID", "Title", "Type", "Author", "Year", "Publisher", "Copies"};
    private List<Book> data;
    private JFrame frame;
    
    public void setJFrame(JFrame frame){
        this.frame = frame;
    }
    
    public void setData(List<Book> data) {
        this.data = data;
    } 
    
    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0:
                return "ID";
            case 1:
                return "Title";
            case 2:
                return "Type";
            case 3:
                return "Author";
            case 4:
                return "Year";
            case 5:
                return "Publisher";
            case 6:
                return "Copies";
        }
        return "";
    }
  
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == 2){
            return String.class;
        }
        if(columnIndex == 4){
            return Integer.class;
        }
        return getValueAt(0, columnIndex).getClass();
    }
    
    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(columnIndex == 1 || columnIndex == 2 || columnIndex == 3 || columnIndex == 4 || columnIndex == 5){
            return true;
        }
        return false;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Book book =  data.get(rowIndex);
        switch(columnIndex){
            case 0:
                return book.getId();
            case 1:
                return book.getTitle();
            case 2:
                switch(book.getType()){
                    case Book.Type.A:
                        return "A";
                    case Book.Type.B:
                        return "B";
                    case Book.Type.C:
                        return "C";                        
                }
            case 3:
                return book.getAuthor();
            case 4:
                Calendar cal = Calendar.getInstance();
                cal.setTime(book.getPublishYear());
                return cal.get(Calendar.YEAR);
            case 5:
                return book.getPublisher();
            case 6:
                return book.getRemainCopy();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        int input = JOptionPane.showOptionDialog(frame, 
                                            "Do you really want to modify this value?",
                                            "Warning",
                                            JOptionPane.OK_CANCEL_OPTION,
                                            JOptionPane.WARNING_MESSAGE,
                                            null,
                                            null,
                                            null);
        if (input == JOptionPane.CANCEL_OPTION){
            return;
        }
        else {
            Book modifiedBook = data.get(rowIndex);
            String stringValue, stringOldValue;
            Date dateOldValue;
            int intValue, intOldValue;
            switch(columnIndex){
            case 1:
                stringValue = (String)aValue;
                stringOldValue = modifiedBook.getTitle();
                modifiedBook.setTitle(stringValue);
                break;
            case 3:
                stringValue = (String)aValue;
                stringOldValue = modifiedBook.getAuthor();
                modifiedBook.setAuthor(stringValue);
                break;
            case 5:
                stringValue = (String)aValue;
                stringOldValue = modifiedBook.getPublisher();
                modifiedBook.setPublisher(stringValue);
                break;
            case 2:
                stringValue = (String)aValue;
                intOldValue = modifiedBook.getType();
                switch(stringValue){
                    case "A":
                        modifiedBook.setType(Book.Type.A);
                        break;
                    case "B":
                        modifiedBook.setType(Book.Type.B);
                        break;
                    case "C":
                        modifiedBook.setType(Book.Type.C);
                        break;
                }
                break;
            case 4:
                intValue = (Integer)aValue;
                dateOldValue = modifiedBook.getPublishYear();
                Calendar cal = Calendar.getInstance();
                cal.clear();
                cal.set(Calendar.YEAR, intValue);
                Date date = cal.getTime();
                modifiedBook.setPublishYear(date);
                break;
            }
            
            if(BusBook.getInstance().updateBook(modifiedBook)){
                JOptionPane.showMessageDialog(frame, 
                                        "Update success!",
                                        "Success",
                                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, 
                                        "There is something wrong. Please try again later",
                                        "Failed",
                                        JOptionPane.ERROR_MESSAGE);
            }
        }      
        fireTableCellUpdated(rowIndex, columnIndex);
    }
    
    
    
}
