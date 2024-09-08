package com.ChefBookingSystem.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chef extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer chefId;

    @NotNull(message = "Age is mandatory")
    @Min(value = 18, message = "Age must be greater than or equal to 18")
    private Integer age;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Food type is mandatory")
    private FoodType foodType;



    @OneToMany(cascade = CascadeType.ALL,  mappedBy = "chef", orphanRemoval = true)
    @JsonIgnore
    List<ChefBooking> chefBookingList = new ArrayList<>();



    private Boolean availablity = true;
    private double hourlyFee;

    public enum FoodType {
        VEG, NON_VEG, BOTH
    }


}
