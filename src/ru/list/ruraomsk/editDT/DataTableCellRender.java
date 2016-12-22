/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.list.ruraomsk.editDT;

import com.tibbo.aggregate.common.datatable.DataTable;
import com.tibbo.aggregate.common.datatable.FieldFormat;
import java.awt.Component;
import javax.swing.table.TableCellRenderer;
import ru.list.ruraomsk.editDT.defrenders.*;

/**
 *
 * @author Русинов Юрий <ruraomsk@list.ru>
 */
class DataTableCellRender
{

    FieldFormat ff;
    DataTable dt;
    private TableCellRenderer render;

    public DataTableCellRender(DataTable dt,FieldFormat ff)
    {
        this.ff = ff;
        this.dt = dt;
        makerenders();
    }
    public TableCellRenderer getRender(){
        return render;
    }
    private void makerenders()
    {
        if (ff.hasSelectionValues()) {
            render = new ChoiceRender(dt);
            return;
        }
        if (ff.getType() == 'I') {
            render = new NumberRender();
            return;
        }
        if (ff.getType() == 'L') {
            render = new NumberRender();
            return;
        }
        if (ff.getType() == 'B') {
            render = new BooleanRender();
            return;
        }
        if (ff.getType() == 'F') {
            render = new NumberRender();
            return;
        }
        if (ff.getType() == 'D') {
            render = new DateRender();
            return;
        }
        if (ff.getType() == 'T') {
            render = new DataTableRender();
            return;
        }
        if (ff.getType() == 'C') {
            render = new ColorRender();
            return;
        }
//        if(ff.getType()=='A'){
//            render=new BlockRender();
//            return;
    }

}


