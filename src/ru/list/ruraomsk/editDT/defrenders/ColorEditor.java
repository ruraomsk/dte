/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.list.ruraomsk.editDT.defrenders;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Русинов Юрий <ruraomsk@list.ru>
 */
public class ColorEditor extends AbstractCellEditor
        implements TableCellEditor,
        ActionListener
{

    Color currentColor;
    JButton button;
    JColorChooser colorChooser;
    JDialog dialog;
    protected static final String EDIT = "edit";

    public ColorEditor()
    {
        button = new JButton();
        button.setActionCommand(EDIT);
        button.addActionListener(this);
        button.setBorderPainted(false);

        colorChooser = new JColorChooser();
        dialog = JColorChooser.createDialog(button,
                "Выберите цвет",
                true, //modal
                colorChooser,
                this, //OK button handler
                null); //no CANCEL button handler
    }

    /**
     * Handles events from the editor button and from the dialog's OK button.
     */
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        if (EDIT.equals(e.getActionCommand()))
        {
            //The user has clicked the cell, so
            //bring up the dialog.
            button.setBackground(currentColor);
            colorChooser.setColor(currentColor);
            dialog.setVisible(true);

            //Make the renderer reappear.
            fireEditingStopped();

        } else
        { //User pressed dialog's "OK" button.
            currentColor = colorChooser.getColor();
        }
    }

    //Implement the one CellEditor method that AbstractCellEditor doesn't.
    @Override
    public Object getCellEditorValue()
    {
        return currentColor;
    }

    //Implement the one method defined by TableCellEditor.
    @Override
    public Component getTableCellEditorComponent(JTable table,
            Object value,
            boolean isSelected,
            int row,
            int column)
    {
        currentColor = (Color) value;
        return button;
    }
}
