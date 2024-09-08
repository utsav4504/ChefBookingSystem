package com.ChefBookingSystem.demo.Service;

import com.ChefBookingSystem.demo.Exception.AdminException;
import com.ChefBookingSystem.demo.Exception.ChefException;
import com.ChefBookingSystem.demo.Exception.InvalidId;
import com.ChefBookingSystem.demo.Model.Chef;
import com.ChefBookingSystem.demo.Model.ChefDTO;
import com.ChefBookingSystem.demo.Model.CurrentUserSession;
import com.ChefBookingSystem.demo.Repository.ChefDao;
import com.ChefBookingSystem.demo.Repository.SessionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChefServiceImpl implements ChefService {

    @Autowired
    private ChefDao dDao;


    @Autowired
    private SessionDao sDao;

    @Override
    public Chef createChef(ChefDTO cdto)throws ChefException {


        Chef chef = new Chef();
        chef.setAddress(cdto.getAddress());
        chef.setUsername(cdto.getUsername());
        chef.setPassword(cdto.getPassword());
        chef.setMobileNumber(cdto.getMobileNumber());
        chef.setEmail(cdto.getEmail());
        chef.setAge(cdto.getAge());
        chef.setFoodType(cdto.getFoodType());
        chef.setHourlyFee(cdto.getHourlyFee());

        Chef existingchef= dDao.findByMobileNumber(cdto.getMobileNumber());

        if(existingchef != null )
            throw new ChefException("Chef Already Registered with Mobile number");

        dDao.save(chef);
        return chef;


    }

    @Override
    public Chef viewChefById(Integer id) throws InvalidId {
        if(id<1)
            throw new InvalidId("Id should be more than 1");

        Optional<Chef> opt=dDao.findById(id);

        return opt.orElseThrow(() -> new InvalidId("No Chef found for id: "+id));
    }

    @Override
    public Chef updateChef(ChefDTO chef, String key) throws ChefException{

        CurrentUserSession loggedInUser= sDao.findByUuid(key);

        if(loggedInUser == null) {
            throw new ChefException("Please provide a valid key to update a Chef");
        }
        if (chef == null) {
            throw new ChefException("Chef object cannot be null");
        }

        if(chef.getChefId() == loggedInUser.getUserId()) {


            Chef d= dDao.findById(chef.getChefId()).get();

            d.setMobileNumber(chef.getMobileNumber());
            d.setUsername(chef.getUsername());
            d.setPassword(chef.getPassword());
            d.setAddress(chef.getAddress());
            d.setEmail(chef.getEmail());
            d.setAge(chef.getAge());
            d.setFoodType(chef.getFoodType());
            d.setHourlyFee(chef.getHourlyFee());

            dDao.save(d);

            return d;


        }
        else
            throw new ChefException("Invalid Chef Details, please login first");
    }
    @Override
    public String deleteChef(Integer chefId, String key) throws ChefException {


        CurrentUserSession loggedInUser= sDao.findByUuid(key);

        if(loggedInUser == null) {
            throw new ChefException("Please provide a valid key to delete a Driver");
        }


        if(chefId == loggedInUser.getUserId()) {


            // TODO Auto-generated method stub

            Chef chefDetails = dDao.findById(chefId).orElseThrow(() -> new ChefException("Chef does not exist with id : "+ chefId));
            dDao.delete(chefDetails);
            return "chef with id : " + chefId + " Deleted" ;

        }else {
            throw new ChefException("Wrong Details please login first!");
        }
    }





}
