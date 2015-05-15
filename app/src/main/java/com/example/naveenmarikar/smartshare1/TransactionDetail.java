package com.example.naveenmarikar.smartshare1;

/**
 * Created by shiv on 20/4/15.
 */

//Waiting -Waiting for owner's approval or rejection
//Accepted -Owner accepted the request but waiting for the exchange to take place
//Declined -Owner declined the request
//Borrowed -The item is with borrower
//Complete -The item is borrowed and then returned to owner.

public class TransactionDetail {

    private int transactionId;
    private int giverId;
    private int takerId;
    private long requestDate;
    private long borrowDate;
    private long DueDate;
    private String transactionStatus;
    private boolean isBroken;
    private boolean isEnhanced;
    private double giverRating;
    private double takerRating;


    public TransactionDetail(){super();}

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public void setGiverId(int giverId) {
        this.giverId = giverId;
    }

    public void setTakerId(int takerId) {
        this.takerId = takerId;
    }

    public void setRequestDate(long requestDate) {
        this.requestDate = requestDate;
    }

    public void setBorrowDate(long borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setDueDate(long dueDate) {
        DueDate = dueDate;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public void setBroken(boolean isBroken) {
        this.isBroken = isBroken;
    }

    public void setEnhanced(boolean isEnhanced) {
        this.isEnhanced = isEnhanced;
    }

    public void setGiverRating(float giverRating) {
        this.giverRating = giverRating;
    }

    public void setTakerRating(float takerRating) {
        this.takerRating = takerRating;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public int getGiverId() {
        return giverId;
    }

    public int getTakerId() {
        return takerId;
    }

    public long getRequestDate() {
        return requestDate;
    }

    public long getBorrowDate() {
        return borrowDate;
    }

    public long getDueDate() {
        return DueDate;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public boolean isBroken() {
        return isBroken;
    }

    public boolean isEnhanced() {
        return isEnhanced;
    }

    public double getGiverRating() {
        return giverRating;
    }

    public double getTakerRating() {
        return takerRating;
    }

    public TransactionDetail(int transactionId,
                                  int giverId, int takerId, long requestDate,
                                  long borrowDate, long dueDate, String transactionStatus,
                                  boolean isBroken, boolean isEnhanced, double giverRating, double takerRating) {
        this.transactionId = transactionId;
        this.giverId = giverId;
        this.takerId = takerId;
        this.requestDate = requestDate;
        this.borrowDate = borrowDate;
        this.DueDate = dueDate;
        this.transactionStatus = transactionStatus;
        this.isBroken = isBroken;
        this.isEnhanced = isEnhanced;
        this.giverRating = giverRating;
        this.takerRating = takerRating;
    }

}
