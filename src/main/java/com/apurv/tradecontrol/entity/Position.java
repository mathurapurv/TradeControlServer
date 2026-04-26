package com.apurv.tradecontrol.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "positions")
public class Position {
    
    @Id
    private String id;
    
    private String cusip;
    private Long accountNumber;
    private Float holding;
    private Date asOfDate;
    
    public Position() {}
    
    public Position(String cusip, Long accountNumber, Float holding, Date asOfDate) {
        this.cusip = cusip;
        this.accountNumber = accountNumber;
        this.holding = holding;
        this.asOfDate = asOfDate;
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
    
    public Float getHolding() {
        return holding;
    }
    
    public void setHolding(Float holding) {
        this.holding = holding;
    }
    
    public Date getAsOfDate() {
        return asOfDate;
    }
    
    public void setAsOfDate(Date asOfDate) {
        this.asOfDate = asOfDate;
    }
}
