package com.furniture.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.furniture.model.Furniture;
import com.furniture.model.Order;
import com.furniture.model.User;

@Repository("loginDao")
public class LoginDaoImpl implements LoginDao{
	private static final Logger logger = LoggerFactory.getLogger(LoginDaoImpl.class);

	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public boolean authenticateUsers(String userId, String password) {
		
		return false;
	}
	@Transactional
	public List<User> findAllUsers() {
		Session session = this.sessionFactory.getCurrentSession();
		List<User> userList = session.createQuery("from com.furniture.model.User").list();
		for(User user : userList){
			logger.info("User List::"+user);
		}
		return userList;
		
	}
	@Transactional
	public User getUserById(String userId) {
		Session session= this.sessionFactory.getCurrentSession();
		Query user=  session.createQuery("from User where userId='"+userId+"'");
		logger.info("User List::"+user.list());
		
		User userById= (User) user.uniqueResult();
		return userById;
	}
	
	@Transactional
	public void deleteUserById(String userId) {
		Session session= this.sessionFactory.getCurrentSession();
		Query user=  session.createQuery("delete User where userId='"+userId+"'");
		user.executeUpdate();
	}
	
	@Transactional
	public void deleteUser() {
		Session session= this.sessionFactory.getCurrentSession();
		Query user=  session.createQuery("delete User");
		user.executeUpdate();
	}
	
	@Transactional
	public boolean isUserExist(String userId) {
		Session session= this.sessionFactory.getCurrentSession();
		Query query=  session.createQuery("from User where userId='"+userId+"'");
		if( query.list().isEmpty()){
			return false;
		}
		return true;
	}
	
	@Transactional
	public void saveUser(User user) {
		Session session= this.sessionFactory.getCurrentSession();
		session.save(user);
	}
	
	@Transactional
	public void updateUser(User currentUser) {
		Session session= this.sessionFactory.getCurrentSession();
		Query query=  session.createQuery("update User set name=?, password=?, role=? where userId=?");
		query.setString(0, currentUser.getName());
		query.setString(1, currentUser.getPassword());
		query.setString(2, currentUser.getRole());
		query.setString(3, currentUser.getUserId());
		query.executeUpdate();
	}
	
	@Transactional
	public User authenticateUser(String userId, String password) {
		Session session= this.sessionFactory.getCurrentSession();
		Query user=  session.createQuery("from User where userId='"+userId+"' and password='"+password+"'");
		logger.info("User List::"+user.list());
		User authorizedUser= (User) user.uniqueResult();
		return authorizedUser;
	}
	
	@Transactional
	public List<Furniture> getfurnitureDetails() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Furniture> furnitureDetails = session.createQuery("from com.furniture.model.Furniture").list();
		return furnitureDetails;
	}
	
	@Transactional
	public void saveOrder(Order order) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(order);
	}
	@Transactional
	public List<Order> getOrderDetails() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Order> orders= new ArrayList<Order>();
		Query orderDetails = session.createQuery("Select userId,furnitureId,quantity FROM Order GROUP BY userId, furnitureId");
		Iterator itr = orderDetails.iterate();
		while(itr.hasNext()){
			Order o= new Order();
		   Object[] obj = (Object[]) itr.next();
		   String userId = String.valueOf(obj[0]); 
		   o.setUserId(userId);
		   Integer furnitureId = Integer.parseInt(String.valueOf(obj[1])); 
		   o.setFurnitureId(furnitureId);
		   Integer quantity = Integer.parseInt(String.valueOf(obj[2]));
		   o.setQuantity(quantity);
		   orders.add(o);

		}
		return orders;
	}
	@Transactional
	public void deleteOrder(String userId, int furnitureId) {
		Session session= this.sessionFactory.getCurrentSession();
		Query user=  session.createQuery("delete Order where userId='"+userId+"' and furnitureId="+furnitureId);
		user.executeUpdate();
	}
	@Transactional
	public boolean isOrderExists(Order order) {
		Session session= this.sessionFactory.getCurrentSession();
		Query query=  session.createQuery("from Order where userId='"+order.getUserId()+"' and furnitureId="+order.getFurnitureId());
		if( query.list().isEmpty()){
			return false;
		}
		return true;
	}
	@Transactional
	public int getQuantity(Order order) {
		Session session= this.sessionFactory.getCurrentSession();
		Query query=  session.createQuery("Select quantity from Order where userId='"+order.getUserId()+"' and furnitureId="+order.getFurnitureId());
		int quantity=(Integer) query.uniqueResult();
		return quantity;
	}
	@Transactional
	public void updateOrder(Order order) {
		Session session= this.sessionFactory.getCurrentSession();
		Query query=  session.createQuery("update Order set quantity=? where userId=? and furnitureId=?");
		query.setInteger(0, order.getQuantity());
		query.setString(1, order.getUserId());
		query.setInteger(2, order.getFurnitureId());
		query.executeUpdate();		
	}
}
