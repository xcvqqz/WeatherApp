package io.github.xcvqqz.weather_app.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;


@Configuration
@EnableScheduling
@PropertySource("classpath:application-dev.properties")
@ComponentScan("io.github.xcvqqz.weather_app")
public class AppConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(5000);
        factory.setReadTimeout(10000);
        return new RestTemplate(factory);
    }

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