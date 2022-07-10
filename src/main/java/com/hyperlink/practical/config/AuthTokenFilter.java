package com.hyperlink.practical.config;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.hyperlink.practical.exception.AuthorizationException;
import com.hyperlink.practical.serviceImpl.UserDetailsServiceImpl;
import com.hyperlink.practical.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;


public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = parseJwt(request);
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String username = jwtUtils.getUserNameFromJwtToken(jwt);

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e);
        }

        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
        }

        return null;
    }

    public UserDetails getUserAndOrg(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(Constants.AUTHORIZATION, token);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails;
    }

    public UserDetails authOrGet(HttpServletRequest httpRequest) {
        try {
            String token = httpRequest.getHeader(Constants.AUTHORIZATION);
            if (token == null) {
                throw new AuthorizationException(Constants.NOT_AUTHORIZE);
            }
            UserDetails auth = getUserAndOrg(token);
            if (auth == null) {
                throw new AuthorizationException(Constants.NOT_AUTHORIZE);
            }
            return auth;
        } catch (Exception e) {
            throw new AuthorizationException(Constants.NOT_AUTHORIZE);
        }
    }

    public String getRole(HttpServletRequest request) {
        UserDetails userDetails = authOrGet(request);
        Optional<? extends GrantedAuthority> first = userDetails.getAuthorities().stream().findFirst();
        if (first.isPresent()) {
            String role = first.get().toString();
            return role;
        }
        return "";
    }
}
