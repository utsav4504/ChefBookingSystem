package com.ChefBookingSystem.demo.Service;

import com.ChefBookingSystem.demo.Exception.CustomerException;
import com.ChefBookingSystem.demo.Model.Customer;

import java.util.List;

public interface CustomerService {
    public Customer createCustomer(Customer customer)throws CustomerException;

    public Customer updateCustomer(Customer customer,String key)throws CustomerException;

    public String deleteCustomer(Integer customerId ,String key)throws CustomerException;


}
