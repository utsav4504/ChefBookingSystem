package com.ChefBookingSystem.demo.Controller;

import com.ChefBookingSystem.demo.Exception.ChefBookingException;
import com.ChefBookingSystem.demo.Exception.ChefException;
import com.ChefBookingSystem.demo.Model.Chef;
import com.ChefBookingSystem.demo.Model.ChefDTO;
import com.ChefBookingSystem.demo.Service.ChefBookingService;
import com.ChefBookingSystem.demo.Service.ChefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/chef")
public class ChefController {
    @Autowired
    private ChefService dService;

    @Autowired
    private ChefBookingService chefBookingService;


    @PostMapping("/create")
    public ResponseEntity<Chef> saveChef(@Valid @RequestBody ChefDTO chef) throws ChefException {

        Chef savedChef= dService.createChef(chef);


        return new ResponseEntity<Chef>(savedChef, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public  ResponseEntity<Chef> updateChef(@Valid @RequestBody ChefDTO chef,@RequestParam(required = false) String sessionKey ) throws ChefException {


        Chef updatedChef= dService.updateChef(chef, sessionKey);

        return new ResponseEntity<Chef>(updatedChef,HttpStatus.OK);

    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteChef(@RequestParam Integer chefId,@RequestParam String sessionKey) throws ChefException{

        String deletedchef = dService.deleteChef(chefId,sessionKey);

        return new ResponseEntity<String>(deletedchef,HttpStatus.OK);
    }

    @GetMapping("/taskCompleted")
    public ResponseEntity<String> bookingCompletionHandler(@RequestParam Integer chefId,@RequestParam String sessionKey) throws ChefBookingException {
        String mess = chefBookingService.calculateBill(chefId,sessionKey);
        return  new ResponseEntity<String>(mess,HttpStatus.OK);
    }

}
