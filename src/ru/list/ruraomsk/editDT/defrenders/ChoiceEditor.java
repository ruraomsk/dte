/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.list.ruraomsk.editDT.defrenders;

import com.tibbo.aggregate.common.datatable.DataTable;
import com.tibbo.aggregate.common.datatable.FieldFormat;
import java.awt.Component;
import java.util.Map;
import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Русинов Юрий <ruraomsk@list.ru>
 */
public class ChoiceEditor extends AbstractCellEditor
        implements TableCellEditor
{

    DataTable dt;
    JComboBox<String> combo;
    Map<Object, String> map;
    Object oldvalue;

    public ChoiceEditor(DataTable dt)
    {
        this.dt = dt;
    }

    @Override
    public Object getCellEditorValue()
    {
        String res = (String) combo.getSelectedItem();
        for (Object value : map.keySet()) {
            if (res.equals(map.get(value))) {
                return value;
            }
        }
        return oldvalue;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
    {
        oldvalue = value;
        combo = new JComboBox<>();
        FieldFormat ff = dt.getFormat(column - 1);
        map=ff.getSelectionValues();
        for (String sit : map.values()) {
            combo.addItem(sit);
        }
        return combo;
    }

}
