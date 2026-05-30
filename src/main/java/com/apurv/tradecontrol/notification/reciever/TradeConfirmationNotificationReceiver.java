package com.apurv.tradecontrol.notification.reciever;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class TradeConfirmationNotificationReceiver {

    private static final Logger logger = LoggerFactory.getLogger(TradeConfirmationNotificationReceiver.class);

    @KafkaListener(topics = "${topic.name.trade-confirmation}", groupId = "${spring.kafka.consumer.group-id}")
    public void receiveTradeConfirmationMessage(@Payload String message,
                                               @Header("kafka_receivedTopic") String topic,
                                               @Header("kafka_receivedPartitionId") int partition,
                                               @Header("kafka_receivedMessageKey") String key) {
        logger.info("Received trade confirmation message from topic: {}, partition: {}, key: {}", topic, partition, key);
        logger.info("Message content: {}", message);
        
        try {
            processTradeConfirmationMessage(message);
            logger.info("Successfully processed trade confirmation message");
        } catch (Exception e) {
            logger.error("Error processing trade confirmation message: {}", e.getMessage(), e);
            throw e;
        }
    }

    private void processTradeConfirmationMessage(String message) {
        logger.debug("Processing trade confirmation message: {}", message);
        
    }






}
