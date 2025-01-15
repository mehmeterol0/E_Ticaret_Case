package com.project.ecommercep.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

//PriceHistory.java, fiyat geçmişini temsil eden bir sınıftır. Veritabanında price_history tablosuna denk gelir.
@Entity
@Table(name = "price_history")
@Data
public class PriceHistory extends BaseEntity{

    //Fiyat geçmişindeki ürünü belirten ilişki.
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    //Fiyat geçmişindeki siparişi belirten ilişki.
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    //Fiyat geçmişindeki fiyatı saklayan sütun.
    @Column(name = "price")
    private double price;

    //Getter ve Setterlar..
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}

