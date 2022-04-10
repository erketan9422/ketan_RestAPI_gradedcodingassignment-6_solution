package com.gl.employee.config;
/**
 * Class configuring authentication & authorization 
 */

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gl.employee.serviceImpl.UserServiceImpl;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserServiceImpl();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}	
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	// Managing authorizations
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
		.antMatchers("/h2-console/**").permitAll()
		.antMatchers("/api/credential/user/*", "/api/credential/role/*").hasAuthority("ADMIN")
		.antMatchers(HttpMethod.POST,"/api/employees/save").hasAuthority("ADMIN")
		.antMatchers(HttpMethod.PUT,"/api/employees/*").hasAuthority("ADMIN")
		.antMatchers(HttpMethod.DELETE,"/api/employees/*").hasAuthority("ADMIN")
		.antMatchers(HttpMethod.GET,"/api/employees/**").hasAnyAuthority("ADMIN", "USER")
		.antMatchers("/").permitAll()
		.anyRequest().authenticated()
		.and().httpBasic()
		.and().formLogin()
		.loginProcessingUrl("/login").successForwardUrl("/swagger-ui.html").permitAll()
		.and().logout().logoutSuccessUrl("/login").permitAll()
		.and().exceptionHandling()
		.and().cors().and().csrf().disable()
		.headers().frameOptions().disable();
	}
}
