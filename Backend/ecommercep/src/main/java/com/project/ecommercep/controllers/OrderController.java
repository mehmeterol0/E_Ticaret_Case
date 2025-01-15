package com.project.ecommercep.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.ecommercep.entities.Order;
import com.project.ecommercep.services.OrderService;

//CORS izinleri tanımladım (Securityi kaldırdım spring başlarken bunu da yazmam gerekli). CartController sınıfı.
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    //Dependenciyleri enjekte ettiğim constructor metodum
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //Yeni bir sipariş oluşturan endpoint.
    @PostMapping("/place")
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        return ResponseEntity.ok().body(orderService.placeOrder(order));
    }

    //Belirli bir siparişi ID'ye göre getiren endpoint.
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) { //GetOrderForCode servisi yerine kullanilmistir.
        return orderService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Tüm siparişleri getiren endpoint.
    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok().body(orderService.getAllOrders());
    }

    //Belirli bir müşteriye ait siparişleri getiren endpoint.
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Order>> GetAllOrdersForCustomer(@PathVariable Long customerId) {
        List<Order> orders = orderService.getOrdersByCustomerId(customerId);
        return ResponseEntity.ok().body(orders);
    }


    //Bir sepetten sipariş oluşturan endpoint.
    @PostMapping("/from-cart/{cartId}")
    public ResponseEntity<Order> placeOrderFromCart(@PathVariable Long cartId) {
        Order order = orderService.placeOrderFromCart(cartId);
        return ResponseEntity.ok().body(order);
    }


}
