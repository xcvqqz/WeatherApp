package io.github.xcvqqz.weather_app.controller;


import io.github.xcvqqz.weather_app.dto.auth.UserAuthDTO;
import io.github.xcvqqz.weather_app.entity.Session;
import io.github.xcvqqz.weather_app.entity.User;
import io.github.xcvqqz.weather_app.service.CookieService;
import io.github.xcvqqz.weather_app.service.SessionService;
import io.github.xcvqqz.weather_app.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.UUID;



@AllArgsConstructor
@Controller
@RequestMapping()
public class AuthController {

    private final UserService userService;
    private final SessionService sessionService;
    private final CookieService cookieService;

    @GetMapping("/sign-in")
    public String showSignIn(@ModelAttribute("user") UserAuthDTO userAuth) {
        return "sign-in";
    }


    @PostMapping("/sign-in")
    public String processSignIn(@Valid @ModelAttribute("user") UserAuthDTO userAuth,
                                BindingResult result, HttpServletResponse response) {

        if(result.hasErrors()){
            return "sign-in";
        }

        User user = userService.findByLogin(userAuth);
        Session session = sessionService.create(user);
        cookieService.setSessionCookie(response, session.getSessionId());

        return "redirect:/home";
    }



    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){

        UUID userSessionId = cookieService.getSessionId(request);
        sessionService.deleteById(userSessionId);
        cookieService.clearSessionCookie(response);

        return "redirect:/sign-in";
    }

}



//
//Пример CRUD`a для сущности Post:
//HTTP метод		URL		Действие
//GET			/posts		Получаем все записи (READ)
//POST			/posts		Создаём новую запись (CREATE)
//GET			/posts/new	HTML форма для создания записи
//GET			/posts/:id/edit		HTML форма редактирования записи
//GET			/posts/:id	Получаем одну запись (READ)
//PATCH или PUT		/posts/:id	Обновляем запись (UPDATE)
//DELETE			/posts/:id	Удаляем запись (DELETE)