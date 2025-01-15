package com.project.ecommercep.services;

import com.project.ecommercep.entities.CartItem;
import com.project.ecommercep.repos.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ecommercep.entities.Cart;
import com.project.ecommercep.entities.Customer;
import com.project.ecommercep.entities.Product;
import com.project.ecommercep.repos.CartRepository;
import com.project.ecommercep.repos.CustomerRepository;
import com.project.ecommercep.repos.ProductRepository;

import java.util.List;
import java.util.regex.Pattern;

//Servis sınıfı anatosyonu olarak işaretledim.
@Service
public class CartService {
    @Autowired
	private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    //Dependenciyleri enjekte ettiğim constructor metodum
    @Autowired
    public CartService(CartRepository cartRepository, ProductRepository productRepository, CustomerRepository customerRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    //Belirli bir müşteri için yeni bir sepet oluşturan metodum.
    public Cart createCartForCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Müşteri bulunamadı: " + customerId));
        Cart cart = new Cart();
        cart.setCustomer(customer);
        cart.setTotalPrice(0.0);
        return cartRepository.save(cart);
    }

    public boolean IsAnyCart(Long customerId){
        boolean cartVarMi = cartRepository.existsByCustomerId(customerId);
        return cartVarMi;
    }
    //Belirli bir id'ye göre sepeti getirir.
    public Cart getCartById(Long id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found with id: " + id));
    }

    //Sepeti güncelleyen metodum.
    public Cart updateCart(Cart cart) {
        return cartRepository.save(cart);
    }

    //Carta ürün ekleme
    public Cart addProductToCart(Long cartId, Long productId, int quantity) {
        Cart cart = getCartById(cartId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Ürün Id'si bulunamadı : " + productId));

        if (product.getStock() < quantity) {
            throw new IllegalArgumentException("Ürüne Ait Stoklar Tükendi, Ürün Adı: " + product.getName());
        }

        // Sepette bu ürün var mı kontrol edelim
        CartItem existingItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            existingItem.setTotalPrice(existingItem.getTotalPrice() + product.getPrice() * quantity);
            cartItemRepository.save(existingItem);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setTotalPrice(product.getPrice() * quantity);
            cart.getCartItems().add(cartItem);
            cartItemRepository.save(cartItem);
        }

        cart.setTotalPrice(cart.getCartItems().stream().mapToDouble(CartItem::getTotalPrice).sum());
        return cartRepository.save(cart);
    }

    //Sepetten ürünleri kaldıran metodum.
    public Cart removeProductFromCart(Long cartId, Long productId) {
        Cart cart = getCartById(cartId);
        CartItem cartItemToRemove = cart.getCartItems().stream()
                .filter(cartItem -> cartItem.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Ürün sepet içerisinde bulunamadı: " + productId));

        // Sepetten ürünü kaldır
        cart.getCartItems().remove(cartItemToRemove);
        cartItemRepository.delete(cartItemToRemove);

        // Toplam fiyatı yeniden hesapla
        cart.setTotalPrice(cart.getCartItems().stream().mapToDouble(CartItem::getTotalPrice).sum());

        return cartRepository.save(cart);
    }

    //sepeti tamamen boşaltır yani sepetteki tüm ürünleri kaldırır.
    public void emptyCart(Long cartId) {
        Cart cart = getCartById(cartId);
        // İlgili tüm ürün ve sepet öğelerini temizle
        cart.getCartItems().clear();
        cart.setTotalPrice(0.0);

        cartRepository.save(cart);
    }
    public Cart increaseQuantity(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Sepet Bulunamadı"));
        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cartId, productId)
                .orElseThrow(() -> new RuntimeException("CartItem bulunamadı"));
        cartItem.setQuantity(cartItem.getQuantity() + 1);
        cartItem.setTotalPrice(cartItem.getTotalPrice()+cartItem.getProduct().getPrice());
        cart.setTotalPrice(cart.getTotalPrice()+cartItem.getProduct().getPrice());
        cartItemRepository.save(cartItem);
        return cart;
    }

    public Cart decreaseQuantity(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Sepet Bulunamadı"));
        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cartId, productId)
                .orElseThrow(() -> new RuntimeException("CartItem bulunamadı"));

        if (cartItem.getQuantity() > 1) {
            cartItem.setQuantity(cartItem.getQuantity() - 1);  // Miktarı azalt
            cartItem.setTotalPrice(cartItem.getTotalPrice()-cartItem.getProduct().getPrice());
            cart.setTotalPrice(cart.getTotalPrice()-cartItem.getProduct().getPrice());
            cartItemRepository.save(cartItem);
        } else {
            cart.setTotalPrice(cart.getTotalPrice()-cartItem.getProduct().getPrice());
            removeCartItem(cartId, productId);  // Miktar 1'e düşerse ürün tamamen silinir
        }
        return cart;
    }
    public void removeCartItem(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cartId, productId)
                .orElseThrow(() -> new RuntimeException("CartItem not found"));
        cartItemRepository.delete(cartItem);  // Ürünü sepetten sil
    }
}
