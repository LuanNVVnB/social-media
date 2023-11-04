package com.example.cnd.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * File: com.example.cnd.request
 * Description:  <div style="font-weight: bold; color: #0073e6;"> ProfileRequest </div><hr>
 * Author:  <span style="font-weight: bold; color: #00a65a;"></span>
 * Date: <span style="font-weight: bold; color: #ff5722;">11/4/2023</span>
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileRequest {
    @NotBlank
    @Size(min = 6, max = 24)
    @JsonProperty("user_name")
    private String userName;
    @NotBlank
    @JsonProperty("first_name")
    private String firstName;
    @NotBlank
    @JsonProperty("last_name")
    private String lastName;

    @NotBlank
    private String address;
    @NotNull
    private LocalDate birthday;
}
