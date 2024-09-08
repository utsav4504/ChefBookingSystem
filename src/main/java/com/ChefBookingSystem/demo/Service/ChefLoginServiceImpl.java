package com.ChefBookingSystem.demo.Service;

import com.ChefBookingSystem.demo.Exception.LoginException;
import com.ChefBookingSystem.demo.Model.Chef;
import com.ChefBookingSystem.demo.Model.CurrentUserSession;
import com.ChefBookingSystem.demo.Model.LoginDTO;
import com.ChefBookingSystem.demo.Repository.ChefDao;
import com.ChefBookingSystem.demo.Repository.SessionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.bytebuddy.utility.RandomString;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ChefLoginServiceImpl implements ChefLoginService{

    @Autowired
    private ChefDao dDao;

    @Autowired
    private SessionDao sDao;



    @Override
    public String logIntoAccount(LoginDTO dto)throws LoginException {


        Chef existingChef= dDao.findByMobileNumber(dto.getMobileNumber());





        if(existingChef == null) {

            throw new LoginException("Please Enter a valid mobile number");


        }


        Optional<CurrentUserSession> validChefSessionOpt =  sDao.findById(existingChef.getChefId());



        if(validChefSessionOpt.isPresent()) {

            throw new LoginException("User already Logged In with this number");

        }

        if(existingChef.getPassword().equals(dto.getPassword())) {

            String key= RandomString.make(6);



            CurrentUserSession currentUserSession = new CurrentUserSession(existingChef.getChefId(),key, LocalDateTime.now());

            sDao.save(currentUserSession);

            return currentUserSession.toString();
        }
        else
            throw new LoginException("Please Enter a valid password");


    }


    @Override
    public String logOutFromAccount(String key)throws LoginException {

        CurrentUserSession validChefSession = sDao.findByUuid(key);


        if(validChefSession == null) {
            throw new LoginException("User Not Logged In with this number");

        }


        sDao.delete(validChefSession);


        return "Logged Out !";


    }
}
