package com.bookstore.service;

import javax.persistence.EntityManager;

import com.bookstore.entities.Author;
import com.bookstore.entities.User;
import com.bookstore.web.util.EMFListener;

public class UserService {

	public User login(String login, String password)
	{
		EntityManager em = EMFListener.createEntityManager();
		
		User user = em.find(User.class, login);
		
		if(user.getPassword().equals(password))
		{
			return user;
		}
		return null;
	}
	
}
