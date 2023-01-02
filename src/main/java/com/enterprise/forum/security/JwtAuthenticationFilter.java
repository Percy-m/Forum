package com.enterprise.forum.security;

import com.enterprise.forum.dto.TokenDTO;
import com.enterprise.forum.exception.JwtAuthException;
import com.enterprise.forum.service.RefreshTokenService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author Jiayi Zhu
 * 2022/12/19
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtTokenProvider tokenProvider;

    private UserDetailsService userDetailsService;

    private RefreshTokenService refreshTokenService;

    @Autowired
    public void setTokenProvider(JwtTokenProvider tokenProvider) {

        this.tokenProvider = tokenProvider;
    }

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {

        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void setRefreshTokenService(RefreshTokenService refreshTokenService) {

        this.refreshTokenService = refreshTokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // get JWT (token) from request
        String token = getJwtFromRequest(request);
        // validate token
        if (StringUtils.hasText(token) && tokenProvider.validateToken(token)) {

            String id = tokenProvider.fromToken(token, Claims::getId);

            // is-refresh-token
            if (StringUtils.hasText(id)) {
                try {
                    if (token.equals(refreshTokenService
                            .getRefreshTokenById(Long.parseLong(id)))) {
                        // tokenProvider.requireRefresh(token)

                        TokenDTO tokenDTO = tokenProvider.generateToken(token);
                        request.setAttribute("token", tokenDTO);
                    }
                    else throw JwtAuthException.RefreshTokenNotMatch;
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            else {
                // get username from token
                String username = tokenProvider.fromToken(token, Claims::getSubject);
                // lead user associated with token
                UserDetails account = userDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authenticationToken
                        = new UsernamePasswordAuthenticationToken(
                        account, null, account.getAuthorities());
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request));
                // set spring security
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }


        }
        filterChain.doFilter(request, response);
    }

    // Bearer <accessToken>
    private String getJwtFromRequest(HttpServletRequest request) {

        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return "";
    }
}
