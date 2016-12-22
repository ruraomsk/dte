/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.list.ruraomsk.editDT;

import com.tibbo.aggregate.common.datatable.DataTable;
import com.tibbo.aggregate.common.datatable.FieldFormat;
import java.awt.Color;
import java.util.Date;
import ru.list.ruraomsk.editDT.defrenders.BooleanRender;
import ru.list.ruraomsk.editDT.defrenders.Choice;
import ru.list.ruraomsk.editDT.defrenders.ColorRender;
import ru.list.ruraomsk.editDT.defrenders.DataTableRender;
import ru.list.ruraomsk.editDT.defrenders.DateRender;
import ru.list.ruraomsk.editDT.defrenders.NumberRender;

/**
 *
 * @author Русинов Юрий <ruraomsk@list.ru>
 */
class DataTableCellClass
{
        FieldFormat ff;
    Class fieldClass; 
    public DataTableCellClass(FieldFormat ff)
    {
        this.ff = ff;
        makeclass();
        
    }
    public Class getFieldClass(){
        return fieldClass;
    }
        private void makeclass()
    {
        if(ff.hasSelectionValues()){
            fieldClass = Choice.class;
            return;
            
        }
        if (ff.getType() == 'I') {
            fieldClass = Integer.class;
            return;
        }
        if (ff.getType() == 'L') {
            fieldClass = Long.class;
            return;
        }
        if (ff.getType() == 'B') {
            fieldClass = Boolean.class;
            return;
        }
        if (ff.getType() == 'F') {
            fieldClass = Float.class;
            return;
        }
        if (ff.getType() == 'D') {
            fieldClass = Date.class;
            return;
        }
        if (ff.getType() == 'T') {
            fieldClass = DataTable.class;
            return;
        }
        if (ff.getType() == 'C') {
            fieldClass = Color.class;
            return;
        }
        fieldClass = String.class;
        return;
//        if(ff.getType()=='A'){
//            render=new BlockRender();
//            return;
    }

}
