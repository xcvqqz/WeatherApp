package io.github.xcvqqz.weather_app.interceptor;


import io.github.xcvqqz.weather_app.exception.SessionNotFoundException;
import io.github.xcvqqz.weather_app.service.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class SessionInterceptor implements HandlerInterceptor {

    private final CookieService cookieService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        try {
            UUID userSessionId = cookieService.getSessionId(request);
            log.info("Session with UUID {} was received", userSessionId);
            request.setAttribute("userSessionId", userSessionId);
            return true;
        } catch (SessionNotFoundException e) {
            log.warn("User session Id is null, redirect to sign-in is needed to establish a session.");
            response.sendRedirect("/sign-in");
            return false;
        }
    }
}