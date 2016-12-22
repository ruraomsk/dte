/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.list.ruraomsk.editDT.defrenders;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Русинов Юрий <ruraomsk@list.ru>
 */
public class FloatEditor extends AbstractCellEditor
        implements TableCellEditor
{
    JTextField jtext;
        protected static final String EDIT = "edit";
        Float value;

    public FloatEditor()
    {
        jtext=new JTextField();
        jtext.setActionCommand(EDIT);
    }

    @Override
    public Object getCellEditorValue()
    {
        try {
        return Float.valueOf(jtext.getText());
        }
        catch (Exception ex) {
            return 0f;
        }
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
    {
        
        this.value=(Float) value;
        jtext.setFont(table.getFont());
        jtext.setText(this.value.toString());
        return jtext;
    }

    
}
