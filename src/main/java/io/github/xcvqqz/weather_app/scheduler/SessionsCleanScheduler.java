package io.github.xcvqqz.weather_app.scheduler;


import io.github.xcvqqz.weather_app.service.SessionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class SessionsCleanScheduler {

    private final SessionService sessionService;

    @Scheduled(cron = "${cleanup.cron.expression}", zone = "${zone.cron.expression}")
    public void cleanExpiredSessions(){
        int countDeletedSessions = sessionService.deleteExpiredSessions();
        log.info("expired sessions have been deleted : {} ", countDeletedSessions);
    }
}