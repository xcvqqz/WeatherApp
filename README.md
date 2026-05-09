# WeatherApp ⛅
[![Java](https://img.shields.io/badge/Java-17-orange)](https://www.oracle.com/java/)
[![Spring](https://img.shields.io/badge/Spring-6.0-brightgreen)](https://spring.io/)
[![Hibernate](https://img.shields.io/badge/Hibernate-6.0-brown)](https://hibernate.org/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue)](https://www.postgresql.org/)
[![Gradle](https://img.shields.io/badge/Gradle-8.13-red)](https://gradle.org/)
[![Docker](https://img.shields.io/badge/Docker-24.0-blue)](https://www.docker.com/)
[![Flyway](https://img.shields.io/badge/Flyway-10.21-red)](https://flywaydb.org/)

## 📋 О проекте
Веб-приложение для просмотра текущей погоды. Пользователь может зарегистрироваться и добавить в коллекцию одну или несколько локаций (городов, сёл, других пунктов), после чего главная страница приложения начинает отображать список локаций с их текущей погодой.

### Мотивация
- Использование cookies и сессий для авторизации пользователей
- Знакомство со Spring
- Работа с внешними API

## 🎯 Функциональность
### Основные возможности
#### Работа с пользователями
-  Регистрация пользователя
-  Авторизация пользователя
-  Logout
-  Подсчёт очков в текущем матче в реальном времени
 #### Работа с локациями
-  Поиск
-  Добавление в список
-  Просмотр списка локаций, для каждой локации отображается название и температура
-  Удаление из списка

  ## 🛠 Технологический стек
### Backend
- **Java 17** - основной язык программирования
- **Apache Tomcat 10.1.x** - веб-сервер (или любой Servlet 6.0 совместимый контейнер)
- **Gradle 8.13** - система сборки
- **Spring Framework 6.2.1** (без Spring Boot) - основной фреймворк:
- *Spring WebMVC* - MVC контроллеры
- *Spring JDBC* / TX / ORM - работа с транзакциями и БД
- *Spring Context* - DI контейнер
- **Thymeleaf 3.1.2** - шаблонизатор (view resolver)
- **Hibernate ORM 6.6.3.Final** - JPA/Hibernate ORM
- **PostgreSQL** - основная реляционная база данных
- **Flyway 10.21.0** - миграции схемы базы данных

### Тестирование
- **JUnit Jupiter 5.10.2** - модульное тестирование
- **Mockito 5.11.0** - мокирование
- **Spring Test** - интеграционное тестирование Spring
- **H2 Database 2.2.22**4 - in-memory БД для тестов
- **AssertJ 3.27.7** - fluent assertions

### Frontend
- **HTML/CSS** - вёрстка интерфейса
- **Блочная вёрстка** - современный подход к CSS

## 🐳 Docker
### Сборка образа
```bash
docker build -t myapp


