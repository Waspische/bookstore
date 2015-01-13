package com.bookstore.service;

import java.util.List;

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
		
		em.close();
		if(user != null && user.getPassword().equals(password))
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
		
		System.out.println(user.getLogin() + ' ' + user.getPassword());
		
		try {
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
			this.printAllUser();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new UserAlreadyExistsException(login, e);
		}
		em.close();
	}
	
	public void printAllUser()
	{
		EntityManager em = EMFListener.createEntityManager();
		List<User> users = em.createQuery("From User", User.class).getResultList();
		for (User u : users) {
			System.out.println(u.getLogin() + ' ' + u.getPassword());
		}
		em.close();
	}
}
