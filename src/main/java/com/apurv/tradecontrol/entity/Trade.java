package com.apurv.tradecontrol.entity;

import com.apurv.tradecontrol.enums.TradeType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "trades")
public class Trade {
    
    @Id
    private String id;

    private String tradeID;
    private String cusip;
    private Long accountNumber;
    private TradeType tradeType;
    private Float units;
    private Float amount;

    
    public Trade() {}
    
    public Trade(String cusip, Long accountNumber, TradeType tradeType, Float units, Float amount, String tradeID) {
        this.cusip = cusip;
        this.accountNumber = accountNumber;
        this.tradeType = tradeType;
        this.units = units;
        this.amount = amount;
        this.tradeID = tradeID;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
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
    
    public TradeType getTradeType() {
        return tradeType;
    }
    
    public void setTradeType(TradeType tradeType) {
        this.tradeType = tradeType;
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
    
    public String getTradeID() {
        return tradeID;
    }
    
    public void setTradeID(String tradeID) {
        this.tradeID = tradeID;
    }
}
