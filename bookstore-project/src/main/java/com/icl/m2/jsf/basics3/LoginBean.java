package com.icl.m2.jsf.basics3;

import java.util.regex.Pattern;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import com.bookstore.entities.User;
import com.bookstore.service.UserService;

//@Named("loginBean")
@ManagedBean
@SessionScoped
public class LoginBean {

	private String login;
	
	private String password;
	
	private String email;
	
	private String passwordComplexityIndicator ;
	
	public String logMe(){
		//Passer sur la BDD
		
		UserService us = new UserService();
		User u = us.login(login, password);
		
		if(u == null)
		{
			FacesContext.getCurrentInstance().addMessage("loginForm:login", new FacesMessage(FacesMessage.SEVERITY_ERROR, "User does not exist or incorrect password!", "User does not existUser does not exist or incorrect password!"));
			return null;
		}
		else
		{
			this.email = u.getEmail();
			FacesContext.getCurrentInstance().addMessage("loginForm:password", new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenue " + login + ".", "Achete!"));
			return "/pages/home.xthml";
		}
	}
	
	public String register()
	{
		return "/pages/register.xhtml";
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
		if (Pattern.matches(".*([0-9]).*", password)){ nbRules++; }
		if (Pattern.matches(".*([a-z]).*", password)){ nbRules++; }
		if (Pattern.matches(".*([A-Z]).*", password)){ nbRules++; }
		if (Pattern.matches(".*([@#$%^&+=]).*", password)){ nbRules++; }
			
		switch (nbRules){
			case 0: setPasswordComplexityIndicator("VERY LOW"); break;
			case 1: setPasswordComplexityIndicator("LOW"); break;
			case 2:	setPasswordComplexityIndicator("AVERAGE"); break;
			case 3: setPasswordComplexityIndicator("HIGH"); break;
		}
	}
		

	
}
