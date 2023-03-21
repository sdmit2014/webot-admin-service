package com.wecodee.SpringBootPractice.admin.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

public class JwtRequestFilter extends OncePerRequestFilter {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	public static String loggedInUserId = null;
	public static String clientIp = null;
	public static String url = null;
	public static String rType = null;
	public static String requestBody = null;
	public static int status = 0;
	public static Date intime = null;

	public static String jwtToken = null;

	@SuppressWarnings("deprecation")
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		clientIp = request.getRemoteAddr();
		url = request.getRequestURI().toString();
		rType = request.getMethod();
		// requestBody = getRequestBodyData(request.getReader());
		status = response.getStatus();
		intime = new Date(new java.util.Date().getTime());

		final String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		// JWT Token is in the form "Bearer token". Remove Bearer word and get
		// only the Token
		log.info("requestTokenHeader->" + requestTokenHeader);
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
				loggedInUserId = username;
			} catch (IllegalArgumentException e) {
				log.error("Unable to get JWT Token");
				response.setStatus(0, "Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				log.error("JWT Token has expired");
				response.setStatus(0, "JWT Token has expired");
			}
		} else {
			logger.warn("JWT Token does not begin with Bearer String");
		}
		// Once we get the token validate it.
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);
			// if token is valid configure Spring Security to manually set
			// authentication
			if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// After setting the Authentication in the context, we specify
				// that the current user is authenticated. So it passes the
				// Spring Security Configurations successfully.
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		AntPathMatcher antPathMatcher = new AntPathMatcher();
		if (antPathMatcher.match("/swagger-ui.html", request.getServletPath())
				|| antPathMatcher.match("/login/authenticate", request.getServletPath())) {
			return true;
		} else {
			return false;
		}
	}

	public String getRequestBodyData(BufferedReader reader) {
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				jb.append(line);
				reader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info("body->" + jb);
		log.info(jb.toString());
		return jb.toString();
	}

}
