package com.altimetric.sso.config;

//import com.nineleaps.cat.dto.UserAuthDto;
//import com.nineleaps.cat.exception.UnAuthorizedException;
import com.altimetric.sso.exception.UnAuthorizedException;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private JWTVerifier jwtVerifier;
//    @Autowired
//    private UserAuthDto userAuthDto;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws UnAuthorizedException, Exception {

        if(!"OPTIONS".equalsIgnoreCase(request.getMethod())) {
            log.info("Getting token from API at interceptor");
            String token = request.getHeader("accessToken");
            Claims claims = jwtVerifier.extractDataFromToken(token);
            log.info("Token verified and details set in userAuthDto");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}