/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.list.ruraomsk.editDT;

import com.tibbo.aggregate.common.datatable.DataRecord;
import com.tibbo.aggregate.common.datatable.DataTable;
import com.tibbo.aggregate.common.datatable.DataTableReplication;
import com.tibbo.aggregate.common.datatable.FieldFormat;
import com.tibbo.aggregate.common.datatable.TableFormat;
import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.TableColumnModel;

/**
 * Собственно редактор DataTable
 *
 * @author Русинов Юрий <ruraomsk@list.ru>
 */
public class EditDT 
{

    DataTable mainDT;
    DataTable workDT;
    boolean onlyread = false;
    DataTableModel dm;
    TableColumnModel cm;
    JButton bsave = new JButton();
    JButton bload = new JButton();
    JButton bdel = new JButton();
    JButton badd = new JButton();

    JButton bsortup = new JButton();

    JTextField tfind = new JTextField();
    JButton bfind = new JButton();
    JButton bOk = new JButton("Сохранить");
    JButton bCancel = new JButton("Отменить");
    JTable table;
    Sorted sorted;
    public int status = -1;
    JPanel own;
    public EditDT(JPanel own, DataTable dt, boolean onlyread) throws HeadlessException
    {
//        super(own, dt.getDescription());
        this.own=own;
        this.onlyread = onlyread;
//        this.setMinimumSize(Util.DIM);
//        this.setSize(Util.DIM);
        mainDT = dt;
        allback();
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
        own.setLayout(new BoxLayout(own, BoxLayout.Y_AXIS));

        dm = new DataTableModel(workDT);
        if (this.onlyread) {
            dm.setReadOnly();
        }
        table = new JTable(dm);
        cm = table.getColumnModel();
        sorted = new Sorted();
        Util.setRendersAndEditors(table, workDT);
        Util.setColumnDefault(table, workDT, sorted);
        JScrollPane center = new JScrollPane(table);
        JToolBar top = new JToolBar();
        top.setLayout(new BoxLayout(top, BoxLayout.X_AXIS));

        bsortup.setIcon(new ImageIcon("resource/el_next.png"));
        bsortup.setToolTipText("Сортирока по возрастанию/убыванию");
        bsortup.setFocusPainted(false);
        bsortup.addActionListener(new Sorting());
        top.add(bsortup);

        badd.setIcon(new ImageIcon("resource/dte_add_row.png"));
        badd.setToolTipText("Добавить строку");
        badd.setFocusPainted(false);
        badd.addActionListener(new AddNewRow());
        if (!onlyread) {
            top.add(badd);
        }

        bdel.setIcon(new ImageIcon("resource/dte_remove_row.png"));
        bdel.setToolTipText("Удалить строку");
        bdel.setFocusPainted(false);
        bdel.addActionListener(new DeleteRow());
        if (!onlyread) {
            top.add(bdel);
        }

        bload.setIcon(new ImageIcon("resource/dte_import.png"));
        bload.setToolTipText("Load from XML");
        bload.setFocusPainted(false);
        bload.addActionListener(new loadDataTableFromXML());
        if (!onlyread) {
            top.add(bload);
        }

        bsave.setIcon(new ImageIcon("resource/dte_export.png"));
        bsave.setToolTipText("Export to XML");
        bsave.setFocusPainted(false);
        bsave.addActionListener(new exportDataTableToXML());
        top.add(bsave);

        tfind.setMaximumSize(new Dimension(1900, 100));
        tfind.setToolTipText("Наберите строку поиска");
        top.add(tfind);

        bfind.setIcon(new ImageIcon("resource/cm_monitor.png"));
        bfind.setToolTipText("Найти...");
        bfind.setFocusPainted(false);
        bfind.addActionListener(new FindTextToDataTable());

        top.add(bfind);
//        top.setLayout(new BoxLayout(top, X_AXIS));
        JPanel bottom = new JPanel();
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));
//        bOk.setAlignmentX(LEFT_ALIGNMENT);
        bOk.addActionListener(new PressedOk());
//        bCancel.setAlignmentX(RIGHT_ALIGNMENT);
        bCancel.addActionListener(new PressedCancel());

        if (!onlyread) {
            bottom.add(bOk);
        }
        bottom.add(bCancel);

//        bottom.set
//        bottom.setLayout(new BoxLayout(top, X_AXIS));
//        JPanel editor=new JPanel();
        own.add(top);
        own.add(center);
        own.add(bottom);
        own.setVisible(true);
