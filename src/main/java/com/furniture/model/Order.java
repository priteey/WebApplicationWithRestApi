package com.furniture.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class Order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	@Column(name="userId")
	String userId;
	@Column(name="furnitureId")
	int furnitureId;
	@Column(name="quantity")
	int quantity;
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public int getFurnitureId() {
	return furnitureId;
}
public void setFurnitureId(int furnitureId) {
	this.furnitureId = furnitureId;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}

}
