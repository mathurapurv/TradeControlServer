package com.apurv.tradecontrol.controller;

import com.apurv.tradecontrol.api.OrderPostingRequest;
import com.apurv.tradecontrol.api.OrderPostingResponse;
import com.apurv.tradecontrol.entity.Asset;
import com.apurv.tradecontrol.entity.Account;
import com.apurv.tradecontrol.entity.Price;
import com.apurv.tradecontrol.entity.Trade;
import com.apurv.tradecontrol.enums.OrderStatus;
import com.apurv.tradecontrol.repository.AssetRepository;
import com.apurv.tradecontrol.repository.AccountRepository;
import com.apurv.tradecontrol.repository.PriceRepository;
import com.apurv.tradecontrol.repository.TradeRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class OrderPostingController {

    @Autowired
    private AssetRepository assetRepository;
    
    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private PriceRepository priceRepository;
    
    @Autowired
    private TradeRepository tradeRepository;

    @PostMapping("/post-order")
    public ResponseEntity<OrderPostingResponse> postOrder(@RequestBody OrderPostingRequest order) {
        // Generate a unique trade ID
        String tradeID = "TRD-" + UUID.randomUUID().toString().toUpperCase();

        // Validate cusip if provided
        if (!StringUtils.isBlank(order.getCusip())) {
            Asset asset = assetRepository.findByCusip(order.getCusip());
            if (asset == null) {
                return ResponseEntity.ok(new OrderPostingResponse(tradeID, OrderStatus.REJECTED, null, null));
            }
        }
        
        // Validate accountNumber if provided
        if (order.getAccountNumber() != null) {
            Account account = accountRepository.findByAccountNumber(order.getAccountNumber());
            if (account == null) {
                return ResponseEntity.ok(new OrderPostingResponse(tradeID, OrderStatus.REJECTED, null, null));
            }
        }
        
        // Validate units or amount - at least one must be non-zero and non-blank
        boolean validUnits = order.getUnits() != null && order.getUnits() > 0;
        boolean validAmount = order.getAmount() != null && order.getAmount() > 0;
        
        if (!validUnits && !validAmount) {
            return ResponseEntity.ok(new OrderPostingResponse(tradeID, OrderStatus.REJECTED, null, null));
        }
        
        // Get latest price for the cusip if cusip is provided
        Price latestPrice = null;
        if (!StringUtils.isBlank(order.getCusip())) {
            latestPrice = priceRepository.findFirstByCusipOrderByPriceDateDesc(order.getCusip());
        }
        
        // Calculate missing values based on price
        Float calculatedAmount = order.getAmount();
        Float calculatedUnits = order.getUnits();
        
        if (latestPrice != null && latestPrice.getPrice() != null && latestPrice.getPrice() > 0) {
            if (order.getUnits() != null && order.getUnits() > 0) {
                // Calculate amount from units * price
                calculatedAmount = order.getUnits() * latestPrice.getPrice();
            } else if (order.getAmount() != null && order.getAmount() > 0) {
                // Calculate units from amount / price
                calculatedUnits = order.getAmount() / latestPrice.getPrice();
            }
        }
        
        // Create Trade object
        Trade trade = new Trade();
        trade.setTradeID(tradeID);
        trade.setCusip(order.getCusip());
        trade.setAccountNumber(order.getAccountNumber());
        trade.setTradeType(com.apurv.tradecontrol.enums.TradeType.BUY); // Default to BUY, can be updated based on request
        trade.setUnits(calculatedUnits);
        trade.setAmount(calculatedAmount);
        
        // Save trade to repository
        tradeRepository.save(trade);
        
        // For now, assume all orders are successfully posted
        OrderStatus orderStatus = OrderStatus.SUCCESS;
        
        // Create response with calculated values
        OrderPostingResponse response = new OrderPostingResponse(
            tradeID,
            orderStatus,
            calculatedAmount,
            calculatedUnits
        );
        
        return ResponseEntity.ok(response);
    }
}
