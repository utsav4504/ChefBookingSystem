package com.ChefBookingSystem.demo.Repository;

import com.ChefBookingSystem.demo.Model.Chef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChefDao extends JpaRepository<Chef,Integer> {
    public Chef findByMobileNumber(String mobileNo);

    public Chef findByChefId(Integer chefId);




}
