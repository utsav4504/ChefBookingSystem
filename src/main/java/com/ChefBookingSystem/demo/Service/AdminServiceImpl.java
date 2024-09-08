package com.ChefBookingSystem.demo.Service;

import com.ChefBookingSystem.demo.Exception.AdminException;
import com.ChefBookingSystem.demo.Model.*;
import com.ChefBookingSystem.demo.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminDao aDao;

    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private SessionDao sDao;
    @Autowired
    private ChefDao chefDao;
    @Autowired
private ChefBookingDao cDao;

    public Admin createAdmin(Admin admin)throws AdminException {


        Admin existingAdmin= aDao.findByMobileNumber(admin.getMobileNumber());




        if(existingAdmin != null )
            throw new AdminException("Admin Already Registered with Mobile number");




        return aDao.save(admin);


    }

    @Override
    public Admin updateAdmin(Admin Admin, String key) throws AdminException{

        CurrentUserSession loggedInUser= sDao.findByUuid(key);

        if(loggedInUser == null) {
            throw new AdminException("Please provide a valid key to update a Admin");
        }
        if (Admin == null) {
            throw new AdminException("Admin object cannot be null");
        }

        if(Admin.getAdminId() == loggedInUser.getUserId()) {

            return aDao.save(Admin);
        }
        else
            throw new AdminException("Invalid Admin Details, please login first");



    }

    @Override
    public String deleteAdmin(Integer adminId,String key) throws AdminException {


        CurrentUserSession loggedInUser= sDao.findByUuid(key);

        if(loggedInUser == null) {
            throw new AdminException("Please provide a valid key to delete a Admin");
        }


        if(adminId == loggedInUser.getUserId()) {



            Admin adminDetails = aDao.findByAdminId(adminId);

            if(adminDetails != null) {

                aDao.delete(adminDetails);
                return "Admin Deleted Successfull with Id : "+adminId ;

            }else {
                throw new AdminException("Admin not found with adminId :"+ adminId);
            }
        }else{
            throw new AdminException("Wrong Details Please login first!");
        }


    }
@Override
    public List<Customer> viewAllCustomers(Integer adminId, String key) throws AdminException {

        CurrentUserSession loggedInUser= sDao.findByUuid(key);
        if(loggedInUser == null) {
            throw new AdminException("Please provide a valid key to view customers details");
        }

    Admin adminDetails = aDao.findByAdminId(adminId);

        if(adminId == loggedInUser.getUserId()) {
            if (adminDetails != null) {

                List<Customer> customerList = customerDao.findAll();
                if (customerList.size() != 0) {
                    return customerList;
                } else {
                    throw new AdminException("No Customer found");
                }
            }
            else{
                throw new AdminException("wrong details");
            }
        }
        else {
            throw new AdminException("wrong details please login first");
        }
    }
    @Override
    public Customer viewCustomer(Integer adminId,Integer customerId,String key) throws AdminException {

        CurrentUserSession loggedInUser= sDao.findByUuid(key);
        if(loggedInUser == null) {
            throw new AdminException("Please provide a valid key of the admin to view customer details");
        }
        Admin adminDetails = aDao.findByAdminId(adminId);
        if(adminId == loggedInUser.getUserId()&&adminDetails!=null) {



            // TODO Auto-generated method stub
            Customer customer = customerDao.findByCustomerId(customerId);
            if(customer != null ) {
                return customer;
            }
            else {
                throw new AdminException("No Customer found");
            }
        }else {
            throw new AdminException("Invalid Admin Details, please login first");
        }

    }
    @Override
    public List<Chef> viewChefAll(Integer adminId, String key) throws AdminException {

        CurrentUserSession loggedInUser= sDao.findByUuid(key);
        if(loggedInUser == null) {
            throw new AdminException("Please provide a valid key to view chef details");
        }
        Admin adminDetails = aDao.findByAdminId(adminId);
        if(adminId == loggedInUser.getUserId()&&adminDetails!=null) {


            List<Chef> chefList = chefDao.findAll();
            if(chefList.size() != 0) {
                return chefList;
            }
            else {
                throw new AdminException("No Chef found");
            }
        }else {
            throw new AdminException("wrong details please login first or enter correct admin details");
        }
    }

    @Override
    public Chef viewChef(Integer adminId,Integer chefId,String key) throws AdminException {

        CurrentUserSession loggedInUser= sDao.findByUuid(key);
        if(loggedInUser == null) {
            throw new AdminException("Please provide a valid key of the admin to view chef details");
        }
        Admin adminDetails = aDao.findByAdminId(adminId);
        if(adminId == loggedInUser.getUserId() && adminDetails!=null) {



            // TODO Auto-generated method stub
            Chef chef = chefDao.findByChefId(chefId);
            if(chef != null ) {
                return chef;
            }
            else {
                throw new AdminException("No Chef found");
            }
        }else {
            throw new AdminException("Invalid Admin Details , please login first");
        }

    }
}
