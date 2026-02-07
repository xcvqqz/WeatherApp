package io.github.xcvqqz.weather_app.util;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@NoArgsConstructor
public class CookieUtil {

    private static final int COOKIE_MAX_AGE = 30 * 60;  //30 минут

    public static Cookie createCookie() {
        Cookie cookie = new Cookie("session_id", UUID.randomUUID().toString());
        cookie.setHttpOnly(true);   // Защита от XSS
        cookie.setSecure(false);    // true для HTTPS
        cookie.setPath("/");        // Доступна на всём сайте
        cookie.setMaxAge(COOKIE_MAX_AGE);
        return cookie;
    }

    public static Optional<String> getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return Optional.ofNullable(cookie.getValue());
                }
            }
        }
        return Optional.empty();
    }


}
