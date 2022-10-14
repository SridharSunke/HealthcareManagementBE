package com.trion.healthcare.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class ResponseHeaderFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse)response;
		String origin = ((HttpServletRequest)request).getHeader("Origin");
		 res.setHeader("Access-Control-Allow-Origin", origin);
		 res.setHeader("Access-Control-Allow-Headers", "X-PINGOTHER,Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
         res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
         res.setHeader("Access-Control-Allow-Credentials", "true");
//         res.setHeader("Access-Control-Max-Age", "3600");
//         res.addHeader("Access-Control-Expose-Headers", "xsrf-token");
          
          chain.doFilter(request, response);
                 
		
	}

}
