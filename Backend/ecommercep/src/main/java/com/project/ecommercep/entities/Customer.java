package com.project.ecommercep.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

//Veritabanında "customer" tablosuna karşılık gelen Customer sınıfı.
@Entity
@Table(name = "customer")
@Data
public class Customer extends BaseEntity {

	//Müşterinin adını saklayan sütun.
	@Column(name = "first_name")
	String firstName;

	//Müşterinin soyadını saklayan sütun.
	@Column(name = "last_name")
	String lastName;

	//Müşterinin e-posta adresini saklayan sütun.
	@Column(name = "email")
	String email;

	//Müşterinin birçok alışveriş sepetine sahip olabileceğini belirten ilişki.
	@OneToMany(mappedBy = "customer")
	@JsonIgnore
	List<Cart> carts;

	//Müşterinin birçok siparişi olabileceğini belirten ilişki.
	@OneToMany(mappedBy = "customer")
	@JsonIgnore
	List<Order> orders;

	//Getter ve Setterlar..
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Customer{" +
				"id=" + getId() +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				'}';
	}

}
