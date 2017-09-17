package com.gremadex.constructionloanmanager.model;

/**
 * Created by Shashank on 17/9/2017.
 */
public class LoanDetails {

    private String bankName;

    private Double amount;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
