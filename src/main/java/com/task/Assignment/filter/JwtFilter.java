package com.task.Assignment.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.task.Assignment.jwtUtil.JwtUtil;
import com.task.Assignment.service.CustomUserDetailService;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private CustomUserDetailService service;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorizarionHeader = request.getHeader("Authorization");
		String token = null;
		String email = null;
		// Bearer
		// eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyYW1AZ21haWwuY29tIiwiZXhwIjoxNTk3NDczOTI0LCJpYXQiOjE1OTc0Mzc5MjR9.klckApe7diesJ9wCTVMLdBuNTsI_-zQ87Aw3vZzaiMw
		//if (authorizarionHeader != null && authorizarionHeader.startsWith("Bearer ")) {
			//token = authorizarionHeader.substring(7);
		if(authorizarionHeader!=null) {
			token = authorizarionHeader;
			email = jwtUtil.extractUsername(token);
		}

		if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails details = service.loadUserByUsername(email);

			if (jwtUtil.validateToken(token, details)) {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						details, null, details.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}

		}
		
		filterChain.doFilter(request, response);

	}

}
