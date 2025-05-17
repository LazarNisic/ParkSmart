package com.lazarnisic.ParkSmart.service.data;

import com.lazarnisic.ParkSmart.enums.AccessType;
import com.lazarnisic.ParkSmart.enums.NumberOfAccesses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingAccessData {
    private AccessType accessType;
    private LocalDateTime accessTimeStart;
    private LocalDateTime accessTimeEnd;
    private NumberOfAccesses numberOfAccesses;
}
