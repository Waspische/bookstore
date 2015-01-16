package com.icl.m2.jsf.basics3;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="registerBean")
@SessionScoped
public class RegisterBean {

	private String login;
	
	private String mail;
	
	private String confirmationMail;
	
	private String password;
	
	private String confirmationPassword;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getConfirmationMail() {
		return confirmationMail;
	}

	public void setConfirmationMail(String confirmationMail) {
		this.confirmationMail = confirmationMail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmationPassword() {
		return confirmationPassword;
	}

	public void setConfirmationPassword(String confirmationPassword) {
		this.confirmationPassword = confirmationPassword;
	}

	@Override
	public String toString() {
		return "RegisterBean [login=" + login + ", mail=" + mail
				+ ", confirmationMail=" + confirmationMail + ", password="
				+ password + ", confirmationPassword=" + confirmationPassword
				+ "]";
	}
}
