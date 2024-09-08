package com.ChefBookingSystem.demo.Service;

import com.ChefBookingSystem.demo.Exception.ChefBookingException;
import com.ChefBookingSystem.demo.Model.BillDetails;
import com.ChefBookingSystem.demo.Model.ChefBooking;
import com.ChefBookingSystem.demo.Model.ChefBookingDTO;

import java.util.List;

public interface ChefBookingService {
    public ChefBooking insertChefBooking(ChefBookingDTO chefBooking, String key)throws ChefBookingException;

    public String deleteChefBooking(Integer customerId,String key) throws ChefBookingException;

    public List<ChefBooking> viewAllBookingCustomer(Integer customerld, String key) throws ChefBookingException;

    public String calculateBill(Integer chefId, String key) throws  ChefBookingException;

    public BillDetails generateBill(Integer customerId, Integer chefBookingId, String key) throws  ChefBookingException;

    public List< ChefBooking> getAllBookings(Integer adminId, String key) throws  ChefBookingException;
}
