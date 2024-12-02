package com.alien.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ORDERS_TBL")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String category;
	private String color;
	private double price;

	public Order(String name, String category, String color, double price) {
		this.name = name;
		this.category = category;
		this.color = color;
		this.price = price;
	}
}
