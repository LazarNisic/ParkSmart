package com.lazarnisic.ParkSmart.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class TokenHeader implements Serializable {
    private String alg;
}
