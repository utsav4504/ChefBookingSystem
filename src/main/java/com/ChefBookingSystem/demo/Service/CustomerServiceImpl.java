package com.ChefBookingSystem.demo.Service;

import com.ChefBookingSystem.demo.Exception.AdminException;
import com.ChefBookingSystem.demo.Exception.CustomerException;
import com.ChefBookingSystem.demo.Model.Chef;
import com.ChefBookingSystem.demo.Model.ChefBooking;
import com.ChefBookingSystem.demo.Model.CurrentUserSession;
import com.ChefBookingSystem.demo.Model.Customer;
import com.ChefBookingSystem.demo.Repository.ChefDao;
import com.ChefBookingSystem.demo.Repository.CustomerDao;
import com.ChefBookingSystem.demo.Repository.SessionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerDao cDao;

    @Autowired
    private SessionDao sDao;

    @Autowired
    private ChefDao ddao;


    @Override
    public Customer createCustomer(Customer customer)throws CustomerException {


        Customer existingCustomer= cDao.findByMobileNumber(customer.getMobileNumber());



        if(existingCustomer != null)
            throw new CustomerException("Customer Already Registered with Mobile number");




        return cDao.save(customer);


    }

    @Override
    public Customer updateCustomer(Customer customer, String key) throws CustomerException{

        CurrentUserSession loggedInUser= sDao.findByUuid(key);

        if(loggedInUser == null) {
            throw new CustomerException("Please provide a valid key to update a customer");
        }

        if (customer == null) {
            throw new CustomerException("Customer object cannot be null");
        }


        if(customer.getCustomerId() == loggedInUser.getUserId()) {
            //If LoggedInUser id is same as the id of supplied Customer which we want to update
            return cDao.save(customer);
        }
        else
            throw new CustomerException("Invalid Customer Details, please login first");

    }

    @Override
    public String deleteCustomer(Integer customerId ,String key ) throws CustomerException {
        CurrentUserSession loggedInUser= sDao.findByUuid(key);
        if(loggedInUser == null) {
            throw new CustomerException("Please provide a valid key to delete a customer");
        }

        if(customerId == loggedInUser.getUserId()) {

            Optional<Customer> c = cDao.findById(customerId);

            if(c.isPresent()) {
                Customer cust = c.get();
                List<ChefBooking> bookDetailsList = cust.getChefBooking();
                if(bookDetailsList.size() > 0) {
                    if(bookDetailsList.get(bookDetailsList.size() -1).isStatus() == false) {
                        Chef chef = bookDetailsList.get(bookDetailsList.size()-1).getChef();
                        chef.setAvailablity(true);
                        ddao.save(chef);
                        bookDetailsList.remove(bookDetailsList.size()-1);
                        cDao.save(cust);
                    }
                }

                cDao.delete(cust);
                return "Customer with id : "+customerId+" deleted";

            }else {
                throw new CustomerException("Customer not found with Id : " +customerId);
            }

        }
        else {
            throw new CustomerException("Invalid Customer Details, please login first");
        }


    }

}
