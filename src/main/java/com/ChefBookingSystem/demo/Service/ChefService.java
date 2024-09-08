package com.ChefBookingSystem.demo.Service;

import com.ChefBookingSystem.demo.Exception.ChefException;
import com.ChefBookingSystem.demo.Exception.InvalidId;
import com.ChefBookingSystem.demo.Model.Chef;
import com.ChefBookingSystem.demo.Model.ChefDTO;

import java.util.List;

public interface ChefService {
    public Chef createChef(ChefDTO chef)throws ChefException;



    public Chef viewChefById(Integer id) throws InvalidId;

    public  Chef updateChef(ChefDTO chef,String key)throws ChefException;

    public String deleteChef(Integer chefId ,String key) throws ChefException;


}
