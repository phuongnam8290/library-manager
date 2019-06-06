package com.java17hcb.library.utils.table;

import com.java17hcb.library.bus.BusLibraryCard;
import com.java17hcb.library.entity.LibraryCard;
import com.java17hcb.library.utils.DateUtils;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class CardTableModel extends AbstractTableModel{
    private String[] colNames = {"ID", "Full Name", "Type", "DOB", "Address", "Email", "Create Date", "Expire Date", "Fines Fee"};
    private List<LibraryCard> data;
    private JFrame frame;
    
    public void setJFrame(JFrame frame){
        this.frame = frame;
    }
    
    public void setData(List<LibraryCard> data) {
        this.data = data;
    } 
    
    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == 2){
            return String.class;
        }
        if(columnIndex == 3 || columnIndex == 6 || columnIndex == 7){
            return LocalDate.class;
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
        if(columnIndex == 0){
            return false;
        }
        return true;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        LibraryCard card =  data.get(rowIndex);
        switch(columnIndex){
            case 0:
                return card.getId();
            case 1:
                return card.getFullName();
            case 2:
                switch(card.getType()){
                    case LibraryCard.Type.X:
                        return "X";
                    case LibraryCard.Type.Y:
                        return "Y";                     
                }
            case 3:
                return DateUtils.asLocalDate(card.getDateOfBirth());
            case 4:
                return card.getAddress();
            case 5:
                return card.getEmail();
            case 6:
                return DateUtils.asLocalDate(card.getCreateDate());
            case 7:
                return DateUtils.asLocalDate(card.getExpireDate());
            case 8:
                return card.getFinesFee();
        }
        return null;
    }    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        LibraryCard modifiedCard = (LibraryCard)data.get(rowIndex).clone();
        switch(columnIndex){
        case 1:
            if(modifiedCard.getFullName().equals((String)aValue)){
                return;
            }
            modifiedCard.setFullName((String)aValue);
            break;
        case 4:
            if(modifiedCard.getAddress().equals((String)aValue)){
                return;
            }
            modifiedCard.setAddress((String)aValue);
            break;
        case 5:
            if(modifiedCard.getEmail().equals((String)aValue)){
                return;
            }
            modifiedCard.setEmail((String)aValue);
            break;
        case 2:
            switch((String)aValue){
                case "X":
                    if(modifiedCard.getType() == LibraryCard.Type.X){
                        return;
                    }
                    modifiedCard.setType(LibraryCard.Type.X);
                    break;
                case "Y":
                    if(modifiedCard.getType() == LibraryCard.Type.Y){
                        return;
                    }
                    modifiedCard.setType(LibraryCard.Type.Y);
                    break;
            }
            break;
        case 8:
            if((long)aValue == modifiedCard.getFinesFee()){
                return;
            }
            else {
                modifiedCard.setFinesFee((long)aValue);
            }
            break;
        case 3:
            if((DateUtils.asLocalDate(modifiedCard.getDateOfBirth())).equals((LocalDate)aValue)){
                return;
            }
            else{
                modifiedCard.setDateOfBirth(DateUtils.asDate((LocalDate)aValue));
            }
            break;
        case 6:
            if((DateUtils.asLocalDate(modifiedCard.getCreateDate())).equals((LocalDate)aValue)){
                return;
            }
            else{
                modifiedCard.setCreateDate(DateUtils.asDate((LocalDate)aValue));
            }
            break;
        case 7:
            if((DateUtils.asLocalDate(modifiedCard.getExpireDate())).equals((LocalDate)aValue)){
                return;
            }
            else{
                modifiedCard.setExpireDate(DateUtils.asDate((LocalDate)aValue));
            }
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
            if(BusLibraryCard.getInstance().updateCard(modifiedCard)){
            JOptionPane.showMessageDialog(frame, 
                                    "Update success!",
                                    "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
            data.set(rowIndex, modifiedCard);
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
