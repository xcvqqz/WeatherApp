package io.github.xcvqqz.weather_app.service;


import io.github.xcvqqz.weather_app.exception.SessionNotFoundException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.UUID;

@Service
public class CookieService {


    private static final String SESSION_NOT_FOUND_MESSAGE = "Session Not Found";
    private static final int COOKIE_MAX_AGE = 30 * 60;
    private static final String SESSION_COOKIE = "sessionId";
    private static final String CLEAR_SESSION_COOKIE_VALUE = "";

    public  void setSessionCookie(HttpServletResponse response, UUID sessionId) {
        Cookie cookie = new Cookie(SESSION_COOKIE, sessionId.toString());
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(COOKIE_MAX_AGE);
        response.addCookie(cookie);
    }

    public UUID getSessionId(HttpServletRequest request) {

        return Arrays.stream(request.getCookies())
                .filter(c -> SESSION_COOKIE.equals(c.getName()))
                .map(Cookie::getValue)
                .map(UUID::fromString)
                .findFirst()
                .orElseThrow(() -> new SessionNotFoundException(SESSION_NOT_FOUND_MESSAGE));
    }

    public void clearSessionCookie(HttpServletResponse response){
        Cookie cookie = new Cookie(SESSION_COOKIE, CLEAR_SESSION_COOKIE_VALUE);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}