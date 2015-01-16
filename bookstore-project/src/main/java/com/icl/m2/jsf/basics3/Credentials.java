package com.icl.m2.jsf.basics3;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.NotNull;

@ManagedBean(name="credentials")
@SessionScoped
public class Credentials {

	private String login;

	private String password;

	@NotNull
//	@Length(min = 3, max = 25)
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@NotNull
//	@Length(min = 3, max = 20)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
