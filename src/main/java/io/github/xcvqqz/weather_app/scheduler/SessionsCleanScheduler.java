package io.github.xcvqqz.weather_app.scheduler;


import io.github.xcvqqz.weather_app.service.SessionService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SessionsCleanScheduler {

    private final SessionService sessionService;

    @Scheduled(cron = "${cleanup.cron.expression}", zone = "${zone.cron.expression}") //чистка каждые 30 минут
    public void cleanExpiredSessions(){
        sessionService.deleteExpiredSessions();
    }


}


//нужно добавить логгирование