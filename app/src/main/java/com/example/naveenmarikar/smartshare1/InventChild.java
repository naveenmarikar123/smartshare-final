package com.example.naveenmarikar.smartshare1;

/**
 * Created by shiv on 22/3/15.
 */
public class InventChild {

    private InventoryDetail itemDetail;

    public int getImage() { return itemDetail.getItemImg();}

    public int getRating() {return itemDetail.getItemRating();}

    public String getName() { return itemDetail.getItemName() ;}

    public String getCategory() {return itemDetail.getCategory();}

    public int getInvolvedIn() {return itemDetail.getInvolvedIn();}

    public int getItemRatingByOwner() {return itemDetail.getItemRatingByOwner();}

    public String getDescription() {return itemDetail.getDescription();}

    public int getRatingCount() {return itemDetail.getRatingCount();}

    public void setItemDetail(InventoryDetail itemDetail){
        this.itemDetail =  itemDetail;
    }

    public String getItemStatus(){return itemDetail.getItemStatus();}
}