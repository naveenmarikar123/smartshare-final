package com.example.naveenmarikar.smartshare1;

import com.smartshare.Item;

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


}
