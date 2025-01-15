package com.project.ecommercep.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

//Veritabanındaki "cart" tablosunu temsil eden Cart sınıfı.
@Entity
@Table(name = "cart")
@Data
public class Cart extends BaseEntity {

	//Cart sınıfının bir müşteriye ait olduğunu belirten ilişki.
	@ManyToOne
	@JoinColumn(name = "customer_id")
	Customer customer;

	//Bir sepetin birden çok siparişi olabilir.
	@OneToMany(mappedBy = "cart")
	@JsonIgnore
	List<Order> orders;

	//Bir sepetin birden çok ürünü olabilir.
	@OneToMany(mappedBy = "cart")
	@JsonIgnore
	List<Product> products;

	//Toplam fiyat bilgisini saklayan sütun.
	@Column(name = "total_price", columnDefinition = "DOUBLE DEFAULT 0.0")
	private Double totalPrice;

	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CartItem> cartItems = new ArrayList<>();


	//toStringi ezmemdeki amaç bazı sorgularda JSON çok uzun geliyordu gerekli olanları aldım.
	@Override
	public String toString() {
		return "Cart{" +
				"id=" + getId() +
				", customer=" + customer +
				", totalPrice=" + totalPrice +
				'}';
	}



	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}


	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
}