package com.ChefBookingSystem.demo.Controller;

import com.ChefBookingSystem.demo.Exception.AdminException;
import com.ChefBookingSystem.demo.Exception.ChefBookingException;
import com.ChefBookingSystem.demo.Model.Admin;
import com.ChefBookingSystem.demo.Model.Chef;
import com.ChefBookingSystem.demo.Model.ChefBooking;
import com.ChefBookingSystem.demo.Model.Customer;
import com.ChefBookingSystem.demo.Service.AdminService;
import com.ChefBookingSystem.demo.Service.ChefBookingService;

import com.ChefBookingSystem.demo.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RequestMapping(value = "/admin")
@RestController
public class AdminController {

    @Autowired
    private AdminService AService;

    @Autowired
    private ChefBookingService chefBookingService;
    @Autowired
    private CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<Admin> saveAdmin(@Valid @RequestBody Admin admin) throws AdminException {

        Admin savedAdmin = AService.createAdmin(admin);


        return new ResponseEntity<Admin>(savedAdmin, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public  ResponseEntity<Admin> updateAdmin(@Valid @RequestBody Admin admin,@RequestParam(required = false) String sessionKey ) throws AdminException {


        Admin updatedCustomer= AService.updateAdmin(admin, sessionKey);

        return new ResponseEntity<Admin>(updatedCustomer,HttpStatus.OK);

    }
    @DeleteMapping("/detete")
    public ResponseEntity<String> deleteAdmin(@RequestParam Integer adminId, @RequestParam String sessionKey) throws AdminException{

        String DeleteAdmin = AService.deleteAdmin(adminId, sessionKey);

        return new ResponseEntity<String>(DeleteAdmin,HttpStatus.OK);
    }

    @GetMapping("/allBookings")
    public ResponseEntity<List<ChefBooking>>  allBookings(@RequestParam Integer adminId , @RequestParam String sessionKey) throws ChefBookingException {

        List<ChefBooking> chefBooking = chefBookingService.getAllBookings(adminId,sessionKey);

        return new ResponseEntity<List<ChefBooking>>(chefBooking,HttpStatus.OK);
    }


    @GetMapping("/viewAll/customer/{adminId}/{sessionKey}")
    public ResponseEntity<List<Customer>> findAllCustomer(@PathVariable("adminId") Integer adminId, @PathVariable("sessionKey") String sessionKey) throws  AdminException {

        List<Customer> customers = AService.viewAllCustomers(adminId,sessionKey);

        return new ResponseEntity<List<Customer>>(customers,HttpStatus.OK);
    }
    @GetMapping("/viewById/customer/{adminId}/{customerId}/{sessionKey}")
    public ResponseEntity<Customer> findCustomerById(@PathVariable("adminId") Integer adminId,@PathVariable("customerId") Integer customerId ,@PathVariable("sessionKey") String sessionKey) throws AdminException{
        Customer customer = AService.viewCustomer(adminId,customerId,sessionKey);

        return new ResponseEntity<Customer>(customer,HttpStatus.OK);

    }
    @GetMapping("/viewAll/chef/{adminId}/{sessionKey}")
    public ResponseEntity<List<Chef>> findAllChef(@PathVariable("adminId") Integer adminId, @PathVariable("sessionKey") String sessionKey) throws  AdminException {

        List<Chef> chefs = AService.viewChefAll(adminId,sessionKey);

        return new ResponseEntity<List<Chef>>(chefs,HttpStatus.OK);
    }

    @GetMapping("/viewById/chef/{adminId}/{chefId}/{sessionKey}")
    public ResponseEntity<Chef> findChefById(@PathVariable("adminId") Integer adminId,@PathVariable("chefId") Integer chefId ,@PathVariable("sessionKey") String sessionKey) throws AdminException{
        Chef chef = AService.viewChef(adminId,chefId,sessionKey);

        return new ResponseEntity<Chef>(chef,HttpStatus.OK);

    }
}


