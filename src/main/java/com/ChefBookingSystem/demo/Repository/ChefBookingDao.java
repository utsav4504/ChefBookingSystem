package com.ChefBookingSystem.demo.Repository;

import com.ChefBookingSystem.demo.Model.ChefBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChefBookingDao extends JpaRepository<ChefBooking,Integer> {

}
