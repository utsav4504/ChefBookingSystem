package com.ChefBookingSystem.demo.Controller;

import com.ChefBookingSystem.demo.Exception.LoginException;
import com.ChefBookingSystem.demo.Model.LoginDTO;
import com.ChefBookingSystem.demo.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginCustomerController {
    @Autowired
    private LoginService customerLogin;

    @PostMapping("/loginCustomer")
    public ResponseEntity<String> logIn(@RequestBody LoginDTO dto) throws LoginException {

        String result = customerLogin.logIntoAccount(dto);



        return new ResponseEntity<String>(result, HttpStatus.OK );


    }

    @GetMapping("/logoutCustomer")
    public String logout(@RequestParam(required = false) String sessionKey) throws LoginException {
        return customerLogin.logOutFromAccount(sessionKey);

    }
}
