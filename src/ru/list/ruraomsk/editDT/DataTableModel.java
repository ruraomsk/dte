/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.list.ruraomsk.editDT;

import com.tibbo.aggregate.common.datatable.DataRecord;
import com.tibbo.aggregate.common.datatable.DataTable;
import com.tibbo.aggregate.common.datatable.FieldFormat;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Русинов Юрий <ruraomsk@list.ru>
 */
public class DataTableModel extends DefaultTableModel
{

    private DataTable dt = null;
    private boolean readonly = false;
    private boolean[] finded;

    public DataTableModel(DataTable dt)
    {
        this.dt = dt;
        finded=new boolean[dt.getRecordCount()];
        for(boolean b:finded){
            b=false;
        }
    }
    public void setFinded(int row,boolean status){
        finded[row]=status;
    }
    /**
     *
     * @return
     */
    public void setReadOnly()
    {
        readonly = true;

    }

    @Override
    public int getRowCount()
    {
        if (dt == null) {
            return 0;
        }
        return dt.getRecordCount();
    }

    @Override
    public int getColumnCount()
    {
        return dt.getFieldCount() + 1;
    }

    @Override
    public String getColumnName(int columnIndex)
    {
        if (columnIndex == 0) {
            return "#";
        }
        return dt.getFormat(columnIndex - 1).getDescription();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        if (columnIndex == 0) {
            return String.class;
        }
        
        FieldFormat ff = dt.getFormat(columnIndex - 1);

        return Util.getFieldClass(ff);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        if (columnIndex == 0) {
            return false;
        }
        return !readonly;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        if (columnIndex == 0) {
            return Integer.toString(rowIndex + 1)+(finded[columnIndex]?"<---":"");
        }
        if (rowIndex < 0 || rowIndex > dt.getRecordCount()) {
            return null;
        }
        DataRecord rd = dt.getRecord(rowIndex);
        //if(dt.getFormat(columns[columnIndex]).getFieldClass().)
        return rd.getValue(columnIndex - 1);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        if (columnIndex == 0) {
            return;
        }
        if (rowIndex < 0 || rowIndex > dt.getRecordCount()) {
            return;
        }
        DataRecord rd = dt.getRecord(rowIndex);
        try {
            rd.setValue(columnIndex - 1, aValue);
        }
        catch (Exception ex) {
            System.err.println("Ошибка " + ex.getMessage());
        }
    }

}
