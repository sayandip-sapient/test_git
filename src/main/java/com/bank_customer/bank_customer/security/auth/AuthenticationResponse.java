package com.bank_customer.bank_customer.security.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    @JsonProperty("access_token")
    private String accessToken;

    // Uncomment the following lines if you want to include 'refresh_token' in toString()
    // @JsonProperty("refresh_token")
    // private String refreshToken;

    @Override
    public String toString() {
        return "AuthenticationResponse{" +
                "accessToken='" + accessToken + '\'' +
                // Uncomment the following line if you want to include 'refresh_token' in toString()
                // ", refreshToken='" + refreshToken + '\'' +
                '}';
    }
}
