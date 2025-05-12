package com.lazarnisic.ParkSmart.model;

import com.lazarnisic.ParkSmart.enums.AccessType;
import com.lazarnisic.ParkSmart.enums.NumberOfAccesses;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

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

    @Column(name = "access_time_start")
    private LocalDateTime accessTimeStart;

    @Column(name = "access_time_end")
    private LocalDateTime accessTimeEnd;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;
}
