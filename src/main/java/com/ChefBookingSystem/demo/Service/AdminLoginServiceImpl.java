package com.ChefBookingSystem.demo.Service;

import com.ChefBookingSystem.demo.Exception.LoginException;
import com.ChefBookingSystem.demo.Model.Admin;
import com.ChefBookingSystem.demo.Model.CurrentUserSession;
import com.ChefBookingSystem.demo.Model.LoginDTO;
import com.ChefBookingSystem.demo.Repository.AdminDao;
import com.ChefBookingSystem.demo.Repository.CustomerDao;
import com.ChefBookingSystem.demo.Repository.SessionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bytebuddy.utility.RandomString;
import java.time.LocalDateTime;
import java.util.Optional;
@Service
public class AdminLoginServiceImpl implements AdminLoginService{
    @Autowired
    private AdminDao aDao;

    @Autowired
    private SessionDao sDao;




    @Override
    public String logIntoAccount(LoginDTO dto)throws LoginException {


        Admin existingAdmin= aDao.findByMobileNumber(dto.getMobileNumber());


        if(existingAdmin == null) {

            throw new LoginException("Please Enter a valid mobile number");


        }

        Optional<CurrentUserSession> validAdminSessionOpt =  sDao.findById(existingAdmin.getAdminId());




        if(validAdminSessionOpt.isPresent()) {

            throw new LoginException("Admin already Logged In with this number");

        }

        if(existingAdmin.getPassword().equals(dto.getPassword())) {

            String key= RandomString.make(6);



            CurrentUserSession currentUserSession = new CurrentUserSession(existingAdmin.getAdminId(),key, LocalDateTime.now());

            sDao.save(currentUserSession);

            return currentUserSession.toString();
        }
        else
            throw new LoginException("Please Enter a valid password");


    }


    @Override
    public String logOutFromAccount(String key)throws LoginException {

        CurrentUserSession validAdminSession = sDao.findByUuid(key);


        if(validAdminSession == null) {
            throw new LoginException("User Not Logged In with this number");

        }


        sDao.delete(validAdminSession);


        return "Logged Out !";


    }

}
