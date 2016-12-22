/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.list.ruraomsk.editDT.defrenders;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Русинов Юрий <ruraomsk@list.ru>
 */
public class BooleanRender extends JCheckBox implements TableCellRenderer
{

    public BooleanRender()
    {
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        if(isSelected){
            setOpaque(true);
            setBackground(table.getSelectionBackground());
        } else{
            setOpaque(false);
        }
        setSelected((Boolean)value);
        return this;
    }
    
}
