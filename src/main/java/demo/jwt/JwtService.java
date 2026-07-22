package demo.jwt;


import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	 private static final String SECRET_KEY =
	            "mysecretkeymysecretkeymysecretkeymysecretkey";

	    private static final long EXPIRATION_TIME =
	            1000 * 60 * 60 * 24;
	    
	    private Key getSignKey() {
	        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	    }
	    
	    public String generateToken(String email) {

	        return Jwts.builder()
	                .subject(email)
	                .issuedAt(new Date())
	                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
	                .signWith(getSignKey(), SignatureAlgorithm.HS256)
	                .compact();
	    }
	    public String extractUsername(String token) {

	        Claims claims = Jwts.parser()
	                .verifyWith((javax.crypto.SecretKey) getSignKey())
	                .build()
	                .parseSignedClaims(token)
	                .getPayload();

	        return claims.getSubject();
	    }
	    
	    
	    public boolean isTokenExpired(String token) {

	        Claims claims = Jwts.parser()
	                .verifyWith((javax.crypto.SecretKey) getSignKey())
	                .build()
	                .parseSignedClaims(token)
	                .getPayload();

	        return claims.getExpiration().before(new Date());
	    }
	    
	    public boolean validateToken(String token, String email) {

	        String extractedEmail = extractUsername(token);

	        return extractedEmail.equals(email) && !isTokenExpired(token);
	    }
}
