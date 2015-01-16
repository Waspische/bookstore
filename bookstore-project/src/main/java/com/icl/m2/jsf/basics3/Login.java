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
	
	@ManagedProperty(value="#{registerBean}")
	RegisterBean registerBean;

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
			return "/pages/login.xthml";
		}

	}


	@SuppressWarnings("finally")
	public String registerMe()
	{
		String page = "";
		
		boolean hasErrors = false;
		
		// TODO : check if user already exists
		
		if(!registerBean.getMail().equals(registerBean.getConfirmationMail())){ // TODO :  check mail
			FacesContext
			.getCurrentInstance()
			.addMessage(
					"resigterForm:confirmMail",
					new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							"The confirmation mail is different",
							"Incorrect mail"));
			hasErrors = true;
		}
		if(!registerBean.getPassword().equals(registerBean.getConfirmationPassword())){ // TODO : check password
			FacesContext
			.getCurrentInstance()
			.addMessage(
					"registerForm:confirmPassword",
					new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							"The confirmation password is different!",
							"Incorrect password"));
			hasErrors = true;
		}
		
		if(!hasErrors){
			
			System.out.println(registerBean);
			
			UserService us = new UserService();
			try
			{
				us.addUser(registerBean.getLogin(), registerBean.getMail(), registerBean.getPassword());
				this.setRegisteringMessage("Inscription Validée !");
				page = "/pages/register.xhtml";
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				this.setRegisteringMessage("Le login '"+ registerBean.getLogin() + "' existe déjà.	"); 
				page = "/pages/register.xhtml";
			}
			finally
			{
				return page;
			}
		}
		
		return null;
	}
	
	public String logout() {
		user = null;
		return null;
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
	
	public RegisterBean getRegisterBean() {
		return registerBean;
	}


	public void setRegisterBean(RegisterBean registerBean) {
		this.registerBean = registerBean;
	}


	@Produces
	User getCurrentUser() {
		return user;
	}


	public String getRegisteringMessage() {
		return registeringMessage;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
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
