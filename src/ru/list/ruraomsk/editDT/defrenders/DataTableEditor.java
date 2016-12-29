/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.list.ruraomsk.editDT.defrenders;

import com.tibbo.aggregate.common.datatable.DataTable;
import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;
import ru.list.ruraomsk.editDT.EditDT;
import ru.list.ruraomsk.editDT.Util;

/**
 *
 * @author Русинов Юрий <ruraomsk@list.ru>
 */
public class DataTableEditor extends AbstractCellEditor
        implements TableCellEditor
{

    JPanel editDT;
    DataTable value;
    JTextField jtext;
    
    public DataTableEditor()
    {
        jtext=new JTextField();
    }


    //Implement the one CellEditor method that AbstractCellEditor doesn't.
    @Override
    public Object getCellEditorValue()
    {
        return value;
    }

    //Implement the one method defined by TableCellEditor.
    @Override
    public Component getTableCellEditorComponent(JTable table,
            Object value,
            boolean isSelected,
            int row,
            int column)
    {
        
        this.value = (DataTable) value;
        if(value==null) {
            jtext.setText("DataTable is empty");
            return jtext;
        }
        JFrame frame=new JFrame("Data Table");
        editDT=new JPanel();
        frame.setSize(Util.DIM);
        EditDT edit=new EditDT(editDT,this.value,false);
        frame.add(editDT);
        frame.setVisible(true);
        return jtext;
    }
}
