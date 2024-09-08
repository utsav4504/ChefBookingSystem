package com.ChefBookingSystem.demo.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class User {
    @NotNull(message = "username cannot be null")
    @Size(min=4 , max = 10 , message = "length of username must be between 4 & 10")
    @Column(unique = true, nullable = false)
    private String username;

    @NotNull(message = "password cannot be null")
    @Size(min=6 , max = 10 , message = "length of password must be between 6 & 10")
    private String password;

    @NotNull(message = "address cannot be null")
    private String address;

    @NotNull(message = "mobile number cannot be null")
    @Pattern(regexp = "[789]{1}[0-9]{9}",message = "invalid mobile number")
    @Column(unique = true, nullable = false)
    private String mobileNumber;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

}
