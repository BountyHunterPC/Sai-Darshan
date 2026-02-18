package com.shirdi.darshan.dtos;

import com.shirdi.darshan.enums.GenderEnum;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DevoteeDetailsDto {

    @NotBlank(message = "Devotee name is required")
    private String name;

    @NotNull(message = "Age is required")
    @Min(value = 1, message = "Age must be greater than 0")
    private Integer age;

    @NotNull(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Mobile number is required")
    private String mobile;

    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Sankalp is required")
    @Size(max = 500, message = "Sankalp cannot exceed 500 characters")
    private String sankalp;

    private String gotra;

    @Size(max = 500, message = "Address cannot exceed 500 characters")
    private String address;
}
