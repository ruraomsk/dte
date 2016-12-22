/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.list.ruraomsk.editDT.defrenders;

import com.tibbo.aggregate.common.datatable.DataTable;
import com.tibbo.aggregate.common.datatable.FieldFormat;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import ru.list.ruraomsk.editDT.Sorted;

/**
 *
 * @author Русинов Юрий <ruraomsk@list.ru>
 */
public class HeaderColumnsRender extends JLabel implements TableCellRenderer
{

    DataTable dt;
    Sorted sorted;

    public HeaderColumnsRender(DataTable dt, Sorted sorted)
    {
        this.dt = dt;
        this.sorted = sorted;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        setFont(table.getFont());
        setBackground(table.getBackground());
        setForeground(table.getForeground());
        if (column == 0) {
            setText("#");
            setToolTipText("Номер строки");
        }
        else {
            FieldFormat ff = dt.getFormat(column - 1);
            setText(sorted.getPrefics(column) + ff.getDescription());
            setToolTipText("Нажимайте для измения порядка сортировки");
        }

        return this;
    }
}
