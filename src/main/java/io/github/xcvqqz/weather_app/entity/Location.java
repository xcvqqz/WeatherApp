package io.github.xcvqqz.weather_app.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Entity
@Table(name = "locations",
uniqueConstraints = {
        @UniqueConstraint(
                name = "unique_location_user_coordinates",
                columnNames = {"user_id", "longitude", "latitude"}
        )
})
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, updatable = false)
    private Long id;

    @NotBlank
    @NotNull
    @Size(min = 3, max = 30)
    @Column(length = 30)
    private String name;

    @Column(columnDefinition = "DECIMAL(9,6)")
    private BigDecimal longitude;

    @Column(columnDefinition = "DECIMAL(9,6)")
    private BigDecimal latitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


}
