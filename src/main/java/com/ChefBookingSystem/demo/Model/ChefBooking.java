package com.ChefBookingSystem.demo.Model;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChefBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bookingId;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Chef chef;

    private String atLocation;
    private LocalDateTime fromDateTime;

    private boolean status;
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    @NotNull(message = "Time is mandatory")
    @Min(value = 2, message = "At least for 2 Hours")
    private float totalTimeInHour;
    private float bill;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Food type is mandatory")
    private Chef.FoodType foodType;
    public enum FoodType {
        VEG, NON_VEG, BOTH
    }
}
