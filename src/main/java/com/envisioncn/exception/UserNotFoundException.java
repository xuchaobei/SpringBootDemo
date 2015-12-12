package com.envisioncn.exception;


public class UserNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1580711025362616880L;
	
	public UserNotFoundException(String id) {
		super("userid :"+ id + " does not exist");
	}

}
