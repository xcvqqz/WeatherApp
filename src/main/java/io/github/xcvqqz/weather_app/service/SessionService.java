package io.github.xcvqqz.weather_app.service;


import io.github.xcvqqz.weather_app.repository.SessionRepository;
import io.github.xcvqqz.weather_app.repository.UserRepository;
import io.github.xcvqqz.weather_app.util.CookieUtil;
import jakarta.servlet.http.Cookie;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SessionService {


    private final SessionRepository sessionRepository;


    Cookie cookie = CookieUtil.createCookie().











}
