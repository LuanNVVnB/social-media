package com.example.cnd.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(min = 6, max = 50)
    private String password;
    @NotBlank
    private String address;
    @NotNull
    private LocalDate birthday;
}
