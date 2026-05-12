package io.github.xcvqqz.weather_app.controller;

import org.springframework.stereotype.Controller;

@Controller
public class BasicController {

    protected static final String REDIRECT_HOME = "redirect:/home";
    protected static final String REDIRECT_SIGN_IN = "redirect:/sign-in";
    protected static final String SIGN_IN_VIEW = "sign-in";
    protected static final String SIGN_UP_VIEW = "sign-up";
    protected static final String HOME_VIEW = "home";
    protected static final String SEARCH_RESULT_VIEW = "locations/search-results";

}