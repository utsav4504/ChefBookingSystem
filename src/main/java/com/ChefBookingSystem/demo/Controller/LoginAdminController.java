package com.ChefBookingSystem.demo.Controller;

import com.ChefBookingSystem.demo.Exception.LoginException;
import com.ChefBookingSystem.demo.Model.LoginDTO;
import com.ChefBookingSystem.demo.Service.AdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginAdminController {
    @Autowired
    private AdminLoginService adminLogin;

    @PostMapping("/loginAdmin")
    public ResponseEntity<String> logIn(@RequestBody LoginDTO dto) throws LoginException {

        String result = adminLogin.logIntoAccount(dto);



        return new ResponseEntity<String>(result, HttpStatus.OK );


    }

    @GetMapping("/logoutAdmin")
    public String logout(@RequestParam(required = false) String sessionKey) throws LoginException {
        return adminLogin.logOutFromAccount(sessionKey);

    }
}
