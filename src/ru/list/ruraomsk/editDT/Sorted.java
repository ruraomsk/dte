/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.list.ruraomsk.editDT;

/**
 *
 * @author Русинов Юрий <ruraomsk@list.ru>
 */
public class Sorted
{
    int column;
    boolean order;

    public Sorted()
    {
        column=-1;
    }
    public boolean isSorted(int column){
        return this.column==column;
    }
    public String getPrefics(int column){
        return (this.column==column?(order?"^ ":"v "):"  ");
    }
    public boolean getOrder(int column){
        return (this.column==column?order:false);
    }
    public void setOrder(int column, boolean order){
        this.column=column;
        this.order=order;
    }
    public void clear(){
        column=-1;
    }
    
}
