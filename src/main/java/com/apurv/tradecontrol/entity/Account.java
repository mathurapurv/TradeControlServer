package com.apurv.tradecontrol.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "accounts")
public class Account {
    
    @Id
    private String id;
    
    private Long accountNumber;
    private String registeredOrgName;
    private Boolean active;
    
    public Account() {}
    
    public Account(Long accountNumber, String registeredOrgName, Boolean active) {
        this.accountNumber = accountNumber;
        this.registeredOrgName = registeredOrgName;
        this.active = active;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public Long getAccountNumber() {
        return accountNumber;
    }
    
    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    public String getRegisteredOrgName() {
        return registeredOrgName;
    }
    
    public void setRegisteredOrgName(String registeredOrgName) {
        this.registeredOrgName = registeredOrgName;
    }
    
    public Boolean getActive() {
        return active;
    }
    
    public void setActive(Boolean active) {
        this.active = active;
    }
}
