package com.lazarnisic.ParkSmart.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class FeaturesDTO {
    private Long id;
    private boolean supportsSUV;
    private boolean hasVideoSurveillance;
    private boolean accessibleForDisabled;
    private boolean nightLighting;
    private boolean hasEVChargingStation;
    private LocalDateTime timestamp;
    private boolean transportationService;
    private boolean indoor;
}
