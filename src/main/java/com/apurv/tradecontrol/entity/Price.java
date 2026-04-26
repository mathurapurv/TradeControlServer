package com.apurv.tradecontrol.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "prices")
public class Price {
    
    @Id
    private String id;
    
    private String cusip;
    private Date priceDate;
    private String currency;
    private Float price;
    
    public Price() {}
    
    public Price(String cusip, Date priceDate, String currency, Float price) {
        this.cusip = cusip;
        this.priceDate = priceDate;
        this.currency = currency;
        this.price = price;
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
    
    public Date getPriceDate() {
        return priceDate;
    }
    
    public void setPriceDate(Date priceDate) {
        this.priceDate = priceDate;
    }
    
    public String getCurrency() {
        return currency;
    }
    
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    public Float getPrice() {
        return price;
    }
    
    public void setPrice(Float price) {
        this.price = price;
    }
}
