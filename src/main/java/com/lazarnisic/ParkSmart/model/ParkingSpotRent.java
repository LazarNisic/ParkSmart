package com.lazarnisic.ParkSmart.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "parking_spot_rent", schema = "public",
        indexes = {
                //@Index(name = "idx_parking_spot_city", columnList = "city_id"),
                @Index(name = "idx_parking_spot_rent_city", columnList = "city_id, is_available")
        })
public class ParkingSpotRent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(targetEntity = City.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "address")
    private String address;

    @Column(name = "is_available")
    private boolean available;

    @Column(name = "price_per_hour")
    private double pricePerHour;

    @Column(name = "price_per_day")
    private Double pricePerDay;

    @Column(name = "price_per_month")
    private Double pricePerMonth;

    @Column(name = "min_booking_duration")
    private int minBookingDuration;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "access_id", referencedColumnName = "id")
    private ParkingAccess parkingAccess;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "features_id", referencedColumnName = "id")
    private Features features;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "parkingSpotRent", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "parkingSpotRent", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ParkingSpotImage> images;

}
