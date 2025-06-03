package com.lazarnisic.ParkSmart.service.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeaturesData {
    private boolean supportsSUV;
    private boolean hasVideoSurveillance;
    private boolean accessibleForDisabled;
    private boolean nightLighting;
    private boolean hasEVChargingStation;
}
