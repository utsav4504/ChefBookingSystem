package com.ChefBookingSystem.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer customerId;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Food type is mandatory")
    private Chef.FoodType foodType;

    public enum FoodType {
        VEG, NON_VEG, BOTH
    }
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer",orphanRemoval = true)
    @JsonIgnore
    private List<ChefBooking> chefBooking = new ArrayList<>();


}
