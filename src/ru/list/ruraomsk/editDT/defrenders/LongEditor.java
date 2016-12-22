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
public class LongEditor extends AbstractCellEditor
        implements TableCellEditor
{
    JTextField jtext;
        protected static final String EDIT = "edit";
        Long value;

    public LongEditor()
    {
        jtext=new JTextField();
        jtext.setActionCommand(EDIT);
    }

    @Override
    public Object getCellEditorValue()
    {
        try {
        return Long.valueOf(jtext.getText());
        }
        catch (Exception ex) {
            return 0L;
        }
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
    {
        this.value=(Long) value;
        jtext.setText(this.value.toString());
        return jtext;
    }

    
}
