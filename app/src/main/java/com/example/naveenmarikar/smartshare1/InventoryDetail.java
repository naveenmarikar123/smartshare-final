package com.example.naveenmarikar.smartshare1;

/**
 * Created by shiv on 25/3/15.
 */
public class InventoryDetail {

    private int itemImg;
    private int itemRating;
    private String itemName;
    private String category;
    private String description;
    private int itemRatingByOwner;
    private int ratingCount;
    private int involvedIn;
    private String itemStatus;
    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public InventoryDetail(){super();}

    public int getItemImg() {
        return itemImg;
    }

    public void setItemImg(int itemImg) {
        this.itemImg = itemImg;
    }

    public int getItemRating() {
        return itemRating;
    }

    public void setItemRating(int itemRating) {
        this.itemRating = itemRating;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getItemRatingByOwner() {
        return itemRatingByOwner;
    }

    public void setItemRatingByOwner(int itemRatingByOwner) {
        this.itemRatingByOwner = itemRatingByOwner;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public int getInvolvedIn() {
        return involvedIn;
    }

    public void setInvolvedIn(int involvedIn) {
        this.involvedIn = involvedIn;
    }

    public InventoryDetail(String itemName, String category, int involvedIn, int img, int itemRatingByOwner, int rating,
                           int ratingCount, String description  ){
        this.itemName = itemName ;
        this.category = category ;
        this.involvedIn = involvedIn;
        this.itemImg = img ;
        this.itemRatingByOwner = itemRatingByOwner;
        this.itemRating = rating ;
        this.ratingCount = ratingCount ;
        this.description = description;

    }

}
