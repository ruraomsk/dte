/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.list.ruraomsk.editDT.defrenders;

import com.tibbo.aggregate.common.datatable.DataTable;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Русинов Юрий <ruraomsk@list.ru>
 */
public class DataTableRender  extends JLabel implements TableCellRenderer
{

    public DataTableRender()
    {
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        setFont(table.getFont());
        if(isSelected){
            setOpaque(true);
            setBackground(table.getSelectionBackground());
        } else{
            setOpaque(false);
        }
        DataTable dt=(DataTable) value;
        if(dt!=null){
        setText("Data Table "+Integer.toString(dt.getRecordCount())+" rows");
        } else{
            setText("Data Table is empty");
        }
        return this;

    }
    
}
