package io.github.xcvqqz.weather_app.controller;


import io.github.xcvqqz.weather_app.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sign-in")
public class AuthenticationController {

    @GetMapping()
    public String signIn(@ModelAttribute("user") User user) {
        return "first/sign-in";
    }


    @PostMapping()
    public String signIn(@ModelAttribute("user") User user){

        //получение из базы пользоватетеля -> dao.find(user.getId())
        //перенаправление на его страницу

        return "redirect:/index";
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