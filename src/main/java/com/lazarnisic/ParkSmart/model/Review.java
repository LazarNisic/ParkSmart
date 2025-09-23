package com.lazarnisic.ParkSmart.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "review", schema = "public")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(targetEntity = ParkingSpotRent.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "parking_spot_rent_id")
    private ParkingSpotRent parkingSpotRent;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "rating")
    @Min(1)
    @Max(5)
    private int rating;

    @Column(name = "comment")
    private String comment;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;
}
