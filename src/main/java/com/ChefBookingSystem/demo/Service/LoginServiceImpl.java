package com.ChefBookingSystem.demo.Service;

import com.ChefBookingSystem.demo.Exception.LoginException;
import com.ChefBookingSystem.demo.Model.CurrentUserSession;
import com.ChefBookingSystem.demo.Model.Customer;
import com.ChefBookingSystem.demo.Model.LoginDTO;
import com.ChefBookingSystem.demo.Repository.CustomerDao;
import com.ChefBookingSystem.demo.Repository.SessionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.bytebuddy.utility.RandomString;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private CustomerDao cDao;

    @Autowired
    private SessionDao sDao;



    @Override
    public String logIntoAccount(LoginDTO dto)throws LoginException {


        Customer existingCustomer= cDao.findByMobileNumber(dto.getMobileNumber());

        if(existingCustomer == null) {

            throw new LoginException("Please Enter a valid mobile number");


        }

        Optional<CurrentUserSession> validCustomerSessionOpt =  sDao.findById(existingCustomer.getCustomerId());


        if(validCustomerSessionOpt.isPresent()) {

            throw new LoginException("User already Logged In with this number");

        }

        if(existingCustomer.getPassword().equals(dto.getPassword())) {

            String key= RandomString.make(6);



            CurrentUserSession currentUserSession = new CurrentUserSession(existingCustomer.getCustomerId(),key, LocalDateTime.now());

            sDao.save(currentUserSession);

            return currentUserSession.toString();
        }
        else
            throw new LoginException("Please Enter a valid password");


    }


    @Override
    public String logOutFromAccount(String key)throws LoginException {

        CurrentUserSession validCustomerSession = sDao.findByUuid(key);


        if(validCustomerSession == null) {
            throw new LoginException("User Not Logged In");

        }


        sDao.delete(validCustomerSession);


        return "Logged Out !";

    }

}
