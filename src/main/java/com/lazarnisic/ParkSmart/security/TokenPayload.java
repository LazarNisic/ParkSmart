package com.lazarnisic.ParkSmart.security;

import com.lazarnisic.ParkSmart.enums.TokenType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class TokenPayload implements Serializable {
    private TokenType tokenType;
    private String apiKey;
}
