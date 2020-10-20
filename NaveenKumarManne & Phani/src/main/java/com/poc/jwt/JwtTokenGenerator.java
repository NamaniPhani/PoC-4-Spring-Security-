package com.poc.jwt;

import java.util.Base64;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import com.poc.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenGenerator {
	private final Logger logger = Logger.getLogger(this.getClass());
	
	private UserService userService;
	@Value("${security.jwt.token.secret-key:secret}")
	private String secretkey;
	@Value("${security.jwt.token.expire-length:3600000}") // 1hour
	private Long tokenValidity;

	@PostConstruct
	private void init() {
		secretkey = Base64.getEncoder().encodeToString(secretkey.getBytes());
	}

	public String createToken(String username, String role) {
		logger.info("inside createToken() :"+username+","+role);
		Claims claims = Jwts.claims().setSubject(username);
		claims.put("roles", role);
		Date validityTokentime = new Date(new Date().getTime() + tokenValidity);
		String compact = Jwts.builder().setClaims(claims).setExpiration(validityTokentime).setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, secretkey).compact();
		return compact;
	}

	public String getUserName(String username) {
		String subject = Jwts.parser().setSigningKey(secretkey).parseClaimsJws(username).getBody().getSubject();
		return subject;
	}

	public Authentication authentication(String username) {
		logger.info("inside authentication() :"+username);
		UserDetails byUsername = userService.loadUserByUsername(getUserName(username));
		return new UsernamePasswordAuthenticationToken(byUsername, "", byUsername.getAuthorities());
	}

	public String tokenResolved(HttpServletRequest request) {
		logger.info("inside tokenResolverd() :"+request);
		String header = request.getHeader("Authorization");
		if (header != null && header.startsWith("Bearer ")) {
			return header.substring(7, header.length());
		}
		return null;
	}

	public boolean validateToken(String token) {
		logger.info("inside validateToken() :"+token);
		try {
			Jws<Claims> parseClaimsJws = Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token);
			if (parseClaimsJws.getBody().getExpiration().before(new Date())) {
				return false;
			}
			return true;
		} catch (MalformedJwtException m) {
			logger.error("exception caught! " + m.getMessage());
			throw new MalformedJwtException("Invalid JWT token: {}" + m.getMessage());
		} catch (JwtException | IllegalArgumentException e) {
			logger.error("exception caught! " + e.getMessage());
			throw new JwtException("Expired or invalid JWT token!" + e.getMessage());
		}
	}
}
