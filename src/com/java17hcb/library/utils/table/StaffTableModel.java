package com.java17hcb.library.utils.table;

import com.java17hcb.library.bus.BusStaff;
import com.java17hcb.library.entity.Staff;
import com.java17hcb.library.utils.CurrentStaff;
import com.java17hcb.library.utils.DateUtils;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class StaffTableModel extends AbstractTableModel {
    private String[] colNames = {"ID", "Username", "Fullname", "DOB", "Diploma", "Position", "Division", "Address", "Phone"};
    private List<Staff> data;
    private JFrame frame;
    
    public void setJFrame(JFrame frame){
        this.frame = frame;
    }
    
    public void setData(List<Staff> data) {
        this.data = data;
    } 
    
    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }
  
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == 4 || columnIndex == 5 || columnIndex == 6){
            return String.class;
        }
        if(columnIndex == 3){
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
        if(!(CurrentStaff.getCurrentStaff().getDivision() == Staff.Division.ADMIN)){
            return false;
        }
        if(columnIndex == 0){
            return false;
        }
        return true;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Staff staff =  data.get(rowIndex);
        switch(columnIndex){
            case 0:
                return staff.getId();
            case 1:
                return staff.getUsername();
            case 2: 
                return staff.getFullName();
            case 3:
                return DateUtils.asLocalDate(staff.getDateOfBirth());
            case 4:
                switch(staff.getDiploma()){
                    case Staff.Diploma.CAO_DANG:
                        return "Cao Đẳng";
                    case Staff.Diploma.DAI_HOC:
                        return "Đại Học";
                    case Staff.Diploma.THAC_SI:
                        return "Thạc Sĩ";
                    case Staff.Diploma.TIEN_SI:
                        return "Tiến Sĩ";
                    case Staff.Diploma.TRUNG_CAP:
                        return "Trung Cấp";
                    case Staff.Diploma.TU_TAI:
                        return "Tú Tài";
                }
            case 5:
                switch(staff.getPosition()){
                    case Staff.Position.GIAM_DOC:
                        return "Giám Đốc";
                    case Staff.Position.NHAN_VIEN:
                        return "Nhân Viên";
                    case Staff.Position.PHO_GIAM_DOC:
                        return "Phó Giám Đốc";
                    case Staff.Position.PHO_PHONG:
                        return "Phó Phòng";
                    case Staff.Position.TRUONG_PHONG:
                        return "Trưởng Phòng";
                }
            case 6:
                switch(staff.getDivision()){
                    case Staff.Division.BAN_GIAM_DOC:
                        return "Bam Giám Đốc";
                    case Staff.Division.THU_KHO:
                        return "Thủ Kho";
                    case Staff.Division.THU_QUY:
                        return "Thủ Quỹ";
                    case Staff.Division.THU_THU:
                        return "Thủ Thư";
                }
            case 7:
                return staff.getAddress();
            case 8:
                return staff.getPhone();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Staff modifiedStaff = (Staff)data.get(rowIndex).clone();
        switch(columnIndex){
        case 1:
            if(modifiedStaff.getUsername().equals((String)aValue)){
                return;
            }
            modifiedStaff.setUsername((String)aValue);
            break;
        case 2:
            if(modifiedStaff.getFullName().equals((String)aValue)){
                return;
            }
            modifiedStaff.setFullName((String)aValue);
            break;
        case 3:
            if((DateUtils.asLocalDate(modifiedStaff.getDateOfBirth())).equals((LocalDate)aValue)){
                return;
            }
            else{
                modifiedStaff.setDateOfBirth(DateUtils.asDate((LocalDate)aValue));
            }
            break;
        case 4:
            switch((String)aValue){
                case "Cao Đẳng":
                    if(modifiedStaff.getDiploma() == Staff.Diploma.CAO_DANG){
                        return;
                    }
                    modifiedStaff.setDiploma(Staff.Diploma.CAO_DANG);
                    break;
                case "Đại Học":
                    if(modifiedStaff.getDiploma() == Staff.Diploma.DAI_HOC){
                        return;
                    }
                    modifiedStaff.setDiploma(Staff.Diploma.DAI_HOC);
                    break;
                case "Thạc Sĩ":
                    if(modifiedStaff.getDiploma() == Staff.Diploma.THAC_SI){
                        return;
                    }
                    modifiedStaff.setDiploma(Staff.Diploma.THAC_SI);
                    break;
                case "Tiến Sĩ":
                    if(modifiedStaff.getDiploma() == Staff.Diploma.TIEN_SI){
                        return;
                    }
                    modifiedStaff.setDiploma(Staff.Diploma.TIEN_SI);
                    break;
                case "Trung Cấp":
                    if(modifiedStaff.getDiploma() == Staff.Diploma.TRUNG_CAP){
                        return;
                    }
                    modifiedStaff.setDiploma(Staff.Diploma.TRUNG_CAP);
                    break;
                case "Tú Tài":
                    if(modifiedStaff.getDiploma() == Staff.Diploma.TU_TAI){
                        return;
                    }
                    modifiedStaff.setDiploma(Staff.Diploma.TU_TAI);
                    break;
                }
            break;
        case 5:
            switch((String)aValue){
                case "Giám Đốc":
                    if(modifiedStaff.getPosition() == Staff.Position.GIAM_DOC){
                        return;
                    }
                    modifiedStaff.setPosition(Staff.Position.GIAM_DOC);
                    break;
                case "Nhân Viên":
                    if(modifiedStaff.getPosition() == Staff.Position.NHAN_VIEN){
                        return;
                    }
                    modifiedStaff.setPosition(Staff.Position.NHAN_VIEN);
                    break;
                case "Phó Giám Đốc":
                    if(modifiedStaff.getPosition() == Staff.Position.PHO_GIAM_DOC){
                        return;
                    }
                    modifiedStaff.setPosition(Staff.Position.PHO_GIAM_DOC);
                    break;
                case "Phó Phòng":
                    if(modifiedStaff.getPosition() == Staff.Position.PHO_PHONG){
                        return;
                    }
                    modifiedStaff.setPosition(Staff.Position.PHO_PHONG);
                    break;
                case "Trưởng Phòng":
                    if(modifiedStaff.getPosition() == Staff.Position.TRUONG_PHONG){
                        return;
                    }
                    modifiedStaff.setPosition(Staff.Position.TRUONG_PHONG);
                    break;
                }
            break;      
        case 6: 
            switch((String)aValue){
                case "Ban Giám Đốc":
                    if(modifiedStaff.getDivision()== Staff.Division.BAN_GIAM_DOC){
                        return;
                    }
                    modifiedStaff.setDivision(Staff.Division.BAN_GIAM_DOC);
                    break;
                case "Thủ Kho":
                    if(modifiedStaff.getDivision()== Staff.Division.THU_KHO){
                        return;
                    }
                    modifiedStaff.setDivision(Staff.Division.THU_KHO);
                    break;
                case "Thủ Quỹ":
                    if(modifiedStaff.getDivision()== Staff.Division.THU_QUY){
                        return;
                    }
                    modifiedStaff.setDivision(Staff.Division.THU_QUY);
                    break;
                case "Thủ Thư":
                    if(modifiedStaff.getDivision()== Staff.Division.THU_THU){
                        return;
                    }
                    modifiedStaff.setDivision(Staff.Division.THU_THU);
                    break;
                }
            break;      
        case 7:
            if(modifiedStaff.getAddress().equals((String)aValue)){
                return;
            }
            modifiedStaff.setAddress((String)aValue);
            break;
        case 8:
            if(modifiedStaff.getPhone().equals((String)aValue)){
                return;
            }
            modifiedStaff.setPhone((String)aValue);
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
            if(BusStaff.getInstance().updateStaff(modifiedStaff)){
            JOptionPane.showMessageDialog(frame, 
                                    "Update success!",
                                    "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
            data.set(rowIndex, modifiedStaff);
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
