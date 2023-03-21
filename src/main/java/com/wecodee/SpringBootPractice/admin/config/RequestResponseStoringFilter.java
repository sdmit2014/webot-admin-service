package com.wecodee.SpringBootPractice.admin.config;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.wecodee.SpringBootPractice.admin.model.AccessLog;
import com.wecodee.SpringBootPractice.admin.repository.AccessLogRepository;
import com.wecodee.SpringBootPractice.admin.util.Helper;

@Component
public class RequestResponseStoringFilter implements Filter {

	final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AccessLogRepository accessLogRepository;

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		log.info("Inside doFilter");
		HttpServletRequest request = (HttpServletRequest) req;
		ContentCachingRequestWrapper wrapperedRequest = new ContentCachingRequestWrapper(request);
		HttpServletResponse response = (HttpServletResponse) res;
		ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

		log.info("User Id : " + request.getRemoteUser());
		log.info("Ip Address : " + request.getRemoteAddr());
		Date intime = Helper.getCurrentDateTime();
		log.info("Intime : " + intime);
		log.info("Req Method :" + request.getMethod());
		log.info("Req Method :" + request.getRequestURI());

		try {
			chain.doFilter(wrapperedRequest, wrappedResponse);
		} finally {
			if (!request.getMethod().equalsIgnoreCase("OPTIONS")) {
				AccessLog accessLog = new AccessLog();
				accessLog.setUserId(request.getRemoteUser());
				accessLog.setIpAddress(request.getRemoteAddr());
				accessLog.setInTime(intime);
				accessLog.setOutTime(Helper.getCurrentDateTime());
				accessLog.setReqMethod(request.getMethod());
				accessLog.setReqUrl(request.getRequestURI());
				accessLog.setReqParameters(null);
				if (request.getMethod().equalsIgnoreCase("POST")) {
					accessLog.setReqBody(logRequestBody(wrapperedRequest));
				}
				String responseBody = new String(wrappedResponse.getContentAsByteArray());
				wrappedResponse.copyBodyToResponse();
				accessLog.setResBody(responseBody);
				accessLog.setHttpStatus(response.getStatus());
				accessLogRepository.save(accessLog);
			}
		}

	}

	private static String logRequestBody(ContentCachingRequestWrapper request) {
		String requestBody = null;
		byte[] buff = request.getContentAsByteArray();
		if (buff.length > 0) {
			try {
				requestBody = new String(buff, 0, buff.length, request.getCharacterEncoding());
			} catch (Exception e) {
				System.out.println("error in reading request body");
			}
		}
		return requestBody;
	}
	
	@Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

}
