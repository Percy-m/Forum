package com.enterprise.forum.security;


import com.enterprise.forum.dto.TokenDTO;
import com.enterprise.forum.exception.JwtAuthException;
import com.enterprise.forum.service.RefreshTokenService;
import com.enterprise.forum.utils.SnowflakeIdUtil;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

/**
 * @author Jiayi Zhu
 * 2022/12/19
 */
@Component
public class JwtTokenProvider {

    @Value("${forum.jwt.secret}")
    private String jwtSecret;

    @Value("${forum.jwt.access-token-expiration}")
    private long jwtAccessExpirationInMs;

    @Value("${forum.jwt.refresh-token-expiration}")
    private long jwtRefreshExpirationInMs;

    private RefreshTokenService refreshTokenService;

    @Autowired
    public void setRefreshTokenService(RefreshTokenService refreshTokenService) {

        this.refreshTokenService = refreshTokenService;
    }

    // generate token
    public TokenDTO generateToken(Authentication authentication) {

        String username = authentication.getName();

        String accessToken = generateAccessToken(username);
        String refreshToken = generateRefreshToken(username);

        return TokenDTO.of(accessToken, refreshToken);
    }

    public TokenDTO generateToken(String refreshToken) {

        // get username from refreshToken
        String username = fromToken(refreshToken, Claims::getSubject);
        String accessToken = generateAccessToken(username);

        return TokenDTO.of(accessToken, refreshToken);
    }

    public String generateAccessToken(String username) {

        Date currentTime = new Date();
        Date expireTime = new Date(currentTime.getTime() + jwtAccessExpirationInMs);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentTime)
                .setExpiration(expireTime)
                .signWith(getKey())
                .compact();
    }

    public String generateRefreshToken(String username) {

        Date currentTime = new Date();
        Date expireTime = new Date(currentTime.getTime() + jwtRefreshExpirationInMs);
        long id = SnowflakeIdUtil.nextId();

        String refreshToken = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentTime)
                .setExpiration(expireTime)
                .setId(String.valueOf(id))
                .signWith(getKey())
                .compact();
        refreshTokenService.saveRefreshToken(id, refreshToken);
        return refreshToken;
    }

    // get from the token
    public <T> T fromToken(String token, Function<Claims, T> func) {

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return func.apply(claims);
    }

    public boolean requireRefresh(String refreshToken) {

        Date expiration = fromToken(refreshToken, Claims::getExpiration);
        Date currentTime = new Date();

        // whether expiration < currentTime + 0.5*refreshExpiration
        // ensure refreshToken valid if login every refreshExpiration/2
        return expiration.before(new Date(currentTime.getTime() + jwtRefreshExpirationInMs/2));
    }

    // validate JWT token
    public boolean validateToken(String token) {

        try {
            Jwts.parserBuilder()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        }
        catch (SecurityException e){
            throw new JwtAuthException(HttpStatus.BAD_REQUEST, "Invalid JWT signature");
        } catch (MalformedJwtException e) {
            throw new JwtAuthException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
        } catch (ExpiredJwtException e) {
            throw new JwtAuthException(HttpStatus.BAD_REQUEST, "Expired JWT token");
        } catch (UnsupportedJwtException e) {
            throw new JwtAuthException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
        } catch (IllegalArgumentException e) {
            throw new JwtAuthException(HttpStatus.BAD_REQUEST, "JWT claims string is empty.");
        }
    }

    private SecretKey getKey() {

        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }
}
