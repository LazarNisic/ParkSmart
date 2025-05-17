package com.lazarnisic.ParkSmart.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lazarnisic.ParkSmart.enums.AccessType;
import com.lazarnisic.ParkSmart.enums.NumberOfAccesses;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@SuperBuilder
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class ParkingAccessDTO {
    private Long id;
    private AccessType accessType;
    private LocalTime accessTimeStart;
    private LocalTime accessTimeEnd;
    private NumberOfAccesses numberOfAccesses;
    private LocalDateTime timestamp;
}
