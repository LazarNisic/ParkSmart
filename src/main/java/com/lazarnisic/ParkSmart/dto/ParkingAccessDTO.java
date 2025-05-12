package com.lazarnisic.ParkSmart.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lazarnisic.ParkSmart.enums.AccessType;
import com.lazarnisic.ParkSmart.enums.NumberOfAccesses;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class ParkingAccessDTO {
    private Long id;
    private AccessType accessType;
    private LocalDateTime accessTimeStart;
    private LocalDateTime accessTimeEnd;
    private NumberOfAccesses numberOfAccesses;
    private LocalDateTime timestamp;
}
