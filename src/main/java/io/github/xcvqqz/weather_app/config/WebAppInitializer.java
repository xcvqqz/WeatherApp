package io.github.xcvqqz.weather_app.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {


    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {AppConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }






    /* аналогия:


Теперь приступим к web.xml (Сервер (Tomcat) считывает содержимое web.xml, мы даёт указания, чтобы сервер делал)

Мы хотим чтобы все запросы от пользователей приходили на DispatcherServlet.

Когда запрос будет попадать сначала на сервер, а потом DispatcherServlet, то тогда запрос будет попадать на наше Spring MVC приложение

    пропишем в web.xml:

    <servlet>
      <servlet-name>dispatcher</servlet-name>
      <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>   //берём из библиотеки SpringFramework
      <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>/WEB-INF/applicationContextMVC.xml</param-value>   //здесь указан xml конфиг, но потом сделаем Java конфиг
      </init-param>
      <load-on-startup>1</load-on-startup> //даёт указание, что DispatcherServlet нужно первым загружать
    </servlet>

    <servlet-mapping>
      <servlet-name>dispatcher</servlet-name>
      <url-pattern>/</url-pattern>  //указывает, что любой url к которому обращается пользователь попадает
    </servlet-mapping>		 	//в DispatcherServlet, а он уже потом сам направит в праивльный контроллер
  </web-app>


    с помощью тега servlet создаём DispatcherServlet
*/



}
