package io.github.xcvqqz.weather_app.config;



import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
//@ComponentScan(basePackages = {
//        "io.github.xcvqqz.weather_app.service",
//        "io.github.xcvqqz.weather_app.repository"
//})
public class AppConfig {



}





//здесь будем прописывать все бины
    /* При использовании конфигурации с помощью Java кода, Spring Container будет представлeн классом AnnotationConfigApplicationContext.
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);


    public class Test6 {
        public static void main(String[] args) {
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
            Person person = context.getBean("personBean", Person.class);
            person.callYourPet();
            context.close();
        }
*/