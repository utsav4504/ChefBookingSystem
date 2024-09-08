package com.ChefBookingSystem.demo.Service;

import com.ChefBookingSystem.demo.Exception.AdminException;
import com.ChefBookingSystem.demo.Model.Admin;
import com.ChefBookingSystem.demo.Model.Chef;
import com.ChefBookingSystem.demo.Model.Customer;

import java.util.List;

public interface AdminService {
    public Admin createAdmin(Admin Admin)throws AdminException;

    public  Admin updateAdmin(Admin Admin,String key)throws AdminException;

    public String deleteAdmin(Integer adminId,String key) throws AdminException;
    public List<Customer> viewAllCustomers(Integer adminId, String key) throws AdminException;
    public Customer viewCustomer(Integer adminId,Integer customerId,String key) throws AdminException;
    public List<Chef> viewChefAll(Integer adminId, String key) throws AdminException;
    public Chef viewChef(Integer adminId,Integer chefId,String key) throws AdminException;
}
