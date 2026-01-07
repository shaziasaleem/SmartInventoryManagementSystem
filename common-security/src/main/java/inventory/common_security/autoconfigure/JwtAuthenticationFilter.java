package inventory.common_security.autoconfigure;


import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class JwtAuthenticationFilter extends OncePerRequestFilter {
	private JWTConfig jwtConfig;
	
	public JwtAuthenticationFilter(JWTConfig jwtConfig) {
		System.out.println("inside condtructor of jwt filter class");
        this.jwtConfig = jwtConfig;
    }
	
	 @Override
	    protected void doFilterInternal(HttpServletRequest request,
	                                    HttpServletResponse response,
	                                    FilterChain filterChain)
	            throws ServletException, IOException {

	        String header = request.getHeader("Authorization");
	        if (header != null && header.startsWith("Bearer ")) {
	            String token = header.substring(7);
	            if (jwtConfig.validateToken(token)) {
	                String username = jwtConfig.extractUsername(token);
	                String role = jwtConfig.extractRole(token);
	                var auth = new UsernamePasswordAuthenticationToken(
	                    username,
	                    null,
	                    List.of(new SimpleGrantedAuthority("ROLE_" + role))
	                );
	                SecurityContextHolder.getContext().setAuthentication(auth);
	            }
	        }
	        filterChain.doFilter(request, response);
	    }

}
