/*
package com.rahul.ecommerce.userservice.security;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.rahul.ecommerce.userservice.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
	    System.out.println("Path: "+request.getServletPath());
		return request.getServletPath().equals("/api/auth/login");
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
	final String authHeader=request.getHeader("Authorization");
	System.out.println("JWT Filter Called");
	System.out.println("Header: "+authHeader);
	
	final String jwt;
	
	// Check if header is missing
	if(authHeader==null || !authHeader.startsWith("Bearer ")) {
		filterChain.doFilter(request, response);
		return;
	}
	
	// Extract JWT
	jwt=authHeader.substring(7);
	
	 // Extract username
	String username=jwtService.extractUsername(jwt);
	
	 // If username exists and not authenticated yet
	if(username!=null && SecurityContextHolder.getContext().getAuthentication() == null)
	{
		
	UserDetails  userDetails=userDetailsService.loadUserByUsername(username);
		
		
		 // Validate token
		if(jwtService.isTokenValid(jwt, username))
		{
			//UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(username, null, null);
			//authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			//SecurityContextHolder.getContext().setAuthentication(authToken);
			
			   UsernamePasswordAuthenticationToken authToken =
			             new UsernamePasswordAuthenticationToken(
			                     userDetails,
			                     null,
			                     userDetails.getAuthorities());
			  SecurityContextHolder.getContext().setAuthentication(authToken);
		}
	}
	filterChain.doFilter(request, response);
	// Now VERY IMPORTANT STEP
	//You must connect this filter in SecurityConfig
	}
}

*/
