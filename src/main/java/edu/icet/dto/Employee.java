package edu.icet.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private Integer employeeId;
    @NotBlank(message = "employeeName is required")
    private  String employeeName;
    @NotBlank(message = "email is required")
    private String email;
    @NotBlank(message = "contactNumber is required")
    private  String  contactNumber;
    @NotBlank(message = "address is required")
    private  String address;
    @NotBlank(message = "position is required")
    private String position;
}
