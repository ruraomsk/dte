/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.list.ruraomsk.editDT;

import com.tibbo.aggregate.common.datatable.DataTable;
import com.tibbo.aggregate.common.datatable.FieldFormat;
import javax.swing.table.TableCellEditor;
import ru.list.ruraomsk.editDT.defrenders.BooleanEditor;
import ru.list.ruraomsk.editDT.defrenders.Choice;
import ru.list.ruraomsk.editDT.defrenders.ChoiceEditor;
import ru.list.ruraomsk.editDT.defrenders.ColorEditor;
import ru.list.ruraomsk.editDT.defrenders.DataTableEditor;
import ru.list.ruraomsk.editDT.defrenders.DateEditor;
import ru.list.ruraomsk.editDT.defrenders.FloatEditor;
import ru.list.ruraomsk.editDT.defrenders.IntegerEditor;
import ru.list.ruraomsk.editDT.defrenders.LongEditor;

/**
 *
 * @author Русинов Юрий <ruraomsk@list.ru>
 */
class DataTableCellEditor
{

    TableCellEditor editor;
    DataTable dt;
    FieldFormat ff;

    public DataTableCellEditor(DataTable dt, FieldFormat ff)
    {
        this.dt = dt;
        this.ff = ff;
        makeeditors();
    }

    public TableCellEditor getEditor()
    {
        return editor;
    }

    private void makeeditors()
    {
        if (ff.hasSelectionValues()) {
            editor = new ChoiceEditor(dt);
            return;
        }
        if (ff.getType() == 'I') {
            editor = new IntegerEditor();
            return;
        }
        if (ff.getType() == 'L') {
            editor = new LongEditor();
            return;
        }
        if (ff.getType() == 'B') {
            editor = new BooleanEditor();
            return;
        }
        if (ff.getType() == 'F') {
            editor = new FloatEditor();
            return;
        }
        if (ff.getType() == 'D') {
            editor = new DateEditor();
            return;
        }
        if (ff.getType() == 'T') {
            editor = new DataTableEditor();
            return;
        }
        if (ff.getType() == 'C') {
            editor = new ColorEditor();
            return;
        }
    }
}
