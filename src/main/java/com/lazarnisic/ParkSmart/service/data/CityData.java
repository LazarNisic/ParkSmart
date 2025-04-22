package com.lazarnisic.ParkSmart.service.data;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityData {
    @NotBlank(message = "City name cannot be null or empty.")
    private String name;
    @NotBlank(message = "City country cannot be null or empty.")
    private String country;
}
