/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author Mahmoud Farah
 */
public class Supplier {
    
    private Item item;
    private int supplierid;
    private String suppliername;
    private int itemsupplied;
    

    public int getSupplierid() {
        return supplierid;
    }

    protected void setSupplierid(int supplierid) {
        this.supplierid = supplierid;
    }

    public String getSuppliername() {
        return suppliername;
    }

    protected void setSuppliername(String suppliername) {
        this.suppliername = suppliername;
    }

    public int itemsupplied() {
        
        return item.getItemid();
    }
     protected void itemsupplied(int itemsupplied) {
        this.itemsupplied = itemsupplied;
     }

   
      
}
