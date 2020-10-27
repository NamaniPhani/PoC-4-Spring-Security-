package com.ojas.securityconfig;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.ojas.jwt.JwtConfigurer;
import com.ojas.jwt.JwtTokenGenerator;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenGenerator jwtTokenGenerator;


	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();

	}
 
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());	}
	

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors().disable().csrf().disable().httpBasic().disable().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling()
		.authenticationEntryPoint(new AuthenticationEntryPoint() {
			@Override
			public void commence(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException authException) throws IOException, ServletException {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			}
		}).and().authorizeRequests().antMatchers("/user/**").permitAll().antMatchers("/user/login/**")
		.hasAuthority("ADMIN").anyRequest().authenticated().and()
		.apply(new JwtConfigurer(jwtTokenGenerator));
}

		/*httpSecurity.csrf().disable().authorizeRequests().antMatchers("/user/**").permitAll().and().apply(new JwtConfigurer(jwtTokenGenerator))
		.and().formLogin();*/
		// httpSecurity.csrf().disable().authorizeRequests().antMatchers("/user").hasRole("ADMIN")
		// .antMatchers("/save").hasAnyRole("ADMIN", "USER").and().httpBasic();
	

}
