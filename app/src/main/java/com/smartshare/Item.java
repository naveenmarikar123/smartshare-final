package com.smartshare;

import org.codehaus.jackson.annotate.JsonProperty;

public class Item {
	
	@JsonProperty("itemId")
    public int itemId;
	
	@JsonProperty("itemOwnerId")
	public int itemOwnerId;
	
	@JsonProperty("itemName")
	public String itemName;
	
	@JsonProperty("itemType")
	public String itemType;
	
	@JsonProperty("itemDescription")
	public String itemDescription;
	
	@JsonProperty("itemStatus")
	public String itemStatus;
	
	@JsonProperty("itemRating")
	public float itemRating;
	
	@JsonProperty("totalBorrows")
	public int totalBorrows;
	
	@JsonProperty("totalRatings")
	public int totalRatings;
	
	@JsonProperty("itemCost")
	public float itemCost;
	
	@JsonProperty("itemCostUnit")
	public String itemCostUnit;
	
	@JsonProperty("ownerName")
	public String ownerName;
	
	@JsonProperty("facebookId")
	public String facebookId;
	
	@JsonProperty("ownerRating")
	public float ownerRating;
	
	@JsonProperty("approximateDistance")
	public float approximateDistance;
	
	@JsonProperty("locationLatitude")
	public double locationLatitude;
	
	@JsonProperty("locationLongitude")
	public double locationLongitude;
	

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getItemOwnerId() {
		return itemOwnerId;
	}

	public void setItemOwnerId(int itemOwnerId) {
		this.itemOwnerId = itemOwnerId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}

	public float getItemRating() {
		return itemRating;
	}

	public void setItemRating(float itemRating) {
		this.itemRating = itemRating;
	}

	public int getTotalBorrows() {
		return totalBorrows;
	}

	public void setTotalBorrows(int totalBorrows) {
		this.totalBorrows = totalBorrows;
	}

	public int getTotalRatings() {
		return totalRatings;
	}

	public void setTotalRatings(int totalRatings) {
		this.totalRatings = totalRatings;
	}

	public float getItemCost() {
		return itemCost;
	}

	public void setItemCost(float itemCost) {
		this.itemCost = itemCost;
	}

	public String getItemCostUnit() {
		return itemCostUnit;
	}

	public void setItemCostUnit(String itemCostUnit) {
		this.itemCostUnit = itemCostUnit;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public float getOwnerRating() {
		return ownerRating;
	}

	public void setOwnerRating(float ownerRating) {
		this.ownerRating = ownerRating;
	}

	public float getApproximateDistance() {
		return approximateDistance;
	}

	public void setApproximateDistance(float approximateDistance) {
		this.approximateDistance = approximateDistance;
	}

	public double getLocationLatitude() {
		return locationLatitude;
	}

	public void setLocationLatitude(double locationLatitude) {
		this.locationLatitude = locationLatitude;
	}

	public double getLocationLongitude() {
		return locationLongitude;
	}

	public void setLocationLongitude(double locationLongitude) {
		this.locationLongitude = locationLongitude;
	}
	
	/*@JsonProperty("")
	public ;
	
	@JsonProperty("")
	public ;*/
	
	

}
