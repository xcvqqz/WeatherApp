package io.github.xcvqqz.weather_app.controller;


import io.github.xcvqqz.weather_app.dto.UserAuthDTO;
import io.github.xcvqqz.weather_app.entity.User;
import io.github.xcvqqz.weather_app.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@AllArgsConstructor
@Controller
@RequestMapping("/sign-in")
public class AuthController {

    private UserService userService;


    @GetMapping()
    public String showSignIn(UserAuthDTO userAuth, Model model) {
        model.addAttribute("user", userAuth);
        return "first/sign-in";
    }


    @PostMapping()
    public String processSignIn(@Valid @ModelAttribute("user") UserAuthDTO userAuth,
                       BindingResult result, Model model) {

        if(result.hasErrors()){
            return "first/sign-in";
        }

        model.addAttribute("authUser", userService.findByLogin(userAuth));  ???

        return "redirect:/home";
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