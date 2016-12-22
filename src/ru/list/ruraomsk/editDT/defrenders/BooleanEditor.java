/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.list.ruraomsk.editDT.defrenders;

import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Русинов Юрий <ruraomsk@list.ru>
 */
public class BooleanEditor extends AbstractCellEditor
        implements TableCellEditor
{

    JCheckBox button;
    protected static final String EDIT = "edit";
    Boolean value;
    public BooleanEditor()
    {
        button = new JCheckBox();
        button.setBorderPainted(false);
    }


    //Implement the one CellEditor method that AbstractCellEditor doesn't.
    @Override
    public Object getCellEditorValue()
    {
        return button.isSelected();
    }

    //Implement the one method defined by TableCellEditor.
    @Override
    public Component getTableCellEditorComponent(JTable table,
            Object value,
            boolean isSelected,
            int row,
            int column)
    {
        button.setSelected((Boolean) value);
        button.setFont(table.getFont());
        button.setBackground(table.getBackground());
        return button;
    }
}
