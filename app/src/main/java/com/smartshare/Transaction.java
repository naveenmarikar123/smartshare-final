package com.smartshare;

import org.codehaus.jackson.annotate.JsonProperty;

public class Transaction {
	
	@JsonProperty("transactionId")
	int transactionId;
	@JsonProperty("giverFacebookId")
	String giverFacebookId;
	@JsonProperty("takerFacebookId")
	String takerFacebookId;
	@JsonProperty("requestDate")
	long requestDate;
	@JsonProperty("borrowDate")
	long borrowDate;
	@JsonProperty("dueDate")
	long dueDate;
	
	//Waiting -Waiting for owner's approval or rejection
	//Accepted -Owner accepted the request but waiting for the exchange to take place
	//Declined -Owner declined the request
	//Borrowed -The item is with borrower
	//Complete -The item is borrowed and then returned to owner.
	@JsonProperty("transactionStatus")
	String transactionStatus;
	
	@JsonProperty("isBroken")
	boolean isBroken;
	@JsonProperty("isEnhanced")
	boolean isEnhanced;
	@JsonProperty("giverRating")
	float giverRating;
	@JsonProperty("takerRating")
	float takerRating;

}
