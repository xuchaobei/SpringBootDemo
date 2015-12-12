package com.envisioncn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.envisioncn.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private RestAuthenticationEntryPoint entryPoint;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/* CustomErrorController 统一处理 spring框架抛出的异常，无需在添加 authenticationEntryPoint */
	    http
	     	//.exceptionHandling().authenticationEntryPoint(entryPoint).and()	     	
	    	.csrf().disable()
	        .authorizeRequests()
	            .antMatchers("/resources/**", "/user/login").permitAll()
	            .anyRequest().authenticated();
	          //  .and()
	        //.httpBasic();
	         
	}
	
/*	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService());
		
	}
	

	@Bean	
	protected UserDetailsService userDetailsService() {
		return (username) -> repository
				.findByUsername(username)
				.map(a -> new org.springframework.security.core.userdetails.User(a.getUsername(), a.getPassword(), true, true, true, true,
						AuthorityUtils.createAuthorityList("USER", "write")))
				.orElseThrow(
						() -> new UsernameNotFoundException("could not find the user '"
								+ username + "'"));
	}
	*/

	

}
