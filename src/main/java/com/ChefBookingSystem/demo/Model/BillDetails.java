package com.ChefBookingSystem.demo.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillDetails {
    private double hourlyFee;
    private Float hours;
    private Float totalBill;


}
