package com.envisioncn.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.envisioncn.CustomAuthenticationManager;
import com.envisioncn.entity.ErrorInfo;
import com.envisioncn.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController implements UserDetailsService {

	@Autowired
	private CustomAuthenticationManager authManager;

	@Autowired
	private UserRepository repository;

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	ResponseEntity<?> logout() {
		SecurityContextHolder.clearContext();
		return new ResponseEntity<>(null, null, HttpStatus.OK);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	ResponseEntity<?> login(HttpServletRequest request,  @RequestParam("username") String username,
			@RequestParam("password") String password) {
		System.out.println(username + " " + password);
		UserDetails userDetails = loadUserByUsername(username);
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				userDetails, password);

		try {
			Authentication auth = authManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(auth);
			return new ResponseEntity<>(null, null, HttpStatus.OK);
		} catch (BadCredentialsException ex) {
			System.out.println("Login failed");
			ErrorInfo info = new ErrorInfo("/login", 40101, ex.getLocalizedMessage(),
					new Date());
			return new ResponseEntity<>(info, null, HttpStatus.UNAUTHORIZED);
		}

	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return repository
				.findByUsername(username)
				.map(a -> new org.springframework.security.core.userdetails.User(
						a.getUsername(), a.getPassword(), true, true, true,
						true, AuthorityUtils.createAuthorityList("USER",
								"write")))
				.orElseThrow(
						() -> new UsernameNotFoundException(
								"could not find the user '" + username + "'"));
	}
}
