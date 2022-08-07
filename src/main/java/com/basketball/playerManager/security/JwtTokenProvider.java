package com.basketball.playerManager.security;

import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${player.api.app.secret}")
    private String APP_SECRET;
    @Value("${player.api.expires.in}")
    private Long EXPIRES_IN;

    public String generateJwtToken(Authentication auth) {
        JwtUserDetails jwtUserDetails = (JwtUserDetails) auth.getPrincipal();
        Date expireDate = new Date(new Date().getTime() + EXPIRES_IN);
        return Jwts.builder().setSubject(Long.toString(jwtUserDetails.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, APP_SECRET)
                .compact();
    }

    public Integer getUserIdFromJwt(String token) {
        Claims claims = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody();
        return Integer.valueOf(claims.getSubject());
    }

    protected boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
            return !isTokenExpired(token);
        } catch (SignatureException exception) {
            return false;
        } catch (MalformedJwtException exception) {
            return false;
        } catch (ExpiredJwtException exception) {
            return false;
        }catch (UnsupportedJwtException exception) {
            return false;
        } catch (IllegalArgumentException exception) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody().getExpiration();
        return expiration.before(new Date());
    }
}
