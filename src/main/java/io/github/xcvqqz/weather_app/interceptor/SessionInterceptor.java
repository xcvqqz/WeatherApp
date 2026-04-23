package io.github.xcvqqz.weather_app.interceptor;



import io.github.xcvqqz.weather_app.entity.User;
import io.github.xcvqqz.weather_app.service.CookieService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
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
            response.sendRedirect("/sign-in");
            return false;
        }

        request.setAttribute("userSessionId", userSessionId);

        return true;
    }
}


User user = userService.getUserBySessionId(userSessionId);
        log.info("The user received is - {}", user.getLogin());