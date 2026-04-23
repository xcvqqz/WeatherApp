package io.github.xcvqqz.weather_app.interceptor;


import io.github.xcvqqz.weather_app.dto.CurrentWeatherDTO;
import io.github.xcvqqz.weather_app.entity.Location;
import io.github.xcvqqz.weather_app.entity.User;
import io.github.xcvqqz.weather_app.service.LocationService;
import io.github.xcvqqz.weather_app.service.UserService;
import io.github.xcvqqz.weather_app.service.api.CurrentWeatherService;
import io.github.xcvqqz.weather_app.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@AllArgsConstructor
public class SessionInterceptor implements HandlerInterceptor {

    private final UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        UUID userSessionId = CookieUtil.getSessionId(request);

        if(userSessionId == null){
            response.sendRedirect("/sign-in");
            return false;
        }
        User user = userService.getUserBySessionId(userSessionId);
        log.info("The user received is - {}", user.getLogin());
        request.setAttribute("user", user);
        return true;

    }
}