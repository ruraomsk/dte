/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.list.ruraomsk.dte;

import com.tibbo.aggregate.common.datatable.DataTable;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import ru.list.ruraomsk.editDT.EditDT;
import ru.list.ruraomsk.editDT.ExtFileFilter;
import ru.list.ruraomsk.editDT.Util;

/**
 *
 * @author Русинов Юрий <ruraomsk@list.ru>
 */
public class Dte extends JFrame
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        if (args.length > 0) {
            nametable = args[0];
        }
        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame.setDefaultLookAndFeelDecorated(true);
                new Dte();
            }
        });
    }      // TODO code application logic here
    static String nametable = null;

    private Dte()
    {
        DataTable dt;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenSize.height -= 50;
        screenSize.width -= 50;
        this.setSize(screenSize);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        if (nametable == null) {
            JFileChooser jfile = new JFileChooser();
            jfile.setDialogTitle("Укажите файл для загрузки");
            ExtFileFilter ff1 = new ExtFileFilter("xml", "XML таблицы");
            jfile.addChoosableFileFilter(ff1);
            ExtFileFilter ff2 = new ExtFileFilter("txt", "текстовые файлы");
            jfile.addChoosableFileFilter(ff2);
            jfile.setApproveButtonToolTipText("Будет загружена таблица данных");
            jfile.setApproveButtonText("Load");
            if (jfile.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                nametable = jfile.getSelectedFile().getPath();
            }

        }
        if(nametable==null){
            System.exit(1);
        }
        dt = Util.loadXML(nametable);
        JPanel jPan=new JPanel();
        this.add(jPan);
        EditDT ed = new EditDT(jPan, dt, false);
//        setVisible(true);

    }
}
