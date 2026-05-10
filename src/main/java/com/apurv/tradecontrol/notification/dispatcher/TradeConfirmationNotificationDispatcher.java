package com.apurv.tradecontrol.notification.dispatcher;

import com.apurv.tradecontrol.entity.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class TradeConfirmationNotificationDispatcher {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value(value = "${topic.name.trade-confirmation}")
    private String tradeConfirmationTopicName;

    public void dispatch(Trade trade){
        tradeConfirmationNotification(trade);
    }


    public void  tradeConfirmationNotification(Trade trade) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(tradeConfirmationTopicName, trade.getTradeID());
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + trade.getTradeID() +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" +
                        trade.getTradeID() + "] due to : " + ex.getMessage());
            }
        });
    }






}
