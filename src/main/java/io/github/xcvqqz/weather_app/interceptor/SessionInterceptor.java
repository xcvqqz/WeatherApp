package io.github.xcvqqz.weather_app.interceptor;


import io.github.xcvqqz.weather_app.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        //получим Cookie
        String sessionId = String.valueOf(CookieUtil.getSessionId(request));

        if(sessionId == null && sessionId.isEmpty()){
            response.sendRedirect("/sign-in");
            return false;
        }

        return true;
    }




}
