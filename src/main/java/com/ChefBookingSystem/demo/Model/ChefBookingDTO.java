package com.ChefBookingSystem.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChefBookingDTO {
    private Integer bookingId;

    private Integer customerId;

    private String atLocation;

    private LocalDateTime fromTime;

     private float totalTimeInHour;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Food type is mandatory")
    private Chef.FoodType foodType;
    public enum FoodType {
        VEG, NON_VEG, BOTH
    }
}
