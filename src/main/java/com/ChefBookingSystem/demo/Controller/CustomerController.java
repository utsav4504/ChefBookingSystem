package com.ChefBookingSystem.demo.Controller;

import com.ChefBookingSystem.demo.Exception.ChefBookingException;
import com.ChefBookingSystem.demo.Exception.CustomerException;
import com.ChefBookingSystem.demo.Model.BillDetails;
import com.ChefBookingSystem.demo.Model.ChefBooking;
import com.ChefBookingSystem.demo.Model.ChefBookingDTO;
import com.ChefBookingSystem.demo.Model.Customer;
import com.ChefBookingSystem.demo.Service.ChefBookingService;
import com.ChefBookingSystem.demo.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    private CustomerService cService;


    @Autowired
    public ChefBookingService chefBookingService;

    @PostMapping("/create")
    public ResponseEntity<Customer> saveCustomer(@Valid @RequestBody Customer customer) throws CustomerException {

        Customer savedCustomer= cService.createCustomer(customer);


        return new ResponseEntity<Customer>(savedCustomer, HttpStatus.CREATED);
    }



    @PutMapping("/update")
    public  ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customer,@RequestParam(required = false) String sessionKey ) throws CustomerException {


        Customer updatedCustomer= cService.updateCustomer(customer, sessionKey);

        return new ResponseEntity<Customer>(updatedCustomer,HttpStatus.OK);

    }



    @DeleteMapping("/delete/{customerId}/{sessionKey}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("customerId") Integer customerId ,@PathVariable("sessionKey") String sessionKey) throws CustomerException{

        String DeleteCustomer = cService.deleteCustomer(customerId,sessionKey);

        return new ResponseEntity<String>(DeleteCustomer,HttpStatus.OK);
    }



    @PostMapping("/chefBooking/{sessionKey}")
    public ResponseEntity<ChefBooking> registerChefBooking(@RequestBody ChefBookingDTO chefBooking, @PathVariable("sessionKey") String sessionKey) throws ChefBookingException {

        ChefBooking savedBooking=chefBookingService.insertChefBooking(chefBooking,sessionKey);
        return new ResponseEntity<ChefBooking>(savedBooking,HttpStatus.OK);

    }


    @DeleteMapping("/cancelBooking/{customerId}/{sessionKey}")
    public ResponseEntity<String> deleteBooking(@PathVariable Integer customerId, @PathVariable String sessionKey) throws ChefBookingException{

        String  deleteBooking=chefBookingService.deleteChefBooking(customerId,sessionKey);
        return new ResponseEntity<String>(deleteBooking,HttpStatus.OK);

    }



    @GetMapping("/chefBooking/{customerId}/{sessionKey}")
    public ResponseEntity<List<ChefBooking>> allBookingByCustomerId(@PathVariable("customerId") Integer customerId, @PathVariable("sessionKey") String sessionKey) throws ChefBookingException{

        List<ChefBooking> savedBooking=chefBookingService.viewAllBookingCustomer(customerId,sessionKey);
        return new ResponseEntity<List<ChefBooking>>(savedBooking,HttpStatus.OK);

    }


    @GetMapping("/generateBill/{customerId}/{chefBookingId}/{sessionKey}")
    public ResponseEntity<BillDetails> generateBillHandler(@PathVariable("customerId") Integer customerId, @PathVariable("chefBookingId") Integer chefBookingId, @PathVariable String sessionKey) throws ChefBookingException{
        BillDetails billDetails = chefBookingService.generateBill(customerId,chefBookingId,sessionKey);
        return new ResponseEntity<BillDetails>(billDetails,HttpStatus.OK);
    }


}
