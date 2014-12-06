package com.bookstore.service.exception;

public class UserAlreadyExistsException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6160486548517183150L;

	public UserAlreadyExistsException(String login, Throwable cause) {
		super("User with login " + login +" already exists !", cause);
	}

	public UserAlreadyExistsException(String login) {
		this(login, null);
	}

}
