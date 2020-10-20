package com.poc.jwt;

import org.apache.log4j.Logger;
//import org.apache.log4j.Logger;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
	private final Logger logger = Logger.getLogger(this.getClass());
	private JwtTokenGenerator jwtTokenGenerator;

	public JwtConfigurer(JwtTokenGenerator jwtTokenGenerator) {
		this.jwtTokenGenerator = jwtTokenGenerator;
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		logger.info("inside JwtConfigurer configure(HttpSecurity http):"+http);
		JwtTokenFilter jwtTokenFilter = new JwtTokenFilter(jwtTokenGenerator);
		http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
