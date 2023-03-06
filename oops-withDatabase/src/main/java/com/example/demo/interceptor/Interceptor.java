package com.example.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class Interceptor implements HandlerInterceptor {

	private static final Logger LOGGER = LogManager.getLogger(Interceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (request.getHeader("Authorization") == null) {
			response.addHeader("Interceptor", "Authorization not sent");
			LOGGER.info("Authorization not sent.");
			LOGGER.info("Validation not OK.");
			return true;
		} else {

			LOGGER.info("Validation OK.");
			return false;
		}

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) throws Exception {

		LOGGER.info("Request and Response is completed from interceptors");
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		LOGGER.info("Post Handle method is Calling, Response status" + " " + response.getStatus());
	}
}
