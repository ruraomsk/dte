/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.list.ruraomsk.editDT.defrenders;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Date;
import javax.swing.AbstractCellEditor;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Русинов Юрий <ruraomsk@list.ru>
 */
public class DateEditor extends AbstractCellEditor
        implements TableCellEditor
{

    JFormattedTextField jtext;
    protected static final String EDIT = "edit";
    Date value;

    public DateEditor()
    {
        jtext = new JFormattedTextField();
        jtext.setText("####/##/## ##:##:##.###");
        jtext.setActionCommand(EDIT);
    }

    @Override
    public Object getCellEditorValue()
    {
        try {
        return new Date(Timestamp.valueOf(jtext.getText()).getTime());
        }
        catch (Exception ex) {
            return new Date();
        }
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
    {
        this.value = (Date) value;
        jtext.setText(this.value.toString());
        return jtext;
    }

}
