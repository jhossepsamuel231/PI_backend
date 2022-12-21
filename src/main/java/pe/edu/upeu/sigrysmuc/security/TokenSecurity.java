package pe.edu.upeu.sigrysmuc.security;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class TokenSecurity {

    private final static String ACCESS_TOKEN_SECRET = "4214jfsajdaojjljllsdjaofaosfasjfoj";
    private final static Long ACCESS_TOKEN_TIME = 2_592_000L;

    public static String createToken(int idUsuario, String nombre, String username, String rol, String correo) {
        long expirationTime = ACCESS_TOKEN_TIME * 1_000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);
        Map<String, Object> extra = new HashMap<>();
        extra.put("idUsuario", idUsuario);
        extra.put("nombre", nombre);
        extra.put("rol", rol);
        extra.put("correo", correo);
        return Jwts
                .builder()
                .setSubject(username)
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
        try {
            Set<SimpleGrantedAuthority> authorities = new HashSet<>();
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            String username = claims.getSubject();
            authorities.add(new SimpleGrantedAuthority((String) claims.get("rol")));
            return new UsernamePasswordAuthenticationToken(username, null, authorities);
        } catch (JwtException e) {
            return null;
        }
    }

}
