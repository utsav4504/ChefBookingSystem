package com.ChefBookingSystem.demo.Repository;

import com.ChefBookingSystem.demo.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends JpaRepository<Customer,Integer> {
    public Customer findByMobileNumber(String mobileNo);

    public Customer findByCustomerId(Integer customerId);
}
