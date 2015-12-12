package com.envisioncn.advice;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.envisioncn.entity.ErrorInfo;
import com.envisioncn.exception.UserNotFoundException;

@ControllerAdvice
public class ExceptionControllerAdvice {

	private static final int USER_NOT_FOUND = 40001;
	private static final int USERNAME_NOT_FOUND = 40101;
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
	public ErrorInfo userNotFoundException(HttpServletRequest req, Exception ex){
		return new ErrorInfo(req.getRequestURI(), USER_NOT_FOUND, ex, new Date());
	}
	
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseBody
	public ErrorInfo usernameNotFoundException(HttpServletRequest req, Exception ex){
		return new ErrorInfo(req.getRequestURI(), USERNAME_NOT_FOUND, ex, new Date());
	}
	
}
