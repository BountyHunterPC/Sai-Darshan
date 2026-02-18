package com.shirdi.darshan.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.shirdi.darshan.enums.GenderEnum;
import com.shirdi.darshan.enums.PhotoIdProofEnum;
import com.shirdi.darshan.enums.RoleEnum;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//JPA
@Entity
@Table(name = "users")

//Lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String firstName;
    
    @Column(nullable = false)
    private String lastName;
    
    @Column(nullable = false)
    private LocalDate dateOfBirth;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GenderEnum gender;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PhotoIdProofEnum photoIdProof;
    
    @Column(nullable = false)
    private String photoIdNumber;
    
    @Column(nullable = false, length = 10)
    private String phoneNumber;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleEnum role = RoleEnum.USER;
    
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
    
