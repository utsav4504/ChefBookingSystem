package com.ChefBookingSystem.demo.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChefDTO extends User{

    private Integer age;


    private Float hourlyFee;
    @NotNull(message = "Food type is mandatory")
    private Chef.FoodType foodType;



    public enum FoodType {
        VEG, NON_VEG, BOTH
    }
    private int chefId;

}
