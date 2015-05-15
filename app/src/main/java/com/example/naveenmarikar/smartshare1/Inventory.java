package com.example.naveenmarikar.smartshare1;

/**
 * Created by shiv on 23/3/15.
 */
public class Inventory {

    private int img;
    private String item;
    private String itemStatus;
        // 0 hidden
        // 1 shared
        // 2 out


    private Inventory(){
        super();
    }

    public Inventory(int img, String item, String status){
        this.img = img;
        this.item = item;
        this.itemStatus = status;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }
}
