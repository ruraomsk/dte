package ru.list.ruraomsk.editDT;

import com.tibbo.aggregate.common.context.ContextException;
import com.tibbo.aggregate.common.datatable.DataTable;
import com.tibbo.aggregate.common.datatable.EncodingUtils;
import com.tibbo.aggregate.common.datatable.FieldFormat;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Enumeration;
import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;
import ru.list.ruraomsk.editDT.defrenders.Choice;
import ru.list.ruraomsk.editDT.defrenders.HeaderColumnsRender;

public class Util
{

    public static void setRendersAndEditors(JTable table, DataTable dt)
    {

        for (int i = 0; i < dt.getFieldCount(); i++) {
            FieldFormat ff = dt.getFormat(i);
            DataTableCellRender tcr = new DataTableCellRender(dt, ff);
            DataTableCellEditor tce = new DataTableCellEditor(dt, ff);
            DataTableCellClass tccr = new DataTableCellClass(ff);
            DataTableCellClass tcce = new DataTableCellClass(ff);

            if (tce != null && tcce != null) {
                table.setDefaultEditor(tcce.getFieldClass(), tce.getEditor());
            }
            if (tcr != null && tccr != null) {
                table.setDefaultRenderer(tccr.getFieldClass(), tcr.getRender());
            }

        }

    }

    public static Class getFieldClass(FieldFormat ff)
    {
        if (ff.hasSelectionValues()) {
            return Choice.class;
        }
        if (ff.getType() == 'I') {
            return Integer.class;
        }
        if (ff.getType() == 'L') {
            return Long.class;
        }
        if (ff.getType() == 'B') {
            return Boolean.class;
        }
        if (ff.getType() == 'F') {
            return Float.class;
        }
        if (ff.getType() == 'D') {
            return Date.class;
        }
        if (ff.getType() == 'T') {
            return DataTable.class;
        }
        if (ff.getType() == 'C') {
            return Color.class;
        }
        return String.class;
//        if(ff.getType()=='A'){
//            render=new BlockRender();
//            return;

    }

    /**
     * Выгружает таблицу в XML
     *
     * @param table
     * @param namefile
     */
    public static void tableToXML(DataTable table, String namefile)
    {
        FileOutputStream rezFile = null;
        try {
            String srez = EncodingUtils.encodeToXML(table);
            rezFile = new FileOutputStream(new File(namefile));
            rezFile.write(srez.getBytes());
            rezFile.close();
        }
        catch (IOException | ParserConfigurationException | ContextException | DOMException ex) {
            System.err.println(ex.getMessage());
        }
        finally {
            try {
                rezFile.close();
            }
            catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    public static DataTable loadXML(String xmlfile)
    {
        InputStreamReader rezFile = null;
        try {
            char[] brez = new char[100000];
            rezFile = new InputStreamReader(new FileInputStream(new File(xmlfile)));
            StringBuilder sb = new StringBuilder();
            int len;
            while ((len = rezFile.read(brez, 0, brez.length)) > 0) {
                sb.append(brez, 0, len);
                //System.err.print("=");
            };
            String ss = sb.toString();
            return EncodingUtils.decodeFromXML(ss);
        }
        catch (IOException | ParserConfigurationException | ContextException | DOMException | IllegalArgumentException | SAXException ex) {
            System.err.println(ex.getMessage());
        }
        finally {
            try {
                if (rezFile == null) {
                    return null;
                }
                rezFile.close();
            }
            catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return null;
    }
    public static Dimension DIM = new Dimension(1000, 800);

    static void setColumnDefault(JTable table, DataTable dt, Sorted sorted)
    {
        TableColumnModel cm = table.getColumnModel();
//        tc.setMaxWidth(20);
        Enumeration<TableColumn> e = cm.getColumns();
        while (e.hasMoreElements()) {
            TableColumn col = (TableColumn) e.nextElement();
//            System.err.println(col.getIdentifier().toString());

            if (col.getIdentifier().equals("#")) {
                col.setMaxWidth(50);
            }
            col.setHeaderRenderer(new HeaderColumnsRender(dt, sorted));

        }
    }
}
