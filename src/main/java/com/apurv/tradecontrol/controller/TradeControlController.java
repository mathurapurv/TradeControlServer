package com.apurv.tradecontrol.controller;

import com.apurv.tradecontrol.entity.*;
import com.apurv.tradecontrol.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trade-control")
public class TradeControlController {

    @Autowired
    private AssetRepository assetRepository;
    
    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private PriceRepository priceRepository;
    
    @Autowired
    private PositionRepository positionRepository;
    
    @Autowired
    private TradeRepository tradeRepository;

    // Asset endpoints
    @GetMapping("/assets")
    public ResponseEntity<List<Asset>> getAllAssets() {
        List<Asset> assets = assetRepository.findAll();
        return ResponseEntity.ok(assets);
    }

    @PostMapping("/add/assets")
    public ResponseEntity<Asset> createAsset(@RequestBody Asset asset) {
        Asset savedAsset = assetRepository.save(asset);
        return ResponseEntity.ok(savedAsset);
    }

    // Account endpoints
    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return ResponseEntity.ok(accounts);
    }

    @PostMapping("/add/accounts")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account savedAccount = accountRepository.save(account);
        return ResponseEntity.ok(savedAccount);
    }

    // Price endpoints
    @GetMapping("/prices")
    public ResponseEntity<List<Price>> getAllPrices() {
        List<Price> prices = priceRepository.findAll();
        return ResponseEntity.ok(prices);
    }

    @PostMapping("/add/prices")
    public ResponseEntity<Price> createPrice(@RequestBody Price price) {
        Price savedPrice = priceRepository.save(price);
        return ResponseEntity.ok(savedPrice);
    }

    // Position endpoints
    @GetMapping("/positions")
    public ResponseEntity<List<Position>> getAllPositions() {
        List<Position> positions = positionRepository.findAll();
        return ResponseEntity.ok(positions);
    }

    @PostMapping("/add/positions")
    public ResponseEntity<Position> createPosition(@RequestBody Position position) {
        Position savedPosition = positionRepository.save(position);
        return ResponseEntity.ok(savedPosition);
    }

    // Trade endpoints
    @GetMapping("/trades")
    public ResponseEntity<List<Trade>> getAllTrades() {
        List<Trade> trades = tradeRepository.findAll();
        return ResponseEntity.ok(trades);
    }

    @PostMapping("/add/trades")
    public ResponseEntity<Trade> createTrade(@RequestBody Trade trade) {
        Trade savedTrade = tradeRepository.save(trade);
        return ResponseEntity.ok(savedTrade);
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Trade Control Server is running");
    }
}
