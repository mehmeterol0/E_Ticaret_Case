package com.project.ecommercep.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommercep.entities.PriceHistory;

public interface PriceHistoryRepository extends JpaRepository<PriceHistory, Long> {
	List<PriceHistory> findByProductId(Long productId);
	List<PriceHistory> findByOrderId(Long orderId);
	List<PriceHistory> findByOrderIdIn(List<Long> orderIds);


}
