package com.project.ecommercep.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;

//import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

//Order.java, bir siparişi temsil eden sınıftır. Veritabanındaki orders tablosuna karşılık gelir.
@Entity
@Table(name = "orders")
@Data
public class Order extends BaseEntity {

    //Siparişin müşteriye ait olduğunu belirten ilişki.
    @ManyToOne
    @JoinColumn(name = "customer_id")
    Customer customer;

    //Siparişin bir sepete ait olduğunu belirten ilişki.
    @ManyToOne
    @JoinColumn(name = "cart_id")
    Cart cart;

    //Siparişteki ürün miktarını saklayan sütun.
    @Column(name = "quantity")
    int quantity;

    //Siparişin toplam fiyatını saklayan sütun.
    @Column(name = "total_price")
    double totalPrice;

    //Siparişin tarihini saklayan sütun.
    @Column(name = "order_date")
    Date orderDate;

    //Siparişe ait fiyat geçmişini saklayan ilişki.
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    List<PriceHistory> priceHistory;

    //Jsonda çok fazla reponse ve gereksiz şeyler dönüyodu. ezdim toStringi
    @Override
    public String toString() {
        return "Order{" +
                "id=" + getId() +
                ", customer=" + customer +
                ", cart=" + cart +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                ", orderDate=" + orderDate +
                '}';
    }

    //Getter ve Setterlar..
    public List<PriceHistory> getPriceHistory() {
        return priceHistory;
    }

    public void setPriceHistory(List<PriceHistory> priceHistory) {
        this.priceHistory = priceHistory;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }


}