package io.github.xcvqqz.weather_app.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "sessions")
public class Session {

    @Id
    @Column(name = "session_id", nullable = false, unique = true, updatable = false)
    private UUID sessionId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;            //Когда токен выпуще

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;            //когда перестанет работать

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


}
