package com.lazarnisic.ParkSmart.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "features", schema = "public")
public class Features {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "supports_SUV")
    private boolean supportsSUV;

    @Column(name = "has_video_surveillance")
    private boolean hasVideoSurveillance;

    @Column(name = "accessible_for_disabled")
    private boolean accessibleForDisabled;

    @Column(name = "night_lighting")
    private boolean nightLighting;

    @Column(name = "has_EV_charging_station")
    private boolean hasEVChargingStation;

    @Column(name = "transportation_service")
    private boolean transportationService;

    @Column(name = "indoor")
    private boolean indoor;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;
}
