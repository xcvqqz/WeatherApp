package io.github.xcvqqz.weather_app.util;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@NoArgsConstructor
public class CookieUtil {

    private static final int COOKIE_MAX_AGE = 30 * 60;  //30 минут
    private static final String SESSION_COOKIE = "sessionId";

    public static void setSessionCookie(HttpServletResponse response, UUID sessionId) {
        Cookie cookie = new Cookie(SESSION_COOKIE, sessionId.toString());
        cookie.setHttpOnly(true);   // Защита от XSS
        cookie.setSecure(false);    // true для HTTPS
        cookie.setPath("/");        // Доступна на всём сайте
        cookie.setMaxAge(COOKIE_MAX_AGE);
        response.addCookie(cookie);
    }

    public static Optional<UUID> getSessionId(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return Optional.empty();
        }
        return Arrays.stream(request.getCookies())
                .filter(c -> SESSION_COOKIE.equals(c.getName()))
                .map(Cookie::getValue)
                .map(UUID::fromString)
                .findFirst();

    }

public static void clearSessionCookie(HttpServletResponse response){
        Cookie cookie = new Cookie(SESSION_COOKIE, "");
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
}


}
