package com.ChefBookingSystem.demo.Repository;

import com.ChefBookingSystem.demo.Model.CurrentUserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionDao extends JpaRepository<CurrentUserSession,Integer> {
    public CurrentUserSession findByUuid(String uuid);
}
