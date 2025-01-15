package com.project.ecommercep.services;

import java.util.*;
import java.util.stream.Collectors;

import com.project.ecommercep.entities.Cart;
import com.project.ecommercep.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ecommercep.entities.Order;
import com.project.ecommercep.entities.PriceHistory;
import com.project.ecommercep.repos.OrderRepository;
import com.project.ecommercep.repos.PriceHistoryRepository;

// Servis sınıfı olarak işaretlenmiş OrderService sınıfı.
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final PriceHistoryRepository priceHistoryRepository;
    private final CartService cartService;
    private final ProductService productService;

    // Bağımlılıkları enjekte eden yapıcı yöntem.
    @Autowired
    public OrderService(OrderRepository orderRepository, PriceHistoryRepository priceHistoryRepository, CartService cartService, ProductService productService) {
        this.orderRepository = orderRepository;
        this.priceHistoryRepository = priceHistoryRepository;
        this.cartService = cartService;
        this.productService = productService;
    }

    // Sipariş oluşturan metod.
    public Order placeOrder(Order order) {
        order.setOrderDate(new Date());
        order = orderRepository.save(order);
        savePriceHistory(order); // Geçmiş fiyat bilgilerini kaydet
        return order;
    }


    // Sepetten sipariş oluşturan metod.
    public Order placeOrderFromCart(Long cartId) {
        Cart cart = cartService.getCartById(cartId);

        // Sepet boşsa sipariş oluşturulamaz
        if (cart.getCartItems().isEmpty()) {
            throw new IllegalStateException("Sepet boş, sipariş oluşturulamadı.");
        }

        Order order = new Order();
        order.setCustomer(cart.getCustomer());
        order.setCart(cart);
        order.setOrderDate(new Date());
        order.setQuantity(cart.getCartItems().size());

        // Fiyat geçmişi oluştur
        List<PriceHistory> priceHistories = cart.getCartItems().stream().map(cartItem -> {
            Product product = cartItem.getProduct();

            if (product.getStock() < cartItem.getQuantity()) {
                throw new IllegalArgumentException("Stok yetersiz: " + product.getName());
            }

            // Stok güncellemesi
            product.setStock(product.getStock() - cartItem.getQuantity());
            productService.updateProduct(product);

            // Fiyat geçmişi kaydı
            PriceHistory priceHistory = new PriceHistory();
            priceHistory.setProduct(product);
            priceHistory.setOrder(order);
            priceHistory.setPrice(product.getPrice());
            return priceHistory;
        }).collect(Collectors.toList());

        order.setPriceHistory(priceHistories);
        order.setTotalPrice(cart.getTotalPrice());

        // Sepeti temizle
        cartService.emptyCart(cartId);

        return orderRepository.save(order);
    }

    // Geçmiş fiyat bilgilerini kaydeden metod.
    private void savePriceHistory(Order order) {
        List<PriceHistory> priceHistoryList = new ArrayList<>();
        for (PriceHistory priceHistory : order.getPriceHistory()) {
            priceHistory.setOrder(order);
            priceHistoryList.add(priceHistory);
        }
        priceHistoryRepository.saveAll(priceHistoryList);
    }

    // Belirli bir siparişi ID'ye göre getiren metod.
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    // Tüm siparişleri getiren metod.
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Belirli bir müşteriye ait siparişleri getiren metod.
    public List<Order> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomer_Id(customerId);
    }
}