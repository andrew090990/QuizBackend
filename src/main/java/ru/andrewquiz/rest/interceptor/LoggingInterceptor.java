package ru.andrewquiz.rest.interceptor;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Andrew on 02.04.2017.
 */
public class LoggingInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = Logger.getLogger(LoggingInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        logger.info("Request received: " + request.getMethod() + " " + request.getRequestURI());

        return true;
    }

}
