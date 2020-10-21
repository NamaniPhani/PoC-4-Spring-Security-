
package com.ojas.task.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.ojas.task.jwt.JwtConfigurer;
import com.ojas.task.jwt.JwtTokenGenerator;


@Configuration
@EnableWebSecurity
public class UserSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final Logger log = Logger.getLogger(UserSecurityConfig.class);


	@Autowired
	CustomUserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenGenerator jwtTokenGenerator;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.debug("Incoming request httpsecurity configure method : " + http);
		http.cors().disable().csrf().disable().httpBasic().disable().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling()
				.authenticationEntryPoint(new AuthenticationEntryPoint() {
					@Override
					public void commence(HttpServletRequest request, HttpServletResponse response,
							AuthenticationException authException) throws IOException, ServletException {
						response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
					}
				}).and().authorizeRequests().antMatchers("/user/**").permitAll().anyRequest().authenticated().and()
				.apply(new JwtConfigurer(jwtTokenGenerator));
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		log.debug("Incoming request web security method : " + web);
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
		web.ignoring().antMatchers("/swagger-ui.html/**", "/configuration/**", "/swagger-resources/**", "/v2/api-docs",
				"/webjars/**");
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
