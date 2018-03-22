package com.furniture.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.furniture.dao.LoginDaoImpl;
import com.furniture.model.Furniture;
import com.furniture.model.Order;
import com.furniture.model.User;
import com.furniture.service.LoginService;

@RestController
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginDaoImpl.class);
	
	@Autowired
	private LoginService loginService;
	

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	/**
	 * Authenticate Users
	 * @param userId
	 * @param password
	 * @param request
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="user/{userId}/{password}", method = RequestMethod.GET)
	public ModelAndView authenticateUser(@RequestParam("userId") String userId, @RequestParam("password") String password, HttpServletRequest request) throws ServletException, IOException{
		HttpSession session = request.getSession();
		User user = loginService.authenticateUser(userId, password);
		ModelAndView mav = null;
		if (user == null) {
			mav = new ModelAndView("index");
		} else if (user.getRole().equals("Customer")) {
			List<Furniture> furnitureDetails = loginService.getfurnitureDetails();
			session.setAttribute("userId", userId);
			mav=new ModelAndView("customerHomePage");
			mav.addObject("furnitureDetails", furnitureDetails);
		} else {
			List<Order> orderDetails = loginService.getOrderDetails();
			mav = new ModelAndView("adminHomePage");
			mav.addObject("orderDetails", orderDetails);
		}
		return mav;
	}
	/**
	 * To save orders
	 * @param quantity
	 * @param furnitureId
	 */
	@RequestMapping(value="users/{quantity}/{furnitureId}", method=RequestMethod.POST)
	public void saveOrder( @RequestParam("quantity") String quantity, @RequestParam("furnitureId") String furnitureId, HttpServletRequest request){
		HttpSession session = request.getSession();
		Order order=new Order();
		int furnitureID=Integer.parseInt(furnitureId);
		order.setFurnitureId(furnitureID);
		order.setUserId((String)session.getAttribute("userId"));
		quantity = quantity.replace(",", "");
		int quant=Integer.parseInt(quantity);
		if(loginService.isOrderExists(order)){
			int currentQuantity=loginService.getQuantity(order);
			quant=currentQuantity+quant;
			order.setQuantity(quant);
			loginService.updateOrder(order);
		}
		else{
		order.setQuantity(quant);
		loginService.saveOrder(order);
		}
	}
	/**
	 * Deletes the order
	 * @param user
	 * @param action
	 * @param userId
	 * @param furnitureId
	 */
	@RequestMapping(value="user/delete/{userId}/{furnitureId}", method = RequestMethod.DELETE)
	public void deleteOrder(@RequestParam("user") String user,@RequestParam("action") String action, @RequestParam("userId") String userId,@RequestParam("furnitureId") int furnitureId){
		loginService.deleteOrder(userId, furnitureId);
	}
	
	/**
	 * Creates new user
	 * @param user
	 * @return
	 */
	@RequestMapping(value="user", method=RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody User user){
		if(loginService.isUserExist(user.getUserId())){
			return new ResponseEntity("User with userId "+user.getUserId()+" already exists", HttpStatus.CONFLICT);
 
		}
		loginService.saveUser(user);
	        return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	/**
	 * Updates the existing user details
	 * @param userId
	 * @param user
	 * @return
	 */
	@RequestMapping(value="user/{userId}", method=RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@PathVariable("userId") String userId, @RequestBody User user){
		 User currentUser = loginService.getUserById(userId);
		 
	        if (currentUser == null) {
	            return new ResponseEntity("Unable to upate user with id " + userId , HttpStatus.NOT_FOUND);
	        }
	        if(user.getPassword()!=null){
		        currentUser.setPassword(user.getPassword());
	        }
	        currentUser.setName(user.getName());
	        currentUser.setRole(user.getRole());
	        loginService.updateUser(currentUser);
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}
	
	/**
	 * Returns the user details 
	 * @return
	 */
	@RequestMapping(value = "user", method = RequestMethod.GET)
	    public ResponseEntity<List<User>> listAllUsers() {
	        List<User> users = loginService.findAllUsers();
	        if (users.isEmpty()) {
	            return new ResponseEntity(HttpStatus.NO_CONTENT);
	        }
	        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	    }
	
	/**
	 * Returns the user details by user Id
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="user/{userId}", method = RequestMethod.GET)
		public ResponseEntity<?> getUserById(@PathVariable("userId") String userId){
		User user=loginService.getUserById(userId);
		if(user==null){
			return new ResponseEntity("User with userId "+userId+" not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	/**
	 * Deletes the user details by user Id
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="user/{userId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUserById(@PathVariable("userId") String userId){
		User user=loginService.getUserById(userId);
		if(user==null){
	            logger.error("Unable to delete user with id {} not found.", userId);
	            return new ResponseEntity("User with userId "+userId+" not found", HttpStatus.NOT_FOUND);
		}
		loginService.deleteUserById(userId);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
		
	/**
	 * Deletes all the user details
	 * @return Nothing
	 */
		@RequestMapping(value="user", method = RequestMethod.DELETE)
		public ResponseEntity<?> deleteUser(){
			loginService.deleteUser();
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}
}
