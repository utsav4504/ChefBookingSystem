package com.ChefBookingSystem.demo.Controller;

import com.ChefBookingSystem.demo.Exception.LoginException;
import com.ChefBookingSystem.demo.Model.LoginDTO;
import com.ChefBookingSystem.demo.Service.ChefLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginChefController {
    @Autowired
    private ChefLoginService chefLogin;

    @PostMapping("/loginChef")
    public ResponseEntity<String> logIn(@RequestBody LoginDTO dto) throws LoginException {

        String result = chefLogin.logIntoAccount(dto);



        return new ResponseEntity<String>(result, HttpStatus.OK );


    }

    @GetMapping("/logoutChef")
    public String logout(@RequestParam(required = false) String sessionKey) throws LoginException {
        return chefLogin.logOutFromAccount(sessionKey);

    }


}
