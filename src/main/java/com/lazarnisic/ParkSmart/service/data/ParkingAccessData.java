package com.lazarnisic.ParkSmart.service.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lazarnisic.ParkSmart.enums.AccessType;
import com.lazarnisic.ParkSmart.enums.NumberOfAccesses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingAccessData {
    private AccessType accessType;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime accessTimeStart;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime accessTimeEnd;
    private NumberOfAccesses numberOfAccesses;
}
