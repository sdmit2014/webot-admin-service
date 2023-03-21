package com.wecodee.SpringBootPractice.admin.config;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import com.google.common.base.Function;
import com.wecodee.SpringBootPractice.admin.util.Helper;
import com.wecodee.SpringBootPractice.appconfiguration.model.SecurityParameters;
import com.wecodee.SpringBootPractice.appconfiguration.repository.SecurityParameterRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenUtil implements Serializable {

	@Autowired
	private SecurityParameterRepository securityParameterRepository;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private static final long serialVersionUID = -2550185165626007488L;

//	public static final long JWT_TOKEN_VALIDITY = 6 * 60 * 60;

	@Value("${jwt.secret}")
	private String secret;

	// for retrieveing any information from token we will need the secret key
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	// retrieve username from jwt token
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	// check if the token has expired
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(Helper.getCurrentDateTime());
	}

	// while creating the token -
	// 1. Define claims of the token, like Issuer, Expiration, Subject, and the ID
	// 2. Sign the JWT using the HS512 algorithm and secret key.
	// 3. According to JWS Compact
	// Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
	// compaction of the JWT to a URL-safe string
	@SuppressWarnings("deprecation")
	private String doGenerateToken(Map<String, Object> claims, String subject) {
		SecurityParameters securityParameters = securityParameterRepository.findAll().get(0);
		log.info("secret->" + secret);
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(
						System.currentTimeMillis() + securityParameters.getSessionTimeoutInMins() * 60 * 1000 * 2))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	// generate token for user
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}

	private String doGenerateRefreshToken(Map<String, Object> claims, String subject) {
		SecurityParameters securityParameters = securityParameterRepository.findAll().get(0);
		log.info("secret->" + secret);
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(
						System.currentTimeMillis() + securityParameters.getSessionTimeoutInMins() * 60 * 1000 * 2))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	// generate refresh token for user
	public String generateRefreshToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateRefreshToken(claims, userDetails.getUsername());
	}

	// validate token
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

}
