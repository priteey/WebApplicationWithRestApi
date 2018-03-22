package com.furniture.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="furniture")
public class Furniture implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="furnitureId")
	int furnitureId;
	@Column(name="type")
	String type;
	@Column(name="price")
	String price;
	@Column(name="quantity")
	int quantity;
	@Column(name="description")
	String description;
	
public int getFurnitureId() {
	return furnitureId;
}
public void setFurnitureId(int furnitureId) {
	this.furnitureId = furnitureId;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getPrice() {
	return price;
}
public void setPrice(String price) {
	this.price = price;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}

}
