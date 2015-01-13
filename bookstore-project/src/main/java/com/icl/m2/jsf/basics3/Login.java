package com.icl.m2.jsf.basics3;

import java.io.Serializable;
import java.util.regex.Pattern;

import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import com.bookstore.entities.User;
import com.bookstore.service.UserService;

@SessionScoped
@ManagedBean(name = "login")
public class Login implements Serializable {

	@ManagedProperty(value = "#{credentials}")
	Credentials credentials;

	private User user;

	private String passwordComplexityIndicator;
	
	private String registeringMessage = "";

	public String login() {

		UserService us = new UserService();
		User u = us.login(credentials.getLogin(), credentials.getPassword());

		if (u == null) {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							"loginForm:login",
							new FacesMessage(
									FacesMessage.SEVERITY_ERROR,
									"User does not exist or incorrect password!",
									"User does not existUser does not exist or incorrect password!"));
			return null;
		} else {
			this.user = u;
			FacesContext.getCurrentInstance().addMessage(
					"loginForm:password",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenue "
							+ user.getLogin() + ".", "Achete!"));
			return "/pages/login.xthml";
		}

	}


	@SuppressWarnings("finally")
	public String registerMe()
	{
		String page = "";
		UserService us = new UserService();
		try
		{
			us.addUser(user.getLogin(), user.getEmail(), user.getPassword()); // TODO change to User
			this.setRegisteringMessage("Inscription Validée !");
			page = "/pages/login.xhtml";
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			this.setRegisteringMessage("Erreur lors de votre inscription"); 
			page = "/pages/register.xhtml";
		}
		finally
		{
			return page;
		}
	}
	
	public String logout() {
		user = null;
		return "/pages/login.xhtml";
	}

	public boolean isLoggedIn() {
		return user != null;

	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	@Produces
	User getCurrentUser() {
		return user;
	}


	public String getRegisteringMessage() {
		return registeringMessage;
	}


	public void setRegisteringMessage(String registeringMessage) {
		this.registeringMessage = registeringMessage;
	}


	public String goToRegister()
	{
		return "/pages/register.xhtml";
	}
	
	public String goToLogin()
	{
		return "/pages/login.xhtml";
	}

	public String getPasswordComplexityIndicator() {
		return passwordComplexityIndicator;
	}

	public void setPasswordComplexityIndicator(String passwordComplexityIndicator) {
		this.passwordComplexityIndicator = passwordComplexityIndicator;
	}

	public void recomputePasswordComplexityIndicator(AjaxBehaviorEvent event){
		//reinit
		setPasswordComplexityIndicator(null);	
		
		//1 chiffre, 1 maj, 1 min, 1 caractere special parmi @#$%^
		int nbRules = -1;
		if (Pattern.matches(".*([0-9]).*", user.getPassword())){ nbRules++; }
		if (Pattern.matches(".*([a-z]).*", user.getPassword())){ nbRules++; }
		if (Pattern.matches(".*([A-Z]).*", user.getPassword())){ nbRules++; }
		if (Pattern.matches(".*([@#$%^&+=]).*", user.getPassword() )){ nbRules++; }
			
		switch (nbRules){
			case 0: setPasswordComplexityIndicator("VERY LOW"); break;
			case 1: setPasswordComplexityIndicator("LOW"); break;
			case 2:	setPasswordComplexityIndicator("AVERAGE"); break;
			case 3: setPasswordComplexityIndicator("HIGH"); break;
		}
	}

}
