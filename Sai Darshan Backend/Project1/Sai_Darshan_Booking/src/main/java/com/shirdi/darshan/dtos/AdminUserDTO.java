package com.shirdi.darshan.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminUserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String photoIdProof;
    private String photoIdNumber;
    private String phoneNumber;
    private String email;
    private String role;
    private LocalDateTime createdAt;
}
