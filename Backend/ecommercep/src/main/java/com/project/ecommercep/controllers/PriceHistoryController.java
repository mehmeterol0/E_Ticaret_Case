package com.project.ecommercep.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.ecommercep.entities.PriceHistory;
import com.project.ecommercep.services.PriceHistoryService;

//CORS izinleri tanımladım (Securityi kaldırdım spring başlarken bunu da yazmam gerekli). CartController sınıfı.
@CrossOrigin(origins = "http://localhost:3000") //reactte gostermek icin crossorgin yaptim.
@RestController
@RequestMapping("/price-history")
public class PriceHistoryController {
    private final PriceHistoryService priceHistoryService;

    //Dependenciyleri enjekte ettiğim constructor metodum
    @Autowired
    public PriceHistoryController(PriceHistoryService priceHistoryService) {
        this.priceHistoryService = priceHistoryService;
    }

    //Belirli bir ürün için fiyat geçmişini getiren endpoint.
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<PriceHistory>> getPriceHistoryByProductId(@PathVariable Long productId) {
        List<PriceHistory> priceHistoryList = priceHistoryService.getPriceHistoryByProductId(productId);
        return ResponseEntity.ok().body(priceHistoryList);
    }

    //Yeni bir fiyat geçmişi ekleyen endpoint.
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/add")
    public ResponseEntity<PriceHistory> addPriceHistory(@RequestBody PriceHistory priceHistory) {
        PriceHistory addedPriceHistory = priceHistoryService.addPriceHistory(priceHistory);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedPriceHistory);
    }

    //Belirli bir sipariş için fiyat geçmişini getiren endpoint.
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<PriceHistory>> getPriceHistoryByOrderId(@PathVariable Long orderId) {
        List<PriceHistory> priceHistoryList = priceHistoryService.getPriceHistoryByOrderId(orderId);
        return ResponseEntity.ok().body(priceHistoryList);
    }

    //Belirli sipariş ID'leri için fiyat geçmişlerini getiren endpoint.
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/orders")
    public ResponseEntity<List<Map<String, Object>>> getPriceHistoryByOrderIds(@RequestParam List<Long> orderIds) {
        List<Map<String, Object>> result = new ArrayList<>();
        for (Long orderId : orderIds) {
            List<PriceHistory> priceHistoryList = priceHistoryService.getPriceHistoriesByOrderId(orderId);
            for (PriceHistory priceHistory : priceHistoryList) {
                Map<String, Object> item = new HashMap<>();
                item.put("productName", priceHistory.getProduct().getName());
                item.put("price", priceHistory.getPrice());
                item.put("orderDate", priceHistory.getOrder().getOrderDate());
                item.put("orderId",priceHistory.getOrder().getId());
                result.add(item);
            }
        }
        return ResponseEntity.ok().body(result);
    }





}
