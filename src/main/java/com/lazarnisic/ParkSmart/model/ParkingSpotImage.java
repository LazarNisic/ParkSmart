package com.lazarnisic.ParkSmart.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "parking_spot_image", schema = "public")
public class ParkingSpotImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(targetEntity = ParkingSpot.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "parking_spot_id")
    private ParkingSpot parkingSpot;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;
}
