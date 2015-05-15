package com.example.naveenmarikar.smartshare1;

/**
 * Created by shiv on 28/2/15.
 */
public class Transactions {

    private int img;
    private String item;
    private String status;
    private int callImg;
    private Transactions(){
        super();
    }

    public Transactions(int img, String item, String status, int callImg){
        this.img = img;
        this.item = item;
        this.status = status;
        this.callImg = callImg;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCallImg() {
        return callImg;
    }

    public void setCallImg(int callImg) {
        this.callImg = callImg;
    }
}
