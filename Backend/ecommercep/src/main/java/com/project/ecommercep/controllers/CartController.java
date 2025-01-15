package com.project.ecommercep.controllers;

import com.project.ecommercep.responses.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.ecommercep.entities.Cart;
import com.project.ecommercep.services.CartService;
import org.springframework.web.server.ResponseStatusException;

//CORS izinleri tanımladım (Securityi kaldırdım spring başlarken bunu da yazmam gerekli). CartController sınıfı.
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    //Dependenciyleri enjekte ettiğim constructor metodum
    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    //Belirli bir müşteri için yeni bir sepet oluşturan endpoint.
    @PostMapping("/create/{customerId}")
    public ResponseEntity<String> createCartForCustomer(@PathVariable Long customerId) {

        boolean existingCart = cartService.IsAnyCart(customerId);
        if (existingCart) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Zaten bu kullanıcıya ait bir sepet var.");
        }
        Cart createdCart = cartService.createCartForCustomer(customerId);
        return ResponseEntity.status(HttpStatus.CREATED).body("Sepet Oluşturuldu");
    }

    //Belirli bir sepeti ID'ye göre getiren endpoint.
    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable Long id) {
        Cart cart = cartService.getCartById(id);
        return ResponseEntity.ok().body(cart);
    }

    //Sepeti güncelleyen endpoint.
    @PutMapping("/update")
    public ResponseEntity<Cart> updateCart(@RequestBody Cart cart) {
        Cart updatedCart = cartService.updateCart(cart);
        return ResponseEntity.ok().body(updatedCart);
    }

    //Sepete ürün ekleyen endpoint.
    @PostMapping("/{cartId}/products/{productId}")
    public ResponseEntity<Cart> addProductToCart(
            @PathVariable Long cartId,
            @PathVariable Long productId,
            @RequestParam(defaultValue = "1") int quantity) {
        Cart updatedCart = cartService.addProductToCart(cartId, productId, quantity);
        return ResponseEntity.ok().body(updatedCart);
    }

    //Sepetten ürün çıkaran endpoint.
    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/{cartId}/products/{productId}")
    public ResponseEntity<Cart> removeProductFromCart(@PathVariable Long cartId, @PathVariable Long productId) {
        Cart updatedCart = cartService.removeProductFromCart(cartId, productId);
        return ResponseEntity.ok().body(updatedCart);
    }

    //Sepeti boşaltan endpoint.
    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/{id}/empty")
    public ResponseEntity<Void> emptyCart(@PathVariable Long id) {
        cartService.emptyCart(id);
        return ResponseEntity.noContent().build();
    }
    //Sepette ürün miktarını arttıran endpoint
    @PutMapping("/carts/{cartId}/products/{productId}/increase")
    public ResponseEntity<Cart> increaseQuantity(@PathVariable Long cartId, @PathVariable Long productId) {
        Cart cart = cartService.increaseQuantity(cartId, productId);
        return ResponseEntity.ok(cart);
    }
    //Sepette ürün miktarını azaltan endpoint
    @PutMapping("/carts/{cartId}/products/{productId}/decrease")
    public ResponseEntity<Cart> decreaseQuantity(@PathVariable Long cartId, @PathVariable Long productId) {
        Cart cart = cartService.decreaseQuantity(cartId, productId);
        return ResponseEntity.ok(cart);
    }

}
