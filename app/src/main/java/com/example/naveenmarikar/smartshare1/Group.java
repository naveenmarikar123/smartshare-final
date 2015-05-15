package com.example.naveenmarikar.smartshare1;

/**
 * Created by shiv on 20/4/15.
 */


public class Group {

    public Transactions transactionSummary;
    public Child transactionDetail;

    public Child getTransactionDetail() {
        return transactionDetail;
    }

    public int getImg() {
        return transactionSummary.getImg();
    }

    public String getItem() {
        return transactionSummary.getItem();
    }

    public String getStatus() {
        return transactionSummary.getStatus();
    }

    public void setTransactionSummary(Transactions transactionSummary) {
        this.transactionSummary = transactionSummary;
    }

    public void setTransactionDetail(Child transactionDetail) {
        this.transactionDetail = transactionDetail;
    }

    public int getCallImg() {
        return transactionSummary.getCallImg();
    }



}
