package io.github.xcvqqz.weather_app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "sessions")
public class Session {

    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private UUID sessionId;

    @Column(name = "expires_at")
    @Builder.Default
    private LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(30);

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}