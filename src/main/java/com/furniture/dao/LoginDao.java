package com.furniture.dao;

import java.util.List;

import com.furniture.model.Furniture;
import com.furniture.model.Order;
import com.furniture.model.User;

public interface LoginDao {

	public boolean authenticateUsers(String userId, String password);
	public List<User> findAllUsers();
	public User getUserById(String userId);
	public void deleteUserById(String userId);
	public void deleteUser();
	public boolean isUserExist(String userId);
	public void saveUser(User user);
	public void updateUser(User currentUser);
	public User authenticateUser(String userId, String password);
	public List<Furniture> getfurnitureDetails();
	public void saveOrder(Order order);
	public List<Order> getOrderDetails();
	public void deleteOrder(String userId, int furnitureId);
	public boolean isOrderExists(Order order);
	public int getQuantity(Order order);
	public void updateOrder(Order order);
}
