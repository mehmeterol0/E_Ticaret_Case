package com.project.ecommercep.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

//Veritabanındaki diğer sınıflar tarafından kullanılmak BaseEntity sınıfı.
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	//Veritabanında her bir kayıt için id oluşturma.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Long id;

	//Getter ve Setterlar..
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


}