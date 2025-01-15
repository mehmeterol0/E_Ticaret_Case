package com.project.ecommercep.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

// Veritabanındaki "product" tablosuna karşılık gelen Product sınıfı.
@Entity
@Table(name = "product")
@Data
public class Product extends BaseEntity{

	//Ürünün adını saklayan sütun.
	@Column(name = "product_name")
	String name;

	//Ürünün fiyatını saklayan sütun.
	@Column(name = "product_price")
	double price;

	//Ürünün stok miktarını saklayan sütun.
	@Column(name = "product_stock")
	int stock;

	//Ürünün bir sepete ait olduğunu belirten ilişki.
	@ManyToOne
	@JoinColumn(name = "cart_id")
	Cart cart;

	//Ürünün resim URL'sini saklayan sütun.
	@Column(name = "image_url")
	String imageUrl;

	//Getter ve Setterlar..
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}


}