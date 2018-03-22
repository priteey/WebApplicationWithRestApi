package com.furniture.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.furniture.dao.LoginDao;
import com.furniture.model.Furniture;
import com.furniture.model.Order;
import com.furniture.model.User;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao loginDao;
	

	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}

	public boolean authenticateUsers(String userId, String password) {
		
		return false;
	}

	public List<User> findAllUsers() {
		List<User> users=loginDao.findAllUsers();
		return users;
	}

	public User getUserById(String userId) {
		User user= loginDao.getUserById(userId);
		return user;
	}

	public void deleteUserById(String userId) {
		loginDao.deleteUserById(userId);
	}

	public void deleteUser() {
		loginDao.deleteUser();
	}

	public boolean isUserExist(String userId) {
		return loginDao.isUserExist(userId);
	}

	public void saveUser(User user) {
		loginDao.saveUser(user);		
	}

	public void updateUser(User currentUser) {
		loginDao.updateUser(currentUser);		
	}

	public User authenticateUser(String userId, String password) {
		User user= loginDao.authenticateUser(userId, password);
		return user;
	}

	public List<Furniture> getfurnitureDetails() {
		List<Furniture> furnitureDetails= loginDao.getfurnitureDetails();
		return furnitureDetails;
	}

	public void saveOrder(Order order) {
		loginDao.saveOrder(order);		
		
	}

	public List<Order> getOrderDetails() {
		List<Order> orderDetails= loginDao.getOrderDetails();
		return orderDetails;
	}

	public void deleteOrder(String userId, int furnitureId) {
		loginDao.deleteOrder(userId,furnitureId);
		
	}

	public boolean isOrderExists(Order order) {
		return loginDao.isOrderExists(order);
	}

	public int getQuantity(Order order) {
		int quantity=loginDao.getQuantity(order);
		return quantity;
	}

	public void updateOrder(Order order) {
		loginDao.updateOrder(order);
	}

}
