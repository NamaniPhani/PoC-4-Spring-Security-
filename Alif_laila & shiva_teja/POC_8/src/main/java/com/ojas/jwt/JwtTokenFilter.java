package com.ojas.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class JwtTokenFilter extends GenericFilterBean {
	static Logger log = Logger.getLogger(JwtTokenFilter.class.getName());
	private JwtTokenGenerator jwtTokenGenerator;

	public JwtTokenFilter(JwtTokenGenerator jwtTokenGenerator) {
		this.jwtTokenGenerator = jwtTokenGenerator;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.debug(
				"Incoming request jwttokenfilter dofilter method : " + request + " and " + response + " and " + chain);
		String tokenResolved = jwtTokenGenerator.tokenResolved((HttpServletRequest) request);
		if (tokenResolved != null && jwtTokenGenerator.validateToken(tokenResolved)) {
			Authentication auth = tokenResolved != null ? jwtTokenGenerator.authentication(tokenResolved) : null;
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		chain.doFilter(request, response);
	}
}
