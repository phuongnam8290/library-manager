package com.java17hcb.library.utils.tablemodel;

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
        Book modifiedBook = (Book)data.get(rowIndex).clone();
        switch(columnIndex){
        case 1:
            if(modifiedBook.getTitle().equals((String)aValue)){
                return;
            }
            modifiedBook.setTitle((String)aValue);
            break;
        case 3:
            if(modifiedBook.getAuthor().equals((String)aValue)){
                return;
            }
            modifiedBook.setAuthor((String)aValue);
            break;
        case 5:
            if(modifiedBook.getPublisher().equals((String)aValue)){
                return;
            }
            modifiedBook.setPublisher((String)aValue);
            break;
        case 2:
            switch((String)aValue){
                case "A":
                    if(modifiedBook.getType() == Book.Type.A){
                        return;
                    }
                    modifiedBook.setType(Book.Type.A);
                    break;
                case "B":
                    if(modifiedBook.getType() == Book.Type.B){
                        return;
                    }
                    modifiedBook.setType(Book.Type.B);
                    break;
                case "C":
                    if(modifiedBook.getType() == Book.Type.C){
                        return;
                    }
                    modifiedBook.setType(Book.Type.C);
                    break;
            }
            break;
        case 4:
            Calendar cal = Calendar.getInstance();
            cal.setTime(modifiedBook.getPublishYear());
            if(cal.get(Calendar.YEAR) == (int)aValue){
                return;
            }
            cal.clear();
            cal.set(Calendar.YEAR, (int)aValue);
            Date date = cal.getTime();
            modifiedBook.setPublishYear(date);
            break;
        }

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
        } else {
            if(BusBook.getInstance().updateBook(modifiedBook)){
            JOptionPane.showMessageDialog(frame, 
                                    "Update success!",
                                    "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
            data.set(rowIndex, modifiedBook);
            fireTableCellUpdated(rowIndex, columnIndex);
            } 
            else {
            JOptionPane.showMessageDialog(frame, 
                                    "There is something wrong. Please try again later",
                                    "Failed",
                                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }  
}
