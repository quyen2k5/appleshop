package com.exe.shojin.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
public class User {
    @Id
    @Column(name = "UserName")
    private String username;

    @Column(name = "FullName")
    private String fullname;

    @Column(name = "Password")
    private String password;

    @Column(name = "Role")
    private String role;

    @Email(message = "Email should be valid")
    @Column(name = "Email")
    private String email;

    @Column(name = "Phone")
    private String phone;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "DateOfBirth")
    private LocalDate dateofbirth;

    @Column(name = "Image")
    private String image;

    

}
