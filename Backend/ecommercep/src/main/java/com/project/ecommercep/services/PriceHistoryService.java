package com.project.ecommercep.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ecommercep.entities.PriceHistory;
import com.project.ecommercep.repos.PriceHistoryRepository;

//Servis sınıfı anatosyonu olarak işaretledim.
@Service
public class PriceHistoryService {

    private final PriceHistoryRepository priceHistoryRepository;

    //Dependenciyleri enjekte ettiğim constructor metodum
    @Autowired
    public PriceHistoryService(PriceHistoryRepository priceHistoryRepository) {
        this.priceHistoryRepository = priceHistoryRepository;
    }

    //Belirli bir ürüne ait fiyat geçmişini getiren metodum.
    public List<PriceHistory> getPriceHistoryByProductId(Long productId) {
        return priceHistoryRepository.findByProductId(productId);
    }

    //Yeni bir fiyat geçmişi ekleyen metodum.
    public PriceHistory addPriceHistory(PriceHistory priceHistory) {
        return priceHistoryRepository.save(priceHistory);
    }

    //Belirli bir siparişe ait fiyat geçmişlerini getiren metodum.
    public List<PriceHistory> getPriceHistoryByOrderId(Long orderId) {
        return priceHistoryRepository.findByOrderId(orderId);
    }

    // Belirli bir siparişe ait fiyat geçmişlerini getiren metodum.
    public List<PriceHistory> getPriceHistoriesByOrderId(Long orderId) {
        List<PriceHistory> priceHistories = priceHistoryRepository.findByOrderId(orderId);
        return priceHistories;
    }


}