//        setSize(dim);
    }
    public synchronized DataTable getDataTable(){
        status=-1;
        return mainDT;
    }
    public boolean isFinished(){
        return status==0;
    }
    public void newdata()
    {
        if (!onlyread) {
            return;
        }
        workDT = mainDT.clone();
        DataTableReplication.copy(mainDT, workDT);
        dm = new DataTableModel(workDT);
        table.setModel(dm);
        cm = table.getColumnModel();
        table.updateUI();
    }

    final void allback()
    {
        workDT = mainDT.clone();
        DataTableReplication.copy(mainDT, workDT);
    }

    void acceptChange()
    {
        DataTableReplication.copy(workDT, mainDT);
    }

    private class FindTextToDataTable implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            TableFormat dtf = workDT.getFormat();
            for (int row = 0; row < workDT.getRecordCount(); row++) {
                DataRecord dr = workDT.getRecord(row);
                dm.setFinded(row, false);
                for (FieldFormat ff : dtf.getFields()) {
                    if (dr.getValueAsString(ff.getName()).contains(tfind.getText())) {
                        dm.setFinded(row, true);
                        break;
                    }
                }
            }
            table.updateUI();

        }
    }
    
    private class PressedOk implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            mainDT = workDT.clone();
            DataTableReplication.copy(workDT, mainDT);
            own.setVisible(false);
            status = 0;

        }
    }

    private class PressedCancel implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            own.setVisible(false);
            status = 1;
        }
    }

    private class AddNewRow implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (onlyread) {
                return;
            }
            int row = table.getSelectedRow();
            if (row < 0) {
                // просто создаем пустую запись
                workDT.addRecord();
            }
            else {
                TableFormat dtf = workDT.getFormat();
                DataTable temp = new DataTable(dtf);
                int wrow = 0;
                while (wrow < workDT.getRecordCount()) {
                    DataRecord dr = workDT.getRecord(wrow);
                    temp.addRecord(dr);
                    if (wrow == row) {
                        temp.addRecord(dr);
                    }
                    wrow++;
                }
                workDT = temp;
            }
            dm = new DataTableModel(workDT);
            table.setModel(dm);
            cm = table.getColumnModel();
            sorted = new Sorted();
            Util.setColumnDefault(table, workDT, sorted);
            table.updateUI();

        }
    }

    private class DeleteRow implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (onlyread) {
                return;
            }
            int row = table.getSelectedRow();
            if (row < 0 || row >= workDT.getRecordCount()) {
                return;
            }
            else {
                workDT.removeRecord(row);
            }
            table.updateUI();
            table.clearSelection();

        }
    }

    private class Sorting implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            int column = table.getSelectedColumn();
            if (table.isEditing()) {
                return;
            }
            if (column <= 1 || column > table.getColumnCount()) {
                return;
            }
            if (sorted.isSorted(column)) {
                sorted.setOrder(column, !sorted.getOrder(column));
            }
            else {
                sorted.clear();
                sorted.setOrder(column, true);
            }

            workDT.sort(workDT.getFormat(column - 1).getName(), sorted.getOrder(column));
            table.updateUI();

        }
    }

    private class exportDataTableToXML extends JDialog implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            JFileChooser jfile = new JFileChooser();
            jfile.setDialogTitle("Укажите файл для сохранения");
            ExtFileFilter ff1 = new ExtFileFilter("xml", "XML таблицы");
            jfile.addChoosableFileFilter(ff1);
            ExtFileFilter ff2 = new ExtFileFilter("txt", "текстовые файлы");
            jfile.addChoosableFileFilter(ff2);
            jfile.setApproveButtonToolTipText("Будет сохранена таблица данных");
            jfile.setApproveButtonText("Save");
            if (jfile.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                Util.tableToXML(workDT, jfile.getSelectedFile().getPath());

            }

        }
    }

    class loadDataTableFromXML extends JDialog implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            JFileChooser jfile = new JFileChooser();
            jfile.setDialogTitle("Укажите файл для загрузки");
            ExtFileFilter ff1 = new ExtFileFilter("xml", "XML таблицы");
            jfile.addChoosableFileFilter(ff1);
            ExtFileFilter ff2 = new ExtFileFilter("txt", "текстовые файлы");
            jfile.addChoosableFileFilter(ff2);
            jfile.setApproveButtonToolTipText("Будет загружена таблица данных");
            jfile.setApproveButtonText("Load");
            if (jfile.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                workDT = Util.loadXML(jfile.getSelectedFile().getPath());
                dm = new DataTableModel(workDT);
                table.setModel(dm);
                table.updateUI();
            }

        }

    }
}
