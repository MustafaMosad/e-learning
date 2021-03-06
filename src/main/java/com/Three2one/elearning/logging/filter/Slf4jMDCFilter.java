package com.Three2one.elearning.logging.filter;

import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.Three2one.elearning.security.util.JwtTokenUtil;

@Component
@Order(3)
public class Slf4jMDCFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Value("${jwt.http.request.header}")
	private String tokenHeader;

	private String responseHeader;
	private String mdcTokenKey;
	private String mdcClientIpKey;
	private String mdcClientUsername;
	private String requestHeader;

	public Slf4jMDCFilter() {
		this.requestHeader = "Authorization";
	}

	@Override
	protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
			final FilterChain chain) throws java.io.IOException, ServletException {
		try {
			final String tokenKey = generateRequestKey(request);
			final String clientIP = extractClientIP(request);
			final String clientUserName = extractUsernameFromToken(request);

			MDC.put(mdcClientIpKey, clientIP);
			MDC.put(mdcTokenKey, tokenKey);
			MDC.put(mdcClientUsername, clientUserName);
			if (!StringUtils.isEmpty(responseHeader)) {
				response.addHeader(responseHeader, tokenKey);
			}
			chain.doFilter(request, response);
		} finally {
			MDC.remove(mdcTokenKey);
			MDC.remove(mdcClientIpKey);
			MDC.remove(mdcClientUsername);
		}
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	private String extractUsernameFromToken(HttpServletRequest request) {
		final String requestTokenHeader = request.getHeader(requestHeader);

		String username = null;
		String jwtToken = null;

		if (requestTokenHeader != null) {
			jwtToken = requestTokenHeader.substring(7);
			username = jwtTokenUtil.getUsernameFromToken(jwtToken);
		}
		return username;
	}

	private String generateRequestKey(final HttpServletRequest request) {

		return UUID.randomUUID().toString().toUpperCase().replace("-", "");

	}

	private String extractClientIP(final HttpServletRequest request) {
		final String clientIP;
		if (request.getHeader("X-Forwarded-For") != null) {
			clientIP = request.getHeader("X-Forwarded-For").split(",")[0];
		} else {
			clientIP = request.getRemoteAddr();
		}
		return clientIP;
	}

	@Override
	protected boolean isAsyncDispatch(final HttpServletRequest request) {
		return false;
	}

	@Override
	protected boolean shouldNotFilterErrorDispatch() {
		return false;
	}

	public String getResponseHeader() {
		return responseHeader;
	}

	public void setResponseHeader(String responseHeader) {
		this.responseHeader = responseHeader;
	}

	public String getMdcTokenKey() {
		return mdcTokenKey;
	}

	public void setMdcTokenKey(String mdcTokenKey) {
		this.mdcTokenKey = mdcTokenKey;
	}

	public String getMdcClientIpKey() {
		return mdcClientIpKey;
	}

	public void setMdcClientIpKey(String mdcClientIpKey) {
		this.mdcClientIpKey = mdcClientIpKey;
	}

	public String getMdcClientUsername() {
		return mdcClientUsername;
	}

	public void setMdcClientUsername(String mdcClientUsername) {
		this.mdcClientUsername = mdcClientUsername;
	}

}