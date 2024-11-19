/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author Mahmoud Farah
 */
public class Item {
    private int itemid;
    private String itemname;
    private int itemquantity;
    private int itemReorderLevel;
    private int itemStocks;
    private int supplierid;
    private String Category;
    private Supplier supplier;

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }
   

    public int getItemid() {
        return itemid;
    }

    protected void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public String getItemname() {
        return itemname;
    }

    protected void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public int getItemquantity() {
        return itemquantity;
    }

    protected void setItemquantity(int itemquantity) {
        this.itemquantity = itemquantity;
    }

    public int getItemReorderLevel() {
        return itemReorderLevel;
    }

    protected void setItemReorderLevel(int itemReorderLevel) {
        this.itemReorderLevel = itemReorderLevel;
    }

    public int getItemStocks() {
        return itemStocks;
    }

    protected void setItemStocks(int itemStocks) {
        this.itemStocks = itemStocks;
    }

    public int getSupplieid() {
        return supplier.getSupplierid();
    }

    
    
}


