package com.swiggy.util;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JWTUtil {

	@Value("${jwt.secret}")
	private String secretKey;

	@Value("${jwt.expiration}")
	private long expMilliseconds;

	private Key key;

	@PostConstruct
	public void init() {
		key = Keys.hmacShaKeyFor(secretKey.getBytes());
	}

	public String generateToken(String email, String role) {

		return Jwts.builder()
				.setSubject(email)
				.claim("role", role)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expMilliseconds))
				.signWith(key, SignatureAlgorithm.HS512).compact();
	}

	public String extractEmail(String token) {
							
		return Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
		
	}

	public boolean isTokenExpired(String token) {

		// using try catch block will help to prevent app crash while the in case
		// expired

		try {
			Date expirationTime = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody()
					.getExpiration();
			return expirationTime.after(new Date());
		} catch (JwtException | IllegalArgumentException e) {
			return true;
		}
	}

	public boolean validateToken(String token, String email) {

		try {

			String extractedEmail = extractEmail(token);
			System.out.println("Extracted emain in validate method "+extractedEmail);
			return extractedEmail.equals(email) && isTokenExpired(token);

		} catch (Exception e) {

			return false;
		}

	}
}
