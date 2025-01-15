package com.project.ecommercep.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.ecommercep.entities.Product;
import com.project.ecommercep.services.ProductService;

//CORS izinleri tanımladım (Securityi kaldırdım spring başlarken bunu da yazmam gerekli). CartController sınıfı.
@CrossOrigin(origins = "http://localhost:3000") //reactte gostermek icin crossorgin yaptim.
@RestController
@RequestMapping("/api/products")
public class ProductController {

	private final ProductService productService;

    //Dependenciyleri enjekte ettiğim constructor metodum
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //Yeni bir ürün oluşturan endpoint.
    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    //Belirli bir ürün ID'sine sahip ürünü getiren endpoint.
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Tüm ürünleri getiren endpoint.
    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok().body(products);
    }

    //Ürünü güncelleyen endpoint.
    @PutMapping("/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(product);
        return ResponseEntity.ok().body(updatedProduct);
    }

    //Belirli bir ürün ID'sine sahip ürünü silen endpoint.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
