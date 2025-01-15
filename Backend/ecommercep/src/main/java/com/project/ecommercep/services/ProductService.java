package com.project.ecommercep.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ecommercep.entities.Product;
import com.project.ecommercep.repos.ProductRepository;


//Servis sınıfı anatosyonu olarak işaretledim.
@Service
public class ProductService {
    private final ProductRepository productRepository;

    //Dependenciyleri enjekte ettiğim constructor metodum
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //Yeni bir ürün oluşturan metodum.
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    //Belirli bir ID'ye göre ürünü getiren metodum.
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    //Belirli bir ürün adına göre ürünü getiren metodum.
    public Product getProductByName(String productName) {
        return productRepository.findByName(productName);
    }

    //Tüm ürünleri getiren metodum.
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    //Ürünü güncelleyen metodum.
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    //Belirli bir ID'ye göre ürünü silen metodum.
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
