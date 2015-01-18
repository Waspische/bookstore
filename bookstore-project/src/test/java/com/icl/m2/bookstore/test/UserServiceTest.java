package com.icl.m2.bookstore.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.bookstore.entities.User;
import com.bookstore.service.UserService;

public class UserServiceTest {

	UserService userService = null;
	
	@Before
	public void init(){
		userService = new UserService();
	}
	
	@Test
	public void testLogin(){
		
		// test avec identifiants corrects
		
		String userLogin = "adrien";
		String userPassword = "adr";
		
		User u = userService.login(userLogin, userPassword);
		
		Assert.assertNotNull("The user should not be null", u);
		
		Assert.assertEquals("The user login should be 'adrien'", userLogin, u.getLogin());
		
		// test avec mauvais mot de passe
		
		String wrongPassword = "adrr";
		
		u = userService.login(userLogin, wrongPassword);

		Assert.assertNull("The user should be null", u);
		
		// test avec mauvais login
		
		String wrongLogin = "adrienn";
		
		u = userService.login(wrongLogin, userPassword);

		Assert.assertNull("The user should be null", u);
		
		// test avec tout de mauvais
		
		u = userService.login(wrongLogin, wrongPassword);

		Assert.assertNull("The user should be null", u);
	}

	@Test
	public void testAddUser(){
		
		// test avec un utilisateur n'existant pas
		
		String testLogin = "testLogin";
		String testMail = "testMail";
		String testPassword = "testPassword";
		
		try {
			userService.addUser(testLogin, testMail, testPassword);
		} catch (Exception e) {
			Assert.fail("L'utilisateur ne devrait pas déjà exister : " + e.getMessage());
		}
		
		// test pour voir s'il est bien créé
		
		User u = userService.login(testLogin, testPassword);
		
		Assert.assertNotNull("The user should not be null : il vient d'être créé", u);
		Assert.assertEquals("Le login ne correspond pas", testLogin, u.getLogin());
		Assert.assertEquals("Le mot de passe ne correspond pas", testPassword, u.getPassword());
		Assert.assertEquals("Le mail ne correspond pas", testMail, u.getEmail());
		

		// test avec un utilisateur qui existe déjà
		
		String userLogin = "adrien";
		String userPassword = "adr";
		String userMail = "adrien.stadler@gmail.com";
		
		try {
			userService.addUser(userLogin, userMail, userPassword);
			Assert.fail("L'utilisateur existe déjà et une exception devrait être levée");
		} catch (Exception e) {
			// cas normal
		}
		
	}
	
}
