package com.apurv.tradecontrol.api;

import com.apurv.tradecontrol.enums.OrderStatus;

public class OrderPostingResponse {
    private String tradeID;
    private OrderStatus orderStatus;
    private Float amount;
    private Float units;

    public OrderPostingResponse() {}

    public OrderPostingResponse(String tradeID, OrderStatus orderStatus, Float amount, Float units) {
        this.tradeID = tradeID;
        this.orderStatus = orderStatus;
        this.amount = amount;
        this.units = units;
    }

    public String getTradeID() {
        return tradeID;
    }

    public void setTradeID(String tradeID) {
        this.tradeID = tradeID;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Float getUnits() {
        return units;
    }

    public void setUnits(Float units) {
        this.units = units;
    }
}
