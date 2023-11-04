package co.escuelaing.edu.ieti.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import static co.escuelaing.edu.ieti.utils.Constants.CLAIMS_ROLES_KEY;
import static co.escuelaing.edu.ieti.utils.Constants.COOKIE_NAME;

import co.escuelaing.edu.ieti.repository.User;

import java.util.Date;
import java.util.HashMap;

@Service
public class JwtUtil {
    private final String secretKey = "abc123"; 

    public String generateToken(User userDetails) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + 3600000); // 1 hora de expiración

        return Jwts.builder()
            .setSubject(userDetails.getEmail())
            .setClaims(new HashMap<>(){{
                put(CLAIMS_ROLES_KEY,"user");
            }})
            .setIssuedAt(now)
            .setExpiration(expirationDate)
            .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256)
            .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(secretKey.getBytes())
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername());
    }
}
