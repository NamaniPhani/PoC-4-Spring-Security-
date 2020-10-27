package com.ojas.SpringSecurity.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	static Logger log = LoggerFactory.getLogger(JwtConfigurer.class.getName());

	private JwtTokenGenerator jwtTokenGenerator;

	public JwtConfigurer(JwtTokenGenerator jwtTokenGenerator) {
		this.jwtTokenGenerator = jwtTokenGenerator;
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		log.debug("Incoming request jwtconfigurer configurer method : " + http);
		JwtTokenFilter jwtTokenFilter = new JwtTokenFilter(jwtTokenGenerator);
		http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
