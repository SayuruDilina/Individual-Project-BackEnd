package edu.icet.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    private Integer userId;

    @NotBlank(message = "email is required")
    private String email;
    @NotBlank(message = "userName is required")
    private String userName;
    @NotBlank(message = "password is required")
    private String password;
    @NotBlank(message = "address is required")
    private String address;
    @NotBlank(message = "contact number is required")
    private String contactNumber;

}
