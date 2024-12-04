package com.hexaware.CodingChallenge.Config;

import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

	private static final String SECRET="26DA594C4DD3192D8D74331895452319B149FBDF72939B75EF2879CA1655C56B15AF80282A7C69A85D630BFA6C194858E1241BC6D8D2011A4DF1C536DB52D806";
	
	private static final long Validity=TimeUnit.MINUTES.toMillis(30);
	
	
	public String generateToken(UserDetails userDetails) {
		
		String token= Jwts.builder()
		.setSubject(userDetails.getUsername())
		.setIssuedAt(Date.from(Instant.now()))
		.setExpiration(Date.from(Instant.now().plusMillis(Validity)))
		.signWith(getKey())
		.compact();
		//System.out.println("Generated JWT Token: " + token);
		return token;
	}
	
	private SecretKey getKey() {
		byte[] decodedKey=Base64.getDecoder().decode(SECRET);
		return Keys.hmacShaKeyFor(decodedKey);
		
	}

	public String extractUsername(String jwt) {
		Claims claims= getClaims(jwt);
		return claims.getSubject();
		
	}
	
	private Claims getClaims(String jwt) {
		return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
	}
	public boolean isTokenValid(String jwt) {
		Claims claims=getClaims(jwt);
		return claims.getExpiration().after(Date.from(Instant.now()));
	}
	
	
	
}
