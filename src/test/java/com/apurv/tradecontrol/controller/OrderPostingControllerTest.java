package com.apurv.tradecontrol.controller;

import com.apurv.tradecontrol.api.OrderPostingRequest;
import com.apurv.tradecontrol.api.OrderPostingResponse;
import com.apurv.tradecontrol.entity.Account;
import com.apurv.tradecontrol.entity.Asset;
import com.apurv.tradecontrol.entity.Price;
import com.apurv.tradecontrol.entity.Trade;
import com.apurv.tradecontrol.enums.OrderStatus;
import com.apurv.tradecontrol.repository.AccountRepository;
import com.apurv.tradecontrol.repository.AssetRepository;
import com.apurv.tradecontrol.repository.PriceRepository;
import com.apurv.tradecontrol.repository.TradeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderPostingControllerTest {

    @Mock
    private AssetRepository assetRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private PriceRepository priceRepository;

    @Mock
    private TradeRepository tradeRepository;

    @InjectMocks
    private OrderPostingController orderPostingController;

    private OrderPostingRequest validRequest;
    private Asset testAsset;
    private Account testAccount;
    private Price testPrice;

    @BeforeEach
    void setUp() {
        validRequest = new OrderPostingRequest();
        validRequest.setCusip("US12345678");
        validRequest.setAccountNumber(123456789L);
        validRequest.setUnits(100.0f);
        validRequest.setAmount(null);
        validRequest.setTradeType("BUY");

        testAsset = new Asset();
        testAsset.setCusip("US12345678");

        testAccount = new Account();
        testAccount.setAccountNumber(123456789L);

        testPrice = new Price();
        testPrice.setCusip("US12345678");
        testPrice.setPrice(50.0f);
    }

    @Test
    void testPostOrder_SuccessWithUnits() {
        // Arrange
        when(assetRepository.findByCusip("US12345678")).thenReturn(testAsset);
        when(accountRepository.findByAccountNumber(123456789L)).thenReturn(testAccount);
        when(priceRepository.findFirstByCusipOrderByPriceDateDesc("US12345678")).thenReturn(testPrice);
        when(tradeRepository.save(any(Trade.class))).thenReturn(new Trade());

        // Act
        ResponseEntity<OrderPostingResponse> response = orderPostingController.postOrder(validRequest);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        OrderPostingResponse responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(OrderStatus.SUCCESS, responseBody.getOrderStatus());
        assertNotNull(responseBody.getTradeID());
        assertEquals(5000.0f, responseBody.getAmount()); // 100 units * 50 price
        assertEquals(100.0f, responseBody.getUnits());
        
        verify(tradeRepository, times(1)).save(any(Trade.class));
    }

    @Test
    void testPostOrder_SuccessWithAmount() {
        // Arrange
        validRequest.setUnits(null);
        validRequest.setAmount(5000.0f);
        
        when(assetRepository.findByCusip("US12345678")).thenReturn(testAsset);
        when(accountRepository.findByAccountNumber(123456789L)).thenReturn(testAccount);
        when(priceRepository.findFirstByCusipOrderByPriceDateDesc("US12345678")).thenReturn(testPrice);
        when(tradeRepository.save(any(Trade.class))).thenReturn(new Trade());

        // Act
        ResponseEntity<OrderPostingResponse> response = orderPostingController.postOrder(validRequest);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        OrderPostingResponse responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(OrderStatus.SUCCESS, responseBody.getOrderStatus());
        assertNotNull(responseBody.getTradeID());
        assertEquals(5000.0f, responseBody.getAmount());
        assertEquals(100.0f, responseBody.getUnits()); // 5000 amount / 50 price
        
        verify(tradeRepository, times(1)).save(any(Trade.class));
    }

    @Test
    void testPostOrder_RejectedWhenAssetNotFound() {
        // Arrange
        when(assetRepository.findByCusip("US12345678")).thenReturn(null);

        // Act
        ResponseEntity<OrderPostingResponse> response = orderPostingController.postOrder(validRequest);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        OrderPostingResponse responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(OrderStatus.REJECTED, responseBody.getOrderStatus());
        assertNotNull(responseBody.getTradeID());
        assertNull(responseBody.getAmount());
        assertNull(responseBody.getUnits());
        
        verify(tradeRepository, never()).save(any(Trade.class));
    }

    @Test
    void testPostOrder_RejectedWhenAccountNotFound() {
        // Arrange
        when(assetRepository.findByCusip("US12345678")).thenReturn(testAsset);
        when(accountRepository.findByAccountNumber(123456789L)).thenReturn(null);

        // Act
        ResponseEntity<OrderPostingResponse> response = orderPostingController.postOrder(validRequest);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        OrderPostingResponse responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(OrderStatus.REJECTED, responseBody.getOrderStatus());
        assertNotNull(responseBody.getTradeID());
        assertNull(responseBody.getAmount());
        assertNull(responseBody.getUnits());
        
        verify(tradeRepository, never()).save(any(Trade.class));
    }

    @Test
    void testPostOrder_RejectedWhenBothUnitsAndAmountAreInvalid() {
        // Arrange
        validRequest.setUnits(null);
        validRequest.setAmount(null);
        
        when(assetRepository.findByCusip("US12345678")).thenReturn(testAsset);
        when(accountRepository.findByAccountNumber(123456789L)).thenReturn(testAccount);

        // Act
        ResponseEntity<OrderPostingResponse> response = orderPostingController.postOrder(validRequest);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        OrderPostingResponse responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(OrderStatus.REJECTED, responseBody.getOrderStatus());
        assertNotNull(responseBody.getTradeID());
        assertNull(responseBody.getAmount());
        assertNull(responseBody.getUnits());
        
        verify(tradeRepository, never()).save(any(Trade.class));
    }

    @Test
    void testPostOrder_RejectedWhenUnitsAreZero() {
        // Arrange
        validRequest.setUnits(0.0f);
        validRequest.setAmount(null);
        
        when(assetRepository.findByCusip("US12345678")).thenReturn(testAsset);
        when(accountRepository.findByAccountNumber(123456789L)).thenReturn(testAccount);

        // Act
        ResponseEntity<OrderPostingResponse> response = orderPostingController.postOrder(validRequest);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        OrderPostingResponse responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(OrderStatus.REJECTED, responseBody.getOrderStatus());
        
        verify(tradeRepository, never()).save(any(Trade.class));
    }

    @Test
    void testPostOrder_RejectedWhenAmountIsZero() {
        // Arrange
        validRequest.setUnits(null);
        validRequest.setAmount(0.0f);
        
        when(assetRepository.findByCusip("US12345678")).thenReturn(testAsset);
        when(accountRepository.findByAccountNumber(123456789L)).thenReturn(testAccount);

        // Act
        ResponseEntity<OrderPostingResponse> response = orderPostingController.postOrder(validRequest);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        OrderPostingResponse responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(OrderStatus.REJECTED, responseBody.getOrderStatus());
        
        verify(tradeRepository, never()).save(any(Trade.class));
    }

    @Test
    void testPostOrder_SuccessWithoutCusip() {
        // Arrange
        validRequest.setCusip(null);
        validRequest.setUnits(100.0f);
        validRequest.setAmount(5000.0f);
        
        when(accountRepository.findByAccountNumber(123456789L)).thenReturn(testAccount);
        when(tradeRepository.save(any(Trade.class))).thenReturn(new Trade());

        // Act
        ResponseEntity<OrderPostingResponse> response = orderPostingController.postOrder(validRequest);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        OrderPostingResponse responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(OrderStatus.SUCCESS, responseBody.getOrderStatus());
        assertNotNull(responseBody.getTradeID());
        assertEquals(5000.0f, responseBody.getAmount());
        assertEquals(100.0f, responseBody.getUnits());
        
        verify(tradeRepository, times(1)).save(any(Trade.class));
        verify(assetRepository, never()).findByCusip(any());
        verify(priceRepository, never()).findFirstByCusipOrderByPriceDateDesc(any());
    }

    @Test
    void testPostOrder_SuccessWithoutAccountNumber() {
        // Arrange
        validRequest.setAccountNumber(null);
        validRequest.setUnits(100.0f);
        validRequest.setAmount(5000.0f);
        
        when(assetRepository.findByCusip("US12345678")).thenReturn(testAsset);
        when(tradeRepository.save(any(Trade.class))).thenReturn(new Trade());

        // Act
        ResponseEntity<OrderPostingResponse> response = orderPostingController.postOrder(validRequest);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        OrderPostingResponse responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(OrderStatus.SUCCESS, responseBody.getOrderStatus());
        assertNotNull(responseBody.getTradeID());
        assertEquals(5000.0f, responseBody.getAmount());
        assertEquals(100.0f, responseBody.getUnits());
        
        verify(tradeRepository, times(1)).save(any(Trade.class));
        verify(accountRepository, never()).findByAccountNumber(any());
    }

    @Test
    void testPostOrder_SuccessWithUnitsButNoPrice() {
        // Arrange
        when(assetRepository.findByCusip("US12345678")).thenReturn(testAsset);
        when(accountRepository.findByAccountNumber(123456789L)).thenReturn(testAccount);
        when(priceRepository.findFirstByCusipOrderByPriceDateDesc("US12345678")).thenReturn(null);
        when(tradeRepository.save(any(Trade.class))).thenReturn(new Trade());

        // Act
        ResponseEntity<OrderPostingResponse> response = orderPostingController.postOrder(validRequest);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        OrderPostingResponse responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(OrderStatus.SUCCESS, responseBody.getOrderStatus());
        assertNotNull(responseBody.getTradeID());
        assertNull(responseBody.getAmount()); // No price to calculate amount
        assertEquals(100.0f, responseBody.getUnits());
        
        verify(tradeRepository, times(1)).save(any(Trade.class));
    }

    @Test
    void testPostOrder_SuccessWithAmountButNoPrice() {
        // Arrange
        validRequest.setUnits(null);
        validRequest.setAmount(5000.0f);
        
        when(assetRepository.findByCusip("US12345678")).thenReturn(testAsset);
        when(accountRepository.findByAccountNumber(123456789L)).thenReturn(testAccount);
        when(priceRepository.findFirstByCusipOrderByPriceDateDesc("US12345678")).thenReturn(null);
        when(tradeRepository.save(any(Trade.class))).thenReturn(new Trade());

        // Act
        ResponseEntity<OrderPostingResponse> response = orderPostingController.postOrder(validRequest);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        OrderPostingResponse responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(OrderStatus.SUCCESS, responseBody.getOrderStatus());
        assertNotNull(responseBody.getTradeID());
        assertEquals(5000.0f, responseBody.getAmount());
        assertNull(responseBody.getUnits()); // No price to calculate units
        
        verify(tradeRepository, times(1)).save(any(Trade.class));
    }

    @Test
    void testPostOrder_TradeIDFormat() {
        // Arrange
        when(assetRepository.findByCusip("US12345678")).thenReturn(testAsset);
        when(accountRepository.findByAccountNumber(123456789L)).thenReturn(testAccount);
        when(tradeRepository.save(any(Trade.class))).thenReturn(new Trade());

        // Act
        ResponseEntity<OrderPostingResponse> response = orderPostingController.postOrder(validRequest);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        OrderPostingResponse responseBody = response.getBody();
        assertNotNull(responseBody);
        assertNotNull(responseBody.getTradeID());
        assertTrue(responseBody.getTradeID().startsWith("TRD-"));
        assertTrue(responseBody.getTradeID().length() > 4); // Should have UUID part
    }

    @Test
    void testPostOrder_WithBlankCusip() {
        // Arrange
        validRequest.setCusip("");
        validRequest.setUnits(100.0f);
        validRequest.setAmount(5000.0f);
        
        when(accountRepository.findByAccountNumber(123456789L)).thenReturn(testAccount);
        when(tradeRepository.save(any(Trade.class))).thenReturn(new Trade());

        // Act
        ResponseEntity<OrderPostingResponse> response = orderPostingController.postOrder(validRequest);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        OrderPostingResponse responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(OrderStatus.SUCCESS, responseBody.getOrderStatus());
        
        verify(assetRepository, never()).findByCusip(any());
        verify(priceRepository, never()).findFirstByCusipOrderByPriceDateDesc(any());
    }
}
