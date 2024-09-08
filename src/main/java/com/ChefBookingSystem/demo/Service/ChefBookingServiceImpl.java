package com.ChefBookingSystem.demo.Service;

import com.ChefBookingSystem.demo.Exception.AdminException;
import com.ChefBookingSystem.demo.Exception.ChefBookingException;
import com.ChefBookingSystem.demo.Model.*;
import com.ChefBookingSystem.demo.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChefBookingServiceImpl implements ChefBookingService{
    @Autowired
    private ChefBookingDao tdao;
    @Autowired
    private AdminDao adao;

    @Autowired
    private ChefDao ddao;

    @Autowired
    private CustomerDao cdao;


    @Autowired
    private SessionDao sDao;

    @Override
    public ChefBooking insertChefBooking(ChefBookingDTO ChefBooking, String key) throws ChefBookingException {

        CurrentUserSession loggedInUser= sDao.findByUuid(key);

        if(loggedInUser == null) {
            throw new ChefBookingException("Please provide a valid key");
        }
        if (ChefBooking == null) {
            throw new ChefBookingException("Booking object cannot be null");
        }

        if(ChefBooking.getCustomerId() == loggedInUser.getUserId()) {



            // TODO Auto-generated method stub

            Optional<Customer> customer = cdao.findById(ChefBooking.getCustomerId());

            if(customer.isPresent()) {
                Customer  c = customer.get();
                ChefBooking chefB = new ChefBooking();

                chefB.setAtLocation(ChefBooking.getAtLocation());
                chefB.setFromDateTime(ChefBooking.getFromTime());
                chefB.setFoodType(ChefBooking.getFoodType());
                chefB.setTotalTimeInHour(ChefBooking.getTotalTimeInHour());

                chefB.setCustomer(c);
                List<Chef> cheflist = ddao.findAll();

                Chef chef = null;
                for(int i = 0;i<cheflist.size();i++) {
                    if(cheflist.get(i).getAvailablity()== true && cheflist.get(i).getFoodType()==ChefBooking.getFoodType() ) {
                        chef = cheflist.get(i);
                        break;
                    }
                }

                if(chef == null) throw new ChefBookingException("No Chef Available at the moment");

                chefB.setChef(chef);
                chef.getChefBookingList().add(chefB);
                chef.setAvailablity(false);


                c.getChefBooking().add(chefB);

                tdao.save(chefB);


                return chefB;

            }else {
                throw new ChefBookingException("Customer not found with id "+ ChefBooking.getCustomerId());
            }

        }else {
            throw new ChefBookingException("Wrong details Please login first");
        }
    }


    @Override
    public String deleteChefBooking(Integer customerId,String key) throws ChefBookingException {

        CurrentUserSession loggedInUser= sDao.findByUuid(key);

        if(loggedInUser == null) {
            throw new ChefBookingException("Please provide a valid key to delete the booking");
        }

        if(customerId == loggedInUser.getUserId()) {


            Optional<Customer> customer = cdao.findById(customerId);
            if(customer.isPresent()) {
                Customer cus = customer.get();
                List<ChefBooking> chefB = cus.getChefBooking();

                if(chefB.size()>0) {
                    if(chefB.get(chefB.size()-1).isStatus()== false) {
                        Chef chef = chefB.get(chefB.size()-1).getChef();
                        chef.setAvailablity(true);
                        ddao.save(chef);
                        chefB.remove(chefB.size()-1);
                        cdao.save(cus);

                        return "Chef booking cancelled Successfully";
                    }
                }
                return "No Booking found";

            }else {
                throw new ChefBookingException("Customer not found with id :"+ customerId);
            }
        }else {
            throw new ChefBookingException("Wrong Details Please login first");
        }

    }

    @Override
    public List<ChefBooking> viewAllBookingCustomer(Integer customerId,String key) throws ChefBookingException {

        CurrentUserSession loggedInUser= sDao.findByUuid(key);

        if(loggedInUser == null) {
            throw new ChefBookingException("Please provide a valid key");
        }


        if(customerId == loggedInUser.getUserId()) {




            // TODO Auto-generated method stub
            Optional<Customer> customer = cdao.findById(customerId);

            if(customer.isPresent()) {
                Customer c = customer.get();
                List<ChefBooking> chefBooking = c.getChefBooking();
                return chefBooking;
            }



            throw new ChefBookingException("No Booking for this customer having id : "+ customerId);
        }
        else {
            throw new ChefBookingException("Wrong details Please login first!");
        }

    }

    @Override
    public String calculateBill(Integer chefId,String key) throws ChefBookingException {
        CurrentUserSession loggedInUser= sDao.findByUuid(key);

        if(loggedInUser == null) {
            throw new ChefBookingException("Please provide a valid key");
        }

        if(chefId == loggedInUser.getUserId()) {


            Optional<Chef> chef = ddao.findById(chefId);
            if(chef.isPresent()) {
                Chef c = chef.get();
                List<ChefBooking> customerBookList = c.getChefBookingList();

                if(customerBookList.isEmpty()) throw new ChefBookingException("No Booking found");


                ChefBooking lastBooking = customerBookList.get(customerBookList.size()-1);
                if(lastBooking.isStatus() == true) throw new ChefBookingException("All Booking Completed");

                float ratePerHour = (float) c.getHourlyFee();
                float hour = lastBooking.getTotalTimeInHour();

                lastBooking.setBill(hour*ratePerHour);
                c.setAvailablity(true);
                lastBooking.setStatus(true);

                ddao.save(c);

                return "Bill is " + lastBooking.getBill();



            }else {
                throw new ChefBookingException("Chef does not exist with id"+ chefId);
            }

        }else {
            throw new ChefBookingException("Wrong details please login first!");
        }


    }


    @Override
    public BillDetails generateBill(Integer customerId, Integer chefBookingId,String key) throws ChefBookingException {


        CurrentUserSession loggedInUser= sDao.findByUuid(key);

        if(loggedInUser == null) {
            throw new ChefBookingException("Please provide a valid key to generateBill");
        }


        if(customerId == loggedInUser.getUserId()) {
            // TODO Auto-generated method stub
            Customer customer = cdao.findById(customerId).get();
            if(customer == null) {
                throw new ChefBookingException("customer not found");
            }
            ChefBooking chefB = tdao.findById(chefBookingId).get();
            if(chefB == null) throw new ChefBookingException("Booking with given id does not exist");

            if(customerId== chefB.getCustomer().getCustomerId()) {
                if(chefB.isStatus() == false) throw new ChefBookingException(" not completed yet");

                BillDetails billDetails = new BillDetails();
                billDetails.setHours(chefB.getTotalTimeInHour());
                billDetails.setHourlyFee(chefB.getChef().getHourlyFee());
                billDetails.setTotalBill(chefB.getBill());
                return billDetails;
            }

            throw new ChefBookingException("User not Verified");
        }
        else {
            throw new ChefBookingException("Provided wrong details Please login first!");
        }

    }


    public List<ChefBooking> getAllBookings(Integer adminId, String key) throws ChefBookingException {
        // TODO Auto-generated method stub

        CurrentUserSession loggedInUser= sDao.findByUuid(key);

        if(loggedInUser == null) {
            throw new ChefBookingException("Please provide a valid key to get all the bookings");
        }
        Admin ad= adao.findByAdminId(adminId);

        if(adminId == loggedInUser.getUserId()) {
         if(ad==null) {
             throw new ChefBookingException("Wrong details!");
         }

            List<ChefBooking> allbookings = tdao.findAll();
            if(allbookings.size() >0) {
                return allbookings;
            }
            throw new ChefBookingException("No booking found");
        }else {
            throw new ChefBookingException("Wrong details please login first!");
        }

    }



}
