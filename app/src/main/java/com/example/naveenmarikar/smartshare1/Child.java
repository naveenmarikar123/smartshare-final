package com.example.naveenmarikar.smartshare1;

/**
 * Created by shiv on 20/4/15.
 */
public class Child {

    private TransactionDetail transactionDetail;

    public void setTransactionDetail(TransactionDetail transactionDetail) {
        this.transactionDetail = transactionDetail;
    }

    public int getTransactionId() {
        return transactionDetail.getTransactionId();
    }

    public int getGiverId() {
        return transactionDetail.getGiverId();
    }

    public int getTakerId() {
        return transactionDetail.getTakerId();
    }

    public long getRequestDate() {
        return transactionDetail.getRequestDate();
    }

    public long getBorrowDate() {
        return transactionDetail.getBorrowDate();
    }

    public long getDueDate() {
        return transactionDetail.getDueDate();
    }

    public String getTransactionStatus() {
        return transactionDetail.getTransactionStatus();
    }

    public boolean isBroken() {
        return transactionDetail.isBroken();
    }

    public boolean isEnhanced() {
        return transactionDetail.isEnhanced();
    }

    public double getGiverRating() {return transactionDetail.getGiverRating();}

    public double getTakerRating() {
        return transactionDetail.getTakerRating();
    }

    public TransactionDetail getTransactionDetail() {
        return transactionDetail;
    }

}
