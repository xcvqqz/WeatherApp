package io.github.xcvqqz.weather_app.interceptor;


import io.github.xcvqqz.weather_app.service.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private CookieService cookieService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        UUID userSessionId = cookieService.getSessionId(request);

        if(userSessionId == null){
            log.warn("User session Id is null, redirect to sign-in is needed to establish a session.");
            response.sendRedirect("/sign-in");
            return false;
        }

        log.info("Session successfully created with UUID: {} ", userSessionId);
        request.setAttribute("userSessionId", userSessionId);

        return true;
    }
}
