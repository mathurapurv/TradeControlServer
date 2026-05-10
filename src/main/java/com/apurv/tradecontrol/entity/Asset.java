package com.apurv.tradecontrol.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "assets")
public class Asset {
    
    @Id
    private String id;
    
    private String cusip;
    private String fundName;
    private Date launchDate;
    private Boolean active;
    
    public Asset() {}
    
    public Asset(String cusip, String fundName, Date launchDate, Boolean active) {
        this.cusip = cusip;
        this.fundName = fundName;
        this.launchDate = launchDate;
        this.active = active;
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
    
    public String getFundName() {
        return fundName;
    }
    
    public void setFundName(String fundName) {
        this.fundName = fundName;
    }
    
    public Date getLaunchDate() {
        return launchDate;
    }
    
    public void setLaunchDate(Date launchDate) {
        this.launchDate = launchDate;
    }
    
    public Boolean getActive() {
        return active;
    }
    
    public void setActive(Boolean active) {
        this.active = active;
    }
}
