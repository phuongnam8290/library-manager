package com.java17hcb.library.utils.table;

import java.awt.Component;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

public class NumberRenderer extends JLabel implements TableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        DecimalFormat decinalFormat = new DecimalFormat("#,### đ");
        setText(decinalFormat.format((Long)value));
        setHorizontalAlignment(SwingConstants.RIGHT);
        return this;
    }
    
}