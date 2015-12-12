package com.envisioncn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationManager implements AuthenticationManager  {
	
	static final List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();

	static {
		AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER"));
	}
	
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		Object principal = auth.getPrincipal();

		if (principal instanceof UserDetails) {
			String password = ((UserDetails)principal).getPassword();
			if (password.equals(auth.getCredentials())) {
				return new UsernamePasswordAuthenticationToken(auth.getPrincipal(),
					auth.getCredentials(), AUTHORITIES);
			}
		} else {
			throw new BadCredentialsException("Bad Credentials Type");
		}
		
		throw new BadCredentialsException("Wrong Password");
	}
	
	
}
