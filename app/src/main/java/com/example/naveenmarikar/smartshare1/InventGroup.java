package com.example.naveenmarikar.smartshare1;

/**
 * Created by shiv on 22/3/15.
 */


public class InventGroup{

    private Inventory ItemSummary;
    private InventChild ItemDetail;

    public Inventory getItemSummary(){
        return ItemSummary;
    }

    public int getItemImg(){
        return ItemSummary.getImg();
    }

    public String getItemName(){
        return ItemSummary.getItem();
    }

    public String getItemStatus(){
        return ItemSummary.getItemStatus();
    }

    public void setItemSummary(Inventory itemSummary ){
        this.ItemSummary = itemSummary;
    }

    public InventChild getItems(){
        return ItemDetail;
    }

    public void setItemDetail(InventChild Items){
        this.ItemDetail = Items;
    }

    public void setItemStatus(String s ){

        ItemSummary.setItemStatus(s);
    }
}
