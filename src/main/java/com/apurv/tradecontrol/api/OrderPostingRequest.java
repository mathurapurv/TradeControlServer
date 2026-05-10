package com.apurv.tradecontrol.api;

public class OrderPostingRequest {
    private String cusip;
    private Long accountNumber;
    private Float units;
    private Float amount;
    private String tradeType;

    public OrderPostingRequest() {}

    public OrderPostingRequest(String cusip, Long accountNumber, Float units, Float amount, String tradeType) {
        this.cusip = cusip;
        this.accountNumber = accountNumber;
        this.units = units;
        this.amount = amount;
        this.tradeType = tradeType;
    }

    public String getCusip() {
        return cusip;
    }

    public void setCusip(String cusip) {
        this.cusip = cusip;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Float getUnits() {
        return units;
    }

    public void setUnits(Float units) {
        this.units = units;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }
}
