package com.lazarnisic.ParkSmart.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lazarnisic.ParkSmart.enums.AccessType;
import com.lazarnisic.ParkSmart.enums.NumberOfAccesses;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "parking_access", schema = "public")
public class ParkingAccess {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "access_type")
    private AccessType accessType;

    @Enumerated(EnumType.STRING)
    @Column(name = "number_of_accesses")
    private NumberOfAccesses numberOfAccesses;

    @JsonFormat(pattern = "HH:mm:ss")
    @Column(name = "access_time_start")
    private LocalTime accessTimeStart;

    @JsonFormat(pattern = "HH:mm:ss")
    @Column(name = "access_time_end")
    private LocalTime accessTimeEnd;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;
}
