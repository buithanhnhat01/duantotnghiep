package com.fptpoly.main.Restcontroller;

import com.fptpoly.main.Dao.*;
import com.fptpoly.main.Entity.Billaccessories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/")
public class AdminRestcontroller {

    @Autowired
    CarRepository carRepository;
    @Autowired
    AccessoriesRepository accessoriesRepository;
    @Autowired
    CartaccessoriesRepository cartaccessoriesRepository;
    @Autowired
    BrandRepository brandRepository;
    @Autowired
    TypecarRepository typecarRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    BillaccessoriesRepository billaccessoriesRepository;
    @Autowired
    BillaccessoriesdetailRepository billaccessoriesdetailRepository;


    @GetMapping("BillAll")
    public List<Billaccessories> billAll(){
        return billaccessoriesRepository.findAll();
    }


}
