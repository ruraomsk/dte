/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.list.ruraomsk.editDT.defrenders;

import com.tibbo.aggregate.common.datatable.DataTable;
import com.tibbo.aggregate.common.datatable.FieldFormat;
import java.awt.Color;
import java.awt.Component;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Русинов Юрий <ruraomsk@list.ru>
 */
public class ChoiceRender extends JLabel implements TableCellRenderer
{
    DataTable dt;
    public ChoiceRender(DataTable dt)
    {
        this.dt=dt;
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
        FieldFormat ff = dt.getFormat(column - 1);
        Map<Object,String> map=ff.getSelectionValues();
        setText(map.get(value).toString());
        return this;
    }
    
}
