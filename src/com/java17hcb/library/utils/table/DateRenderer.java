package com.java17hcb.library.utils.table;

import java.awt.Component;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class DateRenderer extends JLabel implements TableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        LocalDate localDate = (LocalDate)value;
        setText(localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        return this;
    }   
}
