package com.bookstore.service;

import java.io.Console;

import javax.persistence.EntityManager;

import com.bookstore.entities.User;
import com.bookstore.service.exception.UserAlreadyExistsException;
import com.bookstore.web.util.EMFListener;

public class UserService {

	public User login(String login, String password)
	{
		EntityManager em = EMFListener.createEntityManager();
		
		User user = em.find(User.class, login);
		
		System.out.println(user);
		
		if(user.getPassword().equals(password))
		{
			return user;
		}
		return null;
	}
	
	public void addUser(String login, String mail, String password) throws UserAlreadyExistsException
	{
		EntityManager em = EMFListener.createEntityManager();
		
		User user = new User();
		user.setEmail(mail);
		user.setLogin(login);
		user.setPassword(password);
		
		
		try {
			em.persist(user);
		} catch (Exception e) {
			throw new UserAlreadyExistsException(login, e);
		}
		
	}
}
