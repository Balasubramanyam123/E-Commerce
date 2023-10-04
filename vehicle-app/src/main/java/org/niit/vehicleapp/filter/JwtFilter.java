package org.niit.vehicleapp.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String authHeader = httpServletRequest.getHeader("authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            throw new ServletException("Missing token or invalid token");
        } else {
            String jwtToken = authHeader.substring(7);
            Claims claims = Jwts.parser().setSigningKey("SecurityKey").parseClaimsJws(jwtToken).getBody();
            httpServletRequest.setAttribute("curr_user_email", claims.get("email"));
            httpServletRequest.setAttribute("curr_user_role", claims.get("role"));
            filterChain.doFilter(servletRequest, servletResponse);

        }
    }
}
