package com.devops.springsecuritydevops.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtService {
    private static final String SECRET_KEY = "mysupersecretkeymysupersecretkeymysupersecretkey";

    private Key getSigningKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
    //Generate JWT including roles
    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        claims.put("roles",userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        return createToken(claims,userDetails.getUsername());
    }
    private String createToken(Map<String,Object> claims ,String subject){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))//10 hrs
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    //extract username from token
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }
    //extract roles
    public List<String> extractRoles(String token){
        Claims claims = extractAllClaims(token);
        return claims.get("roles",List.class);
    }
    //extract expiration date
    public Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }
    //generic claim extractor
    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public Boolean valiateToken(String token,UserDetails userDetails){
        return (extractUsername(token).equals(userDetails.getUsername()) && !extractExpiration(token).before(new Date()));
    }
}
