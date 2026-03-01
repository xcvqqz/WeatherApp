package io.github.xcvqqz.weather_app.interceptor;


import io.github.xcvqqz.weather_app.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Optional;
import java.util.UUID;


@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        //получим Cookie
        UUID sessionId = CookieUtil.getSessionId(request);

//        if(sessionId.isEmpty() || sessionId.isEmpty()){
//            response.sendRedirect("/sign-in");
//            return false;
//        }

        request.setAttribute("userSessionId", sessionId);

        return true;
    }



}