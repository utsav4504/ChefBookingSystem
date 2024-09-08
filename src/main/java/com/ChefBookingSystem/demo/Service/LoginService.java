package com.ChefBookingSystem.demo.Service;

import com.ChefBookingSystem.demo.Exception.LoginException;
import com.ChefBookingSystem.demo.Model.LoginDTO;

public interface LoginService {
    public String logIntoAccount(LoginDTO dto)throws LoginException;

    public String logOutFromAccount(String key)throws LoginException;
}
