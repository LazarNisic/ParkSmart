package com.lazarnisic.ParkSmart.service.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
    private String username;
    private String password;
    private String fullName;
}
