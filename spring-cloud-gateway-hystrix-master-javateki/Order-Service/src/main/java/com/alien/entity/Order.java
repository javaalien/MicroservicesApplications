package com.alien.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_tbl")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	
	
	@Id
	private int id;
	private String name;
	private int qty;
	private double price;

}
