package com.smartshare;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;



public class UserDetails {

	

	@JsonProperty("userId")
	int userId;
	@JsonProperty("facebookId")
	String facebookId;
	@JsonProperty("fullName")
	String fullName;
	@JsonProperty("email")
	String email;
	@JsonProperty("password")
	String password;
	@JsonProperty("mobileNumber")
	String mobileNumber;
	@JsonProperty("address")
	String address;

	//Location details
	@JsonProperty("currentLongitude")
	double currentLongitude; 
	@JsonProperty("currentLatitude")
	double currentLatitude; 
	@JsonProperty("permanentLongitude")
	double permanentLongitude; 
	@JsonProperty("permanentLatitude")
	double permanentLatitude;	
	@JsonProperty("locationRadiusPreferred")
	double locationRadiusPreferred;

	//Rating details
	@JsonProperty("giveCount")
	int giveCount;
	@JsonProperty("borrowCount")
	int borrowCount;
	@JsonProperty("numberOfBreaks")
	int numberOfBreaks;
	@JsonProperty("numberOfEnhancements")
	int numberOfEnhancements;	
	@JsonProperty("averageRating")
	float averageRating;
	@JsonProperty("unifiedRating")
	float unifiedRating;
	
	//List of items
	@JsonProperty("listOfItems")
	List<Item> listOfItems;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getCurrentLongitude() {
		return currentLongitude;
	}

	public void setCurrentLongitude(double currentLongitude) {
		this.currentLongitude = currentLongitude;
	}

	public double getCurrentLatitude() {
		return currentLatitude;
	}

	public void setCurrentLatitude(double currentLatitude) {
		this.currentLatitude = currentLatitude;
	}

	public double getPermanentLongitude() {
		return permanentLongitude;
	}

	public void setPermanentLongitude(double permanentLongitude) {
		this.permanentLongitude = permanentLongitude;
	}

	public double getPermanentLatitude() {
		return permanentLatitude;
	}

	public void setPermanentLatitude(double permanentLatitude) {
		this.permanentLatitude = permanentLatitude;
	}

	public double getLocationRadiusPreferred() {
		return locationRadiusPreferred;
	}

	public void setLocationRadiusPreferred(double locationRadiusPreferred) {
		this.locationRadiusPreferred = locationRadiusPreferred;
	}

	public int getGiveCount() {
		return giveCount;
	}

	public void setGiveCount(int giveCount) {
		this.giveCount = giveCount;
	}

	public int getBorrowCount() {
		return borrowCount;
	}

	public void setBorrowCount(int borrowCount) {
		this.borrowCount = borrowCount;
	}

	public int getNumberOfBreaks() {
		return numberOfBreaks;
	}

	public void setNumberOfBreaks(int numberOfBreaks) {
		this.numberOfBreaks = numberOfBreaks;
	}

	public int getNumberOfEnhancements() {
		return numberOfEnhancements;
	}

	public void setNumberOfEnhancements(int numberOfEnhancements) {
		this.numberOfEnhancements = numberOfEnhancements;
	}

	public float getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(float averageRating) {
		this.averageRating = averageRating;
	}

	public float getUnifiedRating() {
		return unifiedRating;
	}

	public void setUnifiedRating(float unifiedRating) {
		this.unifiedRating = unifiedRating;
	}

	public List<Item> getListOfItems() {
		return listOfItems;
	}

	public void setListOfItems(List<Item> listOfItems) {
		this.listOfItems = listOfItems;
	}


}
